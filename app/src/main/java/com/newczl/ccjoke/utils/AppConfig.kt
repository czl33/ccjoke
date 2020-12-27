package com.newczl.ccjoke.utils

import android.util.Log
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.TypeReference
import com.google.gson.Gson
import com.newczl.ccjoke.model.BottomBar
import com.newczl.ccjoke.model.Destination


/**
 * AppConfig.kt 类：app配置
 * @author czl
 * created at 2020/12/26 10:50
 */
object AppConfig {
    /**
     * 获取节点配置
     */
    var sDestConfig: HashMap<String, Destination>? = null
        get() {
            if (field == null) {
                val content = parseFile("destnation.json")
                field = JsonUtils.getDestination(content);
            }
            return field;
        }


     var sBottomBar: BottomBar? = null
        get() {
            if (field == null) {
                val content = parseFile("main_tabs_config.json")
                field = JsonUtils.getBottomBar(content)
            }
            return field
        }

    /**
     * 解析assert文件方法
     */
    fun parseFile(fileName: String): String {
        val assets = AppGlobals.sApplication?.resources?.assets
        val builder = StringBuilder()
        assets?.open(fileName)?.bufferedReader()?.use {
            it.readLines().forEach { line ->
                builder.append(line)
            }
        }
        return builder.toString()
    }

}