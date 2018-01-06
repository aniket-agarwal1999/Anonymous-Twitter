package com.example.aniket.loginscreen;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {

    private EditText input_title, input_body;
    private Button post, back;
    private DatabaseReference DatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        //This last line is so that even if we switch off the app, then even there is data persistance as Cache.

        //Now these block of lines are basically just to use findViewById
        input_title = (EditText) findViewById(R.id.title_text);
        input_body = (EditText) findViewById(R.id.body_text);
        post = (Button) findViewById(R.id.post_button);
        back = (Button) findViewById(R.id.back_button);


//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user!=null)
//        {
//            uid = user.getUid();
//        }

        //This is the function of the button which is posting the data to the server.
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Now in this we are defining a message for it to be displayed in our database
                String title = input_title.getText().toString();
                String body = input_body.getText().toString();
                Message message = new Message(body, title);
                if(title.isEmpty() || body.isEmpty())
                {
                    Toast.makeText(PostActivity.this, "Some of the spaces are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseRef = FirebaseDatabase.getInstance().getReference("Messages");
                    DatabaseRef.child(title).setValue(message);
                    Toast.makeText(PostActivity.this, "Your post has been successfully posted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back  = new Intent(PostActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });

    }
}