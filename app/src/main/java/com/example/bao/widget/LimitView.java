package com.example.bao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bao.calendarlist.R;


/**
 * Created by zhh on 2019/1/9.
 * 加减view
 */
public class LimitView extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    private Button subtractBtn;
    private Button addBtn;
    private String typeName;//类型名称  间数   成人数  儿童
    private int mMostValue;//最多
    private int mLeastValue;//最少
    private TextView numTv;
    private int mNum;//改变的值
    private boolean mHasListener;


    public LimitView(Context context) {
        super(context);
        initViews(context);
    }

    public LimitView(Context context, AttributeSet attrs) {
        super(context, attrs);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LimitView);
        typeName = typedArray.getString(R.styleable.LimitView_type_name);
        mMostValue = typedArray.getInt(R.styleable.LimitView_most, 3);
        mLeastValue = typedArray.getInt(R.styleable.LimitView_least, 0);
        mNum = typedArray.getInt(R.styleable.LimitView_num, 0);
        mHasListener = typedArray.getBoolean(R.styleable.LimitView_has_listener, false);

        initViews(context);
    }

    private void initViews(Context context) {
        mContext = context;


        LayoutInflater.from(getContext()).inflate(R.layout.view_limit, this);
        numTv = findViewById(R.id.limit_num);
        subtractBtn = findViewById(R.id.limit_subtract);
        addBtn = findViewById(R.id.limit_add);
        subtractBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);

        setNum();
    }

    public void setNum() {
        numTv.setText(mNum + "");
    }

    public int getNum() {
        return mNum;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.limit_subtract://减
                if (mNum > mLeastValue) {
                    numTv.setText(--mNum+"");
                }
                //改变按钮状态
                changeBtnStatus();
                //监听
                if (mHasListener) {
                    if (mNumListener != null) {
                        mNumListener.getNumChanged(mNum);
                    }
                }
                break;
            case R.id.limit_add://加
                if (mNum < mMostValue) {
                    numTv.setText(++mNum+"");
                }
                //改变按钮状态
                changeBtnStatus();
                //监听
                if (mHasListener) {
                    if (mNumListener != null) {
                        mNumListener.getNumChanged(mNum);
                    }
                }
                break;
        }
    }

    private void changeBtnStatus() {
        if (mNum > mLeastValue) {
            subtractBtn.setEnabled(true);
        } else {
            subtractBtn.setEnabled(false);
        }
        if (mNum >= mMostValue) {
            addBtn.setEnabled(false);
        } else {
            addBtn.setEnabled(true);
        }
    }

    public interface NumListener {
        void getNumChanged(int num);
    }

    public NumListener mNumListener;

    public void setNumListener(NumListener numListener) {
        this.mNumListener = numListener;
    }
}
