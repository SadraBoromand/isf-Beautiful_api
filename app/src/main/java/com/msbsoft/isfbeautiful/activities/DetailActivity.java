package com.msbsoft.isfbeautiful.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.msbsoft.isfbeautiful.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView txtTitle, txtDesc;
    ImageView imgDetail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViewById(R.id.imgBackDetail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtTitle = findViewById(R.id.txtTitleDetail);
        txtDesc = findViewById(R.id.txtDescDetail);
        imgDetail = findViewById(R.id.imgDetail);

        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");
        String img = getIntent().getStringExtra("img");

        txtTitle.setText(title);
        txtDesc.setText(desc);
        Picasso.get().load(img).into(imgDetail);

    }
}