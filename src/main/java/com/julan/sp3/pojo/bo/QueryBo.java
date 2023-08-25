package com.julan.sp3.pojo.bo;

import lombok.Data;

@Data
public abstract class QueryBo {
    public int page = 0;
    public int pageSize = 20;
    public String order = "id";
    public String direction = "desc";
}
