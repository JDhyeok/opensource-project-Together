package com.example.together.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.together.R;
import com.example.together.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        // Session으로 확인
//        if(mAuth.getCurrentUser() != null){
//            finish();
//            startHomeActivity();
//        }

        setContentView(R.layout.activity_login);// UI view
        findViewById(R.id.signUpBtn).setOnClickListener(onClickListener);
        findViewById(R.id.loginBtn).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.loginBtn:
                    authLogin();
                    break;
                case R.id.signUpBtn:
                    startSignUpActivity();
                    break;
            }
        }
    };

    private void authLogin() {
        String email = ((EditText)findViewById(R.id.userID)).getText().toString();
        String password = ((EditText)findViewById(R.id.userPW)).getText().toString();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void startHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void startSignUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
