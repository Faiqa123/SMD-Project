package com.example.travelshare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelroom.TravelDB;
import com.example.travelshare.Classes.RetrofitClient;
import com.example.travelshare.Classes.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText LoginEmail;
    private EditText LoginPassword;
    private Button LoginExit;
    private Button Register;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        LoginEmail = (EditText) findViewById(R.id.login_Email);
        LoginPassword = (EditText) findViewById(R.id.login_password);
        LoginExit = (Button) findViewById(R.id.login_exit);
        Register = (Button) findViewById(R.id.register);
        Login = (Button) findViewById(R.id.sign_in);

        startService(new Intent(this, MyService.class));
        LoginExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit");
                builder.setMessage("Do you want to exit ??");
                builder.setPositiveButton("Yes. Exit now!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();

                    }
                });
                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(LoginEmail.getText().toString(), LoginPassword.getText().toString());
            }
        });
    }

    private boolean Login(String email, String pass) {
        if (email.isEmpty() || pass.isEmpty()) {
            showMessage("Email or Password Cannot be empty.");
            return false;
        }

        JsonObject obj = new JsonObject();
        obj.addProperty("email", email);
        obj.addProperty("pass", pass);
        TravelDB travelDB= new TravelDB(getApplicationContext());
        travelDB.insertUser(pass,email);
        Call<User> user = RetrofitClient.getClient().login(obj);
        user.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                    intent.putExtra("User");

                    User user = response.body();

                    SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit();
                    editor.putString("id", user.getId());
                    editor.putString("name", user.getName());
                    editor.putString("phone", user.getPhone());
                    editor.putString("email", user.getEmail());
                    editor.putString("pass", user.getPass());
                    editor.putString("gender", user.getGender());
                    editor.putString("token", user.getToken());
                    editor.commit();

                    startActivity(intent);
                } else {
                    showMessage("Invalid Request.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showMessage("Network Error. Please try again later.");
            }
        });
        return false;
    }
    public void showMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
