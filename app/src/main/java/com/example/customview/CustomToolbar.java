package com.example.customview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToolbar extends LinearLayout {
    private LinearLayout rootLayout;
    private TextView tvTitle;
    private ImageView ivBack;
    private ImageView ivMenu;
    private int bgColor;
    private String title;
    private int menuSrc;


    public CustomToolbar(Context context) {
        this(context,null);
    }

    public CustomToolbar(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomToolbar(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initTypeArray(context,attrs,defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_title,this);

        rootLayout=findViewById(R.id.root_layout);
        ivBack=findViewById(R.id.iv_back);
        tvTitle=findViewById(R.id.tv_title);
        ivMenu=findViewById(R.id.iv_menu);

        rootLayout.setBackgroundColor(bgColor);
        tvTitle.setText(title);
        if(menuSrc!=-1){
            ivMenu.setImageResource(menuSrc);
        }

        ivBack.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
                Toast.makeText(getContext(),"点击左键",Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void initTypeArray(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CustomToolbar,defStyleAttr,0);
        bgColor=typedArray.getColor(R.styleable.CustomToolbar_backgroundColor, Color.TRANSPARENT);
        title=typedArray.getString(R.styleable.CustomToolbar_title);
        menuSrc=typedArray.getResourceId(R.styleable.CustomToolbar_menuSrc,-1);
        typedArray.recycle();

    }


}
