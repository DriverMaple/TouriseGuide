package com.maple.touriseguide.Common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.asm.ClassWriter;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Result {
    public static Byte FAIL = 1;
    public static Byte SUCCESS = 0;
    private Byte result;
    private Object value;
    private String message;

    public Result(String string, Class cls, boolean isList){
        if (isList){
            JSONObject object = JSONObject.parseObject(string);
            result = object.getByte("result");
            List list = null;
            value = JSON.parseArray(object.getString("value"), cls);
            message = object.getString("message");
        } else {
            JSONObject object = JSONObject.parseObject(string);
            result = object.getByte("result");
            value = JSON.parseObject(object.getString("value"),cls);
            message = object.getString("message");
        }
    }

    public Byte getResult() {
        return result;
    }

    public void setResult(Byte result) {
        this.result = result;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
