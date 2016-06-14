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
public class TextBlock extends EditText {
    private Context mContext;
    private boolean mIsFix = false;
    private int mWidth;
    private int mHeight;

    public TextBlock(Context context) {
        super(context);
        mContext = context;
        initCommon();
    }

    /**
     * 고정 블럭의 생성자
     * @param context
     * @param number 고정블럭의 숫자
     */
    public TextBlock(Context context,String number) {
        super(context);
        mContext = context;
        initCommon();
        setFixBlock(number);
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

    // 해당 블럭의 텍스트 값 리턴
    public String getNumber(){
        return this.getText().toString();
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
        }
    }

    /**
     * 고정된 블록을 만드는 함수 (mIsFix = true로 고정)
     * @param number 블록안의 숫자
     */
    public void setFixBlock(String number) {
        mIsFix = true;
        this.setText(number);
        this.setFocusable(false);
        this.setTextColor(Color.BLACK);
    }

    interface listenTextInput{

    }
}
