package com.newczl.libnavannotation

@Target(AnnotationTarget.CLASS)
annotation class FragmentDestination(val pageUrl:String,
                                     val needLogin:Boolean = false,
                                     val asStart:Boolean = false)