package com.cf.lib.formview.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.cf.lib.formview.R;
import com.cf.lib.formview.base.FormView;


/**
 * @authoer create by cf
 * @github https://github.com/chenfeichongqing
 * 时间: 2021/09/03
 * 表单实现基类
 * 编写方式按照此类方式编写
 */
public class FormBaseView extends FormView<String> {

    public FormBaseView(Context context) {
        super(context);
    }

    public FormBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
    }

    @Override
    public int layoutId() {
        return R.layout.bimfoo_form_base_view;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    protected void initCustom() {

    }
}
