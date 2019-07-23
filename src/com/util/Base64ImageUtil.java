package com.util;

import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author hanley
 * @date 2019/2/21 17:31
 * 风萧萧兮易水寒
 */
public class Base64ImageUtil {

    private static final Logger log = Logger.getLogger(Base64ImageUtil.class);

    public String img(){
        String hql="from App_Verify_CardFacePhone where 1=1 and delFlag";

        return "";
    }


    public static void main(String[] args) {
        // 测试从Base64编码转换为图片文件
        String strImg = "";
        //GenerateImage(strImg, "D:\\newPic.jpg");

        // 测试从图片文件转换为Base64编码
        String b64 = GetImageStr("D:\\xyzy.jpg");
        GenerateImage(b64, "D:\\work\\newPic.jpg");
        System.out.println("ok");

    }

    public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;

        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
    public static String GetImageStr(InputStream in) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;

        // 读取图片字节数组
        try {
            //InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //去空格
    public static String fomatImageBase64(String imageStr){
        try{
            imageStr = imageStr.substring(imageStr.lastIndexOf("base64,")+7,imageStr.length());
            imageStr = imageStr.substring(0,imageStr.length()-3);
            imageStr = imageStr.replaceAll(" ", "");
            imageStr = imageStr.replaceAll("\\\\n", "");
        }catch(Exception ex){
            ex.printStackTrace();
            imageStr = "";
        }finally{
            return imageStr;
        }

    }
}
