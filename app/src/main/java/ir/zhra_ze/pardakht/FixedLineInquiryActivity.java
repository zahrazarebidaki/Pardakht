package ir.zhra_ze.pardakht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import ir.zhra_ze.pardakht.model.fixedline.FixedLineNumber;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FixedLineInquiryActivity extends AppCompatActivity {
    private static final String TAG = "FixedLineInquiryActivit";
    private Retrofit retrofit;
    private ApiService apiService;

    private TextView tvBillingId;
    private TextView tvMidPaymentId, tvMidAmount;
    private TextView tvFinPaymentId, tvFinAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_line_inquiry);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://charge.sep.ir/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        TextInputLayout etl = findViewById(R.id.etlFixedNumber);
        TextInputEditText editText = findViewById(R.id.etFixedNumber);

        Button btnInquiry = findViewById(R.id.btnInquiry_FixedNumber);
        btnInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().length()>0){
                    sendRequest(editText.getText().toString());
                }else {
                    Toast.makeText(FixedLineInquiryActivity.this, "شماره تلفن نمیتواند خالی باشد", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvBillingId = findViewById(R.id.tvBillingID_FixedNumber);
        tvMidPaymentId = findViewById(R.id.tvMidPaymentId_FixedNumber);
        tvMidAmount = findViewById(R.id.tvMidAmount_FixedNumber);
        tvFinPaymentId = findViewById(R.id.tvFinPaymentId_FixedNumber);
        tvFinAmount = findViewById(R.id.tvFinAmount_FixedNumber);


    }

    private void sendRequest(String phoneNumber){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("FixedLineNumber", phoneNumber);
        apiService.getInquiry(jsonObject).enqueue(new Callback<FixedLineNumber>() {
            @Override
            public void onResponse(Call<FixedLineNumber> call, Response<FixedLineNumber> response) {
                FixedLineNumber fixedLineNumber = response.body();
                if (fixedLineNumber.getErrorMessage() != null){
                    Toast.makeText(FixedLineInquiryActivity.this, fixedLineNumber.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                tvBillingId.setText(fixedLineNumber.getData().getFinalTerm().getBillID());
                tvMidPaymentId.setText(fixedLineNumber.getData().getMidTerm().getPaymentID());
                tvMidAmount.setText(String.valueOf(fixedLineNumber.getData().getMidTerm().getAmount()));

                tvFinPaymentId.setText(fixedLineNumber.getData().getFinalTerm().getPaymentID());
                tvFinAmount.setText(String.valueOf(fixedLineNumber.getData().getFinalTerm().getAmount()));
            }

            @Override
            public void onFailure(Call<FixedLineNumber> call, Throwable t) {
                Toast.makeText(FixedLineInquiryActivity.this, "خطا در اتصال به سرور", Toast.LENGTH_SHORT).show();
            }
        });
    }
}