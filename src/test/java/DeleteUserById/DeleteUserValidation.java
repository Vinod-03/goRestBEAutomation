package DeleteUserById;

import io.restassured.response.Response;
import org.goRestApiAutomation.utils.ApiAssertions;
import org.testng.asserts.SoftAssert;

public class DeleteUserValidation extends ApiAssertions {


    public DeleteUserValidation(SoftAssert softAssert) {
        super(softAssert);
    }

    public void validateDeleteUserResponse(Response response) {

        assertFieldNotNull(response, "Delete user response");

        assertFieldsEquals(
                response.getStatusCode(),
                204,
                "Delete user status code"
        );
    }
    public void validateDeleteUserNotFound(Response response) {

        assertFieldNotNull(response, "Delete invalid user response");

        assertFieldsEquals(
                response.getStatusCode(),
                404,
                "Delete invalid user status code"
        );
    }
    public void validateUserIsDeleted(Response response) {

        assertFieldsEquals(
                response.getStatusCode(),
                404,
                "Get deleted user status code"
        );
    }

}
