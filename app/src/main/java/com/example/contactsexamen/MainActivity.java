package com.example.contactsexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText login_et;
private EditText password_et;
private Button login_btn;
private SharedPreferences loginPreferances;
private final String SHARED_PASSWORD="231989";
private final String SHARED_LOGIN="aram.muradyan@gmail.com";
private final String CHECK_VALIDATION="valid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginSharedPreferancesInit();
        if (loginPreferances!=null){
        checkingPasswordValidation();}
        viewInit();
        loginSharedPreferancesInit();

    }

    private void checkingPasswordValidation(){
        String validation =loginPreferances.getString("checkValidation","invalid");
        if(validation.equals(CHECK_VALIDATION)){
            openContactActivity();
        }
    }

    private void viewInit(){

        login_et=findViewById(R.id.login_et);
        password_et=findViewById(R.id.passwort_et);
        login_btn=findViewById(R.id.login_btn);
    }

    public void onClickLogin(View v){
        String login=login_et.getText().toString();
        String password=password_et.getText().toString();
        if(TextUtils.isEmpty(login)||TextUtils.isEmpty(password)){
            Toast.makeText(this,R.string.toast_empty_login,Toast.LENGTH_SHORT).show();
        }
        String loginTextFromPreferences=loginPreferances.getString("login","login");
        String passwordTextFromPreferences=loginPreferances.getString("password","password");
        if(!loginTextFromPreferences.equals(login)|| !passwordTextFromPreferences.equals(password)){
            Toast.makeText(this,R.string.toast_vrong_login,Toast.LENGTH_SHORT).show();
        }
        else {
            loginPreferances.edit().putString("checkValidation",CHECK_VALIDATION).apply();
            openContactActivity();
        }
    }
    private void loginSharedPreferancesInit(){
        loginPreferances=getSharedPreferences("loginPref",MODE_PRIVATE);
        loginPreferances.edit()
                .putString("password",SHARED_PASSWORD)
                .putString("login",SHARED_LOGIN)
                .apply();
    }
    private void openContactActivity(){
        Intent inten =new Intent(this,ContactMainActivity.class);
        startActivity(inten);
        finish();
    }
}
