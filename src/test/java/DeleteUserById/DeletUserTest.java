package DeleteUserById;

import gorestgetusers.getAllUserMethods;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeletUserTest {



    long USER_ID=8333146L;


    @Test(priority = 3, description = "Delete user by hardcoded ID")
    public void deleteUserByIdTest() {


        SoftAssert softAssert = new SoftAssert();
        DeleteUserValidation validator =
                new DeleteUserValidation(softAssert);

        try {
            Response response =
                    DeleteUserMethod.deleteUserById(USER_ID);

            // GET after DELETE
            Response getResponse =
                    getAllUserMethods.getUserByIdAllow404(USER_ID);

            validator.validateUserIsDeleted(getResponse);
        } catch (Exception e) {
            softAssert.fail(
                    "Exception occurred while deleting user: " + e.getMessage()
            );
        }

        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Delete user with invalid ID should return 404")
    public void deleteUserByInvalidIdTest() {

        SoftAssert softAssert = new SoftAssert();
        DeleteUserValidation validator =
                new DeleteUserValidation(softAssert);

        long invalidUserId = 999999999L; // definitely does not exist

        try {
            Response response =
                    DeleteUserMethod.deleteUserByIdAllow404(invalidUserId);

            validator.validateDeleteUserNotFound(response);

        } catch (Exception e) {
            softAssert.fail(
                    "Exception occurred while deleting invalid user: " + e.getMessage()
            );
        }

        softAssert.assertAll();
    }

}
