package com.example.together.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.together.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private FirebaseUser user;

    private ImageButton netflix;
    private ImageButton watcha;
    private ImageButton disney;
    private ImageButton tving;
    private ImageButton wavve;
    private ImageButton hbomax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            setContentView(R.layout.activity_home);
        }else{
            startLoginActivity();
        }

        findViewById(R.id.netflix).setOnClickListener(onClickListener);
        findViewById(R.id.watcha).setOnClickListener(onClickListener);
        findViewById(R.id.tving).setOnClickListener(onClickListener);
        findViewById(R.id.wavve).setOnClickListener(onClickListener);
        findViewById(R.id.disney).setOnClickListener(onClickListener);
        findViewById(R.id.hbomax).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.netflix:
                    startNetflixBbsActivity();
                    break;
                case R.id.watcha:
                    startWatchaBbsActivity();
                    break;
                case R.id.tving:
                    startTvingBbsActivity();
                    break;
                case R.id.wavve:
                    startWavveBbsActivity();
                    break;
                case R.id.disney:
                    startDisneyBbsActivity();
                    break;
                case R.id.hbomax:
                    startHbomaxBbsActivity();
                    break;
            }
        }
    };

    private void startNetflixBbsActivity() {
        Intent intent = new Intent(HomeActivity.this, PostListActivity.class);
        startActivity(intent);
    }

    private void startWatchaBbsActivity() {
        Intent intent = new Intent(HomeActivity.this, PostListActivity.class);
        startActivity(intent);
    }

    private void startTvingBbsActivity() {
        Intent intent = new Intent(HomeActivity.this, PostListActivity.class);
        startActivity(intent);
    }

    private void startWavveBbsActivity() {
        Intent intent = new Intent(HomeActivity.this, PostListActivity.class);
        startActivity(intent);
    }

    private void startDisneyBbsActivity() {
        Intent intent = new Intent(HomeActivity.this, PostListActivity.class);
        startActivity(intent);
    }

    private void startHbomaxBbsActivity() {
        Intent intent = new Intent(HomeActivity.this, PostListActivity.class);
        startActivity(intent);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

