package com.zarate.jesus.drinkwater.Graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

import java.util.HashMap;

/**
 * Created by Jesus Zarate on 8/8/15.
 */
public class GraphView extends View
{
    Paint _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF _contentRect = new RectF();

    HashMap<Integer, Float> _graphPointPosition = new HashMap<>();

    public GraphView(Context context)
    {
        super(context);

        setBackgroundColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        _paint.setColor(Color.BLUE);

        _contentRect.left = getPaddingLeft();
        _contentRect.top = getPaddingTop();
        _contentRect.right = getWidth() - getPaddingRight();
        _contentRect.bottom = getHeight() - getPaddingBottom();

        _paint.setColor(Color.WHITE);
        _paint.setTextSize(50);
        _paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        int padding = 10;
        int graphHeight = 10;
        int graphWidth = 10;
        int heightSpacing = getHeight() / graphHeight;
        int widthSpacing = getWidth() / graphWidth;

        for(int i = 0; i < graphHeight; i++)
        {
            canvas.drawText(graphHeight - i + "",
                    padding,
                    i * heightSpacing,
                    _paint);
        }
        for(int i = 0; i < graphHeight; i++)
        {
            canvas.drawText(i + "",
                    (i * widthSpacing) + padding,
                    (graphHeight * heightSpacing),
                    _paint);
        }
    }
}
