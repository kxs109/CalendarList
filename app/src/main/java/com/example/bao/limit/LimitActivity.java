package com.example.bao.limit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.bao.calendarlist.R;
import com.example.bao.widget.LimitView;

/**
 * Created by zhh on 2019/2/18.
 */
public class LimitActivity extends AppCompatActivity implements LimitView.NumListener, View.OnClickListener {

    private ConstraintLayout childLayout1;
    private ConstraintLayout childLayout2;
    private ConstraintLayout childLayout3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit);
        LimitView mRoomLimit = findViewById(R.id.room_limit);
        LimitView mAdultLimit = findViewById(R.id.adult_limit);
        LimitView mChildLimit = findViewById(R.id.child_limit);
        childLayout1 = findViewById(R.id.child_layout1);
        childLayout2 = findViewById(R.id.child_layout2);
        childLayout3 = findViewById(R.id.child_layout3);
        childLayout1.setOnClickListener(this);
        childLayout2.setOnClickListener(this);
        childLayout3.setOnClickListener(this);
        mChildLimit.setNumListener(this);
    }

    @Override
    public void getNumChanged(int num) {
        if (num==0){
            childLayout1.setVisibility(View.GONE);
        }else if (num==1){
            childLayout1.setVisibility(View.VISIBLE);
            childLayout2.setVisibility(View.GONE);
            childLayout3.setVisibility(View.GONE);
        }else if (num==2){
            childLayout1.setVisibility(View.VISIBLE);
            childLayout2.setVisibility(View.VISIBLE);
            childLayout3.setVisibility(View.GONE);
        }else if (num==3){
            childLayout1.setVisibility(View.VISIBLE);
            childLayout2.setVisibility(View.VISIBLE);
            childLayout3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.child_layout1:
                break;
            case R.id.child_layout2:
                break;
            case R.id.child_layout3:
                break;
        }
    }
}
