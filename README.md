# LogonLabs Java

The official LogonLabs Java Client library.

## Download

Maven
```xml
<dependency>
    <groupId>com.logonlabs</groupId>
    <artifactId>logonlabs-java</artifactId>
    <version>1.0</version>
</dependency>
```

## LogonLabs API


- Prior to coding, some configuration is required at https://logonlabs.com/app/#/app-settings.

- For the full Developer Documentation please visit: https://logonlabs.com/docs/api/

---
### Instantiating a new client

- Your `APP_ID` can be found in [App Settings](https://logonlabs.com/app/#/app-settings)
- `APP_SECRETS` are configured [here](https://logonlabs.com/app/#/app-secrets)
- The `LOGONLABS_API_ENDPOINT` should be set to `https://api.logonlabs.com`

Create a new instance of `LogonClient`.  

```java
import com.logonlabs.LogonClient;

LogonClient client = new LogonClient("{APP_ID}", "{APP_SECRET}", "{LOGONLABS_API_ENDPOINT}");
```
---
### SSO Login QuickStart

The StartLogin function in the JS library begins the LogonLabs managed SSO process.

>Further documentation on starting the login process via our JavaScript client can be found at our GitHub page [here](https://github.com/logonlabs/logonlabs-js)

The following example demonstrates what to do once the `Callback Url` has been used by our system to redirect the user back to your page:

```java
import com.logonlabs.LogonClient;
import com.logonlabs.dtos.ValidateLoginResponse;
import com.logonlabs.dtos.SsoValidationDetails;

String callbackUrl = "{CallbackUrl}?token=4033d32b8e334f80af6a3e627b66e640";

String queryToken = client.parseToken(callbackUrl);

ValidateLoginResponse response = client.validateLogin(queryToken);

String eventId = response.getEventId(); //used to update the SSO event later via UpdateEvent

if(response.isEventSuccess()) {
    //authentication and validation succeeded. proceed with post-auth workflows for your system
    
}

```
---
### Java Only Workflow
The following workflow is required if you're using a java framework that handles both the front and back ends.  If this does not apply to you, please refer to the SSO Login QuickStart section.
#### Step 1 - StartLogin
This call begins the LogonLabs managed SSO process.  The `clientData` property is optional and is used to pass any data that is required after validating the request.  The `clientEncryptionKey` property is optionally passed if the application requires encrypting any data that is passed between the front and back end infrastructure. The `tags`property is an ArrayList of type Tag which is a simple object representing a key/value pair.

```java
import com.logonlabs.LogonClient;
import com.logonlabs.constants.IdentityProviders;
import com.logonlabs.dtos.Tag;

//optional parameters
String clientData = "{\"ClientData\":\"Value\"}";
String clientEncryptionKey = "qbTRzCvUju";
ArrayList<Tag> tags = new ArrayList<Tag>();
Tag tag = new Tag();
tag.setKey("example-key");
tag.setValue("example-value");
tags.Add(tag);
//

String redirectUri = client.startLogin(IdentityProviders.Google, "emailAddress", clientData, clientEncryptionKey, tags);
```
The `redirectUri` property returned should be redirected to by the application.  Upon submitting their credentials, users will be redirected to the `CallbackUrl` set within the application settings at https://logonlabs.com/app/#/app-settings.
&nbsp;
#### Step 2 - ValidateLogin
This method is used to validate the results of the login attempt.  `queryToken` corresponds to the query parameter with the name `token` appended to the callback url specified for your app.

The response contains all details of the login and the user has now completed the SSO workflow.  If there is any additional information to add, UpdateEvent can be called on the `eventId` returned.
```java
import com.logonlabs.LogonClient;
import com.logonlabs.dtos.ValidateLoginResponse;
import com.logonlabs.dtos.SsoValidationDetails;

String callbackUrl = "{CallbackUrl}?token=4033d32b8e334f80af6a3e627b66e640";
String queryToken = client.parseToken(callbackUrl);

ValidateLoginResponse response = client.validateLogin(queryToken);

String eventId = response.getEventId();

if(response.isEventSuccess()) {
    //authorization with the identity provider succeeded.  proceed with your system's workflows...
    
} else {
    //some validations failed.  details contained in SsoValidationDetails object.

    ValidationDetails validationDetails = response.getValidationDetails();
    if(validationDetails.getAuthValidation().equals(EventValidationTypes.Fail)) {
        //authentication with identity provider failed
    }
    if(validationDetails.getEmailMatchValidation().equals(EventValidationTypes.Fail)) {
        //email didn't match the one provided to StartLogin
    }
    if(validationDetails.getGeoValidation().equals(EventValidationTypes.Fail) 
        || validationDetails.getIpValidation().equals(EventValidationTypes.Fail) 
        || validationDetails.getTimeValidation().equals(EventValidationTypes.Fail)) {
        //validation failed via restriction settings for the app
    }

}

```
---
### Events
The CreateEvent method allows one to create events that are outside of our SSO workflows.  UpdateEvent can be used to update any events made either by CreateEvent or by our SSO login.
```java
import com.logonlabs.LogonClient;
import com.logonlabs.dtos.CreateEventResponse;
import com.logonlabs.constants.EventTypes;
import com.logonlabs.dtos.Tag;

Boolean validateEvent = true;
ArrayList<Tag> tags = new ArrayList<Tag>();
Tag tag = new Tag();
tag.setKey("example-key");
tag.setValue("example-value");
tags.Add(tag);
String localValidation = EventValidationTypes.Pass;

CreateEventResponse response = client.createEvent(EventTypes.LocalLogin, validateEvent, 
                                    "ipAddress", "emailAddress", "firstName", "lastName", 
                                    localValidation, "userAgent", tags);

String eventId = response.getEventId();

//some later local validation fails which requires further updates to the event...

localValidation = EventValidationTypes.Fail;
tags = new ArrayList<Tag>();
tag = new Tag();
tag.setKey("failure-field");
tag.setValue("detailed reason for failure");
tags.Add(tag);

client.updateEvent(eventId, localValidation, tags);
```

---
### Helper Methods
#### GetProviders
This method is used to retrieve a list of all providers enabled for the application.
If an email address is passed to the method, it will return the list of providers available for that email domain.
If any Enterprise Identity Providers have been configured a separate set of matching providers will also be returned in enterprise_identity_providers.

```java
import com.logonlabs.LogonClient;
import com.logonlabs.dtos.GetProvidersResponse;
import com.logonlabs.dtos.Provider;
import com.logonlabs.constants.IdentityProviders;

GetProvidersResponse response = client.getProviders("emailAddress");

for(Provider provider : response.identity_providers) {

    if(provider.getType().equals(IdentityProviders.Google)) {
        //make google available in UI or handle other custom rules
    }
}

for(EnterpriseProvider provider : response.enterprise_identity_providers) {
        //use the identity_provider_id to find the correct provider and apply any custom rules
    }
```

#### Encrypt/Decrypt
The Java SDK has built in methods for encrypting/decrypting strings using AES encryption.  Use a value for your encryption key that only your client/server will know. 
```java
import com.logonlabs.LogonClient;

String baseString = "string to be encrypted";
String encryptionKey = "qbTRzCvUju";

String encryptedString = client.encrypt(encryptionKey, baseString);

String decryptedString = client.decrypt(encryptionKey, encryptedString);
```

#### ParseToken
This method parses out the value of the token query parameter returned with your callback url.
```java
import com.logonlabs.LogonClient;

String callbackUrl = "https://example.com?token=7dc6e5dc4f2641aab64a6fa1ed91a3b1";
String token = client.parseToken(callbackUrl);
System.out.println(token);

//output
//7dc6e5dc4f2641aab64a6fa1ed91a3b1
```
