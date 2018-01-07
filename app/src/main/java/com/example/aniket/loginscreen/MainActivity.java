package com.example.aniket.loginscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Camera;
import android.nfc.Tag;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button post, camera;
    ProgressDialog progressDialog;
    private DatabaseReference databaseReference, databaseReference_images;
    private StorageReference storageReference;
    List <Message> messageList = new ArrayList<>();
    List <MessageImage> messageImageList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.Adapter adapter_images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        post = (Button) findViewById(R.id.post_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        camera = (Button) findViewById(R.id.camera_button);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        progressDialog = new ProgressDialog(MainActivity.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();

        databaseReference_images = FirebaseDatabase.getInstance().getReference("Image Messages");
        databaseReference = FirebaseDatabase.getInstance().getReference("Messages");
        storageReference = FirebaseStorage.getInstance().getReference("images");

        //This is used to fetch the data from the Firebase Database, that is, the messages
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Message message = dataSnapshot.getValue(Message.class);
                    messageList.add(message);
                }

                adapter = new RecyclerViewAdapter(MainActivity.this, messageList);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


        //From here on I am starting to display the ValueEventListener for the Images to be displayed
        databaseReference_images.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MessageImage messageImage = dataSnapshot.getValue(MessageImage.class);
                    messageImageList.add(messageImage);
                }

                adapter_images = new RecyclerViewAdapterImages(MainActivity.this, messageImageList);
                recyclerView.setAdapter(adapter_images);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_PostActivity = new Intent(MainActivity.this, PostActivity.class);
                startActivity(open_PostActivity);
                finish();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(camera);
                finish();
            }
        });
    }
}