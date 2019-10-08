package com.logonlabs;
/* Options:
Date: 2019-10-08 14:31:01
Version: 5.60
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: https://local-idpx.logon-dev.com

//Package:
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

import java.math.*;
import java.util.*;
import net.servicestack.client.*;

public class dtos
{

    @Route(Path="/callback", Verbs="GET")
    // @Route(Path="/callback", Verbs="POST")
    @DataContract
    public static class Callback implements IReturn<String>
    {
        @DataMember
        public String code = null;

        @DataMember
        public String state = null;

        @DataMember
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
        private static Object responseType = String.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/events", Verbs="POST")
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

    @Route(Path="/events", Verbs="GET")
    @DataContract
    public static class GetEvents implements IReturn<GetEventsResponse>, IAppId
    {
        @DataMember(IsRequired=true)
        @ApiMember(IsRequired=true, ParameterType="path")
        public String app_id = null;

        /**
         * Defaults to 1
         */
        @DataMember
        @ApiMember(Description="Defaults to 1", ParameterType="query")
        public Integer page = null;

        /**
         * Defaults to 25
         */
        @DataMember
        @ApiMember(Description="Defaults to 25", ParameterType="query")
        public Integer page_size = null;

        @DataMember
        @ApiMember(ParameterType="query")
        public String start_date = null;

        @DataMember
        @ApiMember(ParameterType="query")
        public String end_date = null;

        @DataMember
        @ApiMember(ParameterType="query")
        public ArrayList<String> completion_states = null;

        public String getAppId() { return app_id; }
        public GetEvents setAppId(String value) { this.app_id = value; return this; }
        public Integer getPage() { return page; }
        public GetEvents setPage(Integer value) { this.page = value; return this; }
        public Integer getPageSize() { return page_size; }
        public GetEvents setPageSize(Integer value) { this.page_size = value; return this; }
        public String getStartDate() { return start_date; }
        public GetEvents setStartDate(String value) { this.start_date = value; return this; }
        public String getEndDate() { return end_date; }
        public GetEvents setEndDate(String value) { this.end_date = value; return this; }
        public ArrayList<String> getCompletionStates() { return completion_states; }
        public GetEvents setCompletionStates(ArrayList<String> value) { this.completion_states = value; return this; }
        private static Object responseType = GetEventsResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/entities", Verbs="DELETE")
    @DataContract
    public static class ClearEntities implements IReturn<ClearEntitiesResponse>
    {
        @DataMember
        public ArrayList<UUID> app_ids = null;

        @DataMember
        public Boolean clear_social = null;

        public ArrayList<UUID> getAppIds() { return app_ids; }
        public ClearEntities setAppIds(ArrayList<UUID> value) { this.app_ids = value; return this; }
        public Boolean isClearSocial() { return clear_social; }
        public ClearEntities setClearSocial(Boolean value) { this.clear_social = value; return this; }
        private static Object responseType = ClearEntitiesResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/session", Verbs="POST")
    @DataContract
    public static class SetDelegatedIdPxSession implements IReturn<SetDelegatedIdPxSessionResponse>, IAppId
    {
        @DataMember
        public String app_id = null;

        @DataMember
        public String session_token = null;

        public String getAppId() { return app_id; }
        public SetDelegatedIdPxSession setAppId(String value) { this.app_id = value; return this; }
        public String getSessionToken() { return session_token; }
        public SetDelegatedIdPxSession setSessionToken(String value) { this.session_token = value; return this; }
        private static Object responseType = SetDelegatedIdPxSessionResponse.class;
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
        public String identity_provider_id = null;

        @DataMember
        public String email_address = null;

        @DataMember
        public String client_data = null;

        @DataMember
        public ArrayList<Tag> tags = null;

        @DataMember
        public String destination_url = null;

        @DataMember
        public String callback_url = null;

        public String getAppId() { return app_id; }
        public StartLogin setAppId(String value) { this.app_id = value; return this; }
        public String getIdentityProvider() { return identity_provider; }
        public StartLogin setIdentityProvider(String value) { this.identity_provider = value; return this; }
        public String getIdentityProviderId() { return identity_provider_id; }
        public StartLogin setIdentityProviderId(String value) { this.identity_provider_id = value; return this; }
        public String getEmailAddress() { return email_address; }
        public StartLogin setEmailAddress(String value) { this.email_address = value; return this; }
        public String getClientData() { return client_data; }
        public StartLogin setClientData(String value) { this.client_data = value; return this; }
        public ArrayList<Tag> getTags() { return tags; }
        public StartLogin setTags(ArrayList<Tag> value) { this.tags = value; return this; }
        public String getDestinationUrl() { return destination_url; }
        public StartLogin setDestinationUrl(String value) { this.destination_url = value; return this; }
        public String getCallbackUrl() { return callback_url; }
        public StartLogin setCallbackUrl(String value) { this.callback_url = value; return this; }
        private static Object responseType = StartLoginResponse.class;
        public Object getResponseType() { return responseType; }
    }

    @Route(Path="/events/{event_id}", Verbs="PUT")
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

    @Route(Path="/session", Verbs="DELETE")
    @DataContract
    public static class LogoutDelegatedIdPxSession implements IReturn<LogoutDelegatedIdPxSessionResponse>, IAppId
    {
        @DataMember
        @ApiMember(ParameterType="query")
        public String app_id = null;

        public String getAppId() { return app_id; }
        public LogoutDelegatedIdPxSession setAppId(String value) { this.app_id = value; return this; }
        private static Object responseType = LogoutDelegatedIdPxSessionResponse.class;
        public Object getResponseType() { return responseType; }
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

    public static class GetEventsResponse implements IBaseResponse
    {
        @DataMember
        public IErrorResponse error = null;

        @DataMember
        public Integer total_items = null;

        @DataMember
        public Integer total_pages = null;

        @DataMember
        public ArrayList<Event> events = null;

        public IErrorResponse getError() { return error; }
        public GetEventsResponse setError(IErrorResponse value) { this.error = value; return this; }
        public Integer getTotalItems() { return total_items; }
        public GetEventsResponse setTotalItems(Integer value) { this.total_items = value; return this; }
        public Integer getTotalPages() { return total_pages; }
        public GetEventsResponse setTotalPages(Integer value) { this.total_pages = value; return this; }
        public ArrayList<Event> getEvents() { return events; }
        public GetEventsResponse setEvents(ArrayList<Event> value) { this.events = value; return this; }
    }

    @DataContract
    public static class ClearEntitiesResponse implements IBaseResponse
    {
        @DataMember
        public IErrorResponse error = null;

        public IErrorResponse getError() { return error; }
        public ClearEntitiesResponse setError(IErrorResponse value) { this.error = value; return this; }
    }

    @DataContract
    public static class SetDelegatedIdPxSessionResponse implements IBaseResponse
    {
        @DataMember
        public IErrorResponse error = null;

        public IErrorResponse getError() { return error; }
        public SetDelegatedIdPxSessionResponse setError(IErrorResponse value) { this.error = value; return this; }
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
        public ArrayList<Provider> social_identity_providers = null;

        @DataMember
        public ArrayList<EnterpriseProvider> enterprise_identity_providers = null;

        @DataMember
        public String suggested_identity_provider = null;

        public IErrorResponse error = null;

        public ArrayList<Provider> getSocialIdentityProviders() { return social_identity_providers; }
        public GetProvidersResponse setSocialIdentityProviders(ArrayList<Provider> value) { this.social_identity_providers = value; return this; }
        public ArrayList<EnterpriseProvider> getEnterpriseIdentityProviders() { return enterprise_identity_providers; }
        public GetProvidersResponse setEnterpriseIdentityProviders(ArrayList<EnterpriseProvider> value) { this.enterprise_identity_providers = value; return this; }
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
        public String event_id = null;

        @DataMember
        public String destination_url = null;

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
        public String getEventId() { return event_id; }
        public ValidateLoginResponse setEventId(String value) { this.event_id = value; return this; }
        public String getDestinationUrl() { return destination_url; }
        public ValidateLoginResponse setDestinationUrl(String value) { this.destination_url = value; return this; }
        public IErrorResponse getError() { return error; }
        public ValidateLoginResponse setError(IErrorResponse value) { this.error = value; return this; }
    }

    @DataContract
    public static class LogoutDelegatedIdPxSessionResponse implements IBaseResponse
    {
        @DataMember
        public IErrorResponse error = null;

        public IErrorResponse getError() { return error; }
        public LogoutDelegatedIdPxSessionResponse setError(IErrorResponse value) { this.error = value; return this; }
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
        public String domain_validation = null;

        @DataMember
        public String ip_validation = null;

        @DataMember
        public String geo_validation = null;

        @DataMember
        public String time_validation = null;

        public String getDomainValidation() { return domain_validation; }
        public ValidationDetails setDomainValidation(String value) { this.domain_validation = value; return this; }
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

    public static interface IErrorResponse
    {
        public String code = null;
        public String message = null;
    }

    public static interface IBaseResponse
    {
        public IErrorResponse error = null;
    }

    public static class Event
    {
        @DataMember
        public Date created_date = null;

        @DataMember
        public String event_type = null;

        @DataMember
        public String completion_state = null;

        @DataMember
        public String email_address = null;

        @DataMember
        public String provider_type = null;

        @DataMember
        public String ip_address = null;

        @DataMember
        public Boolean event_success = null;

        @DataMember
        public String local_success = null;

        @DataMember
        public Date local_success_date = null;

        @DataMember
        public ValidationDetails validation_details = null;

        @DataMember
        public String country_code = null;

        @DataMember
        public String latitude = null;

        @DataMember
        public String longitude = null;

        @DataMember
        public ArrayList<Tag> tags = null;

        @DataMember
        public String operating_system = null;

        @DataMember
        public String browser = null;

        @DataMember
        public String device = null;

        @DataMember
        public Boolean is_bot = null;

        public Date getCreatedDate() { return created_date; }
        public Event setCreatedDate(Date value) { this.created_date = value; return this; }
        public String getEventType() { return event_type; }
        public Event setEventType(String value) { this.event_type = value; return this; }
        public String getCompletionState() { return completion_state; }
        public Event setCompletionState(String value) { this.completion_state = value; return this; }
        public String getEmailAddress() { return email_address; }
        public Event setEmailAddress(String value) { this.email_address = value; return this; }
        public String getProviderType() { return provider_type; }
        public Event setProviderType(String value) { this.provider_type = value; return this; }
        public String getIpAddress() { return ip_address; }
        public Event setIpAddress(String value) { this.ip_address = value; return this; }
        public Boolean isEventSuccess() { return event_success; }
        public Event setEventSuccess(Boolean value) { this.event_success = value; return this; }
        public String getLocalSuccess() { return local_success; }
        public Event setLocalSuccess(String value) { this.local_success = value; return this; }
        public Date getLocalSuccessDate() { return local_success_date; }
        public Event setLocalSuccessDate(Date value) { this.local_success_date = value; return this; }
        public ValidationDetails getValidationDetails() { return validation_details; }
        public Event setValidationDetails(ValidationDetails value) { this.validation_details = value; return this; }
        public String getCountryCode() { return country_code; }
        public Event setCountryCode(String value) { this.country_code = value; return this; }
        public String getLatitude() { return latitude; }
        public Event setLatitude(String value) { this.latitude = value; return this; }
        public String getLongitude() { return longitude; }
        public Event setLongitude(String value) { this.longitude = value; return this; }
        public ArrayList<Tag> getTags() { return tags; }
        public Event setTags(ArrayList<Tag> value) { this.tags = value; return this; }
        public String getOperatingSystem() { return operating_system; }
        public Event setOperatingSystem(String value) { this.operating_system = value; return this; }
        public String getBrowser() { return browser; }
        public Event setBrowser(String value) { this.browser = value; return this; }
        public String getDevice() { return device; }
        public Event setDevice(String value) { this.device = value; return this; }
        public Boolean getIsBot() { return is_bot; }
        public Event setIsBot(Boolean value) { this.is_bot = value; return this; }
    }

    public static class Provider
    {
        @DataMember
        public String type = null;

        public String getType() { return type; }
        public Provider setType(String value) { this.type = value; return this; }
    }

    public static class EnterpriseProvider
    {
        @DataMember
        public String name = null;

        @DataMember
        public String identity_provider_id = null;

        @DataMember
        public String type = null;

        @DataMember
        public String login_button_image_uri = null;

        @DataMember
        public String login_background_hex_color = null;

        @DataMember
        public String login_icon_image_uri = null;

        @DataMember
        public String login_text_hex_color = null;

        public String getName() { return name; }
        public EnterpriseProvider setName(String value) { this.name = value; return this; }
        public String getIdentityProviderId() { return identity_provider_id; }
        public EnterpriseProvider setIdentityProviderId(String value) { this.identity_provider_id = value; return this; }
        public String getType() { return type; }
        public EnterpriseProvider setType(String value) { this.type = value; return this; }
        public String getLoginButtonImageUri() { return login_button_image_uri; }
        public EnterpriseProvider setLoginButtonImageUri(String value) { this.login_button_image_uri = value; return this; }
        public String getLoginBackgroundHexColor() { return login_background_hex_color; }
        public EnterpriseProvider setLoginBackgroundHexColor(String value) { this.login_background_hex_color = value; return this; }
        public String getLoginIconImageUri() { return login_icon_image_uri; }
        public EnterpriseProvider setLoginIconImageUri(String value) { this.login_icon_image_uri = value; return this; }
        public String getLoginTextHexColor() { return login_text_hex_color; }
        public EnterpriseProvider setLoginTextHexColor(String value) { this.login_text_hex_color = value; return this; }
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