package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class SimpleImageView extends View {
    private Paint paint;
    private Drawable drawable;
    private int width;
    private int height;

    public SimpleImageView(Context context) {
        this(context,null);
    }

    public SimpleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        if(attrs!=null){
            TypedArray array= null;
            try {


            array=getContext().obtainStyledAttributes(attrs,R.styleable.CustomImageView);
            drawable=array.getDrawable(R.styleable.CustomImageView_src);
            measureDrawable();
            }finally {
                if(array!=null){

                    array.recycle();
                }
            }

        }
    }

    private void measureDrawable() {
        if(drawable==null){
            throw  new RuntimeException("drawable不能为空");

        }
        width=drawable.getIntrinsicWidth();
        height=drawable.getIntrinsicHeight();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(drawable==null){
            return;
        }

        canvas.drawBitmap(ImageUtils.drawableToBitmap(drawable),getLeft(),getTop(),paint);
    }

    public SimpleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
