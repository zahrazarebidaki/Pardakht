package ir.zhra_ze.pardakht.model.fixedline;

import com.google.gson.annotations.SerializedName;

public class FinalTerm{

	@SerializedName("USSDCode")
	private Object uSSDCode;

	@SerializedName("Amount")
	private int amount;

	@SerializedName("BillID")
	private String billID;

	@SerializedName("PaymentID")
	private String paymentID;

	@SerializedName("ValidForPayment")
	private boolean validForPayment;

	@SerializedName("Cycle")
	private Object cycle;

	public Object getUSSDCode(){
		return uSSDCode;
	}

	public int getAmount(){
		return amount;
	}

	public String getBillID(){
		return billID;
	}

	public String getPaymentID(){
		return paymentID;
	}

	public boolean isValidForPayment(){
		return validForPayment;
	}

	public Object getCycle(){
		return cycle;
	}
}