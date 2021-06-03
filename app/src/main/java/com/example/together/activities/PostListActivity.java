package com.example.together.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.together.R;
import com.example.together.adapter.PostAdapter;
import com.example.together.models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostListActivity extends AppCompatActivity {
    private static final String TAG = "PostListActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Post> postList;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Toolbar mToolbar;

    public static PostListActivity closePostList;   // For finish previous PostListActivity after post


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("posts");
        setContentView(R.layout.activity_netflix_bbs);

        closePostList=PostListActivity.this;    // For finish previous PostListActivity after post


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        postList = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("posts");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for(DataSnapshot snap : snapshot.getChildren()) {
                    Log.e(TAG, "success database");
                    Post post = snap.getValue(Post.class);
                    postList.add(post);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "에러럴러러러러러러러럴러러ㅓ");
            }
        });

        adapter = new PostAdapter(postList, this);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.postBtn).setOnClickListener(onClickListener); //게시글 작성 버튼 아이디
        findViewById(R.id.arrow).setOnClickListener(onClickListener);  //뒤로가기 버튼 아이디
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.postBtn:
                    startPostActivity();
                    break;
                case R.id.arrow:        // 뒤로가기 버튼 누를 시
                    onBackPressed();
                    break;
            }
        }
    };

    private void startPostActivity() {
        Intent intent = new Intent(PostListActivity.this, PostActivity.class);
        startActivity(intent);
    }

}
