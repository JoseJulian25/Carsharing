package com.rd.utils;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

    public static Map<String, Object> createPageResponse(Page<?> page, String contentKey){
        Map<String, Object> responsePage = new HashMap<>();
        responsePage.put(contentKey, page.getContent());
        responsePage.put("currentPage", page.getNumber());
        responsePage.put("totalItems", page.getTotalElements());
        responsePage.put("totalPages", page.getTotalPages());
        return responsePage;
    }
}
