package com.newczl.ccjoke.ui.view


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.newczl.ccjoke.R
import com.newczl.ccjoke.ext.dp2px
import com.newczl.ccjoke.utils.AppConfig
import com.newczl.ccjoke.utils.JsonUtils

/**
 * AppBottomBar.kt 类：自定义导航栏
 * @author czl
 * created at 2020/12/27 18:06
 */
@SuppressLint("RestrictedApi")
class AppBottomBar : BottomNavigationView {
    companion object {
        private val sIcons = intArrayOf(
            R.drawable.icon_tab_home,
            R.drawable.icon_tab_sofa,
            R.drawable.icon_tab_publish,
            R.drawable.icon_tab_find,
            R.drawable.icon_tab_mine
        )
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val bottomBar = AppConfig.sBottomBar
        bottomBar?.apply {
            val tabs = tabs
            val states = JsonUtils.array
            val colors = intArrayOf(
                Color.parseColor(activeColor),
                Color.parseColor(inActiveColor)
            )
            val colorStateList = ColorStateList(states, colors)
            //设置
            itemIconTintList = colorStateList
            itemTextColor = colorStateList
            //设置模式  4种模式
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
            //默认选中按钮
            selectedItemId = selectTab

            //遍历tabs集合，一个一个添加到bottomBar上面去
            tabs.forEach continuing@{ tab ->
                if (!tab.enable) {
                    return@continuing
                }
                val id = getId(tab.pageUrl)
                if (id < 0) {
                    return@continuing
                }
                val item = menu.add(0, id, tab.index, tab.title)
                item.setIcon(sIcons[tab.index])
            }
            //改变大小必须在所有按钮都添加至导航栏后，在重新来一次for循环

                tabs.forEach continuing@{ tab ->
                    if (!tab.enable) {
                        return@continuing
                    }
                    val id = getId(tab.pageUrl)
                    if (id < 0) {
                        return@continuing
                    }
                    val iconSize = context.dp2px(tab.size.toFloat())

                    val menuView = getChildAt(0) as BottomNavigationMenuView?

                    val itemView = menuView!!.getChildAt(tab.index) as BottomNavigationItemView?
                    itemView?.setIconSize(iconSize)

                    if (tab.title.isEmpty()) {
                        itemView?.setIconTintList(ColorStateList.valueOf(Color.parseColor(tab.tintColor)))
                        itemView?.setShifting(false)
                    }
                }



        }
    }

    private fun getId(pageUrl: String):Int{
        val destination = AppConfig.sDestConfig?.get(pageUrl) ?: return -1
        return destination.id
    }

}