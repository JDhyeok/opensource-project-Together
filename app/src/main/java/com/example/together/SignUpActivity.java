package com.example.together;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "signUp";
    private FirebaseAuth mAuth;


    View.OnClickListener onClickListener = new View.OnClickListener() {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.회원가입페이지이름);// UI view
        findViewById(R.id.회원가입버튼).setOnClickListener(onClickListener);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.회원가입버튼:
                createUser();
                break;
            case R.id.GPS찾기버튼:
                break;
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
        }
    }

    private void createUser() {
        String email = ((EditText)findViewById(R.id.이메일에딧텍스트)).getText().toString();
        String password = ((EditText)findViewById(R.id.비번에딧텍스트)).getText().toString();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                           // updateUI(user); // 성공 UI
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //updateUI(null); // 실패 UI
                        }
                    }
                });
    }
}
