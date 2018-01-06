package com.example.aniket.loginscreen;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class DeviceRegistration extends FirebaseInstanceIdService{
    private static final String TAG= "FCM Service";

    @Override
    public void onTokenRefresh()
    {
        //Get updated InstanceID token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed Token: "+refreshedToken);

        //If you want to send messages to this application instance or
        //manage this apps subscription on the server side, send the
        //Intance ID token to your app server.
    }

//    private void sendRegistrationToServer(String token)
//    {
//        FirebaseDatabase database;
//        database = FirebaseDatabase.getInstance();
//        final DatabaseReference myRef = database.getReference("Token ID");
//        myRef.setValue(token);
//    }
}
