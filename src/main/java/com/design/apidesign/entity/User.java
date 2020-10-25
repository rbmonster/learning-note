package com.design.apidesign.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Userr
 * @Author: sanwu
 * @Date: 2020/10/24 12:37
 */
@Getter
@Setter
@Data
public class User {
    @NotNull(message = "用户id不能为空")
    private long id;

    @NotNull(message = "用户昵称不能为空")
    @Size(min = 6, max = 11, message = "昵称长度必须是6-11个字符")
    private String nickname;

    @NotNull(message = "用户账号不能为空")
    @Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
    private String username;

    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符")
    private String password;

    @Pattern(regexp = "^[150[0-9]+]{11}", message = "电话格式有误")
    private String phone;

    private String remark;

}
