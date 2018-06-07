package com.ebay.weather.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent


class NoScrolledViewPager : ViewPager {

    private var isPagingEnable = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isPagingEnable && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return isPagingEnable && super.onInterceptTouchEvent(ev)
    }

    fun setPagingEnable(pagingEnable: Boolean) {
        isPagingEnable = pagingEnable
    }
}