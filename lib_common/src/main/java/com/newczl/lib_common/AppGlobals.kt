package com.newczl.lib_common

import android.annotation.SuppressLint
import android.app.Application
/**
* AppGlobals.kt 类：全局获取application
* @author czl
* created at 2020/12/26 11:22
*/
object AppGlobals {
    //全局application
     var sApplication:Application? = null
         @SuppressLint("DiscouragedPrivateApi", "PrivateApi")
         get() {
            if(field == null){
                val declaredMethod = Class.forName("android.app.ActivityThread")
                    .getDeclaredMethod("currentApplication")
                declaredMethod.isAccessible = true
                sApplication = declaredMethod.invoke(null) as Application
            }
             return field;
         }
}