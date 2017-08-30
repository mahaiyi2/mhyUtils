package cipher;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * 文件上传加解密工具类
 * @author AB048704
 *
 */
public class EncryptUtil {
	private static SecretKey sk;
	private final static String strKey = "12345869";
	private static Cipher enCipher;
	private static Cipher deCipher;
	private EncryptUtil(){
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
			   DESKeySpec desKS = new DESKeySpec(strKey.getBytes()); 
			   SecretKeyFactory skf = SecretKeyFactory.getInstance("DES"); 
			   sk = skf.generateSecret(desKS); 
			    
 
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
				enCipher = Cipher.getInstance("DES");
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
				deCipher = Cipher.getInstance("DES");
				deCipher.init(Cipher.DECRYPT_MODE, sk);
				return deCipher;
			} catch (Exception e){
				throw new RuntimeException("Error: error occurred during getting DECRYPT MODE Cihper Cause: " + e);
			}
		}
	}
}