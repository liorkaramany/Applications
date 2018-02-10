package com.example.liorkaramany.liork_readandwrite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main extends AppCompatActivity {

    EditText input;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input=(EditText) findViewById(R.id.input);
        output=(TextView) findViewById(R.id.output);
    }

    public void write(View view) {
        String in=input.getText().toString();
        FileOutputStream fos=null;
        try {
            fos=openFileOutput("input.txt", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter osw=new OutputStreamWriter(fos);
        BufferedWriter bw=new BufferedWriter(osw);
        try {
            bw.write(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(View view) {
        InputStream is=null;
        try {
            is=openFileInput("input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader tmp=new InputStreamReader(is);
        BufferedReader reader=new BufferedReader(tmp);
        String st="";
        StringBuffer buffer=new StringBuffer();
        try {
            while ((st=reader.readLine()) != null)
            {
                buffer.append(st+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        output.setText(buffer);
    }
}
