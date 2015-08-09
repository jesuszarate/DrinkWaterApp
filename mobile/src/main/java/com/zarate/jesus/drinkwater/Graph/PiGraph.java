package com.zarate.jesus.drinkwater.Graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Jesus Zarate on 8/9/15.
 */
public class PiGraph extends View
{
    Paint _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public PiGraph(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        float height = (getHeight() / 2);
        float width = (getWidth() / 2);

        RectF rectF = new RectF();
        rectF.left = getPaddingLeft();
        rectF.top = getPaddingTop();
        rectF.right = getWidth() - getPaddingRight();
        rectF.bottom = getWidth();

        int[] colors = new int[]{Color.BLACK, Color.BLUE, Color.YELLOW, Color.GREEN, Color.GRAY,
                Color.MAGENTA, Color.RED};
        float sumAngle = 0;
        float currentAngle = 360/6;
        for(int i = 0; i < 7; i++)
        {
            _paint.setColor(colors[i]);
            canvas.drawArc(rectF, sumAngle, currentAngle, true, _paint);
            sumAngle += currentAngle;
        }
    }
}