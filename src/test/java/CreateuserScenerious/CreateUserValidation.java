package CreateuserScenerious;

import io.restassured.response.Response;
import org.goRestApiAutomation.data.requestModel.CreateUserRequest;
import org.goRestApiAutomation.data.responseModel.CreateUserResponse;
import org.goRestApiAutomation.utils.ApiAssertions;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class CreateUserValidation extends ApiAssertions {

    public CreateUserValidation(SoftAssert softAssert) {
        super(softAssert);
    }

    /**
     * Validate Create User API response against request payload
     */
    public void validateCreateUserResponse(
            CreateUserRequest request,
            CreateUserResponse response) {

        // Response object validation
        assertFieldNotNull(response, "CreateUserResponse");

        if (response == null) {
            return;
        }

        // Mandatory fields validation
        assertFieldNotNull(response.getId(), "response.id");
        assertFieldNotNull(response.getName(), "response.name");
        assertFieldNotNull(response.getEmail(), "response.email");
        assertFieldNotNull(response.getGender(), "response.gender");
        assertFieldNotNull(response.getStatus(), "response.status");

        // Validate response values against request
        assertFieldsEquals(
                response.getName(),
                request.getName(),
                "name"
        );

        assertFieldsEquals(
                response.getEmail(),
                request.getEmail(),
                "email"
        );

        assertFieldsEquals(
                response.getGender().toLowerCase(),
                request.getGender().toLowerCase(),
                "gender"
        );

        assertFieldsEquals(
                response.getStatus().toLowerCase(),
                request.getStatus().toLowerCase(),
                "status"
        );
    }


    public void validateEmailFieldError(Response response) {
        assertFieldNotNull(response, "Response");


        List<Map<String, String>> errors = response.jsonPath().getList("$");
        assertFieldNotNull(errors, "Errors list");

        boolean emailErrorFound = false;
        for (Map<String, String> error : errors) {
            if ("email".equals(error.get("field")) &&
                    error.get("message") != null &&
                    error.get("message").toLowerCase().contains("can't be blank")) {
                emailErrorFound = true;
                break;
            }
        }


        assertFieldTrue(emailErrorFound, "Email field error",
                "Response should contain 'email can't be blank' error");
    }

    public void validateUnauthorizedResponse(Response response) {

        assertFieldNotNull(response, "Response");

        // Status code
        assertFieldsEquals(
                response.getStatusCode(),
                401,
                "HTTP Status Code"
        );

        // Exact message validation
        String message = response.jsonPath().getString("message");

        assertFieldsEquals(
                message,
                "Authentication failed",
                "Unauthorized error message"
        );
    }

}
