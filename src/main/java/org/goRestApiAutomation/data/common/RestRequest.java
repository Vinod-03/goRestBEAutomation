package org.goRestApiAutomation.data.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data

public class RestRequest {
    private String url;
    private Map<String, String> headers;
    private Map<String, ?> queryParams;
    private Object body;
}