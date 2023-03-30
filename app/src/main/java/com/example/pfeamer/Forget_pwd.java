package com.example.pfeamer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Forget_pwd extends AppCompatActivity {
private FirebaseAuth firebaseAuth;
private ProgressBar barr;
 private TextView reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        Toolbar lbar=findViewById(R.id.frbar);
        setSupportActionBar(lbar);
        getSupportActionBar().setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forget_pwd.this,MainActivity.class));
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        final MaterialEditText addressmail=findViewById(R.id.mail);
        reset=findViewById(R.id.reset);
        barr=findViewById(R.id.prgbar);
        Button resetpwd=findViewById(R.id.send_msg);
        resetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barr.setVisibility(View.VISIBLE);
                firebaseAuth.fetchSignInMethodsForEmail(addressmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if(task.getResult().getSignInMethods().isEmpty()) {
                            barr.setVisibility(View.GONE);
                            reset.setText("this is not register email,you can create new account! ");
                        }else
                        {
firebaseAuth.sendPasswordResetEmail(reset.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        barr.setVisibility(View.GONE);
        if(task.isSuccessful())
        {
            reset.setText("An reset email has been send to your email address");
        }
        else
        {
            reset.setText(task.getException().getMessage());
        }
    }
});
                        }
                    }
                });
            }
        });
    }
}
