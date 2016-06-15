package com.teracle.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by user on 2016-06-13.
 */
public class TextBlock extends EditText{
    private Context mContext;
    private boolean mIsFix = false;
    private boolean mIsAnswer;
    private int mWidth;
    private int mHeight;
    private int intBigVerticalNum = 0;
    private int intBigHorizonNum = 0;
    private int intSmallVerticalNum = 0;
    private int intSmallHorizonNum = 0;

    /**
     * 일반 블럭의 생성자
     * @param context
     * @param arrInt 블럭의 배열 위치[큰행,큰열,작은행,작은열]
     */
    public TextBlock(Context context,int arrInt[]) {
        super(context);
        mContext = context;
        initCommon();
        initLocation(arrInt);
    }

    /**
     * 고정 블럭의 생성자
     * @param context
     * @param number 고정블럭의 숫자
     * @param arrInt 블럭의 배열 위치[큰행,큰열,작은행,작은열]
     */
    public TextBlock(Context context,String number,int arrInt[]) {
        super(context);
        mContext = context;
        initCommon();
        setFixBlock(number);
        initLocation(arrInt);
    }

    public TextBlock(Context context, AttributeSet attr) {
        super(context,attr);
        mContext = context;
        initCommon();
    }

    /**
     * 블럭의 기본 세팅
     */
    private void initCommon() {
        mWidth = Common.getDPtoPX(mContext,100.0f);
        mHeight = Common.getDPtoPX(mContext,100.0f);
        this.setTextColor(Color.BLUE);
        this.setBackgroundResource(R.drawable.block_boarder);
        this.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    /**
     * 해당 블럭 위치 잡기(없으면 0이 들어가기에 문제가 된다)
     * @param arrInt 해당 블럭의 위치 값[큰행,큰열,작은행,작은열]
     */
    private void initLocation(int arrInt[]) {
        if(arrInt.length != 4) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("arrInt length must be 4.");
            }
        } else {
            for (int i = 0; i < arrInt.length; i++) {
                switch (i) {
                    case 0:
                        intBigVerticalNum = arrInt[i];
                        break;
                    case 1:
                        intBigHorizonNum = arrInt[i];
                        break;
                    case 2:
                        intSmallVerticalNum = arrInt[i];
                        break;
                    case 3:
                        intSmallHorizonNum = arrInt[i];
                        break;
                }
            }
        }
    }

    /**
     * 블럭이 입력될 때 숫자가 아니면 지워버리는 함수
     * @param text
     * @param start
     * @param lengthBefore
     * @param lengthAfter
     */
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(!text.toString().matches("^[1-9]$") && text.length() != 0) { // 한 자리 숫자가 아닌 경우
            this.setText("");
            Toast.makeText(mContext, "1~9 까지의 수만 가능합니다. ", Toast.LENGTH_SHORT).show();
        } else {
            if((!mIsFix)) { // 고정된 숫자가 아닌 경우
                if (!checkRow(text.toString()) || !checkCol(text.toString()) || !checkBlock(text.toString())) {
                    this.setTextColor(Color.RED);
                    mIsAnswer = false;
                } else {
                    this.setTextColor(Color.BLUE);
                    mIsAnswer = true;
                }
                // 해당 위치에 숫자 집어넣기
                MainActivity.str[intBigVerticalNum][intBigHorizonNum][intSmallVerticalNum][intSmallHorizonNum] = text.toString();
                if (mIsAnswer && checkAllCount()) {
                    Toast.makeText(mContext, "Victory!!! You Win~", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 하나의 행 안에서 같은 숫자가 있는지 체크
     * @param number String : 입력한 숫자
     * @return boolean : 동일 숫자 있으면 false, 없으면 true
     */
    private boolean checkRow(String number) {

        String bigRow[][][] = MainActivity.str[intBigVerticalNum];
        for(int j = 0 ; j < bigRow.length ; j++) {
            String mediumRow[] = bigRow[j][intSmallVerticalNum];
            System.out.println(mediumRow.length);
            for(int l = 0 ; l < mediumRow.length ; l++) {
                if(number.equals(MainActivity.str[intBigVerticalNum][j][intSmallVerticalNum][l])) {
                    System.out.println("Row : false");
                    return false;
                } else {
                    System.out.println("Row : true");
                }
            }
        }

        return true;
    }

    /**
     * 하나의 열 안에서 같은 숫자가 있는지 체크
     * @param number String : 입력한 숫자
     * @return boolean : 동일 숫자 있으면 false, 없으면 true
     */
    private boolean checkCol(String number) {
        for(int j = 0 ; j < MainActivity.str.length ; j++) {
            String bigCol[][][] = MainActivity.str[j];
            String mediumBlock[][] = bigCol[intBigHorizonNum];
            for(int l = 0 ; l < mediumBlock.length ; l++) {
                if(number.equals(MainActivity.str[j][intBigHorizonNum][l][intSmallHorizonNum])) {
                    System.out.println("Col : false");
                    return false;
                } else {
                    System.out.println("Col : true");
                }
            }
        }

        return true;
    }

    /**
     * 큰 블럭 안에서 같은 숫자가 있는지 체크
     * @param number String : 입력한 숫자
     * @return boolean : 동일 숫자 있으면 false, 없으면 true
     */
    private boolean checkBlock(String number) {
        String mediumBlock[][] = MainActivity.str[intBigVerticalNum][intBigHorizonNum];
        for(int i =0 ; i < mediumBlock.length ; i++) {
            String mediumRow[] = mediumBlock[i];
            for(int j = 0 ; j < mediumRow.length ; j++) {
                if(number.equals(MainActivity.str[intBigVerticalNum][intBigHorizonNum][i][j])) {
                    System.out.println("Block : false");
                    return false;
                } else {
                    System.out.println("Block : true");
                }
            }
        }
        return true;
    }

    /*
    str 배열의 빈칸 체크
    다 채워져있으면 true
    아닐 시 false
    */
    private boolean checkAllCount() {
        for (String bigRow[][][] : MainActivity.str) {
            for (String bigBlock[][] : bigRow) {
                for (String smallRow[] : bigBlock) {
                    for (String aStr : smallRow) {
                        if (aStr == null || aStr.equals("")) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 고정된 블록을 만드는 함수 (mIsFix = true,mIsAnswer = true 로 고정)
     * @param number 블록안의 숫자
     */
    public void setFixBlock(String number) {
        mIsFix = true;
        mIsAnswer = true;
        this.setText(number);
        this.setFocusable(false);
        this.setTextColor(Color.BLACK);
    }
}
