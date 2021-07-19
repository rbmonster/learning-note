package com.four.annotation.cotroller;


import com.four.annotation.annotationobj.EnrichUserInfo;
import com.four.annotation.model.BookUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    @EnrichUserInfo
    private BookUserInfo userInfo;


    public void test() {
        log.info("sdfsadf, {}", userInfo);
    }
}
