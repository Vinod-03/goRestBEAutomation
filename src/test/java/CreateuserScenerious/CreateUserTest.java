package CreateuserScenerious;


import io.restassured.response.Response;
import org.goRestApiAutomation.data.requestModel.CreateUserRequest;
import org.goRestApiAutomation.data.responseModel.CreateUserResponse;
import org.goRestApiAutomation.utils.JavaUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateUserTest {


        @Test(priority = 1,description = "Create user with valid payload")
        public void createUserValidPayload() {

            SoftAssert softAssert = new SoftAssert();
            CreateUserValidation validator =
                    new CreateUserValidation(softAssert);

            try {
                CreateUserRequest createUserPayload =
                        CreateUserRequest.createUserRequestPayload(
                                "Tenali Ramakrishna",
                                "male",
                                JavaUtil.getRandomEmail(),
                                "active"
                        );

                CreateUserResponse response =
                        CreateUserMethods.postCreateUserValidPayload(createUserPayload);

                validator.validateCreateUserResponse(createUserPayload, response);

            } catch (Exception e) {
                softAssert.fail(
                        "Exception occurred while creating user: "
                                + e.getMessage()
                );
            }

            softAssert.assertAll();
        }



    @Test(priority = 2,description = "Create user with invalid payload (missing email)")
    public void createUserInvalidPayload() {

        SoftAssert softAssert = new SoftAssert();
        CreateUserValidation validator = new CreateUserValidation(softAssert);

        try {
            CreateUserRequest createUserPayload = CreateUserRequest.createUserRequestPayload(
                    "Tenali Ramakrishna",
                    "male",
                    null,
                    "active"
            );

            Response response = CreateUserMethods.postCreateUserInvalidValidPayload(createUserPayload);

            validator.validateEmailFieldError(response);

        } catch (Exception e) {
            softAssert.fail("Exception occurred while creating user: " + e.getMessage());
            e.printStackTrace();
        }

        softAssert.assertAll();
    }



    @Test(priority = 3,description = "Create user without token → 401 Unauthorized")
    public void createUserWithoutToken() {

        SoftAssert softAssert = new SoftAssert();
        CreateUserValidation validator =
                new CreateUserValidation(softAssert);

        try {
            CreateUserRequest payload =
                    CreateUserRequest.createUserRequestPayload(
                            "Tenali Ramakrishna",
                            "male",
                            "tenali@example.com",
                            "active"
                    );

            Response response =
                    CreateUserMethods.postCreateUserWithoutToken(payload);

            validator.validateUnauthorizedResponse(response);

        } catch (Exception e) {
            softAssert.fail("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        softAssert.assertAll();
    }
}










