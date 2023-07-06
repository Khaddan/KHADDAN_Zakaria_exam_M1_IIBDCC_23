package com.example.khaddan_zakaria_exam_m1_iibdcc_23;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.usrname);
        password=findViewById(R.id.pswd);
        login=findViewById(R.id.btnLogin);
        sharedPreferences = getSharedPreferences("loginPref",
                MODE_PRIVATE);
        login.setOnClickListener(view -> {
            if(sharedPreferences==null) sharedPreferences=getSharedPreferences("loginPref",MODE_PRIVATE);
            String n = username.getText().toString();
            String e = password.getText().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("emailKey", n);
            editor.putString("pwdKey", e);
            editor.commit();
            username.setText("");
            password.setText("");
            Intent i=new Intent(MainActivity.this,IpFinderActivity.class);
            startActivity(i);

        });
    }
}