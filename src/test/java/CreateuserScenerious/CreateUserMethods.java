package CreateuserScenerious;

import io.restassured.response.Response;
import org.goRestApiAutomation.configuration.BaseUri;
import org.goRestApiAutomation.data.common.RestRequest;
import org.goRestApiAutomation.data.requestModel.CreateUserRequest;
import org.goRestApiAutomation.data.responseModel.CreateUserResponse;
import org.goRestApiAutomation.endpoints.GetuserEndpoints;
import org.goRestApiAutomation.utils.ApiRequests;
import org.goRestApiAutomation.utils.CommonSerlizationUtil;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.goRestApiAutomation.configuration.BaseUri.ACCESSTOKEN;

public class CreateUserMethods {

    private static final ApiRequests apiRequests = new ApiRequests();

    public static CreateUserResponse postCreateUserValidPayload(
            CreateUserRequest createUserRequest) {

        RestRequest req = RestRequest.builder()
                .headers(Map.of(
                        "Authorization", "Bearer " + ACCESSTOKEN,
                        "Content-Type", "application/json"
                ))
                .url(BaseUri.GOREST_BASE_URI + GetuserEndpoints.GETUSERS)
                .body(createUserRequest)
                .build();

        Response response = apiRequests.post(req);
        response.then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath(
                        "schemasValidation/create_user_success_schema.json"
                ));

        return CommonSerlizationUtil.readObject(
                response.getBody().asString(),
                CreateUserResponse.class
        );
    }


    public static Response postCreateUserInvalidValidPayload(
            CreateUserRequest createUserRequest) {

        RestRequest req = RestRequest.builder()
                .headers(Map.of(
                        "Authorization", "Bearer " + ACCESSTOKEN,
                        "Content-Type", "application/json"
                ))
                .url(BaseUri.GOREST_BASE_URI + GetuserEndpoints.GETUSERS)
                .body(createUserRequest)
                .build();

        Response response = apiRequests.post(req);

        response.then()
                .statusCode(422)
                .body(matchesJsonSchemaInClasspath(
                        "schemasValidation/create_user_422_error_schema.json"
                ));

        return response;
    }


    public static Response postCreateUserWithoutToken(CreateUserRequest payload) {

        RestRequest request = RestRequest.builder()
                .url(BaseUri.GOREST_BASE_URI + GetuserEndpoints.GETUSERS)
                .headers(Map.of(
                        "Content-Type", "application/json"
                ))
                .body(payload)
                .build();

        Response response = apiRequests.post(request);

        response.then()
                .statusCode(401)
                .body(matchesJsonSchemaInClasspath(
                        "schemasValidation/create_user_401_error_schema.json"
                ));

        return response;
    }

}
