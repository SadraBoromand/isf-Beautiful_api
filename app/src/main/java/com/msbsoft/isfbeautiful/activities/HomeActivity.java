package com.msbsoft.isfbeautiful.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class HomeActivity extends AppCompatActivity {

    APIInterface request;
    List<Place> datas = new ArrayList<>();

    RecyclerView rvPlaces, rvFoods;
    Button btnAllFood, btnAllPlace;

    String errorConnextionMessage = "خطا: اتصال اینترنت خود را بررسی کنید و مجددا وارد برنامه شوید";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(this, "خوش آمدید", Toast.LENGTH_LONG).show();

        rvPlaces = findViewById(R.id.recycler_view1);
        rvFoods = findViewById(R.id.recycler_view2);

        btnAllFood = findViewById(R.id.btnAllFood);
        btnAllPlace = findViewById(R.id.btnAllPlace);

        request = APIClient.getClient().create(APIInterface.class);
        ResponsePlaceData();
        ResponseFoodData();


        btnAllPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AllActivity.class);
                intent.putExtra("type", true);
                startActivity(intent);
            }
        });
        btnAllFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AllActivity.class);
                intent.putExtra("type", false);
                startActivity(intent);
            }
        });

        findViewById(R.id.imgAboutMe)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String aboutMe = "نرم افزار 'اصفهان زیبای من'" +
                                "\n" +
                                "سازنده: محمد صدرا برومند" +
                                "\n" +
                                "برای نمایش مکان های زیبا و گردشگری اصفهان و غذاهای خوش مزه اصفهان" +
                                "\n" +
                                "آدرس ایمیل: sadrabroo@gmail.com" +
                                "\n" +
                                "وب سایت: http://msbsoft2.github.io";

                        new AlertDialog.Builder(HomeActivity.this)
                                .setTitle("درباره من")
                                .setMessage(aboutMe)
                                .setPositiveButton("نمایش سایت", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://msbsoft2.github.io"));
                                        startActivity(myIntent);
                                    }
                                })
                                .setNegativeButton("بستن",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        })
                                .show();

                    }
                });
    }

    private void ResponsePlaceData() {
        /**
         GET List Resources
         **/
        request.getPlaces().enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {

                Log.i("sa", response.body().get(0).title);

                PlaceAdapter placeAdapter = new PlaceAdapter(HomeActivity.this, response.body());
                rvPlaces.setAdapter(placeAdapter);
                rvPlaces.setLayoutManager(new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, true));

            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, errorConnextionMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void ResponseFoodData() {
        request.getFoods().enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {

                FoodAdapter placeAdapter = new FoodAdapter(HomeActivity.this, response.body());
                rvFoods.setAdapter(placeAdapter);
                rvFoods.setLayoutManager(new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, true));

            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, errorConnextionMessage, Toast.LENGTH_LONG).show();

            }
        });
    }
}