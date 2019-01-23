package com.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Random;


public class Pinyin4jUtil {

    public static String homephone(String cha){
        boolean is_con = true;
        String result = "";
        while(is_con){

            String a  = cha;
            String b = getRandomJianHan(1);
            if(translation(a).equals(translation(b))){
                if(a.equals(b)){
                    continue;
                }
// System.out.println(a);
// System.out.println(b);
// System.out.println(i);
                result = b;
                is_con = false;

            }else{
                is_con = true;
            }
        }
        return result;
    }
    // 将字符串转移为ASCII码
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    public static String getRandomJianHan(int len)
    {
        String ret="";
        for(int i=0;i<len;i++){
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try
            {
                str = new String(b, "GBk"); //转成中文
            }
            catch (UnsupportedEncodingException ex)
            {
                ex.printStackTrace();
            }
            ret+=str;
        }
        return ret;
    }

    public static String translation(String src) {


        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();


        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
// 判断是否为汉字字符
                if (Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else
                    t4 += Character.toString(t1[i]);
            }
// System.out.println(t4);
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }


    /**
     * 转为大写字母, 如：中国人民银行 =====>ZHONGGUORENMINYINHAN
     */
    public static String convertUpper(String text){
        return convert(text, HanyuPinyinCaseType.UPPERCASE, false);
    }

    /**
     * 转为小写字母, 如：中国人民银行 =====>zhongguorenminyinhang
     */
    public static String convertLower(String text){
        return convert(text, HanyuPinyinCaseType.LOWERCASE, false);
    }

    /**
     * 首字母大写, 如：中国人民银行 =====>ZhongGuoRenMinYinHang
     * @author lance
     * 2016年1月16日 下午5:04:11
     */
    public static String converCapitalize(String text){
        return convert(text, null, true);
    }

    /**
     * 所有中文的第一个字母大写, 如：中国人民银行 =====>ZGRMYH
     */
    public static String capitalizeLetter(String text){
        String c = converCapitalize(text);
        if(StringUtils.isBlank(c)) {
            return "";
        }

        return StringUtils.replacePattern(c, "[a-z]", "");
    }

    /**
     * 获取首字母, 如：中国人民银行 =====>Z
     * @author lance
     * 2016年1月17日 下午10:11:57
     */
    public static String firstLetter(String text){
        String c = converCapitalize(text);
        if(StringUtils.isBlank(c)) {
            return "";
        }

        return StringUtils.substring(c, 0, 1);
    }

    /**
     * 转为拼音
     * @param text          待转化的中文字符
     * @param caseType      转化类型, 即大写小写
     * @param isCapitalize  是否首字母大写
     */
    public static String convert(String text, HanyuPinyinCaseType caseType, boolean isCapitalize) {
        if(StringUtils.isBlank(text)){
            return "";
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        if(caseType != null) {
            format.setCaseType(caseType);
            isCapitalize = false;
        }

        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] input = StringUtils.trimToEmpty(text).toCharArray();
        StringBuilder builder = new StringBuilder();
        try {
            for (char c: input) {
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if(isCapitalize) {
                        builder.append(StringUtils.capitalize(temp[0]));
                    }else {
                        builder.append(temp[0]);
                    }
                } else {
                    if(isCapitalize) {
                        builder.append(StringUtils.capitalize(Character.toString(c)));
                    }else {
                        builder.append(Character.toString(c));
                    }
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination ex) {
            ex.printStackTrace();
        }

        return builder.toString();
    }

    public static void main(String[] args) {

        String m = firstLetter("晏绍力");
        System.out.println("首字母是="+m);
    }
}
