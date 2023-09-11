package core.util;

import java.util.Base64;

public class Base64Util {
	public static void main(String[] args) throws Exception {
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		final String text = "字串文字";
		final byte[] textByte = text.getBytes("UTF-8");
		//編碼
		final String encodedText = encoder.encodeToString(textByte);
		System.out.println(encodedText);
		//解碼
		System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
	}
	public static String Base64EncoderByByte(byte[] b) throws Exception {
		final Base64.Encoder encoder = Base64.getEncoder();
		final String encodedText = encoder.encodeToString(b);
		return encodedText;
	}
	
	public static String Base64EncoderByString(String str) throws Exception {
		final byte[] textByte = str.getBytes("UTF-8");
		final Base64.Encoder encoder = Base64.getEncoder();
		final String encodedText = encoder.encodeToString(textByte);
		return encodedText;
	}
	
	public static String Base64Decoder(String str) throws Exception {
		final Base64.Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(str));
	}
}
