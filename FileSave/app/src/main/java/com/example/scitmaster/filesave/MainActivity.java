package com.example.scitmaster.filesave;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText text;
    EditText filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        filename = findViewById(R.id.filename);

    }

    //내장메모리에 기본적으로 저장
    public void buttonClick(View v) {
        //저장기능
        if (v.getId() == R.id.btSave) {
            String str = text.getText().toString();  //내용
            String name = filename.getText().toString(); //사용자가 입력한 파일이름

            if (str.isEmpty() || name.isEmpty()) {    //내용과 파일이 없을 때
                Toast.makeText(this, "내용과 파일명을 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }
            //출력 스트림 생성 - 하드디스크에 파일을 만듬
            FileOutputStream out = null;
            try {
                out = openFileOutput(name, Context.MODE_PRIVATE);//스트림 객체 얻어옴
                //new 로 생성하는게 아님

                // 내용쓰기
                out.write(str.getBytes());
                // 스트림닫기
                out.close();
                // 파일로 나감
                Toast.makeText(this, "저장 성공", Toast.LENGTH_SHORT).show();
                text.setText("");
                filename.setText("");
            } catch (IOException e) {
                Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        //파일 읽기 기능
        if (v.getId() == R.id.btOpen) {
            String name = filename.getText().toString(); //사용자가 입력한 파일이름

            if (name.isEmpty()) {    //내용과 파일이 없을 때
                Toast.makeText(this, "파일명을 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }
            //출력 스트림 생성 - 하드디스크에 파일을 만듬
            FileInputStream in = null;
            try {
                in = openFileInput(name);//스트림 객체 얻어옴
                //new 로 생성하는게 아님

                // 내용쓰기
                int cnt = in.available();//내용을 읽지 않고 읽으려는 파일이 몇바이트인지 알아보는
                byte buf[] = new byte[cnt];// 얻은 바이트를 배열에 넣어서
                in.read(buf);
                //화면에 출력
                String str = new String(buf);  //byte배열은 읽지못해서 String으로 생성
                text.setText(str);
                // 스트림닫기
                in.close();
                // 파일로 나감
                Toast.makeText(this, "불러오기 성공", Toast.LENGTH_SHORT).show();
                text.getText();
                filename.getText();
            } catch (IOException e) {
                Toast.makeText(this, "불러오기 실패", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

        //모든 파일 목록 보기
        if(v.getId() == R.id.btList){
            String names[] = fileList();       //외부 메모리에 있는 리스트가 불러와져 스트링 배열에 저장
            //{"a.txt","b.txt","c.txt"}
            AlertDialog.Builder d = new AlertDialog.Builder(this);
            d.setTitle("전체 목록보기");
            d.setItems(names,null); //확인만 하는 거니까 선택 리스너 필요 없으니까 null로
            d.setPositiveButton("확인",null);
            d.show();

        }
    }
}
