package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class login extends AppCompatActivity {
    EditText username, password;
    Button loginbtn;
    String finalResult;
    String UserHolder, PasswordHolder;
    String HttpURL = "https://mp730quiz.000webhostapp.com/login.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    httpparse httpParse = new httpparse();

    public static final String UserName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.user_pass);
        loginbtn = (Button) findViewById(R.id.user_login);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    UserLoginFunction(UserHolder, PasswordHolder);

                } else {

                    Toast.makeText(login.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void CheckEditTextIsEmptyOrNot() {

        UserHolder = username.getText().toString();
        PasswordHolder = password.getText().toString();

        if (TextUtils.isEmpty(UserHolder) || TextUtils.isEmpty(PasswordHolder)) {
            CheckEditText = false;
        } else {

            CheckEditText = true;
        }
    }

    public void UserLoginFunction(final String username, final String password) {

        class UserLoginClass extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(login.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if (httpResponseMsg.equalsIgnoreCase("Data Matched")) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra(UserName, username);
                    startActivity(intent);
                } else {
                    Toast.makeText(login.this, httpResponseMsg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("username",params[0]);
                hashMap.put("password",params[1]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }

        }
        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(username, password);
    }
}