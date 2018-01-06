package com.example.aniket.loginscreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth mAuth;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() !=null)
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        //Now here I have declared all the buttons that will be needed in this activity
        inputEmail = (EditText) findViewById(R.id.email_textview);
        inputPassword = (EditText) findViewById(R.id.password_textview);
        btn_login = (Button) findViewById(R.id.signIn_textview);
        btn_register = (Button) findViewById(R.id.register_textview);

        //Now here is the thing that will be executed once the Register option is clicked
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(register);
                finish();
            }
        });

        //Now here is the thing that will be executed once the login button is clicked
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(getApplicationContext(),"Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(), "Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        /*If sign in fails, dispay a message to the user. If sign in succeeds
                         the mAuth state listener will be notified and logic to handle the signed
                         in user can be handled in the listener
                         */
                        if(!task.isSuccessful())
                        {
                            //This is basically the error message
                            Toast.makeText(LoginActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                            Intent main = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(main);
                            finish();
                        }
                    }
                });
            }
        });


    }

}