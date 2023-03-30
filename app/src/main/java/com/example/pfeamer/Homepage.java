package com.example.pfeamer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;
import androidx.appcompat.widget.Toolbar;
public class Homepage extends AppCompatActivity {
private CircleImageView img;
private TextView name;
private FirebaseAuth firebaseAuth;
private FirebaseUser firebaseUser;
private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar bar=findViewById(R.id.bar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("HomePage");
        name=findViewById(R.id.name);
        img=findViewById(R.id.img);

firebaseAuth=FirebaseAuth.getInstance();
firebaseUser=firebaseAuth.getCurrentUser();
databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
    }

}
