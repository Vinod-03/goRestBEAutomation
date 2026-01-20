package gorestgetusers;

import io.restassured.response.Response;
import org.goRestApiAutomation.configuration.BaseUri;
import org.goRestApiAutomation.data.common.RestRequest;
import org.goRestApiAutomation.data.responseModel.ResponseGetUsers;
import org.goRestApiAutomation.endpoints.GetuserEndpoints;
import org.goRestApiAutomation.utils.ApiRequests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.goRestApiAutomation.configuration.BaseUri.ACCESSTOKEN;

public class getAllUserMethods {


    private static final ApiRequests apiRequests=new ApiRequests();
    private static final Logger log = LoggerFactory.getLogger(getAllUserMethods.class);

    public static List<ResponseGetUsers> getAllUserResponse() {
        RestRequest req = RestRequest.builder()
                .headers(Map.of(
                        "Authorization",
                        "Bearer " + ACCESSTOKEN))
                .url(BaseUri.GOREST_BASE_URI + GetuserEndpoints.GETUSERS)
                .build();

        Response response = apiRequests.get(req);
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemasValidation/get_users_schema.json"));

        List<ResponseGetUsers> users = response.jsonPath().getList("", ResponseGetUsers.class);

        log.info("Total users fetched: {}", users.size());

        return users;  // no cast needed
    }

    public static List<ResponseGetUsers> getUsersWithPagination(int page, int perPage) {

        RestRequest req = RestRequest.builder()
                .headers(Map.of(
                        "Authorization",
                        "Bearer " + ACCESSTOKEN
                ))
                .queryParams(Map.of(
                        "page", page,
                        "per_page", perPage
                ))
                .url(BaseUri.GOREST_BASE_URI + GetuserEndpoints.GETUSERS)
                .build();

        Response response = apiRequests.get(req);
        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemasValidation/get_users_list_schema.json"));

        List<ResponseGetUsers> users =
                response.jsonPath().getList("", ResponseGetUsers.class);

        log.info("Page: {}, PerPage: {}, Users fetched: {}", page, perPage, users.size());

        return users;
    }



    public static ResponseGetUsers getUserById(long userId) {

        RestRequest req = RestRequest.builder()
                .headers(Map.of(
                        "Authorization",
                        "Bearer " +ACCESSTOKEN
                ))
                .url(BaseUri.GOREST_BASE_URI
                        + GetuserEndpoints.GETUSERS
                        + "/" + userId)
                .build();

        Response response = apiRequests.get(req);
        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemasValidation/get_user_by_id_schema.json"));

        response.then().statusCode(200);

        return response.as(ResponseGetUsers.class);
    }


    public static Response getUserByInvalidId(int userId) {

        RestRequest req = RestRequest.builder()
                .headers(Map.of("Authorization", "Bearer " + ACCESSTOKEN))
                .url(BaseUri.GOREST_BASE_URI
                        + GetuserEndpoints.GETUSERS
                        + "/" + userId)
                .build();

        Response response = apiRequests.get(req);
        response.then()
                .statusCode(404)
                .body(matchesJsonSchemaInClasspath("schemasValidation/get_user_by_invalidid_schema.json"));

        return response;
    }


    public static Response getUserByIdAllow404(long userId) {

        RestRequest req = RestRequest.builder()
                .headers(Map.of(
                        "Authorization", "Bearer " + ACCESSTOKEN
                ))
                .url(BaseUri.GOREST_BASE_URI
                        + GetuserEndpoints.GETUSERS
                        + "/" + userId)
                .build();

        return apiRequests.get(req);
    }


}

