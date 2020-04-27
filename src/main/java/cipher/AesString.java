package cipher;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesString {
	private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";//默认的加密算法
    private static final byte[] iv = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key 加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
    
            byte[] byteContent = content.getBytes("utf-8");
            
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key),new IvParameterSpec(iv));// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密
            
            return Base64.getEncoder().encodeToString(result);//通过Base64转码返回
            
        } catch (Exception ex) {
//            Logger.getLogger(AesString.class.getName()).log(Level.SEVERE, null, ex);
        	ex.printStackTrace();
        }
    
        return null;
    }
    
    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
    
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key),new IvParameterSpec(iv));
    
            //执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content.getBytes()));
            
    
            return new String(result, "utf-8");
        } catch (Exception ex) {
//            Logger.getLogger(AesString.class.getName()).log(Level.SEVERE, null, ex);
        	ex.printStackTrace();
        }
    
        return null;
    }
    
    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        return new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
    
    }
    
    public static void main(String[] args) {
//        String content = "hello,您好";
        String key = "1111111111111111";
//        System.out.println("content:" + content);
//        String s1 = AesString.encrypt(content, key);
//        System.out.println("s1:" + s1);
        String s1 = "whISVHxIux8YfKXky1QAfw==";
        System.out.println("s2:"+AesString.decrypt(s1, key));
    
    }
}
