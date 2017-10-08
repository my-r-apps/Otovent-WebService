package com.otovent.webservice.entity.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class BaseResponse {
    private HttpStatus httpStatus;
    private String message;
    private List<Object> result;
}
