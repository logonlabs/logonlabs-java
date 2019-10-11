package com.logonlabs;

import net.servicestack.client.*;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.ParameterParser;
import org.apache.commons.httpclient.util.URIUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.Random;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Implementation of LogonLabs API defined in https://logonlabs.com/docs/api/#introduction.
 */
public class LogonClient {

    private final String appId;
    private final String secret;
    private final String apiUrl;
    private final JsonServiceClient client;

    /**
     * Create a new instance of the Client.  AppId, secret, and apiUrl can all be retrieved via https://logonlabs.com/app/#/app-settings
     * @param appId     the application's unique identifier
     * @param secret    the application's secret
     * @param apiUrl    the API endpoint
     */
    public LogonClient(String appId, String secret, String apiUrl) {

        if(appId == null) {
            throw new IllegalArgumentException("appId must have a value");
        }

        if(apiUrl == null) {
            throw new IllegalArgumentException("apiUrl must have a value");
        }

        if(!apiUrl.startsWith("https://") && !apiUrl.startsWith("http://")){
            apiUrl = "https://" + apiUrl;
        }

        if(!apiUrl.endsWith("/")) {
            apiUrl = apiUrl + "/";
        }

        this.appId = appId;
        this.secret = secret;
        this.apiUrl = apiUrl;
        this.client = new JsonServiceClient(apiUrl);
        this.client.RequestFilter = conn -> conn.addRequestProperty("x-app-secret", secret);
    }


    /**
     * Request to the API to check status of the server
     * @return string representation of the API version on success
     */
    public String ping() {

        dtos.Ping request = new dtos.Ping();
        request.setAppId(appId);

        dtos.PingResponse response = client.get(request);

        return response.getVersion();
    }

    /**
     * Request a list of the providers associated with an App
     * @return a response containing an ArrayList of Provider objects
     * @throws LogonLabsException thrown in case of API error
     */
    public dtos.GetProvidersResponse getProviders() throws LogonLabsException {

        try {
            dtos.GetProviders request = new dtos.GetProviders();
            request.setAppId(appId);

            dtos.GetProvidersResponse response = client.get(request);
            return response;
        } catch (WebServiceException ex) {
            throw new LogonLabsException(ex);
        }
    }

    /**
     * Request a list of the providers available to a given email address for the App
     * @param emailAddress  filters the providers for those enabled
     * @return              a response containing an ArrayList of Provider objects
     * @throws LogonLabsException thrown in case of API error
     */
    public dtos.GetProvidersResponse getProviders(String emailAddress) throws LogonLabsException {

        try {
            dtos.GetProviders request = new dtos.GetProviders();
            request.setAppId(appId);
            request.setEmailAddress(emailAddress);

            dtos.GetProvidersResponse response = client.get(request);
            return response;
        } catch (WebServiceException ex) {
            throw new LogonLabsException(ex);
        }
    }

    /**
     * Request that begins the SSO login process
     * @param identityProvider      the name of the identity provider. a list of valid strings is provided in IdentityProviders. if this is passed then identityProviderId must be null
     * @param identityProviderId    the unique identifier for the identity provider. if this is passed then identityProvider parameter must be null
     * @param emailAddress          required if matching the email against the one returned by the identity provider
     * @param clientData            any custom data required by your application.  will be present in client_data of the ValidateLoginResponse
     * @param destinationUrl        used to inform the back end which client to return the login to.  mainly used for mobile workflows
     * @param callbackUrl           used to determine which server endpoint to return the user to.  useful when supporting different backend solutions
     * @param tags                  any custom information required for viewing the event logs
     * @return                      token to pass to validateLogin in order to continue the current SSO login process
     * @throws LogonLabsException   thrown in case of API error
     */
    public String startLogin(String identityProvider, String identityProviderId, String emailAddress, String clientData, String destinationUrl, String callbackUrl, ArrayList<dtos.Tag> tags) throws LogonLabsException {
        try {

            dtos.StartLogin request = new dtos.StartLogin();

            request.setAppId(appId);

            if((identityProvider == null || identityProvider.isEmpty()) &&
                    (identityProviderId == null || identityProviderId.isEmpty())) {
                throw new IllegalArgumentException("identityProvider and identityProviderId cannot both be null");
            }

            if((identityProvider != null || !identityProvider.isEmpty()) &&
                    (identityProviderId != null || !identityProviderId.isEmpty())) {
                throw new IllegalArgumentException("identityProvider and identityProviderId cannot both have a value");
            }

            request.setIdentityProvider(identityProvider);
            request.setIdentityProviderId(identityProviderId);
            request.setEmailAddress(emailAddress);
            request.setClientData(clientData);
            //request.setClientEncryptionKey(clientEncryptionKey);
            request.setTags(tags);
            request.setDestinationUrl(destinationUrl);
            request.setCallbackUrl(callbackUrl);

            dtos.StartLoginResponse response = client.post(request);

            dtos.RedirectLogin redirectLogin = new dtos.RedirectLogin();
            redirectLogin.setToken(response.token);

            return apiUrl + "redirect?token=" + redirectLogin.getToken();

        } catch(WebServiceException ex) {
            throw new LogonLabsException(ex);
        }
    }

    /**
     * Request that continues the SSO process initiated by startLogin
     * @param token the value returned by startLogin
     * @return      full validation details outlined here https://logonlabs.com/docs/api/#validatelogin
     * @throws LogonLabsException thrown in case of API error
     */
    public dtos.ValidateLoginResponse validateLogin(String token) throws LogonLabsException {

        throwIfNoSecret();

        try {
            dtos.ValidateLogin request = new dtos.ValidateLogin();
            request.setAppId(appId);
            request.setToken(token);

            dtos.ValidateLoginResponse response = client.post(request);
            return response;
        } catch(WebServiceException ex) {
            throw new LogonLabsException(ex);
        }
    }

    /**
     * Request to manually add an Event to the EventLogs record https://logonlabs.com/docs/api/#createEvent
     * @param type              the category of event to create - allowable values found in the EventTypes
     * @param validate          determines whether to apply restriction validations to the event
     * @param ipAddress         required in order to acquire geolocation details
     * @param emailAddress      required to uniquely identify the user performing the event
     * @param firstName         optional value for identifying the user in the EventLogs
     * @param lastName          optional value for identifying the user in the EventLogs
     * @param localValidation   indicates the result of the event locally - value must be in EventValidationTypes
     * @param userAgent         optional value to identify the
     * @param tags              optional list of key-value pairs to add custom information to the event
     * @return                  response with results of the create event action - outlined here https://logonlabs.com/docs/api/#createEvent
     * @throws LogonLabsException   thrown in case of API error
     */
    public dtos.CreateEventResponse createEvent(String type, Boolean validate, String ipAddress, String emailAddress,
                                                String firstName, String lastName, String localValidation,
                                                String userAgent, ArrayList<dtos.Tag> tags) throws LogonLabsException {

        throwIfNoSecret();

        try {
            dtos.CreateEvent request = new dtos.CreateEvent();
            request.setAppId(appId);
            request.setFirstName(firstName);
            request.setLastName(lastName);
            request.setIpAddress(ipAddress);
            request.setEmailAddress(emailAddress);
            request.setType(type);
            request.setUserAgent(userAgent);
            request.setTags(tags);
            request.setLocalValidation(localValidation);
            request.setValidate(validate);

            dtos.CreateEventResponse response = client.post(request);
            return response;

        } catch(WebServiceException ex) {
            throw new LogonLabsException(ex);
        }
    }

    /**
     * Request to update an existing event in the EventLogs
     * @param eventId           the event's unique identifier
     * @param localValidation   indicates the result of the event locally - value must be in EventValidationTypes
     * @param tags              optional list of key-value pairs to add custom information to the event
     * @return                  response with details of the update action result
     * @throws LogonLabsException   thrown in case of API error
     */
    public dtos.UpdateEventResponse updateEvent(String eventId, String localValidation, ArrayList<dtos.Tag> tags) throws LogonLabsException {
        throwIfNoSecret();

        if(eventId == null || eventId.isEmpty()) {
            throw new IllegalArgumentException("eventId must have value");
        }

        try {
            dtos.UpdateEvent request = new dtos.UpdateEvent();
            request.setAppId(appId);
            request.setEventId(eventId);
            request.setTags(tags);
            request.setLocalValidation(localValidation);

            dtos.UpdateEventResponse response = client.put(request);
            return response;

        } catch (WebServiceException ex) {
            throw new LogonLabsException(ex);
        }
    }

    public String parseToken(String url) throws MalformedURLException, URIException {
        if(url == null || url.isEmpty()){
            throw new IllegalArgumentException("url must have value");
        }

        URL parsedUrl = new URL(url);

        String decoded = URIUtil.decode(parsedUrl.getQuery());
        ParameterParser parser = new ParameterParser();
        List<NameValuePair> pairs = parser.parse(decoded, '&');

        for (NameValuePair pair : pairs) {
            String parameterName = pair.getName();
            if (parameterName.equalsIgnoreCase("token")) {
                return pair.getValue();
            }
        }

        throw new IllegalArgumentException(String.format("url %s is missing a token parameter in query string", url));
    }

    private void throwIfNoSecret()
    {
        if (secret == null || secret.isEmpty())
        {
            throw new IllegalArgumentException("Secret must have value");
        }
    }

}
