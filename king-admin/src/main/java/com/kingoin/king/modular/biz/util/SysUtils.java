package com.kingoin.king.modular.biz.util;

import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.dto.Result;


public class SysUtils {


    /**
     * 请求错误信息封装
     */
    public static Result setErrorMsg(Result result){
        result.setCode(500);
        result.setMessage("服务器异常!");
        return result;
    }

    /**
     * 请求错误信息封装重载
     */
    public static  Result setErrorMsg(Result result,String erroMsg){
        result.setCode(500);
        if(ToolUtil.isNotEmpty(erroMsg)){
            result.setMessage(erroMsg);
        }else{
            result.setMessage("服务器异常!");
        }
        return result;
    }

}
