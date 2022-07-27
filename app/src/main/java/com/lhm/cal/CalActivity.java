package com.lhm.cal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class CalActivity extends AppCompatActivity {

    Button[] numbers; //0~9숫자 버튼
    Button plus,minus,multi,div,equal, clear;
    TextView txt_result;
    String resultStr = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        txt_result = findViewById(R.id.txt_result);
        numbers = new Button[10];
        for(int i = 0; i < numbers.length; i++){
            numbers[i] = findViewById(getResources().getIdentifier("btn"+i,"id",getPackageName()));
            numbers[i].setOnClickListener(numberClick);
        }   //for
        plus = findViewById(R.id.btn10);
        minus = findViewById(R.id.btn11);
        multi = findViewById(R.id.btn12);
        div = findViewById(R.id.btn13);
        equal = findViewById(R.id.btn14);
        clear = findViewById(R.id.btn15);

        plus.setOnClickListener(signClick);
        minus.setOnClickListener(signClick);
        multi.setOnClickListener(signClick);
        div.setOnClickListener(signClick);
        equal.setOnClickListener(signClick);
        clear.setOnClickListener(signClick);



    }   //onCreate()

    View.OnClickListener numberClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //숫자 버튼을 감지
            resultStr += ((Button)view).getText().toString();
            txt_result.setText(resultStr);

        }
    };

    View.OnClickListener signClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //기호버튼 클릭 ( =버튼 제외)
            if (view != equal){
                resultStr += " " + ((Button)view).getText().toString() + " " ;
                txt_result.setText(resultStr);

                if(view == clear){
                    resultStr ="";
                    txt_result.setText(resultStr);
                }
            }else{
                //=기호를 클릭시 결과 처리
                JsEvaluator jsEvaluator = new JsEvaluator(CalActivity.this);
                //evalute(연산하고자 하는 문자열, 수식으로 변경해서 연산 결과를 반환한다.)
                jsEvaluator.evaluate(resultStr, new JsCallback() {
                    @Override
                    public void onResult(String s) {
                        txt_result.setText(s);
                    }
                });
            }
        }
    };
}