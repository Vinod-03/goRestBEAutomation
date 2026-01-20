package DeleteUserById;

import io.restassured.response.Response;
import org.goRestApiAutomation.configuration.BaseUri;
import org.goRestApiAutomation.data.common.RestRequest;
import org.goRestApiAutomation.endpoints.GetuserEndpoints;
import org.goRestApiAutomation.utils.ApiRequests;

import java.util.Map;

import static org.goRestApiAutomation.configuration.BaseUri.ACCESSTOKEN;

public class DeleteUserMethod {
    private static final ApiRequests apiRequests = new ApiRequests();
    public static Response deleteUserById(Long userId) {

        RestRequest request = RestRequest.builder()
                .url(BaseUri.GOREST_BASE_URI
                        + GetuserEndpoints.GETUSERS
                        + "/" + userId)
                .headers(Map.of(
                        "Authorization", "Bearer " + ACCESSTOKEN,
                        "Content-Type", "application/json",
                        "Accept", "application/json"
                ))
                .build();

        Response response = apiRequests.delete(request);

        response.then()
                .statusCode(204); // DELETE success

        return response;
    }
    public static Response deleteUserByIdAllow404(long userId) {

        RestRequest request = RestRequest.builder()
                .url(BaseUri.GOREST_BASE_URI
                        + GetuserEndpoints.GETUSERS
                        + "/" + userId)
                .headers(Map.of(
                        "Authorization", "Bearer " + ACCESSTOKEN,
                        "Accept", "application/json"
                ))
                .build();

        return apiRequests.delete(request);
    }

}
