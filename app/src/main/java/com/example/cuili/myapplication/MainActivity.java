package com.example.cuili.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private String time = null;
    private String t = null;
    private EditText namec = null;
    private EditText cashc = null;
    private String str = null;
    private String str1 = null;
    private Button run = null;
    private Button clear = null;
    private String N = null;
    private String C = null;

    /**************************************/
    private static void saveFile(String str, String time) {
        String filePath = null;

        filePath = Environment.getExternalStorageDirectory().toString() + File.separator + time + "消费记录.txt";


        try {
            File file = new File(filePath);

            File dir = new File(file.getParent());
            dir.mkdirs();
            file.createNewFile();

            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(str.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namec = (EditText) findViewById(R.id.namec);
        cashc = (EditText) findViewById(R.id.cashc);
        run = (Button) findViewById(R.id.run);
        clear = (Button) findViewById(R.id.clear);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                time = format.format(date);
                N = namec.getText().toString().trim();
                C = cashc.getText().toString().trim();
                str = "时间：" + time + ";项目：" + N + ";金额：" + C + ";";
                DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
                String timec = format1.format(date);
                str1 = readFileSdcard(Environment.getExternalStorageDirectory().toString() + File.separator + timec + "消费记录.txt");
                saveFile(str + "\r\n" + str1, timec);
                Toast toast = Toast.makeText(getApplicationContext(), "消费记录已经登记", Toast.LENGTH_SHORT);
                //显示toast信息
                toast.show();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                N = null;
                C = null;
                str = null;
                str1 = null;
                namec.setText("");
                cashc.setText("");

            }
        });

    }

    /***********************************/
    private String readFileSdcard(String fileName) {

        String res = "";

        try {

            FileInputStream fin = new FileInputStream(fileName);

            int length = fin.available();

            byte[] buffer = new byte[length];

            fin.read(buffer);

            res = new String(buffer);

            fin.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return res;

    }
    ///////////////****//////////
}
