package com.util;


import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;

/** 
  * 3DES加密工具类 
  */ 
public class Des3Util {  
     // 密钥  
     //private final static String secretKey = "my.oschina.net/penngo?#@" ;   
	 private  static String secretKey = "my.oschina.net/penngo?#@" ;   
     // 向量  
	 private final static String iv = "20180724" ;  // 此向量用于在pam系统里加密解密
	 private final static String key = "3acredit20180724pamanage";
     // 加解密统一使用的编码方式  
     private final static String encoding = "utf-8" ;  
        
     /** 
      * 3DES加密 
      *  
      * @param plainText 普通文本 
      * @return 
      * @throws Exception  
      */ 
     public static String encode(String plainText) throws Exception {  
         Key deskey = null ;  
         DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
         SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );  
         deskey = keyfactory.generateSecret(spec);  
        
         Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );  
         IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
         cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
         byte [] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
         return Base64.encode(encryptData);  
     }
	public static String encode(String plainText,String key) throws Exception {
		Key deskey = null ;
		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte [] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}
     
     public static String encrypt(String plainText) throws Exception {     //加密
         Key deskey = null ;  
         DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());  
         SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );  
         deskey = keyfactory.generateSecret(spec);  
        
         Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );  
         IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
         cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
        String str = plainText;
         
         byte [] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
         return Base64.encode(encryptData);  
     }
     
     public static String decrypt(String encryptText ) throws Exception {   //解密
         Key deskey = null ;  
         DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());   
         SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );  
         deskey = keyfactory.generateSecret(spec);  
         //Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );  //desede/CBC/PKCS5Padding
		 Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );
         IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
         cipher.init(Cipher.DECRYPT_MODE, deskey, ips);  
        
         byte [] decryptData = cipher.doFinal(Base64.decode(encryptText));  
        
         return new String(decryptData, encoding);  
     }
	

	/**
      * 3DES解密 
      *  
      * @param encryptText 加密文本 
      * @return 
      * @throws Exception 
      */ 
     public static String decode(String encryptText) throws Exception {  
         Key deskey = null ;  
         DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());   
         SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );  
         deskey = keyfactory.generateSecret(spec);  
         Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );  
         IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
         cipher.init(Cipher.DECRYPT_MODE, deskey, ips);  
        
         byte [] decryptData = cipher.doFinal(Base64.decode(encryptText));  
        
         return new String(decryptData, encoding);  
     }  
     
     
     

	

	public static String padding(String str) {
		byte[] oldByteArray;
		try {
			oldByteArray = str.getBytes("UTF8");
			int numberToPad = 8 - oldByteArray.length % 8;
			byte[] newByteArray = new byte[oldByteArray.length + numberToPad];
			System.arraycopy(oldByteArray, 0, newByteArray, 0,
					oldByteArray.length);
			for (int i = oldByteArray.length; i < newByteArray.length; ++i) {
				newByteArray[i] = 0;
			}
			return new String(newByteArray, "UTF8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Crypter.padding UnsupportedEncodingException");
		}
		return null;
	}
	
	/** 
	  * Base64编码工具类 
	  * 
	  */ 
	public static class Base64 {  
	     private static final char [] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/" .toCharArray();  
	        
	     public static String encode( byte [] data) {  
	         int start = 0 ;  
	         int len = data.length;  
	         StringBuffer buf = new StringBuffer(data.length * 3 / 2 );  
	        
	         int end = len - 3 ;  
	         int i = start;  
	         int n = 0 ;  
	        
	         while (i <= end) {  
	             int d = (((( int ) data[i]) & 0x0ff ) << 16 ) | (((( int ) data[i + 1 ]) & 0x0ff ) << 8 ) | ((( int ) data[i + 2 ]) & 0x0ff );  
	        
	             buf.append(legalChars[(d >> 18 ) & 63 ]);  
	             buf.append(legalChars[(d >> 12 ) & 63 ]);  
	             buf.append(legalChars[(d >> 6 ) & 63 ]);  
	             buf.append(legalChars[d & 63 ]);  
	        
	             i += 3 ;  
	        
	             if (n++ >= 14 ) {  
	                 n = 0 ;  
	                 buf.append( " " );  
	             }  
	         }  
	        
	         if (i == start + len - 2 ) {  
	             int d = (((( int ) data[i]) & 0x0ff ) << 16 ) | (((( int ) data[i + 1 ]) & 255 ) << 8 );  
	        
	             buf.append(legalChars[(d >> 18 ) & 63 ]);  
	             buf.append(legalChars[(d >> 12 ) & 63 ]);  
	             buf.append(legalChars[(d >> 6 ) & 63 ]);  
	             buf.append( "=" );  
	         } else if (i == start + len - 1 ) {  
	             int d = ((( int ) data[i]) & 0x0ff ) << 16 ;  
	        
	             buf.append(legalChars[(d >> 18 ) & 63 ]);  
	             buf.append(legalChars[(d >> 12 ) & 63 ]);  
	             buf.append( "==" );  
	         }  
	        
	         return buf.toString();  
	     }  
	        
	     private static int decode( char c) {  
	         if (c >= 'A' && c <= 'Z' )  
	             return (( int ) c) - 65 ;  
	         else if (c >= 'a' && c <= 'z' )  
	             return (( int ) c) - 97 + 26 ;  
	         else if (c >= '0' && c <= '9' )  
	             return (( int ) c) - 48 + 26 + 26 ;  
	         else 
	             switch (c) {  
	             case '+' :  
	                 return 62 ;  
	             case '/' :  
	                 return 63 ;  
	             case '=' :  
	                 return 0 ;  
	             default :  
	                 throw new RuntimeException( "unexpected code: " + c);  
	             }  
	     }  
	        
	     /** 
	      * Decodes the given Base64 encoded String to a new byte array. The byte array holding the decoded data is returned. 
	      */ 
	        
	     public static byte [] decode(String s) {  
	        
	         ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	         try {  
	             decode(s, bos);  
	         } catch (IOException e) {  
	             throw new RuntimeException();  
	         }  
	         byte [] decodedBytes = bos.toByteArray();  
	         try {  
	             bos.close();  
	             bos = null ;  
	         } catch (IOException ex) {  
	             System.err.println( "Error while decoding BASE64: " + ex.toString());  
	         }  
	         return decodedBytes;  
	     }  
	        
	     private static void decode(String s, OutputStream os) throws IOException {  
	         int i = 0 ;  
	        
	         int len = s.length();  
	        
	         while ( true ) {  
	             while (i < len && s.charAt(i) <= ' ' )  
	                 i++;  
	        
	             if (i == len)  
	                 break ;  
	        
	             int tri = (decode(s.charAt(i)) << 18 ) + (decode(s.charAt(i + 1 )) << 12 ) + (decode(s.charAt(i + 2 )) << 6 ) + (decode(s.charAt(i + 3 )));  
	        
	             os.write((tri >> 16 ) & 255 );  
	             if (s.charAt(i + 2 ) == '=' )  
	                 break ;  
	             os.write((tri >> 8 ) & 255 );  
	             if (s.charAt(i + 3 ) == '=' )  
	                 break ;  
	             os.write(tri & 255 );  
	        
	             i += 4 ;  
	         }  
	     }  
	} 
     
     public static void main(String[] args) throws Exception{
    	 String plainText = "来自http://my.oschina.net/penngo的博客";
    	 System.out.println("my.oschina.net/penngo?#@".length());
    	
    	 plainText = "1234" ;
    	 
    	 plainText = "{\"address\":\"Sagas fast\",\"areaCode\":\"Flags\",\"checkPwd\":\"\",\"cpmCustomerId\":0,\"delFlag\":\"1\",\"email\":\"linyafei1234@163.com\",\"group\":{\"cpmCustomerId\":8891,\"delFlag\":\"1\",\"email\":\"\",\"expeditedPrice\":100000,\"groupId\":42,\"groupName\":\"SAX\",\"lanEmail\":0,\"monitorBasePrice\":50,\"monitorDiscount\":0,\"note\":\"\",\"photoPath\":\"\",\"point\":96200,\"regDate\":\"\"},\"groupId\":0,\"groupName\":\"\",\"mobileNum\":\"Fast\",\"newPwd\":\"\",\"orderMail\":\"\",\"photoPath\":\"\",\"Position\":\"Fascia\",\"pwd\":\"25f9e794323b453885f5181f1b624d0b\",\"qrCodePath\":\"\",\"realName\":\"���hH \",\"reportMail\":\"\",\"role\":{\"delFlag\":\"1\",\"menuList\":[],\"roleId\":5,\"roleName\":\"�ͻ�\"},\"roleId\":0,\"roleName\":\"\",\"statementMail\":\"\",\"tel\":\"Gas1\",\"userId\":28,\"userIp\":\"\",\"userName\":\"13188888888\",\"website\":\"Flags a\"}" ;
    	 String encryptText = Des3Util.encode(plainText);
    	 System.out.println(encryptText);
    	 System.out.println(Des3Util.decode(encryptText));

    	 System.out.println("\r\n");

		 plainText="admin";
    	 encryptText = Des3Util.encrypt(plainText); //加密  abiz20180724personmanage
    	 System.out.println("admin加密后:"+encryptText);
    	 System.out.println("admin解密后:"+Des3Util.decrypt(encryptText));//解密


		 plainText="20180724";
		 System.out.println("20180724加密后:"+Des3Util.encrypt(plainText));
		 System.out.println("20180724解密后:"+Des3Util.decrypt(encryptText));

		 plainText="Sax2008&";
		 System.out.println("Sax2008& 加密后:"+Des3Util.encrypt(plainText));
		 System.out.println("Sax2008& 解密后:"+Des3Util.decrypt(encryptText));
    	 
     }
}