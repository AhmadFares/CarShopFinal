package com.example.car_shop_final_projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Car_Details extends AppCompatActivity {
Intent getname;
    String carname;
ImageView img;
TextView nm,mdl,clr,spcs,mtr,cond,klmtr;
Button back;
    int[] resourcesarray={R.drawable.liam_martens_uui5rqcyudm_unsplash,R.drawable.bmww,R.drawable.bestami_sarikaya_5plxputziiq_unsplash,R.drawable.krish_parmar_i70_s2okwm4_unsplash,R.drawable.lance_asper_wl6oesgyof4_unsplash,R.drawable.martin_katler_71mfodxiz8c_unsplash};

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
                String name="",model="",km="",specs="",color="",motor="",condition="";
                JSONArray jsonarray = new JSONArray(s);
                for (int i=0; i< jsonarray.length();i++){
                    JSONObject jsonobj=jsonarray.getJSONObject(i);
                    if(jsonobj.getString("name").equalsIgnoreCase(carname)){
                         name = jsonobj.getString("name");
                         model = jsonobj.getString("model");
                         km = jsonobj.getString("kilometer");
                         specs = jsonobj.getString("specs");
                         color = jsonobj.getString("color");
                         motor = jsonobj.getString("motor");
                         condition = jsonobj.getString("car_condition");

                    }
                }

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

        carname= getIntent().getStringExtra("carname");
        String  img_url= getIntent().getStringExtra("img_url");
        String userr=getIntent().getStringExtra("logged_user");
        nm= (TextView) findViewById(R.id.name);
        mdl= (TextView)findViewById(R.id.model);
        spcs=(TextView)findViewById(R.id.specsholder);
        klmtr= (TextView)findViewById(R.id.kilometer);
        clr= (TextView)findViewById(R.id.colorHolder);
        cond= (TextView)findViewById(R.id.conditionholder);
        mtr= (TextView)findViewById(R.id.motorholder);
        back=findViewById(R.id.back);
img=(ImageView) findViewById(R.id.imageView6);
img.setImageResource(resourcesarray[Integer.parseInt(img_url)]);


                String url = "http://192.168.0.108/car_dealership_project/car_details_Get.php";
                DownloadTask task = new DownloadTask();
                task.execute(url);
//                    Log.i("ERRORE:",usernameData);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), Home.class);
                i.putExtra("logged_user",userr);
                startActivity(i);
            }
        });



    }



}