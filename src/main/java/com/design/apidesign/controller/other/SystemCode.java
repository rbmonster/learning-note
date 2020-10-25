package com.design.apidesign.controller.other;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**code 变量定义*/
public class SystemCode {
	
	private final static HashMap<Integer , String> _codeDict ;
	/**
	 * 请求成功
	 */
	public static final int SUCCESS= 200;
	/**
	 * 请求失败
	 */
    public static final int FAILURE = 500;
   
    /**
     * 用户未登录或登录超时
     */
    public static final int NO_LOGIN = 301;
    /**
     *  用户不存在或密码错误
     */
    public static final int NO_OBJ_ERROR_PASS = 302;
    /**
   	 * 请求频繁
   	 */
       public static final int OFTEN = 303; 
       /**
        * 参数错误
        */
   	public static final int PARAM_ERROR=400;
    /**
     * 内部错误
     */
	public static final int INNER_ERROR=401;
	/**
	 * 用户名或密码错误
	 */
	public static final int WRONG_PASSWORD=402;
	/**
	 * 无此权限
	 */
	public static final int NO_PRI=403;
	/**
	 * 用户名已存在
	 */
	public static final int USER_EXISTS=410;
	/**
	 * 对象已存在
	 */
	public static final int OBJ_EXISTS=411;
	/**
	 * 对象不存在
	 */
	public static final int OBJ_NOT_EXISTS=413;
	
	/**
	 * 对象未绑定
	 */
	public static final int OBJ_NOT_BIND=414;
	/**
	 * 用户名不存在
	 */
	public static final int USER_NOT_EXISTS=412;
	
	/**
	 * 手机号已存在
	 */
	public static final int PHONE_EXISTS=415;
	/**
	 * /**
	 * 手机号不存在
	 */
	public static final int PHONE_NOT_EXISTS=416;
	/**
	 *  传入的参数为null
	 */
	public static final int NULL_ARGUMENT=501;
	/**
	 * 不合法的参数
	 */
	public static final int ILLEGAL_ARGUMENT=502;
	/**
	 * 超时
	 */
	public static final int TIME_OUT=503;
	/**
	 * sign 错误
	 */
	public static final int SIGN_ERROR=503;

	

	static {
		Object[] codeList = new Object[]{
			SUCCESS,"请求成功",
			FAILURE,"请求失败",
			NO_LOGIN,"用户未登录或登录超时",
			NO_OBJ_ERROR_PASS,"用户不存在或密码错误",
			INNER_ERROR,"系统内部错误",
			NULL_ARGUMENT,"传入的参数为空或null",
			ILLEGAL_ARGUMENT,"不合法的参数",
			WRONG_PASSWORD,"用户名或密码错误",NO_PRI,"无此权限",
			USER_EXISTS,"用户已存在",OBJ_EXISTS,"对象已存在",
			USER_NOT_EXISTS,"用户不存在",
			OBJ_NOT_EXISTS,"对象不存在",
			OBJ_NOT_BIND,"用户未绑定"
		};
		_codeDict = new HashMap<Integer , String>();
		for(int i = 0 ; i < codeList.length / 2 ; i++ ){
			_codeDict.put(Integer.parseInt(codeList[i*2].toString()),
						codeList[i*2+1].toString());
		}			
	}
	
	public static String GetErrorDesc(int code){
		if(!_codeDict.containsKey(code)) {
			return "";
		} else {
			return _codeDict.get(code);
		}
	}
	public static HttpStatus parseHttpMethod(HttpMethod httpMethod, int code) {
		// 判断http的方法 及 systemcode 的类型 返回对应的httpcode
		if (httpMethod.matches(HttpMethod.POST.name())){

		} else {

		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
	public static String GetErrorDesc(String code){
		return GetErrorDesc(Integer.parseInt(code.trim()));
	}
}
