package xyz.y2sec.blog.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApiResult {

    private Object data;

    private String message;

}
