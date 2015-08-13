package com.zarate.jesus.drinkwater.Graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;

import com.zarate.jesus.drinkwater.User;
import com.zarate.jesus.drinkwater.WaterConsumptionHistory.Day;
import com.zarate.jesus.drinkwater.WaterConsumptionHistory.WaterConsumptionHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 *
 *
 * Created by Jesus Zarate on 8/8/15.
 */
public class GraphView extends View
{
    private Paint _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF _contentRect = new RectF();
    private Point _point;
    private Rect _textBounds = new Rect();
    private int _yPadding = 50;
    private String[] _days = new String[]{"", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private GraphPoints _graphPoints;

    public GraphView(Context context)
    {
        super(context);

        setBackgroundColor(Color.parseColor("#009788"));
        _graphPoints = new GraphPoints();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        _contentRect.left = getPaddingLeft();
        _contentRect.top = getPaddingTop();
        _contentRect.right = getWidth() - getPaddingRight();
        _contentRect.bottom = getHeight() - getPaddingBottom();

        _paint.setColor(Color.WHITE);
        _paint.setTextSize(50);
        _paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        int padding = 10;
        int graphHeight = 10;
        int graphWidth = 8;
        int heightSpacing = getHeight() / graphHeight;
        int widthSpacing = getWidth() / graphWidth;

        int waterAmount = User.getInstance().getTotalWaterNeeded();
        int increaseAmount = waterAmount / 10;

        for (int i = 0; i < graphHeight; i++)
        {
            _point = new Point();
            _point.setX(padding);
            _point.setY(i * heightSpacing);

            _graphPoints.getyPoints().put(i, _point);
            _graphPoints.getY().put(waterAmount, (float)(i * heightSpacing));

            canvas.drawText(waterAmount+"",
                    _point.getX(),
                    _point.getY() + _yPadding,
                    _paint);
            waterAmount -= increaseAmount;
        }

        for (int i = 0; i < 8; i++)
        {
            _point = new Point();
            _point.setX((i * widthSpacing) + (padding));
            _point.setY((graphHeight * heightSpacing));


            _graphPoints.getxPoints().put(i, _point);

            canvas.drawText(_days[i],
                    _point.getX() - 30,
                    _point.getY(),
                    _paint);
        }

        //Todo: May want to change this to a list instead of a hashmap
        ArrayList<Day> week = WaterConsumptionHistory.getInstance().getCurrentWeek();

        if(week != null)
        {
            for (Day day : week)
            {
                int radius = 35;

                drawPoint(canvas, radius, day);
            }
        }
    }

    public void drawPoint(Canvas canvas, int radius, Day day)
    {
        try
        {
            int waterAmount = day.waterAmount;

            int x = day.dayOfWeek + 1; // Plus one for alignment purposes.

            x = (int) _graphPoints.getxPoints().get(x).getX();
            float y = _graphPoints.getY().get(waterAmount) + _yPadding;

            _paint.setColor(Color.WHITE);
            canvas.drawCircle(x, y, radius, _paint);

            String text = waterAmount + "";
            _paint.getTextBounds(text, 0, text.length(), _textBounds);
            _paint.setColor(Color.BLACK);
            canvas.drawText(text, x - _textBounds.exactCenterX(), y - _textBounds.exactCenterY(), _paint);
        }
        catch (Exception e)
        {
            Log.e("drawPoint Error: ", e.toString());
        }
    }
}










