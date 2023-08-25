package com.julan.sp3.pojo.bo.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.julan.sp3.pojo.bo.QueryBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonPropertyOrder(value = {"status", "keywords"})
public class QueryUserBo extends QueryBo {
    public int status;
    public String keywords;
}





