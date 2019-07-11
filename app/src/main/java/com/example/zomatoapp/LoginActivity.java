package com.example.zomatoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        TextView skipLogin=findViewById(R.id.buttonForSkippingLogin);
        Button googleLogin=findViewById(R.id.googleSignInButton);
        skipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                updateUI(account);
            } catch (ApiException e) {
                Log.w("failed to fetch api", "signInResult:failed code=" + e.getStatusCode());
                updateUI(null);
            }
        }
    }
    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this,"Failed to sign-in",Toast.LENGTH_SHORT).show();
        }
    }
}
