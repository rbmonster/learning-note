package com.four.messageconvert;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: MessageConvertController
 * @Author: sanwu
 * @Date: 2021/1/24 11:24
 */
@RestController
public class MessageConvertController {

    @GetMapping("/convert")
    public Object parse(@RequestBody Person person) {

        return person;
    }
}
