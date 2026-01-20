package gorestgetusers;

import io.restassured.response.Response;
import org.goRestApiAutomation.data.responseModel.ResponseGetUsers;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



import java.util.List;

public class GetUsersTest {

    @Test(priority = 1, description = "To fetch all the records of the user")
    public void getAllUsersTest() {
        SoftAssert softAssert = new SoftAssert();
        getUsersValidation validator = new getUsersValidation(softAssert);

        try {
            List<ResponseGetUsers> users = getAllUserMethods.getAllUserResponse();
            validator.getAllUsersValidation(users);

        } catch (Exception e) {
            softAssert.fail("Exception occurred while fetching users: " + e.getMessage());
            e.printStackTrace();
        }
        softAssert.assertAll();
    }


    @Test(priority = 2, description = "To fetch users with pagination")
    public void getUsersWithPaginationTest() {

        SoftAssert softAssert = new SoftAssert();
        getUsersValidation validator = new getUsersValidation(softAssert);

        int page = 1;
        int perPage = 10;

        try {
            List<ResponseGetUsers> users =
                    getAllUserMethods.getUsersWithPagination(page, perPage);

            validator.getUsersPaginationValidation(users, page, perPage);

        } catch (Exception e) {
            softAssert.fail("Exception occurred while fetching paginated users: " + e.getMessage());
            e.printStackTrace();
        }

        softAssert.assertAll();
    }


    @Test(priority = 3, description = "Get user by valid user ID")
    public void getUserByValidIdTest() {

        SoftAssert softAssert = new SoftAssert();
        getUsersValidation validator = new getUsersValidation(softAssert);

        int validUserId = 8333154;

        try {
            ResponseGetUsers user =
                    getAllUserMethods.getUserById(validUserId);

            validator.getUserByIdValidation(user);

        } catch (Exception e) {
            softAssert.fail("Exception occurred while fetching user by valid ID: " + e.getMessage());
            e.printStackTrace();
        }

        softAssert.assertAll();
    }


    @Test(priority = 4, description = "Get user by invalid user ID")
    public void getUserByInvalidIdTest() {

        SoftAssert softAssert = new SoftAssert();
        getUsersValidation validator = new getUsersValidation(softAssert);

        int invalidUserId = 1234567890;

        Response response =
                getAllUserMethods.getUserByInvalidId(invalidUserId);

        validator.getUserByInvalidIdValidation(response);

        softAssert.assertAll();
    }


}