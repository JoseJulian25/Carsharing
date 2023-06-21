package com.rd.utils;

import com.rd.exception.DataNotFoundException;

import java.util.List;
import java.util.function.Supplier;

public class ListValidation {

    public static <T> List<T> checkNonEmptyList(List<T> list, Supplier<String> errorMessage){
        if (list.isEmpty()){
            throw new DataNotFoundException(errorMessage.get());
        }
        return list;
    }
}
