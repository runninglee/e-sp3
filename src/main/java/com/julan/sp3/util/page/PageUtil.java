package com.julan.sp3.util.page;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PageUtil {

    public static Object pretty(Page<?> page) {
        List<?> data = page.getContent();
        return getPage(page, data);
    }

    public static Object pretty(Page<?> page, Class<?> viewObject) {
        ModelMapper modelMapper = new ModelMapper();
        List<?> entities = page.getContent();
        List<?> data = entities.stream().map(user -> modelMapper.map(user, viewObject)).collect(Collectors.toList());
        return getPage(page, data);
    }

    private static Object getPage(Page<?> page, List<?> data) {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("total", page.getTotalElements());
        pagination.put("page", page.getNumber() + 1);
        pagination.put("pageSize", page.getSize());
        pagination.put("total_pages", page.getTotalPages());
        pagination.put("sort", page.getSort());
        result.put("pagination", pagination);
        result.put("data", data);
        return result;
    }
}
