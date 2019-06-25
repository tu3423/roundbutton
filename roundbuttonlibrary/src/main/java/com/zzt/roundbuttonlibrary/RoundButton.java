package com.zzt.roundbuttonlibrary;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public final class RoundButton extends AppCompatButton {

    //按下颜色
    private int pressedColor;
    //当前颜色
    private int normalColor;
    //当前圆角
    private float currCorner;
    // 四边宽度
    private float strokeWidth;
    // 颜色
    private int strokeColor;

    GradientDrawable gradientDrawable;

    boolean isTouchPass = true;

    public RoundButton(Context context) {
        this(context, null);
    }

    public RoundButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }


    private void init() {
        setGravity(Gravity.CENTER);
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(normalColor);
        gradientDrawable.setStroke((int) strokeWidth, strokeColor);
        gradientDrawable.setCornerRadius(currCorner);
        setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                setBackgroundDrawable(gradientDrawable);
                return setColor(event.getAction());
            }
        });
        setBackgroundDrawable(gradientDrawable);
        setPadding(getPaddingLeft() == 0 ? 20 : getPaddingLeft(), getPaddingTop() == 0 ? 10 : getPaddingTop(), getPaddingRight() == 0 ? 20 : getPaddingRight(), getPaddingBottom() == 0 ? 10 : getPaddingBottom());
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ButtonStyle);
            int color = Color.parseColor("#f1f1f1");
            normalColor = typedArray.getColor(R.styleable.ButtonStyle_normal_color, color);
            strokeWidth = typedArray.getDimension(R.styleable.ButtonStyle_stroke_width, 2);
            strokeColor = typedArray.getColor(R.styleable.ButtonStyle_stroke_color, Color.parseColor("#cecfd2"));
            pressedColor = typedArray.getColor(R.styleable.ButtonStyle_press_color, Color.WHITE);
            currCorner = typedArray.getDimension(R.styleable.ButtonStyle_corner, 5);
            typedArray.recycle();
        }
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        isTouchPass = false;
    }

    public boolean setColor(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                gradientDrawable.setColor(pressedColor);
                break;
            case MotionEvent.ACTION_UP:
                gradientDrawable.setColor(normalColor);
                break;
            case MotionEvent.ACTION_CANCEL:
                gradientDrawable.setColor(normalColor);
                break;
        }

        return isTouchPass;
    }

    /**
     * @return 获取按下颜色
     */
    public int getPressedColor() {
        return pressedColor;
    }

    /**
     * @param pressedColor 按下颜色设置
     */
    public void setPressedColor(int pressedColor) {
        this.pressedColor = getResources().getColor(pressedColor);
    }

    /**
     * @param pressedColor 设置按下颜色 例如：#ffffff
     */
    public void setPressedColor(String pressedColor) {
        this.pressedColor = Color.parseColor(pressedColor);
    }

    /**
     * @return 获取默认颜色
     */
    public int getNormalColor() {
        return normalColor;
    }

    /**
     * @param normalColor 设置默认颜色
     */
    public void setNormalColor(int normalColor) {
        this.normalColor = getResources().getColor(normalColor);
        if (gradientDrawable != null)
            gradientDrawable.setColor(this.normalColor);
    }

    /**
     * @param normalColor 设置默认颜色 例如：#ffffff
     */
    public void setNormalColor(String normalColor) {
        this.normalColor = Color.parseColor(normalColor);
        if (gradientDrawable != null)
            gradientDrawable.setColor(this.normalColor);
    }

    /**
     * @return 返回当前圆角大小
     */
    public float getCurrCorner() {
        return currCorner;
    }

    /**
     * @param currCorner 设置当前圆角
     */
    public void setCurrCorner(float currCorner) {
        this.currCorner = currCorner;
        if (gradientDrawable != null)
            gradientDrawable.setCornerRadius(currCorner);
    }

    /**
     * @return 返回边框大小
     */
    public float getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * @param strokeWidth 设置边框大小
     */
    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        if (gradientDrawable != null)
            gradientDrawable.setStroke((int) strokeWidth, this.strokeColor);
    }

    /**
     * @return 返回边框颜色
     */
    public int getStrokeColor() {
        return strokeColor;
    }

    /**
     * @param strokeColor 设置边框颜色
     */
    public void setStrokeColor(int strokeColor) {
        this.strokeColor = getResources().getColor(strokeColor);
        if (gradientDrawable != null)
            gradientDrawable.setStroke((int) strokeWidth, this.strokeColor);
    }

    /**
     * @param strokeColor 设置边框颜色 例如：#ffffff
     */
    public void setStrokeColor(String strokeColor) {
        this.strokeColor = Color.parseColor(strokeColor);
        if (gradientDrawable != null)
            gradientDrawable.setStroke((int) strokeWidth, this.strokeColor);
    }
}