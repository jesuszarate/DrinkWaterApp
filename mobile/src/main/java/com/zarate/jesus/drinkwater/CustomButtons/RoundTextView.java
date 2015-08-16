package com.zarate.jesus.drinkwater.CustomButtons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

import com.zarate.jesus.drinkwater.User;

import java.text.DecimalFormat;

/**
 * Created by Jesus Zarate on 7/30/15.
 */
public class RoundTextView extends View
{
    Paint _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF _contentRect = new RectF();
    Rect _textBounds = new Rect();
    DecimalFormat decimalFormat = new DecimalFormat("####0.00");

    public RoundTextView(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        _contentRect.left = getPaddingLeft();
        _contentRect.top = getPaddingTop();
        _contentRect.right = getWidth() - getPaddingRight();
        _contentRect.bottom = getHeight() - getPaddingBottom();

        // Total water consumption indicator
        _paint.setColor(Color.WHITE);
        _paint.setTextSize(100);
        _paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        String text = User.getInstance().getTotalWaterConsumption() + " oz";
        _paint.getTextBounds(text, 0, text.length(), _textBounds);

        canvas.drawText(text,
                _contentRect.centerX() - _textBounds.exactCenterX(),
                _contentRect.centerY() - _textBounds.exactCenterY(),
                _paint);

        // Water percentage indicator
        _paint.setColor(Color.TRANSPARENT);
        int radius = 150;
        float x = _contentRect.right/2;
        float y = _contentRect.top + radius;
        canvas.drawCircle(x, y, radius, _paint);

        text = decimalFormat.format(User.getInstance().getTotalWaterPercentage()) + "%";
        _paint.setTextSize(70);
        _paint.getTextBounds(text, 0, text.length(), _textBounds);
        _paint.setColor(Color.WHITE);
        canvas.drawText(text, x - _textBounds.exactCenterX(), y - _textBounds.exactCenterY(), _paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if(widthMeasureSpec > heightMeasureSpec)
            widthMeasureSpec = heightMeasureSpec;
        else
            heightMeasureSpec = widthMeasureSpec;

        widthMeasureSpec = widthMeasureSpec - 100;
        heightMeasureSpec = heightMeasureSpec - 100;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
