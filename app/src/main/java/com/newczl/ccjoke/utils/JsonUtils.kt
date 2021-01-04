package com.newczl.ccjoke.utils

import android.R
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.google.gson.Gson
import com.newczl.ccjoke.model.BottomBar
import com.newczl.ccjoke.model.Destination
import java.util.*

/**
 * JsonUtils 类：json工具类
 * @author czl
 * created at 2020/12/26 14:31
 */
object JsonUtils {
    fun getDestination(content: String?): HashMap<String, Destination> {
        return JSON.parseObject(
            content,
            object :
                TypeReference<HashMap<String?, Destination?>?>() {}.type
        )
    }

    fun getBottomBar(content: String?): BottomBar {
        return Gson().fromJson(content, BottomBar::class.java)
    }

    val array: Array<IntArray?>
        get() {
            val state = arrayOfNulls<IntArray>(2)
            state[0] = intArrayOf(R.attr.state_selected)
            state[1] = intArrayOf()
            return state
        }
}