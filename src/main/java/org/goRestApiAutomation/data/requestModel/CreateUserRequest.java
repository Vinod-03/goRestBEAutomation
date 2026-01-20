package org.goRestApiAutomation.data.requestModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserRequest {

    private String name;
    private String gender;
    private String email;
    private String status;


    public static CreateUserRequest createUserRequestPayload(
            String name,
            String gender,
            String email,
            String status) {

        return CreateUserRequest.builder()
                .name(name)
                .gender(gender)
                .email(email)
                .status(status)
                .build();
    }
}
