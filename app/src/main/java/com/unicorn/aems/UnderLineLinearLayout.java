package com.unicorn.aems;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ConvertUtils;

public class UnderLineLinearLayout extends LinearLayout {

    public UnderLineLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        initPaint();
    }

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int colorFocused = ContextCompat.getColor(getContext(), R.color.colorPrimary);
    private int colorUnFocused = ContextCompat.getColor(getContext(), R.color.md_grey_300);

    private void initPaint() {
        paint.setStrokeWidth(ConvertUtils.dp2px(2f));
        paint.setColor(colorUnFocused);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUnderLine(canvas);
    }

    private void drawUnderLine(Canvas canvas) {
        canvas.drawLine(0, getHeight(), getWidth(), getHeight(), paint);
    }

    public void focus() {
        paint.setColor(colorFocused);
        invalidate();
    }

    public void unFocus() {
        paint.setColor(colorUnFocused);
        invalidate();
    }

    public void onFocusChange(boolean hasFocus) {
        if (hasFocus) {
            focus();
        } else {
            unFocus();
        }
    }

}
