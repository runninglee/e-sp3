package com.julan.sp3.bo.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"status", "keywords"})
public class QueryUserBo {

    private int page;

    private int pageSize;

    private int status;

    private String keywords;

}





