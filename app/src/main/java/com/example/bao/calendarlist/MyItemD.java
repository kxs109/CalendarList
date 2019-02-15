package com.example.bao.calendarlist;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyItemD extends RecyclerView.ItemDecoration {
    Paint paint = new Paint();
    Paint colorPaint = new Paint();
    Paint linePaint = new Paint();
    Paint bgPaint = new Paint();

    public MyItemD() {
        paint.setColor(Color.parseColor("#eeeeee"));
        paint.setStyle(Paint.Style.FILL);
//文字
        colorPaint.setColor(Color.parseColor("#333333"));
        colorPaint.setAntiAlias(true);
        colorPaint.setTypeface(Typeface.DEFAULT_BOLD);
//背景
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(Color.parseColor("#ffffff"));
        //线
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#EFEFEF"));
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getChildCount() <= 0) {
            return;
        }

        //头部的高度
        int height = 55;
        final float scale = parent.getContext().getResources().getDisplayMetrics().density;
        height = (int) (height * scale + 0.5f);
        int lineHeight = 1;
        final float lineScale = parent.getContext().getResources().getDisplayMetrics().density;
        lineHeight = (int) (lineHeight * lineScale + 0.5f);

        //获取第一个可见的view，通过此view获取其对应的月份
        CalendarList.CalendarAdapter a = (CalendarList.CalendarAdapter) parent.getAdapter();
        View fistView = parent.getChildAt(0);
        String text = a.data.get(parent.getChildAdapterPosition(fistView)).getMonthStr();

        String fistMonthStr = "";
        int fistViewTop = 0;
        //查找当前可见的itemView中第一个月份类型的item
        for (int i = 0; i < parent.getChildCount(); i++) {
            View v = parent.getChildAt(i);
            if (2 == parent.getChildViewHolder(v).getItemViewType()) {
                fistMonthStr = a.data.get(parent.getChildAdapterPosition(v)).getMonthStr();
                fistViewTop = v.getTop();
                break;
            }
        }

        //计算偏移量
        int topOffset = 0;
        if (!fistMonthStr.equals(text) && fistViewTop < height) {
            //前提是第一个可见的月份item不是当前显示的月份和距离的顶部的距离小于头部的高度
            topOffset = height - fistViewTop;
        }
        int t = 0 - topOffset;

        //绘制头部区域
        c.drawRect(parent.getLeft(), t, parent.getRight(), t + height, bgPaint);
        c.drawRect(parent.getLeft(), t + height - lineHeight, parent.getRight(), t + height, linePaint);

        colorPaint.setTextAlign(Paint.Align.CENTER);
        colorPaint.setTextSize(14 * scale + 0.5f);
        //绘制头部月份文字
        Paint.FontMetrics fontMetrics = colorPaint.getFontMetrics();
        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;

        c.drawText(text, parent.getRight() / 2, (t + height) / 2 + distance, colorPaint);

        //绘制分割线
//        if(fistViewTop!=height) {
//            linePaint.setStrokeWidth(scale * 0.5f + 0.5f);
//            c.drawLine(parent.getLeft(), t + height, parent.getRight(), t + height, linePaint);
//        }

    }


}
