package com.example.zomatoapp.viewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.zomatoapp.HomeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginViewModel {
    private static final int RC_SIGN_IN = 9001;

    public GoogleSignInClient setupSignIn (Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        return GoogleSignIn.getClient(context, gso);
    }

    public void signIn(int requestCode, Context context, Intent data){
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                updateUI(account,context);
            } catch (ApiException e) {
                Log.w("failed to fetch api", "signInResult:failed code=" + e.getStatusCode());
                updateUI(null,context);
            }
        }}

    private void updateUI(@Nullable GoogleSignInAccount account, Context context) {
        if (account != null) {
            loginSuccessfully(context);
            Activity activity=(Activity)context;
            ((Activity) context).finish();
        } else {
            Toast.makeText(context,"Failed to sign-in",Toast.LENGTH_SHORT).show();
        }
    }

    public void loginSuccessfully(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

}
