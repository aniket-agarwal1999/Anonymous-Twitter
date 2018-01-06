package com.example.aniket.loginscreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;
    private Button btn_register, btn_login, btn_reset;
    private EditText inputEmail, inputPassword, inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        btn_register = (Button) findViewById(R.id.register_textview);
        btn_login = (Button) findViewById(R.id.signIn_textview);
        inputEmail = (EditText) findViewById(R.id.email_textview);
        inputPassword = (EditText) findViewById(R.id.password_textview);
        btn_reset = (Button) findViewById(R.id.forgot_textview);
        inputName = (EditText) findViewById(R.id.name_textview);

        //Here is the thing that will be executed once the user clicks the reset password button
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reset = new Intent(SignupActivity.this, ResetPasswordActivity.class);
                startActivity(reset);
                finish();
            }
        });

        //Here is the thing that will be executed once the user types in that he already has an account
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });

        //Now this is the thing that will be executed once the user clicks the Register button to register his new email ID
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String name = inputName.getText().toString();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(getApplicationContext(), "Enter Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6)
                {
                    Toast.makeText(getApplicationContext(), "Too weak password, enter minimum 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Create the user by just following the instruction as given on the Assisstant page
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:"+task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                /*If sign in fails, display a message to the user. If a sign in succeeds
                                The auth state listener will be notified and logic to handle the
                                signed in user can be handled in the listener.
                                 */
                                if(!task.isSuccessful())
                                {
                                    Toast.makeText(SignupActivity.this, "Authentication Failed."+task.getException(),Toast.LENGTH_SHORT).show();
                                }
                               else
                                {
                                    //In the first four lines here I am also saving the entire data about my users in the Database
                                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
                                    String userID = mAuth.getUid();
                                    User user = new User(email, name);
                                    mDatabaseRef.child(userID).setValue(user);
                                    Intent main = new Intent(SignupActivity.this, MainActivity.class);
                                    startActivity(main);
                                    finish();
                                }
                            }
                        });
            }
        });
    }

}