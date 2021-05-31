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


public class BbsDetailActivity extends AppCompatActivity {

    private static final String TAG = "BbsDetailActivity";
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bbs_detail);// UI view
        findViewById(R.id.joinBtn).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.joinBtn:
                    startChatRoomActivity();
                    break;
            }
        }
    };

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void startChatRoomActivity(){
        Intent intent = new Intent(BbsDetailActivity.this, ChatRoomActivity.class);
        startActivity(intent);
    }
}
