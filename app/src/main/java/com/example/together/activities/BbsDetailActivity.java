package com.example.together.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.together.R;
import com.example.together.adapter.PostAdapter;
import com.example.together.models.Post;
import com.example.together.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BbsDetailActivity extends AppCompatActivity {

    private static final String TAG = "BbsDetailActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Toolbar mToolbar;

    TextView postTitle;
    TextView postContent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bbs_detail);// UI view
        findViewById(R.id.joinBtn).setOnClickListener(onClickListener);
        findViewById(R.id.arrow).setOnClickListener(onClickListener);
        mToolbar = (Toolbar)findViewById(R.id.header);


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title=intent.getExtras().getString("title");
        String content=intent.getExtras().getString("content");

        postTitle=(TextView)findViewById(R.id.postTitle);
        postContent=(TextView)findViewById(R.id.postContent);

        postTitle.setText(title);
        postContent.setText(content);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.joinBtn:
                    startPaymentActivity();
                    break;
                case R.id.arrow:        // 뒤로가기 버튼 누를 시
                    onBackPressed();
                    break;
            }
        }
    };

    private void startPaymentActivity(){
        Intent intent = new Intent(BbsDetailActivity.this, PaymentActivity.class);
        startActivity(intent);
    }
}
