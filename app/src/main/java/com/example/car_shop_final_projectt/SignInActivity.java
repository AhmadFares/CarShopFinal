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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignInActivity extends AppCompatActivity {
    EditText username,password;
    Button signin;
    String usernameData,passwordData;

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }



    public class DownloadTask extends AsyncTask<String, Void, String> {

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
                String passcheck="";
                JSONArray jsonarray = new JSONArray(s);
                for (int i=0; i< jsonarray.length();i++){
                    JSONObject jsonobj=jsonarray.getJSONObject(i);
                    if(jsonobj.getString("username").equalsIgnoreCase(usernameData)){
                        passcheck = jsonobj.getString("password");
                        String hasss=toHexString(getSHA(passwordData));
                            if(passcheck.equals(hasss)){
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                Intent intent2 = new Intent(getApplicationContext(), Profile.class);
                                intent2.putExtra("logged_user",usernameData);

                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Error in your password or username", Toast.LENGTH_SHORT).show();
                            }
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
        setContentView(R.layout.activity_sign_in);
        username=findViewById(R.id.username2);

        password=findViewById(R.id.password2);
        signin=findViewById(R.id.signinbtn);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 usernameData= ""+username.getText();
                  passwordData= ""+password.getText();
                String url = "http://192.168.0.108/car_dealership_project/Login.php";

                DownloadTask task = new DownloadTask();
                task.execute(url);
//                    Log.i("ERRORE:",usernameData);
            }
        });



    }
}



