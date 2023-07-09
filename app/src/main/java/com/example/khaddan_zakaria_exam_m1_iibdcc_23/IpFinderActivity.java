package com.example.khaddan_zakaria_exam_m1_iibdcc_23;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khaddan_zakaria_exam_m1_iibdcc_23.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class IpFinderActivity extends AppCompatActivity {
    private EditText ipInput;
    private String IP_ADDRESS;
    private LinearLayout container;
    private List<String> infoList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_finder_);
        this.ipInput=findViewById(R.id.IpInput);
        this.container=findViewById(R.id.container);
        findViewById(R.id.button).setOnClickListener(view -> {
            IP_ADDRESS=ipInput.getText().toString();
            RequestQueue requestQueue= Volley.newRequestQueue(IpFinderActivity.this);
            String url="http://ipinfo.io/"+IP_ADDRESS+"/geo";
            @SuppressLint({"SetTextI18n", "ResourceAsColor"})
            StringRequest request=new StringRequest(Request.Method.GET, url, response -> {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String city = jsonObject.getString("city");
                    String region = jsonObject.getString("region");
                    String country = jsonObject.getString("country");

                    infoList.add(getString(R.string.city) +" : "+ city);
                    infoList.add(getString(R.string.region) +" : "+ region);
                    infoList.add(getString(R.string.country) +" : "+ country);
                    String LatLand = jsonObject.getString("loc");
                    infoList.forEach(text -> {
                        TextView toAdd = new TextView(IpFinderActivity.this);
                        toAdd.setText(text);
                        toAdd.setTextSize(16);
                        toAdd.setTypeface(null, Typeface.BOLD);
                        toAdd.setTextColor(Color.WHITE);
                        toAdd.setBackgroundColor(R.color.list_bg_color);
                        int paddingY=20;
                        toAdd.setPadding(0,paddingY,0,paddingY);
                        toAdd.setGravity(Gravity.CENTER);
                        int marginBottom=20;
                        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams.setMargins(0,0,0,marginBottom);
                        toAdd.setLayoutParams(layoutParams);
                        container.addView(toAdd);
                    });
                    Button MapBtn=new Button(IpFinderActivity.this);
                    MapBtn.setText(R.string.show_map);
                    int paddingY=30;
                    MapBtn.setPadding(0,paddingY,0,paddingY);
                    container.addView(MapBtn);

                    MapBtn.setOnClickListener(view1 -> {
                        Intent Map=new Intent(view.getContext(),MapsActivity.class);
                        Map.putExtra("LatLand",LatLand);
                        startActivity(Map);
                    });
                }catch(Exception e){
                    throw new RuntimeException(e);
                }
            },error -> {
                Log.e("API Error",error.toString());
                Toast toast = Toast.makeText(this, "connectivity exception", Toast.LENGTH_LONG);
                toast.show();
            });
            requestQueue.add(request);
        });
    }


}