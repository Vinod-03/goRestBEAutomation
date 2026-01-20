package org.goRestApiAutomation.data.responseModel;

import lombok.Data;

import java.util.List;

@Data
public class ResponseGetUsers {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;
}