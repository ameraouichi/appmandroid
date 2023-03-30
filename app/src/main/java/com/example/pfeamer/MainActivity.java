package com.example.pfeamer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
 private MaterialEditText user,pass;
private Button btnlogin,r;
private ProgressBar progressBar;
private TextView forgetp;
private FirebaseAuth firebaseAuth;
private FirebaseUser usercj;
private DatabaseReference dataprofile,datuser;
private int countp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        datuser=FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        dataprofile=FirebaseDatabase.getInstance().getReference().child("tprofile");
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        btnlogin=findViewById(R.id.btnlog);
        r=findViewById(R.id.r);
        forgetp=findViewById(R.id.forget);
            progressBar=findViewById(R.id.prgb);
            firebaseAuth=FirebaseAuth.getInstance();
            r.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,register.class));
                }
            });
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tex_mail=user.getText().toString().trim();
                    String tex_password=pass.getText().toString().trim();
                    if(TextUtils.isEmpty(tex_mail) || TextUtils.isEmpty(tex_password))
                    {
                        Toast.makeText(MainActivity.this,"all files required",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        login(tex_mail,tex_password);
                    }
                }
            });
        forgetp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Forget_pwd.class));
            }
        });


    }
       dataprofile.child(uid).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists())
            {
                countp=(int) dataSnapshot.getChildrenCount();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    private void login(final String tex_mail,final String tex_password) {
progressBar.setVisibility(View.VISIBLE);
firebaseAuth.signInWithEmailAndPassword(tex_mail,tex_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        progressBar.setVisibility(View.GONE);
        if(task.isSuccessful()) {
            if (countp > 1) {
                Intent i = new Intent(MainActivity.this, dashbordclient.class);
                i.setFlags(i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
            else
            {
                Intent i = new Intent(MainActivity.this, profile.class);
                i.setFlags(i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        }
        else
        {
            Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

        }
    }
});
    }
}
