package com.sdigitizers.hotel.paymentgateways.payumoney;



//import com.paytm.pg.merchant.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class checksumGeneration {
	
	//Below parameters provided by Paytm
	
public static String hashCal(Model model) {
      StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(model.getKey() + "|");
        stringBuilder.append(model.getTxnId() + "|");
        stringBuilder.append(model.getAmount() + "|");
        stringBuilder.append(model.getProductName() + "|");
        stringBuilder.append(model.getFirstName() + "|");
        stringBuilder.append(model.getEmail() + "|");
        stringBuilder.append(model.getUdf1() + "|");
        stringBuilder.append(model.getUdf2() + "|");
        stringBuilder.append(model.getUdf3() + "|");
        stringBuilder.append(model.getUdf4() + "|");
        stringBuilder.append(model.getUdf5() + "||||||");
        stringBuilder.append(Constants.SALT);
    
        String hashString = stringBuilder.toString();
	StringBuilder hash = new StringBuilder();
	MessageDigest messageDigest = null;
	try {
		messageDigest = MessageDigest.getInstance("SHA-512");
		messageDigest.update(hashString.getBytes());
		byte[] mdbytes = messageDigest.digest();
		for (byte hashByte : mdbytes) {
			 hash.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
		}
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	return hash.toString();
}
        
}
