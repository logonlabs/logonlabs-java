package com.logonlabs;

import com.logonlabs.constants.EventTypes;
import com.logonlabs.constants.EventValidationTypes;
import com.logonlabs.constants.IdentityProviders;
import org.junit.*;

import java.util.ArrayList;


public class LogonClientTest {

    private static final String IDPX_URI = "";
    private static final String APP_ID = "";
    private static final String APP_SECRET = "";
    private static final String CLIENT_ENCRYPTION_SECRET = "";
    private static final String VALIDATE_TOKEN = ""; //this has to be hardcoded since redirect can't be performed
    private static final String IP_ADDRESS = "";
    private static final String EMAIL_ADDRESS = "";

    @Test
    public void pingTest() throws Exception {
        LogonClient client = new LogonClient(APP_ID, APP_SECRET, IDPX_URI);
    }

    @Test
    public void startLoginTest() throws Exception {

        LogonClient client = new LogonClient(APP_ID, APP_SECRET, IDPX_URI);

        String response = client.startLogin(IdentityProviders.Microsoft, null,"isiah.pasquale@deliveryslip.com", null, null, null, null);
        if(response == null || response.isEmpty()) {
            throw new Exception("failed to get uri");
        }
    }

    @Test
    public void validateLoginTest() throws Exception {
        LogonClient client = new LogonClient(APP_ID, APP_SECRET, IDPX_URI);

        dtos.ValidateLoginResponse response = client.validateLogin(VALIDATE_TOKEN);

        if(!response.isEventSuccess()){
            dtos.ValidationDetails validationDetails = response.getValidationDetails();
            if(validationDetails.getDomainValidation().equals(EventValidationTypes.Fail)){
                //authentication with correct identity provider for domain failed
                throw new Exception("auth failed");
            }
            if(validationDetails.getGeoValidation().equals(EventValidationTypes.Fail)
                    || validationDetails.getIpValidation().equals(EventValidationTypes.Fail)
                    || validationDetails.getTimeValidation().equals(EventValidationTypes.Fail)) {
                //validation failed via restriction settings for the app
                throw new Exception("validation failed");
            }

        } else {
            //authentication and validation was good to go
            String emailAddress = response.getEmailAddress();
        }
    }

    @Test
    public void createAndUpdateEventTest() throws Exception {
        LogonClient client = new LogonClient(APP_ID, APP_SECRET, IDPX_URI);
        dtos.CreateEventResponse response = client.createEvent(EventTypes.LocalLogin, true,
                IP_ADDRESS, EMAIL_ADDRESS, "TestFirstName", "TestLastName",
                EventValidationTypes.Pass, null, null);

        ArrayList<dtos.Tag> tags = new ArrayList<dtos.Tag>();
        dtos.Tag tag = new dtos.Tag();
        tag.setKey("TestKey");
        tag.setValue("TestValue");
        tags.add(tag);

        client.updateEvent(response.getEventId(), EventValidationTypes.Fail, tags);
    }

    @Test
    public void parseTokenFromUrlTest() throws Exception {
        LogonClient client = new LogonClient(APP_ID, APP_SECRET, IDPX_URI);
        String tokenValue = "therealtokenvalue";
        String url = "https://www.logonlabs.com/callbacktest?faketoken=123toke&token="+tokenValue;
        String token = client.parseToken(url);
        if(!token.equalsIgnoreCase(tokenValue)){
            throw new Exception("Could not parse token correctly");
        }

        System.out.println(String.format("Token parsed as %s", token));
    }

}
