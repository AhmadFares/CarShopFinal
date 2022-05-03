package com.example.car_shop_final_projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Profile extends AppCompatActivity {
Button deleteacc;
String username;
TextView e;
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
                String access = json.getString("result");
                if(access.equalsIgnoreCase("deleted")){
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Deleting failed", Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        username=getIntent().getStringExtra("logged_user");
        e=(TextView) findViewById(R.id.signUptext4);
//        e.setText(username);
//        deleteacc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url = "http://192.168.0.108/car_dealership_project/delete.php?username="+username;
//
//                DownloadTask task = new DownloadTask();
//                task.execute(url);
//            }
//        });



    }
}