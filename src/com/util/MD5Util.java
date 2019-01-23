package  com.util;


import java.security.MessageDigest;

import com.twmacinta.util.MD5;



/**
 * 采用MD5加密解密
 * @author tfq
 * @datetime 2011-10-13
 */
public class MD5Util {

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */ 
	public static String convertMD5(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}
	/**
	 * 以小写字母方式显示md5加密后的字符串
	 * @param str
	 * @return
	 */
	public static String md5(String str){
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(str.getBytes());
	            byte[] b = md.digest();
	            StringBuffer sb = new StringBuffer();
	            for (int i = 0; i < b.length; i++) {
	                int v = (int) b[i];
	                v = v < 0 ? 0x100 + v : v;
	                String cc = Integer.toHexString(v);
	                if (cc.length() == 1)
	                    sb.append('0');
	                sb.append(cc);
	            }
	            return sb.toString();
	        }
	        catch (Exception e) {
	        }
	        return "";
	 }

	/**
	 * 以大写字母方式显示md5加密后的字符串
	 * @param s
	 * @return
	 */
	public static String md5Capital(String s){
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		         try {
		             //byte[] strTemp = s.getBytes();
		             byte[] strTemp = s.getBytes("UTF-8");
		             //使用MD5创建MessageDigest对象
		             MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		             mdTemp.update(strTemp);
		             byte[] md = mdTemp.digest();
		             int j = md.length;
		             char str[] = new char[j * 2];
		             int k = 0;
		             for (int i = 0; i < j; i++) {
		                 byte b = md[i];
		                 //System.out.println((int)b);
		                 //将没个数(int)b进行双字节加密
		                 str[k++] = hexDigits[b >> 4 & 0xf];
		                 str[k++] = hexDigits[b & 0xf];
		             }
		             return new String(str);
		         } catch (Exception e) {return null;}
		}
		
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes("UTF-8");
			// 获得MD5摘要算法 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 测试主函数
	public static void main(String args[]) {
		//String s = new String("9366686182"+"CRS"+"2013"+"09"+"0871");
		String s = "123456";
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + string2MD5(s));
		//System.out.println("加密的：" + convertMD5(s));
		System.out.println("解密的：" + convertMD5("e10adc3949ba59abbe56e057f20f883e"));
		
		System.out.println(MD5("153984234853702粤S7A3W9261917D00A8B4CD080EF0A5C7E244E90"));//4025267DD17C573C3FF14E7BF73F61F4

	}
}
