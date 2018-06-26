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
    int count = 0;

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

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String num = ((Button) view).getText().toString(); //버튼 Text
            //Toast.makeText(MainActivity.this,((Button) view).getText().toString(),Toast.LENGTH_SHORT).show();
            String reset = ((Button) view).getText().toString(); //재시작 버튼
            //Toast.makeText(MainActivity.this,((Button) view).getText().toString(),Toast.LENGTH_SHORT).show();

            Button numid = (Button) view;


            if (text.getText().toString().equals("player1 win") || text.getText().toString().equals("player2 win")) {
                return;
            }

            if(numid.getText().equals(num)) {    //O로 변환
               // Toast.makeText(MainActivity.this,"1",Toast.LENGTH_SHORT).show();


                if(b == true) {
                    if(numid.getText().equals("O") || numid.getText().equals("X")) {
                        return;
                    }
                    numid.setText("O");
                    numid.setTextColor(Color.RED);
                    b = false;
                    count++;
                    if (count == 9 && !text.getText().toString().equals("player1 win") && !text.getText().toString().equals("player2 win")) {
                        text.setText("비겼습니다.");
                        text.setTextColor(Color.BLACK);
                        return;

                    }
                    text.setText("player1");
                    text.setTextColor(Color.RED);
                    check();

                }

                else if(b == false) {
                    if(numid.getText().equals("O") || numid.getText().equals("X")) {
                        return;
                    }
                    numid.setText("X");
                    numid.setTextColor(Color.BLUE);
                    b = true;
                    count++;
                    text.setText("player2");
                    text.setTextColor(Color.BLUE);
                    check();

                }

            }
        }
    }

    class resetListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            reset = (Button) view;
            int i, numid;

            for(i=1;i<num.length;i++) {
                numid = getResources().getIdentifier("num"+i,"id","com.example.scitmaster.oxgame.oxgame");
                num[i] = (Button)findViewById(numid);
                num[i].setText(String.valueOf(i));
                num[i].setTextColor(Color.BLACK);
                b = true;
                count = 0;
                text.setText("player1");

            }
        }
    }

    void check() {

        // player 1이 이길 경우
        if (num[1].getText().toString().equals("O") && num[2].getText().toString().equals("O") && num[3].getText().toString().equals("O")) {
            text.setTextColor(Color.RED);
            text.setText("player1 win");
        }

        if (num[4].getText().toString().equals("O") && num[5].getText().toString().equals("O") && num[6].getText().toString().equals("O")) {
            text.setTextColor(Color.RED);
            text.setText("player1 win");
        }

        if (num[7].getText().toString().equals("O") && num[8].getText().toString().equals("O") && num[9].getText().toString().equals("O")) {
            text.setTextColor(Color.RED);
            text.setText("player1 win");
        }

        if (num[1].getText().toString().equals("O") && num[4].getText().toString().equals("O") && num[7].getText().toString().equals("O")) {
            text.setTextColor(Color.RED);
            text.setText("player1 win");
        }

        if (num[2].getText().toString().equals("O") && num[5].getText().toString().equals("O") && num[8].getText().toString().equals("O")) {
            text.setTextColor(Color.RED);
            text.setText("player1 win");
        }

        if (num[3].getText().toString().equals("O") && num[6].getText().toString().equals("O") && num[9].getText().toString().equals("O")) {
            text.setTextColor(Color.RED);
            text.setText("player1 win");
        }

        if (num[1].getText().toString().equals("O") && num[5].getText().toString().equals("O") && num[9].getText().toString().equals("O")) {
            text.setTextColor(Color.RED);
            text.setText("player1 win");
        }

        if (num[3].getText().toString().equals("O") && num[5].getText().toString().equals("O") && num[7].getText().toString().equals("O")) {
            text.setTextColor(Color.RED);
            text.setText("player1 win");
        }

        // player2이 이길 경우
        if (num[1].getText().toString().equals("X") && num[2].getText().toString().equals("X") && num[3].getText().toString().equals("X")) {
            text.setTextColor(Color.BLUE);
            text.setText("player2 win");
        }

        if (num[4].getText().toString().equals("X") && num[5].getText().toString().equals("X") && num[6].getText().toString().equals("X")) {
            text.setTextColor(Color.BLUE);
            text.setText("player2 win");
        }

        if (num[7].getText().toString().equals("X") && num[8].getText().toString().equals("X") && num[9].getText().toString().equals("X")) {
            text.setTextColor(Color.BLUE);
            text.setText("player2 win");
        }

        if (num[1].getText().toString().equals("X") && num[4].getText().toString().equals("X") && num[7].getText().toString().equals("X")) {
            text.setTextColor(Color.BLUE);
            text.setText("player2 win");
        }

        if (num[2].getText().toString().equals("X") && num[5].getText().toString().equals("X") && num[8].getText().toString().equals("X")) {
            text.setTextColor(Color.BLUE);
            text.setText("player2 win");
        }

        if (num[3].getText().toString().equals("X") && num[6].getText().toString().equals("X") && num[9].getText().toString().equals("X")) {
            text.setTextColor(Color.BLUE);
            text.setText("player2 win");
        }

        if (num[1].getText().toString().equals("X") && num[5].getText().toString().equals("X") && num[9].getText().toString().equals("X")) {
            text.setTextColor(Color.BLUE);
            text.setText("player2 win");
        }

        if (num[3].getText().toString().equals("X") && num[5].getText().toString().equals("X") && num[7].getText().toString().equals("X")) {
            text.setTextColor(Color.BLUE);
            text.setText("player2 win");
        }
    }

}
