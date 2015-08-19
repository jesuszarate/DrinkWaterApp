package com.zarate.jesus.drinkwater;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.LinearLayout;

/**
 * Created by Jesus Zarate on 7/26/15.
 */
public class PaintWater extends LinearLayout
{
    double WATER_AMOUNT = 0;
    private Rect _textBounds = new Rect();

    public interface OnWaterTouchListener
    {
        public void onWaterTouched(PaintWater paintWater);
    }

    OnWaterTouchListener onWaterTouchListener = null;

    public OnWaterTouchListener getOnWaterTouchListener()
    {
        return onWaterTouchListener;
    }

    public void setOnWaterTouchListener(OnWaterTouchListener onWaterTouchListener)
    {
        this.onWaterTouchListener = onWaterTouchListener;
    }

    public PaintWater(Context context)
    {
        super(context);
        setFocusable(true);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);

        setBackground(new drawable());
    }

    public void addWater()
    {
        if (User.getInstance().addWater(getHeight()))
            invalidate();
    }

    public void removeWater()
    {
       if(User.getInstance().removeWater(getHeight()))
           invalidate();
    }

    private class drawable extends Drawable
    {

        public void draw(Canvas canvas)
        {
            Paint paint = new Paint();
            paint.setStrokeWidth(5.0f);
            paint.setColor(Color.parseColor("#80DEEA"));
            //polylinePaint.setColor(Color.parseColor("#88FFFF"));

            RectF area = new RectF();
            area.left = (getPaddingLeft());
            area.top = (int)(getHeight() - (User.getInstance().getTotalWaterConsumptionFill()));
            area.right = ((getWidth() - getPaddingRight()));
            area.bottom = ((getHeight() - getPaddingBottom()));

            canvas.drawRect(area.left, area.top, area.right, area.bottom, paint);

            paint.setColor(Color.parseColor("#009788"));
            float x = area.left + 50;
            float y = area.top;
            int radius = 50;


            // Ensures that the circle indicator doesn't go out of bounds
            if(y >= getHeight())
                y = y - radius;
            if(y <= 0)
                y = radius;

            canvas.drawCircle(x, y, radius, paint);

            String line = "5";
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            paint.getTextBounds("5", 0, line.length(), _textBounds);

            canvas.drawText(line,
                    x - _textBounds.exactCenterX(),
                    y - _textBounds.exactCenterY(),
                    paint);

        }

        @Override
        public void setAlpha(int alpha)
        {

        }

        @Override
        public void setColorFilter(ColorFilter cf)
        {

        }

        @Override
        public int getOpacity()
        {
            return 0;
        }

    }

}

