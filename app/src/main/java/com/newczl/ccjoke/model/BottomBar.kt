package com.newczl.ccjoke.model

data class BottomBar(
    val activeColor: String,
    val inActiveColor: String,
    val selectTab: Int,
    val tabs: List<Tab>
){
    inner class Tab(
        val enable: Boolean,
        val index: Int,
        val pageUrl: String,
        val size: Int,
        val tintColor: String,
        val title: String
    )
}

