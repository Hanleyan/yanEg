package com.test;

import com.util.Des3Util;

/*import com.zenwide.util.encrypt.Des3Util;*/


/** 
  * 3DES加密工具类 
  */ 
public class Des3UtilTest {   
       
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