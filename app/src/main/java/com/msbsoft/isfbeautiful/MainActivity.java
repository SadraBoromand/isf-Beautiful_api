package com.msbsoft.isfbeautiful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.msbsoft.isfbeautiful.activities.HomeActivity;
import com.msbsoft.isfbeautiful.api.APIClient;
import com.msbsoft.isfbeautiful.api.APIInterface;
import com.msbsoft.isfbeautiful.models.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    APIInterface request;

    TextView txtConnection;

    String errorConnextionMessage = "خطا: اتصال اینترنت خود را بررسی کنید و مجددا وارد برنامه شوید";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        ResponseData();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void ResponseData() {
        /**
         GET List Resources
         **/
        request.getPlaces().enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                Log.i("sa", response.isSuccessful() + "");
                if (response.isSuccessful()) {
                    new Handler().postDelayed(() -> {
                        finish();
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }, 2000);
                } else {
                    txtConnection.setText(errorConnextionMessage);
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                txtConnection.setText(errorConnextionMessage);
            }
        });

    }

    private void init() {
        request = APIClient.getClient().create(APIInterface.class);
        txtConnection = findViewById(R.id.txtConnection);
    }
}