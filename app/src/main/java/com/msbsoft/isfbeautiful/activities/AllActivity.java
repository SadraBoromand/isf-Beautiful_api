package com.msbsoft.isfbeautiful.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.msbsoft.isfbeautiful.R;
import com.msbsoft.isfbeautiful.api.APIClient;
import com.msbsoft.isfbeautiful.api.APIInterface;
import com.msbsoft.isfbeautiful.models.Food;
import com.msbsoft.isfbeautiful.models.FoodAdapter;
import com.msbsoft.isfbeautiful.models.Place;
import com.msbsoft.isfbeautiful.models.PlaceAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllActivity extends AppCompatActivity {

    APIInterface request;

    RecyclerView rvAll;
    TextView txtTitleListAll;

    String errorConnextionMessage = "خطا: اتصال اینترنت خود را بررسی کنید و مجددا وارد برنامه شوید";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        rvAll = findViewById(R.id.rvAll);
        txtTitleListAll = findViewById(R.id.txtTitleAll);

        request = APIClient.getClient().create(APIInterface.class);

        boolean type = getIntent().getBooleanExtra("type", false);

        if (type) {
            ResponsePlaceData();
            txtTitleListAll.setText("مکان های گردشگری اصفهان");
        } else {
            ResponseFoodData();
            txtTitleListAll.setText("غذاهای خوش مزه اصفهان");
        }

        findViewById(R.id.imgBackDetail)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
    }

    private void ResponsePlaceData() {
        /**
         GET List Resources
         **/
        request.getAllPlaces().enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {

                Log.i("sa All", response.body().get(0).title);

                PlaceAdapter placeAdapter = new PlaceAdapter(AllActivity.this, response.body());
                rvAll.setAdapter(placeAdapter);
                rvAll.setLayoutManager(new GridLayoutManager(AllActivity.this, 2));
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Toast.makeText(AllActivity.this, errorConnextionMessage, Toast.LENGTH_LONG).show();

            }
        });
    }

    private void ResponseFoodData() {
        request.getAllFoods().enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {

                Log.i("sa All", response.body().get(0).title);
                FoodAdapter foodAdapter = new FoodAdapter(AllActivity.this, response.body());
                rvAll.setAdapter(foodAdapter);
                rvAll.setLayoutManager(new GridLayoutManager(AllActivity.this, 2));

            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(AllActivity.this, errorConnextionMessage, Toast.LENGTH_LONG).show();

            }
        });
    }
}