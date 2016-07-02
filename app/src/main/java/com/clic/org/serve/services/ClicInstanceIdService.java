package com.clic.org.serve.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Venkatesh on 22-06-2016.
 */
public class ClicInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh()
    {
        // Get updated InstanceID token.
       String refreshedToken = FirebaseInstanceId.getInstance().getToken();
       Log.d("debug", "Refreshed token: " + refreshedToken);

        // TODO: Implement this method to send any registration to your app's servers.
        //sendRegistrationToServer(refreshedToken);
    }
}
