package com.example.pfeamer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.core.os.LocaleListCompat.create;

public class profile extends AppCompatActivity {
    private MaterialEditText na, pre, tel, nai, add;
    private Button ok, btnimg;
    private CircleImageView img;
    private RadioGroup sex;
    private ProgressBar progressBar;
    private StorageReference storageReference;
    private Uri imguri;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private StorageTask uploedtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("create profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this, MainActivity.class));
            }
        });
        na = findViewById(R.id.no);
        pre = findViewById(R.id.pre);
        nai = findViewById(R.id.nai);
        add = findViewById(R.id.address);
        tel = findViewById(R.id.tel);
        sex = findViewById(R.id.sex);
        ok = findViewById(R.id.conffirme);
        img = findViewById(R.id.img);
        btnimg = findViewById(R.id.btnimg);
        progressBar = findViewById(R.id.progressbar);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        assert  firebaseUser !=null;
        String uid=firebaseUser.getUid();
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users/tprofile").child(uid);
      databaseReference.child("tprofile").child("usres");
        databaseReference.keepSynced(true);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nn = na.getText().toString().trim();
                final String pp = pre.getText().toString().trim();
                final String addr = add.getText().toString().trim();
                final String telf = tel.getText().toString().trim();
                final String nais = nai.getText().toString().trim();
                int check = sex.getCheckedRadioButtonId();
                RadioButton select = sex.findViewById(check);
                if (select == null) {
                    Toast.makeText(profile.this, "selected the sexe plase", Toast.LENGTH_LONG).show();
                } else {
                    final String sexe = select.getText().toString();
                    if (TextUtils.isEmpty(nn) || TextUtils.isEmpty(pp) || TextUtils.isEmpty(addr) || TextUtils.isEmpty(telf) || TextUtils.isEmpty(nais)) {
                        Toast.makeText(profile.this, "plase testing if the fileds is required or non", Toast.LENGTH_LONG).show();
                    } else {

                        up();
                    }
                }
            }
        });
        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 22) {
                    checkimg();
                }
            }
        });
    }

    private void checkimg() {
        Intent intent = new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
            img.setImageURI(imguri);
            Picasso.with(this).load(imguri).into(img);

        }
    }

    private String getFileimg(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void up() {
        if (imguri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileimg(imguri));
            fileReference.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
Handler handler= new Handler();
handler.postDelayed(new Runnable() {
    @Override
    public void run() {
        progressBar.setProgress(0);
    }
}, 500);
Toast.makeText(profile.this,"upload succesful",Toast.LENGTH_LONG).show();
                    int check = sex.getCheckedRadioButtonId();
                    RadioButton select = sex.findViewById(check);
modelprofile model=new modelprofile(taskSnapshot.getStorage().toString(),na.getText().toString().trim(),pre.getText().toString().trim(),add.getText().toString().trim(),nai.getText().toString().trim(),tel.getText().toString().trim(),select.getText().toString());
String idprofile=databaseReference.push().getKey();
databaseReference.child(idprofile).setValue(model);
startActivity(new Intent(profile.this,dashbordclient.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(profile.this, e.getMessage(), Toast.LENGTH_LONG);
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);

                }
            });
        }

    }
}
