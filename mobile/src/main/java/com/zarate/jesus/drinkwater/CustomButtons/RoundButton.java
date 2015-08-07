package com.zarate.jesus.drinkwater.CustomButtons;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import com.zarate.jesus.drinkwater.R;

/**
 * Created by Jesus Zarate on 7/30/15.
 */
public class RoundButton extends View
{
    RectF contentRect = new RectF();
    int _radius;
    private String text;
    private int _textSize = -1;
    private int _image = -1;
    private boolean _imageSet = false;

    public RoundButton(Context context)
    {
        super(context);
        this.setBackgroundResource(R.drawable.oval_ripple);
    }

    public void set_image(int _image)
    {
        this._imageSet = true;
        this._image = _image;
    }

    public void setText(int text)
    {
        this.text = getResources().getString(text);
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public int getTextSize()
    {
        return _textSize;
    }

    public void setTextSize(int _textSize)
    {
        this._textSize = _textSize;
    }

    public interface OnClickListener
    {
        public void onClick(RoundButton v, MotionEvent event);
    }

    OnClickListener _onClickListener = null;

    // The parameter is the interface type.
    public void setOnClickListener(OnClickListener listener) {
        _onClickListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        // Check if click is inside the circle, by measuring the distance
        //  between the center of the circle and the radius of the circle.
        // -> If the point clicked is less than the radius of the circle
        //    then it is a click.
        float CircleCenterX = contentRect.centerX();
        float CircleCenterY = contentRect.centerY();

        // Distance formula-> sqrt((x1 - x2)^2 + (y1 - y2)^2)
        float distance = (float) Math.sqrt(Math.pow(CircleCenterX - x, 2) + Math.pow(CircleCenterY - y, 2));
        if (distance < _radius) {

            if (_onClickListener != null) {
                _onClickListener.onClick(this, event);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#009788"));

        contentRect = new RectF();

        contentRect.left = getPaddingLeft();
        contentRect.top = getPaddingTop();
        contentRect.right = getWidth() - getPaddingRight();
        contentRect.bottom = getHeight() - getPaddingBottom();

        paint.setShadowLayer(10, contentRect.centerX(), contentRect.centerY(), Color.GRAY);

        _radius = getHeight()/2;
        //canvas.drawCircle(contentRect.centerX(), contentRect.centerY(), _radius, paint);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(Color.WHITE);

        int textSize = this._textSize < 0 ? 32 : this._textSize;
        paint.setTextSize(textSize);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        if(text != null && !text.isEmpty())
        {
            canvas.drawText(text,
                    calculateCenterX(text, paint.getTextSize()),
                    calculateCenterY(text, paint.getTextSize()),
                    paint);
        }

        if(_imageSet)
        {
            Resources resources = getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, _image);

            canvas.drawBitmap(bitmap, contentRect.centerX() - 50, contentRect.centerY() - 50, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        widthMeasureSpec  = heightMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
        float centerOfText = (textSize/3);

        int center = (getHeight() / 2);

        return center + centerOfText;
    }
}