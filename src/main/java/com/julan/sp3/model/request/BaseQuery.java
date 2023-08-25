package com.julan.sp3.model.request;

import lombok.Data;

@Data
public abstract class BaseQuery {
    public int page = 1;
    public int pageSize = 20;
    public String order = "id";
    public String direction = "desc";
}
