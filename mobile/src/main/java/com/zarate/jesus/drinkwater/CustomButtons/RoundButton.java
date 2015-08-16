package com.zarate.jesus.drinkwater.CustomButtons;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
    RectF _contentRect = new RectF();
    int _radius;
    private String text = "";
    private int _textSize = -1;
    private int _image = -1;
    private boolean _imageSet = false;
    Rect _textBounds = new Rect();
    Paint _paint;


    public RoundButton(Context context)
    {
        super(context);
        this.setBackgroundResource(R.drawable.oval_ripple);
        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setImage(int _image)
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
        float CircleCenterX = _contentRect.centerX();
        float CircleCenterY = _contentRect.centerY();

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
        _paint.setColor(Color.parseColor("#009788"));

        _contentRect.left = getPaddingLeft();
        _contentRect.top = getPaddingTop();
        _contentRect.right = getWidth() - getPaddingRight();
        _contentRect.bottom = getHeight() - getPaddingBottom();

        _radius = getHeight()/2;

        _paint.setColor(Color.WHITE);

        int textSize = this._textSize < 0 ? 32 : this._textSize;
        _paint.setTextSize(textSize);

        _paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        _paint.getTextBounds(text, 0, text.length(), _textBounds);

        if(text != null && !text.isEmpty())
        {
            canvas.drawText(text,
                    _contentRect.centerX() - _textBounds.exactCenterX(),
                    _contentRect.centerY() - _textBounds.exactCenterY(),
                    _paint);
        }

        if(_imageSet)
        {
            Resources resources = getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, _image);

            canvas.drawBitmap(bitmap, _contentRect.centerX() - 50, _contentRect.centerY() - 50, _paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int padding = getPaddingBottom();
        if(padding < getPaddingRight())
            padding = getPaddingRight();
        if(padding < getPaddingTop())
            padding = getPaddingTop();
        if(padding < getPaddingLeft())
            padding = getPaddingLeft();

        if(widthMeasureSpec > heightMeasureSpec)
            widthMeasureSpec = heightMeasureSpec;
        else
            heightMeasureSpec = widthMeasureSpec;
        //widthMeasureSpec  = heightMeasureSpec;
        super.onMeasure(widthMeasureSpec - padding, heightMeasureSpec - padding);
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