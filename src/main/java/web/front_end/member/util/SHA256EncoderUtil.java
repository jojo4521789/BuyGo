package web.front_end.member.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256EncoderUtil {
	public static String SHA256Encode(final String strText){
		// 演算法設定
		final String strType = "SHA-256";
		
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密開始
                // 創建加密對象 並傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 傳入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;
	}
}
