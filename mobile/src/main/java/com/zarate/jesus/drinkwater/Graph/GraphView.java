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

    public GraphView(Context context)
    {
        super(context);

        //setBackgroundColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        _contentRect.left = getPaddingLeft();
        _contentRect.top = getPaddingTop();
        _contentRect.right = getWidth() - getPaddingRight();
        _contentRect.bottom = getHeight() - getPaddingBottom();

        _paint.setColor(Color.BLACK);
        _paint.setTextSize(50);
        _paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        int padding = 10;
        int graphHeight = 10;
        int graphWidth = 10;
        int heightSpacing = getHeight() / graphHeight;
        int widthSpacing = getWidth() / graphWidth;

        for (int i = 0; i < graphHeight; i++)
        {
            Point point = new Point();
            point.setX(padding);
            point.setY(i * heightSpacing);

            GraphPoints.getInstance().getyPoints().put(i, point);

            canvas.drawText(graphHeight - i + "",
                    point.getX(),
                    point.getY(),
                    _paint);
        }
        for (int i = 0; i < graphHeight; i++)
        {
            Point point = new Point();
            point.setX((i * widthSpacing) + padding);
            point.setY((graphHeight * heightSpacing));

            GraphPoints.getInstance().getxPoints().put(i, point);

            canvas.drawText(i + "",
                    point.getX(),
                    point.getY(),
                    _paint);
        }

        // Graph point
        int x = 1;
        int y = graphHeight - 2; // This i equal to the point 2
        int radius = 20;

        x = (int) GraphPoints.getInstance().getxPoints().get(x).getX();
        y = (int) GraphPoints.getInstance().getyPoints().get(y).getY();

        canvas.drawCircle(x, y, radius, _paint);

        // Graph point
        x = 3;
        y = graphHeight - 6; // This i equal to the point 2
        radius = 20;

        x = (int) GraphPoints.getInstance().getxPoints().get(x).getX();
        y = (int) GraphPoints.getInstance().getyPoints().get(y).getY();

        canvas.drawCircle(x, y, radius, _paint);

        // Graph point
        x = 2;
        y = graphHeight - 5; // This i equal to the point 2
        radius = 20;

        x = (int) GraphPoints.getInstance().getxPoints().get(x).getX();
        y = (int) GraphPoints.getInstance().getyPoints().get(y).getY();

        canvas.drawCircle(x, y, radius, _paint);
    }
}










