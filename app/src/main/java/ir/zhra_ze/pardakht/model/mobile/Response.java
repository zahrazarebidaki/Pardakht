package ir.zhra_ze.pardakht.model.mobile;

import com.google.gson.annotations.SerializedName;

public class Response{
	private String url;

	private String error;

	public String getError() {
		return error;
	}

	public String getUrl(){
		return url;
	}
}