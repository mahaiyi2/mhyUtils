package cipher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public class CipherTest {
	/**
	 * 下载并解密文件
	 * 
	 * @param maping
	 * @param request
	 * @param response
	 *            2015.11.27
	 */
	public static void download() {
		InputStream in = null;
		OutputStream out = null;
		CipherInputStream cIn = null;

		try {
			in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\20170830144217.zip");
			Cipher cipher = EncryptUtil.getDeCipher();
			cIn = new CipherInputStream(in, cipher);
			out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\222decoded.zip");
			byte[] buffer = new byte[1024];
			int r;
			while ((r = cIn.read(buffer)) >= 0) {
				out.write(buffer, 0, r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 上传并解密文件
	 * 
	 * @param maping
	 * @param request
	 * @param response
	 *            2015.11.27
	 */
	public static void upload() {
		InputStream in = null;
		OutputStream out = null;
		CipherOutputStream cOut = null;
		
		try {
			in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\新建文本文档 (3).txt");
			Cipher cipher = EncryptUtil.getEnCipher();
			out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\encoded.txt");
			cOut = new CipherOutputStream(out, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = in.read(buffer)) >= 0) {
				cOut.write(buffer, 0, r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				cOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		//upload();
		download();
	}
}
