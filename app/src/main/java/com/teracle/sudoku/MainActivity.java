package com.teracle.sudoku;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<TextBlock> textBlockArrayList = new ArrayList<>();
        LinearLayout linBackground = (LinearLayout) findViewById(R.id.background);

        String str[][][][] = new String[3][3][3][3];
        str[0][0][0][2] = "4";
        str[0][0][1][0] = "2";
        str[0][0][2][0] = "3";
        str[0][0][2][1] = "8";
        str[0][0][2][2] = "6";

        str[0][1][0][0] = "8";
        str[0][1][0][1] = "6";
        str[0][1][1][0] = "3";
        str[0][1][1][1] = "4";

        str[0][2][0][0] = "9";
        str[0][2][1][0] = "6";
        str[0][2][2][1] = "7";
        str[0][2][2][2] = "5";

        str[1][0][0][0] = "9";
        str[1][0][0][1] = "2";
        str[1][0][0][2] = "5";
        str[1][0][2][0] = "6";

        str[1][1][0][1] = "3";
        str[1][1][2][1] = "1";

        str[1][2][2][2] = "4";
        str[1][2][2][0] = "5";
        str[1][2][2][1] = "9";
        str[1][2][2][2] = "8";

        str[2][0][0][0] = "7";
        str[2][0][0][1] = "4";
        str[2][0][1][2] = "1";
        str[2][0][2][2] = "3";

        str[2][1][1][1] = "7";
        str[2][1][1][2] = "3";
        str[2][1][2][1] = "9";
        str[2][1][2][2] = "4";

        str[2][2][0][0] = "3";
        str[2][2][0][1] = "5";
        str[2][2][0][2] = "9";
        str[2][2][1][2] = "6";
        str[2][2][2][0] = "8";


        /* 스도쿠 보드판 만들기 */
        for(String bigRow[][][] : str) {
            LinearLayout linBigRow = new LinearLayout(this);
            linBigRow.setOrientation(LinearLayout.HORIZONTAL);
            for(String bigBlock[][] : bigRow) {
                LinearLayout linBigRows = new LinearLayout(this);
                linBigRows.setOrientation(LinearLayout.VERTICAL);
                for(String smallRow[] : bigBlock) {
                    LinearLayout linOneRow = new LinearLayout(this);
                    linOneRow.setOrientation(LinearLayout.HORIZONTAL);
                    for (String aStr : smallRow) {
                        TextBlock txtBlock;
                        if (aStr != null) {
                            txtBlock = new TextBlock(this, aStr);
                        } else {
                            txtBlock = new TextBlock(this);
                        }
                        linOneRow.addView(txtBlock);
                        textBlockArrayList.add(txtBlock);
                    }
                    linBigRows.addView(linOneRow);
                }
                linBigRow.addView(linBigRows);
            }
            linBackground.addView(linBigRow);
        }



    }
}
