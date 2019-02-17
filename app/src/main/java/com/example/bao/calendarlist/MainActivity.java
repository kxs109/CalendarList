package com.example.bao.calendarlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.Date;

/**
 * 思路
 * 1、生成日历数据
 * 生成每个月的每一天的数据
 */

public class MainActivity extends AppCompatActivity {

    private LinearLayout mTotalDaysLayout;
    private Button mTotalDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTotalDaysLayout = findViewById(R.id.total_days_layout);
        mTotalDays = findViewById(R.id.total_days);

        CalendarList calendarList=findViewById(R.id.calendarList);
        calendarList.setOnDateSelected(new CalendarList.OnDateSelected() {
            @Override
            public void selected(Date startDate, Date endDate) {
                if (startDate==null||endDate==null){
                    if (mTotalDaysLayout.getVisibility()==View.VISIBLE){
                        TranslateAnimation hideAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                                Animation.RELATIVE_TO_SELF, 0.0f,
                                Animation.RELATIVE_TO_SELF, 0.0f,
                                Animation.RELATIVE_TO_SELF, 1.0f);
                        hideAnim.setDuration(500);
                        mTotalDaysLayout.startAnimation(hideAnim);
                        mTotalDaysLayout.setVisibility(View.GONE);
                    }
                }else{
                    TranslateAnimation showAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 1.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f);
                    showAnim.setDuration(500);
                    mTotalDaysLayout.startAnimation(showAnim);
                    mTotalDaysLayout.setVisibility(View.VISIBLE);
                    mTotalDays.setText("共"+daysOfTwo(startDate, endDate)+"晚");
                }
            }
        });
    }

    private long daysOfTwo(Date dateStart, Date dateEnd) {
        long days = (dateEnd.getTime() - dateStart.getTime()) / (1000 * 3600 * 24);
        return days;
    }
}
