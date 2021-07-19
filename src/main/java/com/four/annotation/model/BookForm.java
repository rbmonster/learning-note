package com.four.annotation.model;

import com.four.annotation.annotationobj.EnrichUserInfo;
import lombok.Data;

@Data
public class BookForm {

    private String name;

    @EnrichUserInfo
    private BookUserInfo userInfo;
}

