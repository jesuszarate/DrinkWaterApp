package com.zarate.jesus.drinkwater;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
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
            Paint polylinePaint = new Paint();
            polylinePaint.setStrokeWidth(5.0f);
            polylinePaint.setColor(Color.parseColor("#80DEEA"));
            //polylinePaint.setColor(Color.parseColor("#88FFFF"));

            RectF area = new RectF();
            area.left = (getPaddingLeft());
            area.top = (getHeight() - (User.getInstance().getTotalWaterConsumption()));
            area.right = ((getWidth() - getPaddingRight()));
            area.bottom = ((getHeight() - getPaddingBottom()));

            canvas.drawRect(area.left, area.top, area.right, area.bottom, polylinePaint);
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

