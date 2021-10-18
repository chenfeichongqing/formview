package com.cf.lib.formview.base;

import android.view.View;

/**
 * @authoer create by cf
 * @github https://github.com/chenfeichongqing
 * 时间: 2021/09/03
 * 描述: 定义表单方法
 */
public interface FormViewInterface {
    int layoutId();

    /**
     * 根据每个FormView初始化视图
     *
     * @param rootView
     */
    void initView(View rootView);
}
