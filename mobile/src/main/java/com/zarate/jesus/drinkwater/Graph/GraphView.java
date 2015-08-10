package com.zarate.jesus.drinkwater.Graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

import com.zarate.jesus.drinkwater.User;

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
    private String[] _days = new String[]{"", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    public GraphView(Context context)
    {
        super(context);

        setBackgroundColor(Color.parseColor("#009788"));
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
        int increaseAmount = waterAmount / 10;//User.getInstance().getCupSize();

        for (int i = 0; i < graphHeight; i++)
        {
            _point = new Point();
            _point.setX(padding);
            _point.setY(i * heightSpacing);

            GraphPoints.getInstance().getyPoints().put(i, _point);

            canvas.drawText(waterAmount+"",
                    _point.getX(),
                    _point.getY() + 50,
                    _paint);
            waterAmount -= increaseAmount;
        }

        for (int i = 0; i < 8; i++)
        {
            _point = new Point();
            _point.setX((i * widthSpacing) + (padding));
            _point.setY((graphHeight * heightSpacing));

            GraphPoints.getInstance().getxPoints().put(i, _point);

            canvas.drawText(_days[i],
                    _point.getX() - 30,
                    _point.getY(),
                    _paint);
        }

        // Graph _point
        int x = 1;
        int y = graphHeight - 2; // This is equal to the _point 2
        int radius = 35;

        x = (int) GraphPoints.getInstance().getxPoints().get(x).getX();
        y = (int) GraphPoints.getInstance().getyPoints().get(y).getY();


        canvas.drawCircle(x, y, radius, _paint);
        _paint.setTextSize(25);
        _paint.setColor(Color.BLACK);

        String text = "29";
        _paint.getTextBounds(text, 0, text.length(), _textBounds);
        canvas.drawText(text, x - _textBounds.exactCenterX(), y - _textBounds.exactCenterY(), _paint);

        // Graph _point
        x = 3;
        y = graphHeight - 6; // This i equal to the _point 2
        radius = 20;

        x = (int) GraphPoints.getInstance().getxPoints().get(x).getX();
        y = (int) GraphPoints.getInstance().getyPoints().get(y).getY();

        canvas.drawCircle(x, y, radius, _paint);

        // Graph _point
        x = 2;
        y = graphHeight - 5; // This i equal to the _point 2
        radius = 20;

        x = (int) GraphPoints.getInstance().getxPoints().get(x).getX();
        y = (int) GraphPoints.getInstance().getyPoints().get(y).getY();

        canvas.drawCircle(x, y, radius, _paint);
    }

}










