package com.example.zomatoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.zomatoapp.viewModels.LoginViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;

    LoginViewModel loginViewModel=new LoginViewModel();

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            loginViewModel.loginSuccessfully(this);
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
        final GoogleSignInClient mGoogleSignInClient= loginViewModel.setupSignIn(this);

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
        loginViewModel.signIn(requestCode,this,data);
        finish();
    }
}
