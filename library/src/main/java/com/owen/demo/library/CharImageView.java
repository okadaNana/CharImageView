package com.owen.demo.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;


public class CharImageView extends ImageView {

    private static final int DEFAULT_SIZE = 50;
    private static final int DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final int DEFAULT_TEXT_SIZE = 16;

    private int mBackgroundColor;
    private String mText;
    private int mTextColor;
    private int mTextSize;

    private int mWidth;
    private int mHeight;
    private int mRadius;
    private Paint mBackgroundPaint;
    private Paint mTextPaint;

    public CharImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CharImageView);
        try {
            mBackgroundColor = typedArray.getColor(R.styleable.CharImageView_civ_background_color, DEFAULT_BACKGROUND_COLOR);
            mText = typedArray.getString(R.styleable.CharImageView_civ_text);
            mTextColor = typedArray.getColor(R.styleable.CharImageView_civ_text_color, DEFAULT_TEXT_COLOR);
            mTextSize = typedArray.getDimensionPixelSize(R.styleable.CharImageView_civ_text_size, DEFAULT_SIZE);
        } finally {
            typedArray.recycle();
        }

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(mBackgroundColor);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec));
    }

    private int measureSize(int widthMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (MeasureSpec.EXACTLY == specMode) {
            result = specSize;
        } else {
            result = DEFAULT_SIZE;
            if (MeasureSpec.AT_MOST == specMode) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = Math.min(mWidth, mHeight) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (TextUtils.isEmpty(mText)) {
            super.onDraw(canvas);
            return;
        }

        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mBackgroundPaint);
        canvas.drawText(mText, (mWidth - getTextWidth()) / 2, (mHeight + getTextHeight()) / 2,  mTextPaint);
    }

    private float getTextWidth() {
        return mTextPaint.measureText(mText);
    }

    private float getTextHeight() {
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        return (fm.descent - fm.ascent);
    }

    public void setText(String text) {
        mText = text;
        invalidate();
    }
}
