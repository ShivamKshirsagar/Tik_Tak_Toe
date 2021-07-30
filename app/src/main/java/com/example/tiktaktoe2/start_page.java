package com.example.tiktaktoe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class start_page extends AppCompatActivity {
    Button start;
    EditText p1,p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        start=findViewById(R.id.start);
        p1=findViewById(R.id.p1);
        p2=findViewById(R.id.p2);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String aa=p1.getText().toString();
                String bb=p2.getText().toString();
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("p1",aa);
                i.putExtra("p2",bb);
                startActivity(i);

            }
        });

    }
}