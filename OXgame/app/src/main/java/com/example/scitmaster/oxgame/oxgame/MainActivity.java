package com.example.scitmaster.oxgame.oxgame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button num[];       //숫자버튼 1~9
    Button reset;       //재시작버튼
    TextView text;      //게임플레이어
    Boolean b = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = new Button[10];

        reset = (Button)findViewById(R.id.reset);
        text= (TextView) findViewById(R.id.text);

        ButtonListener bl = new ButtonListener();
        //num - 리스너 연결
        int i;
        int numid;
        for(i=1;i<num.length;i++){
            numid = getResources().getIdentifier("num"+i,"id","com.example.scitmaster.oxgame.oxgame");
            num[i] = (Button)findViewById(numid);
            num[i].setOnClickListener(bl);
        }
        //reset - 리스너연결

        resetListener rl = new resetListener();
        reset.setOnClickListener(rl);

    }

    //숫자 버튼 처리
    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String num = ((Button) view).getText().toString(); //버튼 Text
            //Toast.makeText(MainActivity.this,((Button) view).getText().toString(),Toast.LENGTH_SHORT).show();
            String reset = ((Button) view).getText().toString(); //재시작 버튼
            //Toast.makeText(MainActivity.this,((Button) view).getText().toString(),Toast.LENGTH_SHORT).show();

            Button numid = (Button) view;
            Button reset1 = (Button) view;



            if(numid.getText().equals(num)){    //O로 변환
               // Toast.makeText(MainActivity.this,"1",Toast.LENGTH_SHORT).show();


                if(b == true){
                    if(numid.getText().equals("X")){
                        return;
                    }
                    numid.setText("O");
                    numid.setTextColor(Color.RED);
                    b = false;

                }
                else if(b == false){
                    if(numid.getText().equals("O")){
                        return;
                    }
                    numid.setText("X");
                    numid.setTextColor(Color.BLUE);
                    b = true;

                }

            }
        }
    }

    class resetListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            reset = (Button) view;
            int i;
            int numid;
            for(i=1;i<num.length;i++){
                numid = getResources().getIdentifier("num"+i,"id","com.example.scitmaster.oxgame.oxgame");
                num[i] = (Button)findViewById(numid);
                num[i].setText(String.valueOf(i));  // String 으로 받아서
                num[i].setTextColor(Color.BLACK);   //검정색으로
                b = true;               // 다시 O로 만들수 있게
            }
            text.setText("재시작합니다");


        }
    }
}
