package com.example.together.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.together.R;
import com.example.together.models.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PostActivity extends AppCompatActivity{
    private static final String TAG = "PostActivity";
    private FirebaseUser user;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writepage); //게시글 레이아웃 아이디
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("posts");



        findViewById(R.id.regist).setOnClickListener(onClickListener); //게시글 작성 버튼 아이디
        //findViewById(R.id.cancel).setOnClickListener(); //게시글 취소 버튼 아이디
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.regist:
                    writePost();
                    break;
            }
        }
    };

    private  void writePost(){
        // 게시글 불러오기
        String UID = user.getUid().toString();
        String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        String content = ((EditText) findViewById(R.id.conentEditText)).getText().toString();
        String name = getUserName(UID);

        if(title.length() > 0 && content.length() > 0) {
            Post post = new Post(UID, title, content, name);
            createPost(post);
        }else{
            startToast("제목 및 내용을 입력하세요.");
        }

    }

    private void createPost(Post post) {
        // DB upload
        // post id는 갯수로 구분
        int postid = getPostCount();
        mRef.child(Integer.toString(postid)).setValue(post);
    }

    public int getPostCount() {

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    postId = (int)snapshot.getChildrenCount();
                }else{
                    postId = 0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return postId + 1;
    }

    private void startToast(String msg){ //토스트메시지
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private Post posts;

    public String getUserName(String UID){
        // 현재 사용자 이름 가져오기


        mRef.equalTo(UID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                posts = snapshot.getValue(Post.class);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                posts = snapshot.getValue(Post.class);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                posts = snapshot.getValue(Post.class);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                posts = snapshot.getValue(Post.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        return posts.getWriter().toString();
    }
}
