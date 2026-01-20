package UserUpdate;

import org.goRestApiAutomation.data.requestModel.UpdateUserRequest;
import org.goRestApiAutomation.data.responseModel.CreateUserResponse;
import org.goRestApiAutomation.utils.ApiAssertions;
import org.testng.asserts.SoftAssert;

public class UserUpdateByIdValidation extends ApiAssertions {

    public UserUpdateByIdValidation(SoftAssert softAssert) {
        super(softAssert);
    }

    public void validateUpdateUserResponse(
            UpdateUserRequest request,
            CreateUserResponse response) {

        assertFieldNotNull(response, "Update user response");
        assertFieldNotNull(response.getId(), "User ID");

        if (request.getName() != null) {
            assertFieldsEquals(response.getName(), request.getName(), "Name");
        }

        if (request.getEmail() != null) {
            assertFieldsEquals(response.getEmail(), request.getEmail(), "Email");
        }

        if (request.getStatus() != null) {
            assertFieldsEquals(response.getStatus(), request.getStatus(), "Status");
        }
    }
    public void validateUpdateUserPutResponse(
            UpdateUserRequest request,
            CreateUserResponse response) {

        assertFieldNotNull(response, "Update user response");
        assertFieldNotNull(response.getId(), "User ID");

        if (request.getName() != null) {
            assertFieldsEquals(response.getName(), request.getName(), "Name");
        }

        if (request.getEmail() != null) {
            assertFieldsEquals(response.getEmail(), request.getEmail(), "Email");
        }

        if (request.getStatus() != null) {
            assertFieldsEquals(response.getStatus(), request.getStatus(), "Status");
        }
    }
}
