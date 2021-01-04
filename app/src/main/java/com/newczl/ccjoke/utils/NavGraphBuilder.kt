package com.newczl.ccjoke.utils

import android.content.ComponentName
import androidx.fragment.app.FragmentActivity
import androidx.navigation.*
import com.newczl.ccjoke.navigator.FixFragmentNavigator

object NavGraphBuilder {
    fun build(controller: NavController, fragmentActivity: FragmentActivity, containerId: Int) {
        val provider = controller.navigatorProvider
        //val fragmentNavigator = provider.getNavigator(FragmentNavigator::class.java)
        //自定义导航器
        val fragmentNavigator = FixFragmentNavigator(
            fragmentActivity,
            fragmentActivity.supportFragmentManager,
            containerId
        )
        provider.addNavigator(fragmentNavigator)

        val activityNavigator = provider.getNavigator(ActivityNavigator::class.java)
        val navGraph = NavGraph(NavGraphNavigator(provider))
        AppConfig.sDestConfig?.values?.forEach {
            if (it.isFragment) {
                val destination = fragmentNavigator.createDestination()
                destination.className = it.className
                destination.id = it.id
                destination.addDeepLink(it.pageUrl)
                //加入节点
                navGraph.addDestination(destination)
            } else {
                val destination = activityNavigator.createDestination()
                destination.id = it.id
                destination.addDeepLink(it.pageUrl)
                destination.setComponentName(
                    ComponentName(
                        com.newczl.lib_common.AppGlobals.sApplication!!.packageName,
                        it.className
                    )
                )
                //加入节点
                navGraph.addDestination(destination)
            }
            if (it.asStarter) {
                navGraph.startDestination = it.id
            }
        }
        //设置
        controller.graph = navGraph
    }
}