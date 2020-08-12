package com.tao.frameworks.base.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseParam {

    @ApiModelProperty("当前页码")
    private int page = 1;

    @ApiModelProperty("每页显示数量")
    private int limit = 10;

}
