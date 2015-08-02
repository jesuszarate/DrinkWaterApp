package com.zarate.jesus.drinkwater.CustomButtons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import com.zarate.jesus.drinkwater.WaterConsumption;

/**
 * Created by Jesus Zarate on 7/30/15.
 */
public class RoundButton extends View
{
    public RoundButton(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);

        RectF _contentRect = new RectF();
        _contentRect.left = getPaddingLeft();
        _contentRect.top = getPaddingTop();
        _contentRect.right = getWidth() - getPaddingRight();
        _contentRect.bottom = getHeight() - getPaddingBottom();

        canvas.drawOval(_contentRect, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(50);

        // TODO: FIX THE TEXT SO THAT THE TEXT CAN ALWAYS BE CENTERED
        canvas.drawText(WaterConsumption.getInstance().getTotalWaterPercentage()+"", _contentRect.centerX() - 50,
                (_contentRect.centerY()), paint);

    }
}
