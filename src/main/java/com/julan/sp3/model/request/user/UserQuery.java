package com.julan.sp3.model.request.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.julan.sp3.model.request.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonPropertyOrder(value = {"status", "keywords"})
public class UserQuery extends BaseQuery {
    public int status;
    public String keywords;
}





