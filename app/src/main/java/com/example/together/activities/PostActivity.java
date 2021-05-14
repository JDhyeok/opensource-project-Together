package com.example.together.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.together.R;
import com.example.together.models.Post;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostActivity extends AppCompatActivity{
    private static final String TAG = "WritePostActivity";
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post); //게시글 레이아웃 아이디

        findViewById(R.id.check).setOnClickListener(onClickListener); //게시글 작성 버튼 아이디
        findViewById(R.id.cancel).setOnClickListener(); //게시글 취소 버튼 아이디
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.check:
                    writePost();
                    break;
            }
        }
    };

    private  void writePost(){ //게시글 작성
        final String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString(); //제목 text 상자
        final String content = ((EditText) findViewById(R.id.contentEditText)).getText().toString();//내용 text 상자
        if(title.length() > 0 && content.length() > 0){
            user = FirebaseAuth.getInstance().getCurrentUser();
            Post post = new Post(title,content, user.getUid());
            uploader(post);
        }else{
            startToast("제목 및 내용을 입력하세요.");
        }
    }
    private void uploader(Post post){ //DB로 게시글 업로드
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("posts").add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot Written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v(TAG, "Error adding document",e);
                    }
                });
    }
    private void startToast(String msg){ //토스트메시지
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();}
}
