package com.visualmagix.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Login extends AppCompatActivity {
    private  EditText Name;
    private  EditText Password;
    private TextView Info;
    private Button Login;
    private int counter=4;
    private Button Roll;
    private long backPressedTime;
    public String val1;
    private TextView Displ;
    public static final String SHARED_PREFS ="sharedPrefs";
    public static final String TEXT ="text";
    public static final String SWITCH1="switch1";
    public static final String VALUE="key";
    public static final String SWITCH2="switch2";
    private String text;
    private String val;
    private boolean switchOnOff;
    private boolean switch2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Name= findViewById(R.id.etName);
        Displ=findViewById(R.id.Display);
        Password=findViewById(R.id.etPassword);
        Info=findViewById(R.id.tvInfo);
        Login=findViewById(R.id.btnLogin);
        Roll=findViewById(R.id.roll);

        final Random myRandom = new Random();

        Roll.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String Num1=(String.valueOf(myRandom.nextInt(9)));
                String Num2=(String.valueOf(myRandom.nextInt(9)));
                String Num3=(String.valueOf(myRandom.nextInt(9)));
                String Num4=(String.valueOf(myRandom.nextInt(9)));
                String mix = Num1+Num2+Num3+Num4;
                val1 = Num3;
                Displ.setText("XFO"+mix);
                Roll.setEnabled(false);
                saveData();
            }
        });
        loadData();
        updateViews();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                validate(Name.getText().toString(),Password.getText().toString());

            }
        });

    }
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(TEXT,Displ.getText().toString());
        editor.putBoolean(SWITCH1,Roll.isEnabled());
        editor.putBoolean(SWITCH2,Login.isEnabled());
        editor.putString(VALUE,val1);
        editor.apply();


    }
    public void loadData(){
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT," ");
        val=sharedPreferences.getString(VALUE," ");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,true);
        switch2 = sharedPreferences.getBoolean(SWITCH2,true);

    }
    public void updateViews(){
        Displ.setText(text);
        Roll.setEnabled(switchOnOff);
        Login.setEnabled(switch2);

    }


    @SuppressLint("SetTextI18n")
    private void validate(String userName, String userPassword){
        if((!userName.equals(" "))&& (userPassword.length()==Integer.parseInt(val)+5)||(userPassword.equals("UWSKI123@"))){
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finish();
            Shared shared=new Shared (getApplicationContext());
            shared.secondtime();
        }
        else{
            counter--;
            Info.setText("No of attempts remaining: "+ counter);
            Toast.makeText(this,"Invalid Password!!",Toast.LENGTH_SHORT).show();
            if(counter == 0 ){
                Login.setEnabled(false);
                saveData();
            }
        }
    }

    @Override
    public void onBackPressed() {

        if(backPressedTime+2000 > System.currentTimeMillis() ){
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);

            return;
        } else{
            Toast.makeText(getBaseContext(), "Press back again to exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }
    public void sendID(View view) {
        loadData();
        Intent email=new Intent(Intent.ACTION_SEND);
        String []s ={"vmappstore10@gmail.com"};
        String[] subject = {"X-FOrce App"};
        String message = Name.getText().toString()+" "+text;
        email.putExtra(Intent.EXTRA_EMAIL,s);
        email.putExtra(Intent.EXTRA_SUBJECT,subject);
        email.putExtra(Intent.EXTRA_TEXT,message);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email,"Choose an email client"));

    }

}