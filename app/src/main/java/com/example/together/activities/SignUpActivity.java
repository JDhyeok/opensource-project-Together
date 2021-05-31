package com.example.together.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.together.R;
import com.example.together.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("users");


        setContentView(R.layout.activity_signup);// UI view
        findViewById(R.id.regBtn).setOnClickListener(onClickListener);
        findViewById(R.id.arrow).setOnClickListener(onClickListener);
        mToolbar = (Toolbar)findViewById(R.id.header);


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.regBtn:
                    createAuthUser();
                    startLoginActivity();
                    break;
                case R.id.arrow:        // 뒤로가기 버튼 누를 시
                    onBackPressed();
                    break;
            }
        }
    };


    private void createAuthUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        String username = ((EditText)findViewById(R.id.userName)).getText().toString();
        String email = ((EditText)findViewById(R.id.userID)).getText().toString();
        String password = ((EditText)findViewById(R.id.userPW)).getText().toString();
//        String preference = ((EditText)findViewById(R.id.userPW)).getText().toString();
        String preference = "Netfilx"; // 임시
        User userData = new User(email, username, preference);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"회원가입 완료",Toast.LENGTH_LONG).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(),"회원가입 실패",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });


        if(user!=null)
            mRef.child(user.getUid()).setValue(userData);

    }

    private void startLoginActivity() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
