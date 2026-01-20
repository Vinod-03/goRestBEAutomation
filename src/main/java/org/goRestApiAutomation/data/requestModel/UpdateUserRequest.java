package org.goRestApiAutomation.data.requestModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserRequest {
    private String name;
    private String gender;
    private String email;
    private String status;

    public static UpdateUserRequest createUpdateUserRequestPayload(
            Consumer<UpdateUserRequest.UpdateUserRequestBuilder> consumer) {

        UpdateUserRequestBuilder builder = UpdateUserRequest.builder();
        consumer.accept(builder);
        return builder.build();
    }
}
