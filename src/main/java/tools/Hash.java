package tools;

import java.security.MessageDigest;

public class Hash {
	public static String hashPwd(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");

            md.update(pwd.getBytes());
            byte[] digest = md.digest();
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < digest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & digest[i]));
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
