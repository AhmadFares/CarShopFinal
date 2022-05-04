package com.example.car_shop_final_projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
EditText username,email,password;
    String usernameData;
Button signup,tosignin;

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
                String access = json.getString("access");
                if(access.equalsIgnoreCase("YES")){
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    Intent intent2 = new Intent(getApplicationContext(), Profile.class);
                    intent2.putExtra("logged_user",usernameData);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
            signup=findViewById(R.id.Signupbtn);
            tosignin=findViewById(R.id.tosigninpage);
tosignin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent in=new Intent(getApplicationContext(),SignInActivity.class);
        startActivity(in);
    }
});
        signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     usernameData= ""+username.getText();
                    String  emailData= ""+email.getText();
                   String  passwordData= ""+password.getText();
                    String url = "http://192.168.0.108/car_dealership_project/signUp.php?username="+usernameData+"&email="+emailData+"&password="+passwordData;

                    DownloadTask task = new DownloadTask();
                    task.execute(url);
//                    Log.i("ERRORE:",usernameData);
                }
            });



    }
}