package com.example.car_shop_final_projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Reviews extends AppCompatActivity {

EditText rev;
Button submit,home;
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
                if(access.equalsIgnoreCase("Review added")){
                    Toast.makeText(getApplicationContext(), "review noted", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        rev=(EditText)findViewById(R.id.review);
        submit=(Button)findViewById(R.id.submit);
    home=(Button)findViewById(R.id.back2);

    home.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent inn=new Intent(getApplicationContext(),Home.class);
            startActivity(inn);
        }
    });

                submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message=rev.getText()+"";
                String url = "http://192.168.0.108/car_dealership_project/Reviews.php?message="+message;

               DownloadTask task = new DownloadTask();
                task.execute(url);
//                    Log.i("ERRORE:",usernameData);
            }
        });



    }
}