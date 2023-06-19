package ir.zhra_ze.pardakht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.JsonObject;

import ir.zhra_ze.pardakht.model.mobile.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChargeMobileActivity extends AppCompatActivity {
    private static final String TAG = "ChargeMobileActivity";
    private EditText etPhone, etPrice;
    private RadioGroup radioGroup;
    private Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_mobile);

        etPhone = findViewById(R.id.etPhone);
        etPrice = findViewById(R.id.etPrice);

        radioGroup = findViewById(R.id.radioGroup);

        btnBuy = findViewById(R.id.btnBuyCharge);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPhone.getText().length() > 0 && etPrice.getText().length() > 0) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("MobileNo", etPhone.getText().toString());
//                    وقتی که مفدار اپراتور تایپ رو برابر با 1 قرار میدیم در پاسخ میگوید شماره موجود نیست. حتی برای همراه اول
//                    به همین دلیل مقدار OperatorType را به صورت static روی 2 قرار میدهم
                    jsonObject.addProperty("OperatorType", 2);
                    jsonObject.addProperty("AmountPure", etPrice.getText().toString());
                    jsonObject.addProperty("mid", "0");
                    sendRequest(jsonObject);
                } else {
                    Toast.makeText(ChargeMobileActivity.this, "شماره همراه و مبلغ نمیتواند خالی باشد", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void sendRequest(JsonObject params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://topup.pec.ir/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.buyCharge(params).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response myResponse = response.body();
                if (myResponse.getError() != null){
                    Toast.makeText(ChargeMobileActivity.this, myResponse.getError(), Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(myResponse.getUrl()));
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(ChargeMobileActivity.this, "خطا در اتصال به سرور", Toast.LENGTH_SHORT).show();
            }
        });
    }
}