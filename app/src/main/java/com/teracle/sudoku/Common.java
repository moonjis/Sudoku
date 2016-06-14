package com.teracle.sudoku;

import android.content.Context;

/**
 * Created by user on 2016-06-13.
 */
public class Common {
    /* dp to px */
    static public int getDPtoPX(Context context, float dp) {
        int px = 0;
        px = (int) (dp * context.getResources().getDisplayMetrics().density);
        return px;
    }
}
