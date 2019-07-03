package com.jiawa365.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by silence-pc on 2019/6/25.
 */
@ResponseStatus(reason = "登录失败",code = HttpStatus.FORBIDDEN)
public class LoginFailedException extends RuntimeException {

}
