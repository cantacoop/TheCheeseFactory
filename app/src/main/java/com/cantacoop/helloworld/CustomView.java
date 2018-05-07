package com.cantacoop.helloworld;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

    private Boolean isBlue = false;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initWithAttrs(attrs, 0, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomView,
                defStyleAttr, defStyleRes
        );

        try {
            isBlue = a.getBoolean(R.styleable.CustomView_isBlue, false);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        if (isBlue)
            p.setColor(0xFF0000FF); // Blue
        else
            p.setColor(0xFFFF0000); // Red
        canvas.drawLine(0, 0,
                getMeasuredWidth(), getMeasuredHeight(),
                p);
    }
}
