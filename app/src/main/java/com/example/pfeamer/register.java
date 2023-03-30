package com.example.pfeamer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Objects;
import java.util.zip.Inflater;

public class register extends AppCompatActivity {
private  MaterialEditText nom,prenom,email,password;
private RadioGroup rdgrp;
private  Button reg;
private ProgressBar prgb;
private FirebaseAuth firebaseAuth;
private DatabaseReference databaseReference;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       Toolbar toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this,MainActivity.class));
            }
        });
firebaseAuth=FirebaseAuth.getInstance();
        nom=findViewById(R.id.nom);
        prenom=findViewById(R.id.prenom);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        reg=findViewById(R.id.reg);
       prgb=findViewById(R.id.prgbr);
       rdgrp=findViewById(R.id.rdbtn);
       reg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String n=nom.getText().toString().trim();
               final String p=prenom.getText().toString().trim();
               final String e=email.getText().toString().trim();
               final String pwd=password.getText().toString().trim();
int check=rdgrp.getCheckedRadioButtonId();
               RadioButton selected=rdgrp.findViewById(check);
               if(selected==null)
               {
                   Toast.makeText(register.this,"selected type plase",Toast.LENGTH_LONG).show();
               }
               else
               {
                 final   String type=selected.getText().toString();
                   if(TextUtils.isEmpty(n)|| TextUtils.isEmpty(p) || TextUtils.isEmpty(e) || TextUtils.isEmpty(pwd))
                   {
                       Toast.makeText(register.this,"plase testing if the fileds is required or non",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       inscrit(n,p,e,pwd,type);
                   }
               }

           }
       });
    }

    private void inscrit(final String n, final String p, final String e, final String pwd, final String type) {
        prgb.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(e,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                    assert firebaseUser != null;
                    String userid =firebaseUser.getUid();
                    databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                    HashMap<String,String> hashMap =new HashMap<>();
                    hashMap.put("userid",userid);
                    hashMap.put("nom",n);
                    hashMap.put("prenom",p);
                    hashMap.put("email",e);
                    hashMap.put("password",pwd);
                    hashMap.put("type",type);
                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            prgb.setVisibility(View.GONE);
                            if(task.isSuccessful())
                            {
                                Intent intent=new Intent(register.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(register.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_LONG).show();
                            }

                        }
                    });



                }else
                {
prgb.setVisibility(View.GONE);
Toast.makeText(register.this,Objects.requireNonNull(task.getException().getMessage()),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
