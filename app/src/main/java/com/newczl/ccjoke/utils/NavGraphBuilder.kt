package com.newczl.ccjoke.utils

import android.content.ComponentName
import androidx.navigation.*
import androidx.navigation.fragment.FragmentNavigator

object NavGraphBuilder {
    fun build(controller:NavController){
        val provider = controller.navigatorProvider
        val fragmentNavigator = provider.getNavigator(FragmentNavigator::class.java)
        val activityNavigator = provider.getNavigator(ActivityNavigator::class.java)
        val navGraph = NavGraph(NavGraphNavigator(provider))
        AppConfig.sDestConfig?.values?.forEach {
            if(it.isFragment){
                val destination = fragmentNavigator.createDestination()
                destination.className = it.className
                destination.id = it.id
                destination.addDeepLink(it.pageUrl)
                //加入节点
                navGraph.addDestination(destination)
            }else{
                val destination = activityNavigator.createDestination()
                destination.id = it.id
                destination.addDeepLink(it.pageUrl)
                destination.setComponentName(ComponentName(AppGlobals.sApplication!!.packageName,it.className))
                //加入节点
                navGraph.addDestination(destination)
            }
            if(it.asStarter){
                navGraph.startDestination = it.id
            }
        }
        //设置
        controller.graph = navGraph
    }
}