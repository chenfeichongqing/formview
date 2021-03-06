package com.github.chenfeichongqing.formview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.chenfeichongqing.formview.R;
import com.github.chenfeichongqing.formview.base.FormView;


/**
 * @authoer create by cf
 * @github https://github.com/chenfeichongqing
 * 时间: 2021/09/03
 * 描述: FormImageView
 */
public class FormImageView extends FormView<String> {

    private int contentSrc;

    private ImageView ivContent;

    public FormImageView(Context context) {
        super(context);
    }

    public FormImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int layoutId() {
        return R.layout.bimfoo_form_image_view;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormImageView);
            contentSrc = typedArray.getResourceId(R.styleable.FormImageView_fiv_src, 0);
            typedArray.recycle();
        }
        if (contentSrc != 0) {
            ivContent.setImageDrawable(context.getResources().getDrawable(contentSrc));
        }
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        ivContent = rootView.findViewById(R.id.iv_content);
    }
    public ImageView getIvContent() {
        return ivContent;
    }

    @Override
    protected void initCustom() {

    }
}

