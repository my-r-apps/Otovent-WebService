package com.otovent.webservice.entity.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class PaginationResponse {
    private HttpStatus httpStatus;
    private String message;
    private Integer totalPages;
    private Page<? extends Object> result;
}
