package com.github.chenfeichongqing.formview.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.chenfeichongqing.formview.R;
import com.github.chenfeichongqing.formview.base.FormView;
import com.github.chenfeichongqing.formview.utils.DpUtils;


/**
 * @authoer create by cf
 * @github https://github.com/chenfeichongqing
 * 时间: 2021/09/03
 * 描述: FromRadioView
 * 单选项
 */
public class FormRadioView extends FormView<Boolean> {

    private RadioGroup rgState;

    private RadioButton rbLeft;

    private RadioButton rbRight;

    private int layoutMinWidth;

    private int textGravity;

    private Drawable leftDrawable = null;

    private Drawable topDrawable = null;

    private Drawable rightDrawable = null;

    private Drawable bottomDrawable = null;

    private int drawablePadding = 0;

    private int left2RightMargin = 0;

    private ColorStateList radioTextColor;

    private int radioTextSize;

    private RadioGroup.OnCheckedChangeListener checkedChangeListener;

    private String leftText;

    private String rightText;

    private ColorStateList tintColor;

    public void setCheckedChangeListener(RadioGroup.OnCheckedChangeListener checkedChangeListener) {
        this.checkedChangeListener = checkedChangeListener;
    }

    public FormRadioView(Context context) {
        super(context);
    }

    public FormRadioView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormRadioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @return
     */
    @Override
    public Boolean getValue() {
        return rgState.getCheckedRadioButtonId() == R.id.rb_right;
    }

    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
        Log.i("init", "FormRadioView");
        radioTextSize = DpUtils.sp2px(context, 16);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormRadioView);
            layoutMinWidth = typedArray.getDimensionPixelSize(R.styleable.FormRadioView_frv_layout_min_width, 0);
            textGravity = typedArray.getInt(R.styleable.FormRadioView_frv_text_gravity, 0);

            leftDrawable = typedArray.getDrawable(R.styleable.FormRadioView_frv_text_drawable_left);
            topDrawable = typedArray.getDrawable(R.styleable.FormRadioView_frv_text_drawable_top);
            rightDrawable = typedArray.getDrawable(R.styleable.FormRadioView_frv_text_drawable_right);
            bottomDrawable = typedArray.getDrawable(R.styleable.FormRadioView_frv_text_drawable_bottom);
            drawablePadding = typedArray.getDimensionPixelSize(R.styleable.FormRadioView_frv_text_drawable_padding, drawablePadding);

            left2RightMargin = typedArray.getDimensionPixelSize(R.styleable.FormRadioView_frv_left_to_right_margin, left2RightMargin);

            radioTextColor = typedArray.getColorStateList(R.styleable.FormRadioView_frv_text_color);
            radioTextSize = typedArray.getDimensionPixelSize(R.styleable.FormRadioView_frv_text_size, radioTextSize);
            leftText = typedArray.getString(R.styleable.FormRadioView_frv_left_text);
            rightText = typedArray.getString(R.styleable.FormRadioView_frv_right_text);
            tintColor = typedArray.getColorStateList(R.styleable.FormRadioView_frv_tint);
            typedArray.recycle();
        }
        initCustom();

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        Log.i("initView", "FormRadioView");
        rgState = rootView.findViewById(R.id.rg_state);
        rbLeft = rootView.findViewById(R.id.rb_left);
        rbRight = rootView.findViewById(R.id.rb_right);
    }

    @Override
    protected void initCustom() {
        Log.i("initCustom", "FormRadioView");
        if (layoutMinWidth != 0) {
            rgState.setMinimumWidth(layoutMinWidth);
        }
        if (textGravity == 0) {
            rbLeft.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            rbRight.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        } else {
            rbLeft.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
            rbRight.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        }
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) rbLeft.getLayoutParams();
        layoutParams.rightMargin = left2RightMargin;
        rbLeft.setLayoutParams(layoutParams);

        if (leftDrawable != null || topDrawable != null || rightDrawable != null || bottomDrawable != null) {
            rbLeft.setButtonDrawable(null);
            rbRight.setButtonDrawable(null);
            rbLeft.setCompoundDrawablePadding(drawablePadding);
            rbRight.setCompoundDrawablePadding(drawablePadding);
            rbLeft.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, topDrawable, rightDrawable, bottomDrawable);
            rbRight.setCompoundDrawablesWithIntrinsicBounds(copyDrawable(leftDrawable),
                    copyDrawable(topDrawable), copyDrawable(rightDrawable), copyDrawable(bottomDrawable));
        }
        if (radioTextColor != null) {
            rbLeft.setTextColor(radioTextColor);
            rbRight.setTextColor(radioTextColor);
        }
        rbLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, radioTextSize);
        rbRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, radioTextSize);

        rbLeft.setText(leftText);
        rbRight.setText(rightText);

        if (tintColor != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                rbLeft.setButtonTintList(tintColor);
                rbRight.setButtonTintList(tintColor);
            }
        }
    }

    @Override
    protected void initListener() {
        rgState.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedChangeListener != null) {
                    checkedChangeListener.onCheckedChanged(group, checkedId);
                }
            }
        });
    }

    public Drawable copyDrawable(Drawable drawable) {
        return drawable != null ? (drawable.getConstantState() != null ?
                drawable.getConstantState().newDrawable() : null) : null;
    }

    public RadioGroup getRgState() {
        return rgState;
    }

    public RadioButton getRbLeft() {
        return rbLeft;
    }

    public RadioButton getRbRight() {
        return rbRight;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setEnable(enabled, rbLeft);
        setEnable(enabled, rbRight);
        setEnable(enabled, rgState);
    }

    @Override
    public int layoutId() {
        return R.layout.bimfoo_form_radio_view;
    }
}
