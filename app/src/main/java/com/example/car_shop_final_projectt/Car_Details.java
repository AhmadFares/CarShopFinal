package com.example.car_shop_final_projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;

public class Car_Details extends AppCompatActivity {
Intent getname;
ImageView img;
TextView nm,mdl,clr,spcs,mtr,cond,klmtr;
Button back;
    int[] resourcesarray={R.drawable.bmww,R.drawable.liam_martens_uui5rqcyudm_unsplash};

    public class DownloadTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls){
            String result = "";
            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                int data = reader.read();

                while( data != -1){
                    ;
                    result = result+ reader.readLine();
                    data = reader.read();

                }
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

            return result;
        }


        protected void onPostExecute(String s){
            super.onPostExecute(s);

            try{
                JSONObject json = new JSONObject(s);
                String name = json.getString("name");
                String model = json.getString("model");
                String km = json.getString("kilometer");
                String specs = json.getString("specs");
                String color = json.getString("color");
                String motor = json.getString("motor");
                String condition = json.getString("car_condition");
nm.setText(name);
mdl.setText(model);
klmtr.setText(km);
spcs.setText(specs);
clr.setText(color);
mtr.setText(motor);
cond.setText(condition);

            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

       String carname= getIntent().getStringExtra("carname");
        String  img_url= getIntent().getStringExtra("img_url");
        nm= findViewById(R.id.name);
        mdl= findViewById(R.id.model);
        klmtr= findViewById(R.id.kilometer);
        clr= findViewById(R.id.color);
        cond= findViewById(R.id.condition);
        mtr= findViewById(R.id.motor);
        back=findViewById(R.id.back);
img=findViewById(R.id.imageView6);
img.setImageResource(resourcesarray[Integer.parseInt(img_url)]);


                String url = "http://192.168.0.108/car_dealership_project/car_details_Get.php";
                DownloadTask task = new DownloadTask();
                task.execute(url);
//                    Log.i("ERRORE:",usernameData);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });



    }



}