package com.example.pfeamer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pfeamer.client.annocesc;
import com.example.pfeamer.client.comptecc;
import com.example.pfeamer.client.evaluationsc;
import com.example.pfeamer.client.litigescc;
import com.example.pfeamer.client.messagesc;
import com.example.pfeamer.client.notificationsc;
import com.example.pfeamer.client.profilec;

public class dashbordclient extends AppCompatActivity implements View.OnClickListener {
private CardView pc,ac,mc,nc,cc,lc,ec;
Fragment f=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbordclient);
        pc=findViewById(R.id.pc);
        cc=findViewById(R.id.cc);
        ac=findViewById(R.id.ac);
        nc=findViewById(R.id.nc);
        mc=findViewById(R.id.mc);
        ec=findViewById(R.id.ec);
        lc=findViewById(R.id.lc);
        pc.setOnClickListener(this);
        cc.setOnClickListener(this);
        ac.setOnClickListener(this);
        nc.setOnClickListener(this);
        mc.setOnClickListener(this);
        ec.setOnClickListener(this);
        lc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.pc:
                Toast.makeText(dashbordclient.this,"yourprofile",Toast.LENGTH_LONG).show();
                startActivity(new Intent(dashbordclient.this, profilec.class));
                break;
            case R.id.cc:
                startActivity(new Intent(dashbordclient.this, comptecc.class));
                break;
            case R.id.ac:
                startActivity(new Intent(dashbordclient.this, annocesc.class));
                break;
            case R.id.nc:
                startActivity(new Intent(dashbordclient.this, notificationsc.class));
                break;
            case R.id.mc:
                startActivity(new Intent(dashbordclient.this, messagesc.class));
                break;
            case R.id.ec:
                startActivity(new Intent(dashbordclient.this, evaluationsc.class));
                break;
            case R.id.lc:
                startActivity(new Intent(dashbordclient.this, litigescc.class));
                break;
        }
    }
}