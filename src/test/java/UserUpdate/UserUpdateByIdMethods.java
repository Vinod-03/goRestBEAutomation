package UserUpdate;

import io.restassured.response.Response;
import org.goRestApiAutomation.configuration.BaseUri;
import org.goRestApiAutomation.data.common.RestRequest;
import org.goRestApiAutomation.data.requestModel.UpdateUserRequest;
import org.goRestApiAutomation.data.responseModel.CreateUserResponse;
import org.goRestApiAutomation.endpoints.GetuserEndpoints;
import org.goRestApiAutomation.utils.ApiRequests;
import org.goRestApiAutomation.utils.CommonSerlizationUtil;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.goRestApiAutomation.configuration.BaseUri.ACCESSTOKEN;

public class UserUpdateByIdMethods {

    private static final ApiRequests apiRequests = new ApiRequests();

    private static final int USER_ID = 8336005;

    public static CreateUserResponse updateUserById(UpdateUserRequest payload) {

        RestRequest request = RestRequest.builder()
                .url(BaseUri.GOREST_BASE_URI
                        + GetuserEndpoints.GETUSERS
                        + "/" + USER_ID)
                .headers(Map.of(
                        "Authorization", "Bearer " + ACCESSTOKEN,
                        "Content-Type", "application/json"
                ))
                .body(payload)
                .build();

        Response response = apiRequests.patch(request);

        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(
                        "schemasValidation/update_user_success_schema.json"));


        return CommonSerlizationUtil.readObject(
                response.getBody().asString(),
                CreateUserResponse.class
        );
    }

    public static CreateUserResponse putUpdateUserById(UpdateUserRequest payload) {

        RestRequest request = RestRequest.builder()
                .url(BaseUri.GOREST_BASE_URI
                        + GetuserEndpoints.GETUSERS
                        + "/" + USER_ID)
                .headers(Map.of(
                        "Authorization", "Bearer " + ACCESSTOKEN,
                        "Content-Type", "application/json"
                ))
                .body(payload)
                .build();

        Response response = apiRequests.put(request);

        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(
                        "schemasValidation/update_user_success_schema.json"));

        return CommonSerlizationUtil.readObject(
                response.getBody().asString(),
                CreateUserResponse.class
        );
    }

}
