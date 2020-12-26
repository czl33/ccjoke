package com.newczl.ccjoke.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.JsonObject;
import com.newczl.ccjoke.model.Destination;

import java.util.HashMap;
/**
* JsonUtils 类：json工具类
* @author czl
* created at 2020/12/26 14:31
*/
public class JsonUtils {
    public static HashMap<String, Destination> getDestination(String content){
        return JSON.parseObject(content,new TypeReference<HashMap<String, Destination>>(){}.getType());
    }
}
