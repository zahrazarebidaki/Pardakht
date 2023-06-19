package ir.zhra_ze.pardakht;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import ir.zhra_ze.pardakht.model.fixedline.FixedLineNumber;
import ir.zhra_ze.pardakht.model.mobile.Response;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("Inquiry/FixedLineBillInquiry")
    Call<FixedLineNumber> getInquiry(@Body JsonObject phoneNumber);

    @POST("/")
    Call<Response> buyCharge(@Body JsonObject params);
}