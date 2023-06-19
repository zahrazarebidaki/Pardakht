package ir.zhra_ze.pardakht.model.fixedline;

import com.google.gson.annotations.SerializedName;

public class FixedLineNumber{

	@SerializedName("code")
	private String code;

	@SerializedName("data")
	private Data data;

	@SerializedName("errorMessage")
	private String errorMessage;

	public String getCode(){
		return code;
	}

	public Data getData(){
		return data;
	}

	public String getErrorMessage(){
		return errorMessage;
	}
}