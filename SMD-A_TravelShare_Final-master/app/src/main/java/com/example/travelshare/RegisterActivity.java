package com.example.travelshare;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelshare.Classes.Response;
import com.example.travelshare.Classes.RetrofitClient;
import com.example.travelshare.Classes.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    private EditText FirstName;
    private EditText PhoneNumber;
    private EditText Email;
    private EditText Password;
    private EditText ReTypePassword;
    private Button SignUp;
    private String SelectedGender;
    private RadioGroup Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    private boolean Validate(String first, String last, String email, String password, String repass)
    {
        if ((!first.isEmpty()) && (!last.isEmpty()) && (!email.isEmpty()) && (!password.isEmpty()) && (!repass.isEmpty())) {
            if (password.equals(repass)) {
                return true;
            }
        }
        return false;
    }

    private void init() {
        FirstName = (EditText)findViewById(R.id.FirstName);
        PhoneNumber = (EditText)findViewById(R.id.phnum);
        Email = (EditText)findViewById(R.id.Email);
        Password = (EditText)findViewById(R.id.password);
        ReTypePassword = (EditText)findViewById(R.id.Retype_password);
        SignUp = (Button)findViewById(R.id.sign_up);
        Gender= (RadioGroup)findViewById(R.id.Radio_G);

        SignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if(((RadioButton)findViewById(Gender.getCheckedRadioButtonId())).getText() == "Male")
                    SelectedGender = "male";
                else
                    SelectedGender = "female";

                Register(FirstName.getText().toString(), PhoneNumber.getText().toString(), Email.getText().toString(), Password.getText().toString(), ReTypePassword.getText().toString(), SelectedGender);
            }
        });
    }

    private void Register(String name, String phone, String email, String pass, final String retype, String gender) {
        if(name.isEmpty() || phone.isEmpty() || email.isEmpty() || pass.isEmpty() || retype.isEmpty()) {
            showMessage("Fields cannot be empty.");
            return;
        }

        if(!pass.equals(retype)) {
            showMessage(pass + retype);
            //showMessage("Passwords do not match.");
            return;
        }

        JsonObject obj = new JsonObject();
        obj.addProperty("name", name);
        obj.addProperty("phone", phone);
        obj.addProperty("email", email);
        obj.addProperty("pass", pass);
        obj.addProperty("gender", gender);

        Call<Response> resp = RetrofitClient.getClient().register(obj);
        resp.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.body().getError() != null) {
                    showMessage(response.body().getError());
                }
                else {
                    showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                showMessage("Network Error. Please try again later.");

            }
        });

    }

    public void showMessage(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
    }
};
