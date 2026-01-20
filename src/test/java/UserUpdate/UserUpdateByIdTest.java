package UserUpdate;

import org.goRestApiAutomation.data.requestModel.UpdateUserRequest;
import org.goRestApiAutomation.data.responseModel.CreateUserResponse;

import org.goRestApiAutomation.utils.JavaUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserUpdateByIdTest {

    @Test(priority = 1, description = "Update user by hardcoded ID using PATCH")
    public void updateUserById() {

        SoftAssert softAssert = new SoftAssert();
        UserUpdateByIdValidation validator =
                new UserUpdateByIdValidation(softAssert);

        try {
            UpdateUserRequest updatePayload =
                    UpdateUserRequest.createUpdateUserRequestPayload(builder -> {
                        builder.name("Allasani Peddana");
                        builder.email(JavaUtil.getRandomEmail());
                        builder.status("active");
                    });

            CreateUserResponse response =
                    UserUpdateByIdMethods.updateUserById(updatePayload);

            validator.validateUpdateUserResponse(updatePayload, response);

        } catch (Exception e) {
            softAssert.fail(
                    "Exception occurred while updating user: " + e.getMessage()
            );
        }

        softAssert.assertAll();
    }


    @Test(priority = 2, description = "Update user by hardcoded ID using PUT")
    public void putUpdateUserByIdTest() {

        SoftAssert softAssert = new SoftAssert();
        UserUpdateByIdValidation validator =
                new UserUpdateByIdValidation(softAssert);

        try {
            UpdateUserRequest updatePayload =
                    UpdateUserRequest.createUpdateUserRequestPayload(builder -> {
                        builder.email(JavaUtil.getRandomEmail());
                    });

            CreateUserResponse response =
                    UserUpdateByIdMethods.putUpdateUserById(updatePayload);

            validator.validateUpdateUserPutResponse(updatePayload, response);

        } catch (Exception e) {
            softAssert.fail(
                    "Exception occurred while updating user: " + e.getMessage()
            );
        }

        softAssert.assertAll();
    }

}


