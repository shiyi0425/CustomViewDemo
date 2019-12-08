package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class SimpleImageViewV2 extends View {
    private Paint paint;
    private Drawable drawable;
    private int mwidth;
    private int mheight;


    public SimpleImageViewV2(Context context) {
        this(context,null);
    }

    public SimpleImageViewV2(Context context, AttributeSet attrs) {
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
        mwidth=drawable.getIntrinsicWidth();
        mheight=drawable.getIntrinsicHeight();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mwidth,mheight);

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(measuredWidth(widthMode,width),
        measuerHeight(heightMode,height));

    }

    private int measuerHeight(int mode, int height) {
        switch (mode){
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
                case MeasureSpec.EXACTLY:
                   mheight=height;
                    break;

        }
        return height;

    }

    private int measuredWidth(int mode, int width) {
        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                case MeasureSpec.AT_MOST:
                    break;
            case MeasureSpec.EXACTLY:
                mwidth=width;
                break;
        }
        return width;
    }

    private Bitmap mBitmap;

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmap==null){
            mBitmap=Bitmap.createScaledBitmap(ImageUtils.drawableToBitmap(drawable),getMeasuredWidth()
            ,getMeasuredHeight(),true);
        }

        canvas.drawBitmap(ImageUtils.drawableToBitmap(drawable),getLeft(),getTop(),paint);


    }

    public SimpleImageViewV2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
