package cipher;
import java.security.Key;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.crypto.provider.AESKeyGenerator;
/**
 * 文件上传加解密工具类
 * @author AB048704
 *
 */
public class EncryptUtilAES {
	private static SecretKey sk;
	private final static String strKey = "adfewidjckslfm39";
	private static Cipher enCipher;
	private static Cipher deCipher;
	private EncryptUtilAES(){
	}
	
//	private static void checkKey(){
//		if(key == null){
//			try{
//				KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
//				keyGenerator.init(new SecureRandom(strKey.getBytes()));
//				key = keyGenerator.generateKey();
//				System.out.println(" key ="+key.getFormat()+"  key="+key.serialVersionUID);
//				keyGenerator = null;
//			}catch(Exception e){
//				throw new RuntimeException("Error: error occurred during setting Key Cause: " + e);
//			}
//		}
//	}
	
	private static void checkKey(){
		if(sk == null){
			try{
			   sk = new SecretKeySpec(strKey.getBytes(), "AES");
			   //SecretKeyFactory skf = SecretKeyFactory.getInstance("AES"); 
			   //sk = skf.generateSecret(keySpec); 
			    
 
			}catch(Exception e){
				throw new RuntimeException("Error: error occurred during setting Key Cause: " + e);
			}
		}
	}
	/**
	 * 获得解密Cipher
	 * @return
	 * 2015.11.27
	 * @author AB048704
	 */
	public static Cipher getEnCipher(){
		if(enCipher != null){
			return enCipher;
		}
		else{
			try {
				checkKey();
				enCipher = Cipher.getInstance("AES");
				enCipher.init(Cipher.ENCRYPT_MODE, sk);
				return enCipher;
			}catch(Exception e){
				throw new RuntimeException("Error: error occurred during getting DECRYPT MODE Cihper Cause: " + e);
			}
		}
	}
	/**
	 * 获得加密Cipher
	 * @return
	 * 2015.11.27
	 * @author AB048704
	 */
	public static Cipher getDeCipher(){
		if(deCipher != null){
			return deCipher;
		}
		else{
			try {
				checkKey();
				deCipher = Cipher.getInstance("AES");
				deCipher.init(Cipher.DECRYPT_MODE, sk);
				return deCipher;
			} catch (Exception e){
				throw new RuntimeException("Error: error occurred during getting DECRYPT MODE Cihper Cause: " + e);
			}
		}
	}
}