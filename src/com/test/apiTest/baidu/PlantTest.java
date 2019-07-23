package com.test.apiTest.baidu;
/**
 * @author Hanley 
 * 2019年4月17日上午11:59:27
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import com.util.Base64ImageUtil;

/**
* 植物识别
*/
public class PlantTest {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String plant() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";
        try {
            // 本地文件路径
            String filePath = "D:\\work\\pic\\花1.jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            
            /*File file = new File("D:\\work\\pic\\植物2.jpg");
			InputStream content = new FileInputStream(file);
			String imgParam = Base64ImageUtil.GetImageStr(content);//io流图片转成base64文件
*/
            String param = "image=" + imgParam +"&baike_num=6";

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.76404c8987aae1a064029e4a5c0da9d4.2592000.1558064670.282335-16038972";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        PlantTest.plant();
    }
}
