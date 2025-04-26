package com.visualmagix.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Activity2 extends AppCompatActivity {
    private static final String FILE_NAME = "Forced.txt";
    public static final String EXTRA_NUMBER="com.example.application.example.EXTRA_NUMBER";
    Button savebtn, Openbtn,abtus;
    EditText enternum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        savebtn = findViewById(R.id.save);
        Openbtn = findViewById(R.id.OpenCal);
        enternum = findViewById(R.id.NewNum);
        abtus =findViewById(R.id.aboutus);


        Openbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
    }
    public void save(View v) {
        String text = enternum.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            enternum.getText().clear();
            Toast.makeText(this, "Saved ", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void openActivity1(){

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }


    public void sendMessage(View view) {
        Intent email=new Intent(Intent.ACTION_SEND);
        String []s ={"vmappstore10@gmail.com"};
        email.putExtra(Intent.EXTRA_EMAIL,s);
        email.setType("text/plain");
        startActivity(Intent.createChooser(email,"send email"));

    }
}



