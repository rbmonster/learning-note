package com.design.apidesign.controller;

import com.design.apidesign.entity.User;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: UserController
 * @Author: sanwu
 * @Date: 2020/10/24 12:43
 */
@RequestMapping("/users")
@RestController
public class UserController {


    /**
     * 通过BindingResult 处理错误内容
     * 错误内容在bingResult中显示
     * @param user
     * @param bindingResult
     * @return
     */
//    @PostMapping
//    public String addUser(@RequestBody @Valid User user, BindingResult bindingResult) {
//        if (!CollectionUtils.isEmpty(bindingResult.getAllErrors())){
//            StringBuilder sb = new StringBuilder();
//            bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).forEach(str -> sb.append(str+"\n"));
//            return sb.toString();
//        }
//        return "create success";
//    }

    /**
     * 直接抛出异常
     * 未处理全局异常时，返回内容如下
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping
    public String addUser2(@RequestBody @Valid User user) throws Exception {
        /**
         * 返回错误内容未格式化
         *     {
         *     "timestamp": "2020-10-24T05:12:26.123+00:00",
         *     "status": 400,
         *     "error": "Bad Request",
         *     "errors":[
         *     {"codes":["NotNull.user.password", "NotNull.password", "NotNull.java.lang.String", "NotNull" ], "arguments":[{"codes":["user.password",…},
         *     {"codes":["NotNull.user.nickname", "NotNull.nickname", "NotNull.java.lang.String", "NotNull" ], "arguments":[{"codes":["user.nickname",…},
         *     {"codes":["NotNull.user.username", "NotNull.username", "NotNull.java.lang.String", "NotNull" ], "arguments":[{"codes":["user.username",…}
         *     ],
         *     "message": "Validation failed for object='user'. Error count: 3",
         *     "path": "/learning/users"
         *     }
         */
        return "create success";
    }
}
