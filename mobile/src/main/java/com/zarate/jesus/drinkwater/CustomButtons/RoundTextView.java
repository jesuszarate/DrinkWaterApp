package com.zarate.jesus.drinkwater.CustomButtons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

import com.zarate.jesus.drinkwater.User;
import com.zarate.jesus.drinkwater.WaterConsumption;

/**
 * Created by Jesus Zarate on 7/30/15.
 */
public class RoundTextView extends View
{
    public RoundTextView(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);

        RectF contentRect = new RectF();
        contentRect.left = getPaddingLeft();
        contentRect.top = getPaddingTop();
        contentRect.right = getWidth() - getPaddingRight();
        contentRect.bottom = getHeight() - getPaddingBottom();

        //paint.setShadowLayer(10, contentRect.centerX(), contentRect.centerY(), Color.GRAY);

        //canvas.drawCircle(contentRect.centerX(), contentRect.centerY(), 20, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        String text = User.getInstance().getTotalWaterPercentage() + "";

        canvas.drawText(text,
                calculateCenterX(text, paint.getTextSize()),
                calculateCenterY(text, paint.getTextSize()),
                paint);
    }

    private float calculateCenterX(String s, float textSize)
    {
        float centerOfString = (float)Math.abs(s.length() / 3.8);
        float centerOfText = (textSize * centerOfString);

        int center = (getWidth() / 2);

        return center - centerOfText;
    }

    private float calculateCenterY(String s, float textSize)
    {
        //float centerOfString = Math.abs(s.length() / 2);
        float centerOfText = (float)(textSize/3);

        int center = (getHeight() / 2);

        return center + centerOfText;
    }
}
