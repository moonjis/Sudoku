package com.teracle.sudoku;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by user on 2016-06-14.
 */
public class MediumBlock extends LinearLayout {

    private Context mContext;
    private String mStrContent[][];

    public MediumBlock(Context context) {
        super(context);
    }

    public MediumBlock(Context context, AttributeSet attr) {
        super(context);
    }

    public MediumBlock(Context context, String strContent[][]) {
        super(context);
        mStrContent = strContent;
        mContext = context;
    }

    public void makeMediumBlock() {
        this.setOrientation(VERTICAL);
        for(String oneRow[] : mStrContent) {
            LinearLayout linRow = new LinearLayout(mContext);
            linRow.setOrientation(HORIZONTAL);
            /*하나의 블록에 숫자 집어넣기*/
            for(String number : oneRow) {
                if (number != null) {
                    linRow.addView(new TextBlock(mContext, number));
                } else {
                    linRow.addView(new TextBlock(mContext));
                }
            }
        }
    }

    public void checkSameNumber() {
        for(String oneRow[] : mStrContent) {
            for(String number : oneRow) {

            }
        }
    }
}
