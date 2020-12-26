package com.newczl.ccjoke.model

/**
* Destination.kt 类：节点Model
* @author czl
* created at 2020/12/26 10:50
*/
data class Destination (
    val asStarter: Boolean,
    val className: String,
    val id: Int,
    val isFragment: Boolean,
    val needLogin: Boolean,
    val pageUrl: String
)