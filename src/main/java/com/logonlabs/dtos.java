/* Options:
Date: 2019-03-29 17:40:21
Version: 5.40
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: https://idpx.logon-dev.com

//Package: com.logonlabs
//GlobalNamespace: dtos
//AddPropertyAccessors: True
//SettersReturnThis: True
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddDescriptionAsComments: True
//AddImplicitVersion:
//IncludeTypes:
//ExcludeTypes:
//TreatTypesAsStrings:
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*
*/

package com.logonlabs;

import java.math.*;
import java.util.*;
import net.servicestack.client.*;

public class dtos
{
    @Route(Path="/callback", Verbs="GET")
    // @Route(Path="/callback", Verbs="POST")
    @DataContract
    public static class Callback implements IReturn<CallbackResponse>
    {
        @DataMember(IsRequired=true)
        public String code = null;

        @DataMember(IsRequired=true)
        public String state = null;

        @DataMember(IsRequired=true)
        public String session_state = null;

        @DataMember
        public String relayState = null;

        @DataMember
        public String samlResponse = null;

        @DataMember
        public String oauth_token = null;

        @DataMember
        public String oauth_verifier = null;

        public String getCode() { return code; }
        public Callback setCode(String value) { this.code = value; return this; }
        public String getState() { return state; }
        public Callback setState(String value) { this.state = value; return this; }
        public String getSessionState() { return session_state; }
        public Callback setSessionState(String value) { this.session_state = value; return this; }
        public String getRelayState() { return relayState; }
        public Callback setRelayState(String value) { this.relayState = value; return this; }
        public String getSamlResponse() { return samlResponse; }
        public Callback setSamlResponse(String value) { this.samlResponse = value; return this; }
        public String getOauthToken() { return oauth_token; }
        public Callback setOauthToken(String value) { this.oauth_token = value; return this; }
        public String getOauthVerifier() { return oauth_verifier; }
        public Callback setOauthVerifier(String value) { this.oauth_verifier = value; return this; }
        private static Object responseType = CallbackResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/event", Verbs="POST")
    @DataContract
    public static class CreateEvent implements IReturn<CreateEventResponse>, IAppId
    {
        @DataMember
        public String app_id = null;

        @DataMember
        public String type = null;

        @DataMember
        public Boolean validate = null;

        @DataMember
        public String local_validation = null;

        @DataMember
        public String email_address = null;

        @DataMember
        public String first_name = null;

        @DataMember
        public String last_name = null;

        @DataMember
        public String ip_address = null;

        @DataMember
        public String user_agent = null;

        @DataMember
        public ArrayList<Tag> tags = null;

        public String getAppId() { return app_id; }
        public CreateEvent setAppId(String value) { this.app_id = value; return this; }
        public String getType() { return type; }
        public CreateEvent setType(String value) { this.type = value; return this; }
        public Boolean isValidate() { return validate; }
        public CreateEvent setValidate(Boolean value) { this.validate = value; return this; }
        public String getLocalValidation() { return local_validation; }
        public CreateEvent setLocalValidation(String value) { this.local_validation = value; return this; }
        public String getEmailAddress() { return email_address; }
        public CreateEvent setEmailAddress(String value) { this.email_address = value; return this; }
        public String getFirstName() { return first_name; }
        public CreateEvent setFirstName(String value) { this.first_name = value; return this; }
        public String getLastName() { return last_name; }
        public CreateEvent setLastName(String value) { this.last_name = value; return this; }
        public String getIpAddress() { return ip_address; }
        public CreateEvent setIpAddress(String value) { this.ip_address = value; return this; }
        public String getUserAgent() { return user_agent; }
        public CreateEvent setUserAgent(String value) { this.user_agent = value; return this; }
        public ArrayList<Tag> getTags() { return tags; }
        public CreateEvent setTags(ArrayList<Tag> value) { this.tags = value; return this; }
        private static Object responseType = CreateEventResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route("/error")
    @DataContract
    public static class ErrorPage implements IReturn<Object>
    {

        private static Object responseType = Object.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/ping", Verbs="GET")
    @DataContract
    public static class Ping implements IReturn<PingResponse>, IAppId
    {
        @DataMember
        public String app_id = null;

        public String getAppId() { return app_id; }
        public Ping setAppId(String value) { this.app_id = value; return this; }
        private static Object responseType = PingResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/providers", Verbs="GET")
    @DataContract
    public static class GetProviders implements IReturn<GetProvidersResponse>, IAppId
    {
        @DataMember(IsRequired=true)
        public String app_id = null;

        @DataMember
        public String email_address = null;

        public String getAppId() { return app_id; }
        public GetProviders setAppId(String value) { this.app_id = value; return this; }
        public String getEmailAddress() { return email_address; }
        public GetProviders setEmailAddress(String value) { this.email_address = value; return this; }
        private static Object responseType = GetProvidersResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/redirect", Verbs="GET")
    @DataContract
    public static class RedirectLogin implements IReturn<RedirectLoginResponse>
    {
        @DataMember(IsRequired=true)
        public String token = null;

        public String getToken() { return token; }
        public RedirectLogin setToken(String value) { this.token = value; return this; }
        private static Object responseType = RedirectLoginResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/start", Verbs="POST")
    @DataContract
    public static class StartLogin implements IReturn<StartLoginResponse>, IAppId
    {
        @DataMember(IsRequired=true)
        public String app_id = null;

        @DataMember(IsRequired=true)
        public String identity_provider = null;

        @DataMember
        public String email_address = null;

        @DataMember
        public String client_data = null;

        @DataMember
        public String client_encryption_key = null;

        @DataMember
        public ArrayList<Tag> tags = null;

        public String getAppId() { return app_id; }
        public StartLogin setAppId(String value) { this.app_id = value; return this; }
        public String getIdentityProvider() { return identity_provider; }
        public StartLogin setIdentityProvider(String value) { this.identity_provider = value; return this; }
        public String getEmailAddress() { return email_address; }
        public StartLogin setEmailAddress(String value) { this.email_address = value; return this; }
        public String getClientData() { return client_data; }
        public StartLogin setClientData(String value) { this.client_data = value; return this; }
        public String getClientEncryptionKey() { return client_encryption_key; }
        public StartLogin setClientEncryptionKey(String value) { this.client_encryption_key = value; return this; }
        public ArrayList<Tag> getTags() { return tags; }
        public StartLogin setTags(ArrayList<Tag> value) { this.tags = value; return this; }
        private static Object responseType = StartLoginResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/event/{event_id}", Verbs="PUT")
    @DataContract
    public static class UpdateEvent implements IReturn<UpdateEventResponse>, IAppId
    {
        @DataMember(IsRequired=true)
        public String app_id = null;

        @DataMember(IsRequired=true)
        public String event_id = null;

        @DataMember
        public String local_validation = null;

        @DataMember
        public ArrayList<Tag> tags = null;

        public String getAppId() { return app_id; }
        public UpdateEvent setAppId(String value) { this.app_id = value; return this; }
        public String getEventId() { return event_id; }
        public UpdateEvent setEventId(String value) { this.event_id = value; return this; }
        public String getLocalValidation() { return local_validation; }
        public UpdateEvent setLocalValidation(String value) { this.local_validation = value; return this; }
        public ArrayList<Tag> getTags() { return tags; }
        public UpdateEvent setTags(ArrayList<Tag> value) { this.tags = value; return this; }
        private static Object responseType = UpdateEventResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/validate", Verbs="POST")
    @DataContract
    public static class ValidateLogin implements IReturn<ValidateLoginResponse>, IAppId
    {
        @DataMember(IsRequired=true)
        public String app_id = null;

        @DataMember(IsRequired=true)
        public String token = null;

        public String getAppId() { return app_id; }
        public ValidateLogin setAppId(String value) { this.app_id = value; return this; }
        public String getToken() { return token; }
        public ValidateLogin setToken(String value) { this.token = value; return this; }
        private static Object responseType = ValidateLoginResponse.class;
        public Object getResponseType() { return responseType; }
    }

    public static class CallbackResponse implements IBaseResponse
    {
        public IErrorResponse error = null;

        public IErrorResponse getError() { return error; }
        public CallbackResponse setError(IErrorResponse value) { this.error = value; return this; }
    }

    public static class CreateEventResponse implements IBaseResponse
    {
        @DataMember
        public String event_id = null;

        @DataMember
        public Boolean event_success = null;

        @DataMember
        public ValidationDetails validation_details = null;

        @DataMember
        public LocationDetails location_details = null;

        public IErrorResponse error = null;

        public String getEventId() { return event_id; }
        public CreateEventResponse setEventId(String value) { this.event_id = value; return this; }
        public Boolean isEventSuccess() { return event_success; }
        public CreateEventResponse setEventSuccess(Boolean value) { this.event_success = value; return this; }
        public ValidationDetails getValidationDetails() { return validation_details; }
        public CreateEventResponse setValidationDetails(ValidationDetails value) { this.validation_details = value; return this; }
        public LocationDetails getLocationDetails() { return location_details; }
        public CreateEventResponse setLocationDetails(LocationDetails value) { this.location_details = value; return this; }
        public IErrorResponse getError() { return error; }
        public CreateEventResponse setError(IErrorResponse value) { this.error = value; return this; }
    }

    public static class PingResponse implements IBaseResponse
    {
        @DataMember
        public String version = null;

        public IErrorResponse error = null;

        public String getVersion() { return version; }
        public PingResponse setVersion(String value) { this.version = value; return this; }
        public IErrorResponse getError() { return error; }
        public PingResponse setError(IErrorResponse value) { this.error = value; return this; }
    }

    public static class GetProvidersResponse implements IBaseResponse
    {
        @DataMember
        public ArrayList<Provider> identity_providers = null;

        @DataMember
        public String suggested_identity_provider = null;

        public IErrorResponse error = null;

        public ArrayList<Provider> getIdentityProviders() { return identity_providers; }
        public GetProvidersResponse setIdentityProviders(ArrayList<Provider> value) { this.identity_providers = value; return this; }
        public String getSuggestedIdentityProvider() { return suggested_identity_provider; }
        public GetProvidersResponse setSuggestedIdentityProvider(String value) { this.suggested_identity_provider = value; return this; }
        public IErrorResponse getError() { return error; }
        public GetProvidersResponse setError(IErrorResponse value) { this.error = value; return this; }
    }

    public static class RedirectLoginResponse implements IBaseResponse
    {
        public IErrorResponse error = null;

        public IErrorResponse getError() { return error; }
        public RedirectLoginResponse setError(IErrorResponse value) { this.error = value; return this; }
    }

    public static class StartLoginResponse implements IBaseResponse
    {
        public IErrorResponse error = null;
        @DataMember(IsRequired=true)
        public String token = null;

        public IErrorResponse getError() { return error; }
        public StartLoginResponse setError(IErrorResponse value) { this.error = value; return this; }
        public String getToken() { return token; }
        public StartLoginResponse setToken(String value) { this.token = value; return this; }
    }

    public static class UpdateEventResponse implements IBaseResponse
    {
        public IErrorResponse error = null;

        public IErrorResponse getError() { return error; }
        public UpdateEventResponse setError(IErrorResponse value) { this.error = value; return this; }
    }

    public static class ValidateLoginResponse implements IBaseResponse
    {
        @DataMember
        public String app_id = null;

        @DataMember
        public String email_address = null;

        @DataMember
        public String ip_address = null;

        @DataMember
        public LocationDetails location = null;

        @DataMember
        public Boolean event_success = null;

        @DataMember
        public ValidationDetails validation_details = null;

        @DataMember
        public IdentityProviderData identity_provider_data = null;

        @DataMember
        public String client_data = null;

        @DataMember
        public String client_encryption_key = null;

        @DataMember
        public String event_id = null;

        public IErrorResponse error = null;

        public String getAppId() { return app_id; }
        public ValidateLoginResponse setAppId(String value) { this.app_id = value; return this; }
        public String getEmailAddress() { return email_address; }
        public ValidateLoginResponse setEmailAddress(String value) { this.email_address = value; return this; }
        public String getIpAddress() { return ip_address; }
        public ValidateLoginResponse setIpAddress(String value) { this.ip_address = value; return this; }
        public LocationDetails getLocation() { return location; }
        public ValidateLoginResponse setLocation(LocationDetails value) { this.location = value; return this; }
        public Boolean isEventSuccess() { return event_success; }
        public ValidateLoginResponse setEventSuccess(Boolean value) { this.event_success = value; return this; }
        public ValidationDetails getValidationDetails() { return validation_details; }
        public ValidateLoginResponse setValidationDetails(ValidationDetails value) { this.validation_details = value; return this; }
        public IdentityProviderData getIdentityProviderData() { return identity_provider_data; }
        public ValidateLoginResponse setIdentityProviderData(IdentityProviderData value) { this.identity_provider_data = value; return this; }
        public String getClientData() { return client_data; }
        public ValidateLoginResponse setClientData(String value) { this.client_data = value; return this; }
        public String getClientEncryptionKey() { return client_encryption_key; }
        public ValidateLoginResponse setClientEncryptionKey(String value) { this.client_encryption_key = value; return this; }
        public String getEventId() { return event_id; }
        public ValidateLoginResponse setEventId(String value) { this.event_id = value; return this; }
        public IErrorResponse getError() { return error; }
        public ValidateLoginResponse setError(IErrorResponse value) { this.error = value; return this; }
    }

    public static interface IErrorResponse
    {
        public String code = null;
        public String message = null;
    }

    public static interface IBaseResponse
    {
        public IErrorResponse error = null;
    }

    @DataContract
    public static class Tag
    {
        @DataMember
        public String key = null;

        @DataMember
        public String value = null;

        public String getKey() { return key; }
        public Tag setKey(String value) { this.key = value; return this; }
        public String getValue() { return value; }
        public Tag setValue(String value) { this.value = value; return this; }
    }

    public static interface IAppId
    {
        public String app_id = null;
    }

    public static class ValidationDetails
    {
        @DataMember
        public String auth_validation = null;

        @DataMember
        public String email_match_validation = null;

        @DataMember
        public String ip_validation = null;

        @DataMember
        public String geo_validation = null;

        @DataMember
        public String time_validation = null;

        public String getAuthValidation() { return auth_validation; }
        public ValidationDetails setAuthValidation(String value) { this.auth_validation = value; return this; }
        public String getEmailMatchValidation() { return email_match_validation; }
        public ValidationDetails setEmailMatchValidation(String value) { this.email_match_validation = value; return this; }
        public String getIpValidation() { return ip_validation; }
        public ValidationDetails setIpValidation(String value) { this.ip_validation = value; return this; }
        public String getGeoValidation() { return geo_validation; }
        public ValidationDetails setGeoValidation(String value) { this.geo_validation = value; return this; }
        public String getTimeValidation() { return time_validation; }
        public ValidationDetails setTimeValidation(String value) { this.time_validation = value; return this; }
    }

    public static class LocationDetails
    {
        @DataMember
        public String continent_code = null;

        @DataMember
        public String continent_name = null;

        @DataMember
        public String country_code = null;

        @DataMember
        public String country_name = null;

        public String getContinentCode() { return continent_code; }
        public LocationDetails setContinentCode(String value) { this.continent_code = value; return this; }
        public String getContinentName() { return continent_name; }
        public LocationDetails setContinentName(String value) { this.continent_name = value; return this; }
        public String getCountryCode() { return country_code; }
        public LocationDetails setCountryCode(String value) { this.country_code = value; return this; }
        public String getCountryName() { return country_name; }
        public LocationDetails setCountryName(String value) { this.country_name = value; return this; }
    }

    public static class Provider
    {
        @DataMember
        public String type = null;

        public String getType() { return type; }
        public Provider setType(String value) { this.type = value; return this; }
    }

    public static class IdentityProviderData
    {
        @DataMember
        public String identity_provider_type = null;

        @DataMember
        public String uid = null;

        @DataMember
        public String first_name = null;

        @DataMember
        public String last_name = null;

        public String getIdentityProviderType() { return identity_provider_type; }
        public IdentityProviderData setIdentityProviderType(String value) { this.identity_provider_type = value; return this; }
        public String getUid() { return uid; }
        public IdentityProviderData setUid(String value) { this.uid = value; return this; }
        public String getFirstName() { return first_name; }
        public IdentityProviderData setFirstName(String value) { this.first_name = value; return this; }
        public String getLastName() { return last_name; }
        public IdentityProviderData setLastName(String value) { this.last_name = value; return this; }
    }

}