package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //studio需要加入下面if判断才可以隐去title
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
            setContentView(R.layout.activity_main);
        }
        tvOne= (TextView) findViewById(R.id.tvOne);
        tvOne.setClickable(true);
        tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,ActivityTwo.class);
                startActivity(intent);
            }
        });
    }
}
