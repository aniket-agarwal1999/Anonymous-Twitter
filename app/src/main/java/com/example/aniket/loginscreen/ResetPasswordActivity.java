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
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText inputEmail;
    private Button btn_back, btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mAuth = FirebaseAuth.getInstance();
        inputEmail = (EditText) findViewById(R.id.email_textview);
        btn_back = (Button) findViewById(R.id.signIn_textview);
        btn_reset = (Button) findViewById(R.id.forgot_textview);

        //This activity is executed once the user clicks the back button
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ResetPasswordActivity.this, SignupActivity.class);
                startActivity(back);
                finish();
            }
        });

        //This option is executed once the reset button is clicked
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputEmail.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(ResetPasswordActivity.this, "Enter Email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions on your Email ID to reset your password", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ResetPasswordActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
