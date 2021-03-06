package com.teracle.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static public String[][][][] str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<TextBlock> textBlockArrayList = new ArrayList<>();
        LinearLayout linBackground = (LinearLayout) findViewById(R.id.background);
        str = new String[3][3][3][3];
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
        /*for(String bigRow[][][] : str) {

            for(String bigBlock[][] : bigRow) {

                for(String smallRow[] : bigBlock) {

                    for (String aStr : smallRow) {

                    }

                }

            }

        }*/
        LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for(int i = 0 ; i < MainActivity.str.length ; i++) {
            String bigRow[][][] = MainActivity.str[i];
            LinearLayout linBigRow = new LinearLayout(this);
            linBigRow.setOrientation(LinearLayout.HORIZONTAL);
            linBigRow.setBackgroundResource(R.drawable.sudoku_boarder);
            linBigRow.setLayoutParams(linParams);
            for(int j = 0 ; j < bigRow.length ; j++) {
                LinearLayout linBigCol = new LinearLayout(this);
                linBigCol.setOrientation(LinearLayout.VERTICAL);
                linBigCol.setBackgroundResource(R.drawable.sudoku_boarder);
                linBigCol.setLayoutParams(linParams);
                String mediumBlock[][] = bigRow[j];
                for(int k = 0 ; k < mediumBlock.length ; k++) {
                    LinearLayout linOneRow = new LinearLayout(this);
                    linOneRow.setOrientation(LinearLayout.HORIZONTAL);
                    String mediumRow[] = mediumBlock[k];
                    for(int l = 0 ; l < mediumRow.length ; l++) {
                        String aStr = mediumRow[l];
                        TextBlock txtBlock;
                        int arrInt[] = {i,j,k,l};
                        if (aStr != null) {
                            txtBlock = new TextBlock(this, aStr,arrInt);
                        } else {
                            txtBlock = new TextBlock(this,arrInt);
                        }
                        linOneRow.addView(txtBlock);
                        textBlockArrayList.add(txtBlock);
                    }
                    linBigCol.addView(linOneRow);
                }

                linBigRow.addView(linBigCol);
            }
            linBackground.addView(linBigRow);
        }
    }
}
