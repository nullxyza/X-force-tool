package com.visualmagix.calculator;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "Forced.txt";
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPlus, btnMinus, btnMultiply, btnDot, btnDivision, btnEqual, btnClear, btnPercent, btnDel;
    TextView tvInput;
    double var1;
    double var2;
    boolean add, sub, mul, div, del, yes;
    Vibrator vibrator;
    String txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onStart() {
        super.onStart();
        Shared shared = new Shared(getApplicationContext());
        shared.firsttime();


        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivision = findViewById(R.id.btnDivision);
        btnEqual = findViewById(R.id.btnEqual);
        btnClear = findViewById(R.id.btnClear);
        btnDot = findViewById(R.id.btnDot);
        btnPercent = findViewById(R.id.btnPercent);
        btnDel = findViewById(R.id.btnDel);
        tvInput = findViewById(R.id.Answer);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        tvInput.setText(" ");


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(50);
                tvInput.setText(" ");
                var1 = 1;
                var2 = 1;


            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText() + "0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText() + "1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText() + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText() + "3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText() + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText() + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText() + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText() + "7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText() + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText() + "9");
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvInput.getText() == " ") {
                    tvInput.setText("+");
                } else {
                    var1 = Double.parseDouble(tvInput.getText() + " ");
                    tvInput.setText(null);
                    add = true;

                }
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvInput.getText() == " ") {
                    tvInput.setText("-");
                } else {
                    var1 = Double.parseDouble(tvInput.getText() + " ");
                    tvInput.setText(null);
                    sub = true;
                }
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvInput.getText() == " ") {
                    tvInput.setText("Error!!");
                } else {
                    var1 = Double.parseDouble(tvInput.getText() + "");
                    tvInput.setText(null);
                    mul = true;
                }
            }
        });
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvInput.getText() == " ") {
                    tvInput.setText("Error!!");
                } else {
                    var1 = Double.parseDouble(tvInput.getText() + "");
                    tvInput.setText(null);
                    div = true;
                }
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText(tvInput.getText().toString() + ".");
            }
        });

        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvInput.getText().length() == 5) {
                    Intent myIntent = new Intent(v.getContext(), Activity2.class);
                    startActivity(myIntent);
                    finish();
                } else if (tvInput.getText() != " ") {
                    var1 = Double.parseDouble(tvInput.getText() + " ");
                    var1 = var1 / 100;
                    tvInput.setText(var1 + "");

                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = tvInput.getText().toString();
                s = s.substring(0, s.length() - 1);
                tvInput.setText(s);
            }
        });

        btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openVault();
                del = true;
                vibrator.vibrate(200);

                return false;
            }
        });


        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var2 = Double.parseDouble(tvInput.getText() + " ");
                if (add == true ) {
                    tvInput.setText(var1 + var2 + "");
                    add = false;

                }
                if (sub == true) {
                    tvInput.setText(var1 - var2 + "");
                    sub = false;
                }
                if (mul == true) {
                    tvInput.setText(var1 * var2 + "");
                    mul = false;
                }
                if (div == true) {
                    tvInput.setText(var1 / var2 + "");
                    div = false;
                }
            }
        });
    }




    public void openVault() {

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1= tvInput.getText().toString();
                tvInput.setText(" ");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    FileInputStream fis = null;

                    try {
                        fis = openFileInput(FILE_NAME);
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        StringBuilder sb = new StringBuilder();
                        String text;

                        while ((text = br.readLine()) != null) {
                            sb.append(text).append("\n");
                        }
                        tvInput.setText(sb.toString());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fis != null) {
                            try {
                                fis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    del = false;

                }

            }
        });




        btnDot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(yes==false){
                    tvInput.setText(txt1);
                    yes=true;
                }else
                {
                    tvInput.setText(" ");
                    yes=false;
                }
            }

        });

    }}







