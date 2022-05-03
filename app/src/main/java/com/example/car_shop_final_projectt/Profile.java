package com.example.car_shop_final_projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Profile extends AppCompatActivity {
Button deleteacc;

TextView e,e1;
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

    public class ProfileTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls){
            String result = "";
            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while( data != -1){
                    char current = (char) data;
                    result += current;
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
                String emaill="";
                JSONArray jsonarray = new JSONArray(s);
                for (int i=0; i< jsonarray.length();i++){
                    JSONObject jsonobj=jsonarray.getJSONObject(i);
                    if(jsonobj.getString("username").equalsIgnoreCase(getIntent().getStringExtra("logged_user"))){

                        emaill = jsonobj.getString("email");
                        e1.setText(emaill);

                    }
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
        e=(TextView) findViewById(R.id.signUptext4);
        e1=(TextView) findViewById(R.id.signUptext3);
        String url = "http://192.168.0.108/car_dealership_project/profile.php";
        e.setText(getIntent().getStringExtra("logged_user"));

                ProfileTask task = new ProfileTask();
                task.execute(url);




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