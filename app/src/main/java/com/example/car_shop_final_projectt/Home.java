package com.example.car_shop_final_projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import io.grpc.internal.SharedResourceHolder;

public class Home extends AppCompatActivity {
ImageView firstImage,second,third,fourth,fifth,sixth;
Button prof,rev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firstImage=findViewById(R.id.imageView4);
        second=findViewById(R.id.imageView12);
        third=findViewById(R.id.imageView13);
        fourth=findViewById(R.id.imageView14);
        fifth=findViewById(R.id.imageView17);
        sixth=findViewById(R.id.imageView18);
prof=(Button)findViewById(R.id.button4);
rev=(Button)findViewById(R.id.reviewpage);

        rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(getApplicationContext(),Reviews.class);

                startActivity(in);
            }
        });
prof.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent in= new Intent(getApplicationContext(),Profile.class);
        in.putExtra("logged_user",getIntent().getStringExtra("logged_user"));
        startActivity(in);
    }
});


        firstImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Car_Details.class);

                in.putExtra("carname","mercedes");
                in.putExtra("img_url","1");
                startActivity(in);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Car_Details.class);
                in.putExtra("carname","bmw");
                in.putExtra("img_url","0");
                startActivity(in);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Car_Details.class);
                in.putExtra("carname","mercedes");
                startActivity(in);
            }
        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Car_Details.class);
                in.putExtra("carname","mercedes");
                startActivity(in);
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Car_Details.class);
                in.putExtra("carname","mercedes");
                startActivity(in);
            }
        });
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Car_Details.class);
                in.putExtra("carname","mercedes");
                startActivity(in);
            }
        });


    }
}