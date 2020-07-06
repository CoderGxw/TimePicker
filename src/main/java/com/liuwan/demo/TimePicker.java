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

public class TimePicker extends LinearLayout {

    private int textColor;
    private float textSize;
    private String TimeRangeStart,TimeRangeEnd;
    private TextView mTvSelectedTime;
    private CustomDatePicker mTimerPicker;
    private String TimeText;

    public TimePicker(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        LayoutInflater.from(context).inflate(R.layout.time_picker_layout,this);
        TypedArray array = context.obtainStyledAttributes(attributeSet,R.styleable.TimePickView);
        textColor = array.getColor(R.styleable.TimePickView_textColor,getResources().getColor(R.color.date_picker_text_light));
        textSize = array.getDimensionPixelSize(R.styleable.TimePickView_textSize,15);
        TimeRangeStart = array.getString(R.styleable.TimePickView_TimeRangeStart);
        TimeRangeEnd = array.getString(R.styleable.TimePickView_TimeRangeEnd);
        mTvSelectedTime = (TextView)findViewById(R.id.tv_TimePicker);
        mTvSelectedTime.setTextSize(textSize);
        mTvSelectedTime.setTextColor(textColor);
        initTimerPicker();
        mTvSelectedTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(null!=TimeText&&DateFormatUtils.isYYYYMMDDHHMM(TimeText)){
                    mTimerPicker.show(TimeText);
                }else{
                    mTimerPicker.show(DateFormatUtils.long2Str(System.currentTimeMillis(), true));
                }
            }
        });
    }
    private void initTimerPicker() {
        //diff为1年
        long diff = 1000 * 60 * 60 * 24 * 365;
        String beginTime =DateFormatUtils.getBeforeDate("yyyy-MM-dd hh:mm",365);
        String endTime =DateFormatUtils.getBeforeDate("yyyy-MM-dd hh:mm",-365);
        System.out.println("beginTime"+beginTime+"/endTime"+endTime);
        if(null!=TimeRangeStart&&DateFormatUtils.isYYYYMMDDHHMM(TimeRangeStart)){
            beginTime = TimeRangeStart;
        }
        if(null!=TimeRangeEnd&&DateFormatUtils.isYYYYMMDDHHMM(TimeRangeEnd)){
            endTime = TimeRangeEnd;
        }
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(this.getContext(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvSelectedTime.setText(DateFormatUtils.long2Str(timestamp, true));
                TimeText= mTvSelectedTime.getText().toString();
            }
        }, beginTime, endTime,true);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(false);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
    }

    public String getText(){
        return this.TimeText;
    }

    public void setText(String newText){
        mTvSelectedTime.setText(newText);
        this.TimeText=newText;
    }

    public void setEnabled(boolean enabled){
            mTvSelectedTime.setEnabled(enabled);
    }

}
