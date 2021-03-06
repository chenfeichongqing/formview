package com.github.chenfeichongqing.formview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.github.chenfeichongqing.formview.R;
import com.github.chenfeichongqing.formview.base.FormView;
import com.github.chenfeichongqing.formview.utils.DpUtils;

/**
 * @authoer create by cf
 * @github https://github.com/chenfeichongqing
 * 时间: 2021/09/03
 * 描述: 右侧输入框
 */
public class FormEditView extends FormView<String> {

    protected EditText etContent;

    private TextView tvUnit;
    //输入内容类型
    private int inputType;
    protected int maxLength;

    private String hintText;

    private int hintTextColor = Color.LTGRAY;

    private String text;

    //内容宽度
    protected int textWidth = -2;

    private int textSize;

    private int textColor = Color.BLACK;

    private int textRightMargin = 0;

    private int textBg;

    private int textGravity = 1;

    private int textLines = 1;

    private int unitColor = Color.BLACK;

    private int unitTextSize = 16;

    private boolean unitVisible = false;

    private String unitText;

    private int unitRightMargin = 0;

    private boolean enable;

    private TextWatcher textWatcher;


    public FormEditView(Context context) {
        super(context);
    }

    public FormEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextWatcher(TextWatcher textWatcher) {
        this.textWatcher = textWatcher;
    }

    @Override
    public int layoutId() {
        return R.layout.bimfoo_form_edit_view;
    }

    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
        textSize = DpUtils.sp2px(context, 16);
        unitTextSize = DpUtils.sp2px(context, 16);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormEditView);

            hintText = typedArray.getString(R.styleable.FormEditView_fev_hint);
            hintTextColor = typedArray.getColor(R.styleable.FormEditView_fev_hint_color, Color.LTGRAY);
            textColor = typedArray.getColor(R.styleable.FormEditView_fev_text_color, Color.BLACK);
            textSize = typedArray.getDimensionPixelSize(R.styleable.FormEditView_fev_text_size, textSize);
            maxLength = typedArray.getInt(R.styleable.FormEditView_fev_text_max_length, -1);
            inputType = typedArray.getInt(R.styleable.FormEditView_fev_text_inputType, 0);
            textRightMargin = typedArray.getDimensionPixelSize(R.styleable.FormEditView_fev_text_right_margin, 0);
            text = typedArray.getString(R.styleable.FormEditView_fev_text);
            textWidth = typedArray.getInt(R.styleable.FormEditView_fev_text_width, 0);
            textBg = typedArray.getResourceId(R.styleable.FormEditView_fev_text_bg, -1);
            textGravity = typedArray.getInt(R.styleable.FormEditView_fev_text_gravity, 2);
            textLines = typedArray.getInt(R.styleable.FormEditView_fev_text_lines, 1);
            unitColor = typedArray.getColor(R.styleable.FormEditView_fev_unit_color, Color.BLACK);
            unitTextSize = typedArray.getDimensionPixelSize(R.styleable.FormEditView_fev_unit_text_size, unitTextSize);
            unitVisible = typedArray.getBoolean(R.styleable.FormEditView_fev_unit_visible, false);
            unitText = typedArray.getString(R.styleable.FormEditView_fev_unit_text);
            unitRightMargin = typedArray.getDimensionPixelSize(R.styleable.FormEditView_fev_unit_right_margin, 0);
            enable = typedArray.getBoolean(R.styleable.FormEditView_fev_edit, true);

            typedArray.recycle();
        }
        initContent();
        initTvUnit();
        initCustom();
    }

    @Override
    protected void initCustom() {

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        etContent = rootView.findViewById(R.id.et_content);
        tvUnit = rootView.findViewById(R.id.tv_unit);
    }

    protected void initContent() {
        setRightMargin(etContent, textRightMargin);
        setContentLayout();
        etContent.setHint(hintText);
        setText(text);
        etContent.setHintTextColor(hintTextColor);
        etContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        etContent.setTextColor(textColor);

        setInputType(inputType);
        setTextGravity(textGravity);
        etContent.setLines(textLines);
        if (textWatcher != null) {
            etContent.addTextChangedListener(textWatcher);
        }
        //setEnable(enable, etContent);
        if (!enable) {
            etContent.setFocusable(enable);
            etContent.setCursorVisible(enable);
            //etContent.setTextIsSelectable(enable);
        }
        etContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enable) {
                    etContent.setFocusable(enable);
                    etContent.setCursorVisible(enable);
                    etContent.setTextIsSelectable(enable);
                }
            }
        });
    }

    protected void setContentLayout() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) etContent.getLayoutParams();
        layoutParams.width = textWidth;
        if (textWidth == 0) {
            layoutParams.weight = 1;
        } else {
            layoutParams.weight = 0;
        }
        etContent.setLayoutParams(layoutParams);
    }

    public void setTextGravity(int textGravity) {
        switch (textGravity) {
            default:
            case 0:
                etContent.setGravity(Gravity.TOP | Gravity.LEFT);
                break;
            case 1:
                etContent.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                break;
            case 2:
                etContent.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                break;
            case 3:
                etContent.setGravity(Gravity.BOTTOM);
                break;
        }
    }

    private void setInputType(int inputType) {
        this.inputType = inputType;
        switch (inputType) {
            default:
                etContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                if (maxLength > 0) {
                    etContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                }
                break;
            case 1:
                etContent.setInputType(InputType.TYPE_CLASS_PHONE);
                etContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
                break;
            case 2:
                etContent.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case 3:
                etContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)});
                etContent.setKeyListener(new NumberKeyListener() {
                    @Override
                    public int getInputType() {
                        return InputType.TYPE_MASK_VARIATION;
                    }

                    /**这里实现字符串过滤，把你允许输入的字母添加到下面的数组即可！*/
                    @Override
                    protected char[] getAcceptedChars() {
                        return new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'x', 'X'};
                    }
                });
                break;
            case 4:
                etContent.setInputType(InputType.TYPE_CLASS_NUMBER);
                if (maxLength > 0) {
                    etContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                }
                break;
            case 5:
                etContent.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                if (maxLength > 0) {
                    etContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                }
                break;
        }
    }

    protected void initTvUnit() {
        setRightMargin(tvUnit, unitRightMargin);
        tvUnit.setVisibility(unitVisible ? View.VISIBLE : View.GONE);
        tvUnit.setTextColor(unitColor);
        tvUnit.setTextSize(TypedValue.COMPLEX_UNIT_PX, unitTextSize);
        setUnitText(unitText);
    }

    public void setText(@StringRes int resid) {
        setText(getContext().getResources().getString(resid));
    }

    public void setText(String text) {
        this.text = text;
        if (!TextUtils.isEmpty(text)) {
            etContent.setText(text);
        }
        etContent.setSelection(etContent.getText().toString().length());
        if (textBg != -1 && !TextUtils.isEmpty(text)) {
            //防止给定的背景有内间距。没内容时还显示背景
            etContent.setBackgroundResource(textBg);
        } else {
            etContent.setBackground(null);
        }
    }

    public void setTextBg(Drawable drawable) {
        if (drawable != null && !TextUtils.isEmpty(text)) {
            //防止给定的背景有内间距。没内容时还显示背景
            etContent.setBackground(drawable);
        } else {
            etContent.setBackground(null);
        }
    }

    public void setTextColor(int resColor) {
        etContent.setTextColor(resColor);
    }

    public void setUnitText(String unitText) {
        tvUnit.setVisibility(View.VISIBLE);
        tvUnit.setText(unitText);
    }

    public EditText getContent() {
        return etContent;
    }

    public TextView gettvUnit() {
        return tvUnit;
    }

    @Override
    public String getValue() {
        if (etContent == null) {
            return null;
        }
        return etContent.getText().toString().trim();
    }

    public void setHint(String string) {
        hintText = string;
        etContent.setHint(hintText);
    }

    public void setEdit(boolean enable){
        etContent.setClickable(enable);
        etContent.setEnabled(enable);
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
