package gorestgetusers;

import io.restassured.response.Response;
import org.goRestApiAutomation.data.responseModel.ResponseGetUsers;
import org.goRestApiAutomation.utils.ApiAssertions;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class getUsersValidation extends ApiAssertions {

    public getUsersValidation(SoftAssert softAssert) {
        super(softAssert);
    }

    public void getAllUsersValidation(List<ResponseGetUsers> users) {
        assertFieldNotNull(users, "Users list");
        assertFieldTrue(users.size() > 0, "Users list", "Users list should not be empty");

        for (int i = 0; i < users.size(); i++) {
            validateSingleUser(users.get(i), i);
        }

        assertAll(); // calls SoftAssert.assertAll()
    }

    public void validateSingleUser(ResponseGetUsers user, int index) {
        assertFieldNotNull(user.getId(), "User[" + index + "].id");
        assertFieldNotNull(user.getName(), "User[" + index + "].name");
        assertFieldNotNull(user.getEmail(), "User[" + index + "].email");
        assertFieldNotNull(user.getGender(), "User[" + index + "].gender");
        assertFieldNotNull(user.getStatus(), "User[" + index + "].status");

        // Email format
        assertFieldTrue(user.getEmail().contains("@"),
                "User[" + index + "].email format",
                "Email should contain '@'");

        // Gender validation
        String gender = user.getGender();
        assertFieldTrue(gender.equals("male") || gender.equals("female"),
                "User[" + index + "].gender value",
                "Gender must be male or female");

        // Status validation
        String status = user.getStatus();
        assertFieldTrue(status.equals("active") || status.equals("inactive"),
                "User[" + index + "].status value",
                "Status must be active or inactive");
    }


    public void getUsersPaginationValidation(List<ResponseGetUsers> users,
                                             int page,
                                             int perPage) {

        assertFieldNotNull(users, "Users list for page " + page);

        assertFieldTrue(users.size() <= perPage,
                "Users count per page",
                "Users count should not exceed per_page value");

        if (page == 1) {
            assertFieldTrue(users.size() > 0,
                    "Users list for first page",
                    "First page should contain users");
        }

        for (int i = 0; i < users.size(); i++) {
            validateSingleUser(users.get(i), i);
        }
    }
    public void getUserByIdValidation(ResponseGetUsers user) {

        assertFieldNotNull(user, "User response");

        assertFieldNotNull(user.getId(), "User.id");
        assertFieldNotNull(user.getName(), "User.name");
        assertFieldNotNull(user.getEmail(), "User.email");
        assertFieldNotNull(user.getGender(), "User.gender");
        assertFieldNotNull(user.getStatus(), "User.status");

        assertFieldTrue(user.getEmail().contains("@"),
                "User.email format",
                "Email should contain '@'");

        assertFieldTrue(
                user.getGender().equals("male") || user.getGender().equals("female"),
                "User.gender value",
                "Gender must be male or female"
        );

        assertFieldTrue(
                user.getStatus().equals("active") || user.getStatus().equals("inactive"),
                "User.status value",
                "Status must be active or inactive"
        );
    }


    public void getUserByInvalidIdValidation(Response response) {

        assertFieldTrue(
                response.getStatusCode() == 404,
                "Status code validation",
                "Expected status code 404"
        );

        String message = response.jsonPath().getString("message");

        assertFieldNotNull(message, "Error message");

        assertFieldTrue(
                message.equalsIgnoreCase("Resource not found"),
                "Error message validation",
                "Expected 'Resource not found'"
        );
    }



}

