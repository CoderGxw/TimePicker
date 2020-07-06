package com.liuwan.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.liuwan.demo.datepicker.CustomDatePicker;
import com.liuwan.demo.datepicker.DateFormatUtils;

public class DatePicker extends LinearLayout {

    private int textColor;
    private float textSize;
    private String DateRangeStart,DateRangeEnd;
    private TextView mTvSelectedDate;
    private CustomDatePicker mDatePicker;
    private String DateText;

    public DatePicker(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        LayoutInflater.from(context).inflate(R.layout.date_picker_layout,this);
        TypedArray array = context.obtainStyledAttributes(attributeSet,R.styleable.TimePickView);
        textColor = array.getColor(R.styleable.TimePickView_textColor,getResources().getColor(R.color.date_picker_text_light));
        textSize = array.getDimensionPixelSize(R.styleable.TimePickView_textSize,15);
        DateRangeStart = array.getString(R.styleable.TimePickView_TimeRangeStart);
        DateRangeEnd = array.getString(R.styleable.TimePickView_TimeRangeEnd);
        mTvSelectedDate= (TextView)findViewById(R.id.tv_DatePicker);
        mTvSelectedDate.setTextSize(textSize);
        mTvSelectedDate.setTextColor(textColor);
        initDatePicker();
        mTvSelectedDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(null!=DateText&&DateFormatUtils.isYYYYMMDD(DateText)){
                    mDatePicker.show(DateText);
                }else{
                    mDatePicker.show(DateFormatUtils.long2Str(System.currentTimeMillis(), false));
                }
            }
        });
    }

    private void initDatePicker(){
        String beginTime =DateFormatUtils.getBeforeDate("yyyy-MM-dd",365*50);//DateFormatUtils.long2Str(System.currentTimeMillis()-diff, false);
        String endTime =DateFormatUtils.getBeforeDate("yyyy-MM-dd",0);//DateFormatUtils.long2Str(System.currentTimeMillis(), false);
        System.out.println("beginDate"+beginTime+"/endDate"+endTime);
        if(null!=DateRangeStart&&DateFormatUtils.isYYYYMMDD(DateRangeStart)){
            beginTime = DateRangeStart;
        }
        if(null!=DateRangeEnd&&DateFormatUtils.isYYYYMMDD(DateRangeEnd)){
            endTime = DateRangeEnd;
        }
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd
        mDatePicker = new CustomDatePicker(this.getContext(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvSelectedDate.setText(DateFormatUtils.long2Str(timestamp, false));
                DateText= mTvSelectedDate.getText().toString();
            }
        }, beginTime, endTime,false);
        // 允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(true);
        // 显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 允许滚动动画
        mDatePicker.setCanShowAnim(true);
    }
    public String getText(){
        return this.DateText;
    }

    public void setText(String newText){
        mTvSelectedDate.setText(newText);
        this.DateText="";
    }

    public void setEnabled(boolean enabled){
        mTvSelectedDate.setEnabled(enabled);
    }

    public void setDateRangeStart(String DateRangeStart){
        this.DateRangeStart = DateRangeStart
    }

    public void setDateRangeEnd(String DateRangeEnd){
        this.DateRangeEnd = DateRangeEnd;
    }
}
