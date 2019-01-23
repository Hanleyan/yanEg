package com.util.luceneUtil;


import com.alibaba.fastjson.JSON;
import com.entity.Customer;
import com.entity.HanziIndex;
import com.util.DateUtil;
import com.util.Tool;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 *TODO  Lucene工具类
 * @author Alvin
 * @date 2018年8月28日
 * @version 1.0
 */
public class SearchLuceneUtil extends  isNullUtil{

    private static final Logger log = Logger.getLogger(SearchLuceneUtil.class);

    private static   int hitsPerPage = 100;

    //索引所在目录的路径
    protected static final String LEVEL = "level";
    public static IndexWriter writer = null;

    /**
     *  * @author Alvin
     * @date 2018年9月13日
     * 搜索公司名称中英文
     * @version 1.0
     * */
   /* public static List<Customer> getIndexListByCodeNameCNAndEN(String key, int pageNo, int pageSize) {
        long start = System.currentTimeMillis();
        IndexReader indexReader = null;
        String flag = "0";
        Tool tool = new Tool();
        try {
          //  Analyzer analyzer = new IKAnalyzer(true);
			//StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_7_2_1);
            Analyzer analyzer =  getAnalyzer();

            // 2、获取读取器
            indexReader = getIndexReader();
            // 3、创建搜索器
            IndexSearcher indexSearcher = getIndexSearcher(indexReader);

            Query query = null;

            Collector collector = null;
            ScoreDoc[] hits = null;
            if ((key != null) && (!key.trim().equals(""))) {
                key = tool.ios8859ByUtf8(key);
                key = key.replace("有限公司","");//去除中文搜索关键字中的有限公司
               // collector = TopScoreDocCollector.create(hitsPerPage);
                String resultKey = Tool.FilterString(key);
                if (resultKey.matches("[a-zA-Z]+") || resultKey.matches(".*\\d.*")) {
                    System.out.println("搜英文");
                    query = new QueryParser("nameEN", analyzer).parse(key);
                    flag = "2";
                } else {
                    System.out.println("搜中文:" + resultKey);
                    query = new QueryParser("nameCN", analyzer).parse(resultKey);
                    flag = "1";
                }
                //Sort sort = new Sort(new SortField(LEVEL, SortField.Type.STRING, true));
               // Sort sort = new Sort(new SortField(LEVEL, SortField.Type.INT, true));
                //Sort sort = new Sort(new SortField[]{new SortField(LEVEL, SortField.Type.INT, true),new SortField("update_time", SortField.Type.LONG, true)});
                Sort sort = new Sort(new SortField[]{new SortField(LEVEL, SortField.Type.INT, true),new SortField("update_time", SortField.Type.INT, true)});
                collector = TopFieldCollector.create(sort, hitsPerPage, false,true, false, false);
                collector = TopScoreDocCollector.create(hitsPerPage);
                indexSearcher.search(query, collector);
                hits = ((TopDocsCollector)collector).topDocs().scoreDocs;
            }

            System.out.println("query =" + query);
            List<BasicInfos> list = new ArrayList<BasicInfos>();

            System.out.println("一共查询了 " + hits.length + " hits.");
            int endIndex = pageNo * pageSize;
            int len = hits.length;
            if (endIndex > len) {
                endIndex = len;
            }

            String crefoNo = "";
            String nameEN = "";
            String nameCN = "";
            if (flag.equals("2")) {
//		          for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {
                for (int i = 0; i < hits.length; i++) {
                    Document d = indexSearcher.doc(hits[i].doc);
                    nameEN = d.get("nameEN");
//		            nameEN = Tool.FilterString(nameEN);
                    if (nameEN.contains(key)){
//		            if (true){
                        crefoNo = d.get("crefoNo");
                        nameCN = d.get("nameCN");
                        nameEN = d.get("nameEN");
                        String creditcode = d.get("creditcode");
                        String streetcn = d.get("streetcn");
                        String streeten = d.get("streeten");
                        String citycn = d.get("citycn");
                        String cityen = d.get("cityen");
                        String postCode = d.get("postCode");
                        String provincecn = d.get("provincecn");
                        String provinceen = d.get("provinceen");
                        String reg = d.get("reg");
                        String tax = d.get("tax");
                        String org = d.get("org");
                        String level = d.get("level");
                        Integer levels = 0;
                        if(null!=level && !"".equals(level)){
                            levels = Integer.parseInt(level);
                        }
                                                System.out.println("crefoNo=="+crefoNo+"--nameCN=="+nameCN+"--nameEN=="+nameEN+"--level=="+level+"----分数=="+hits[i].toString()+"----"+hits[i].doc);
                        list.add(new BasicInfos(crefoNo,nameCN,nameEN,"",citycn,provincecn,creditcode,reg,"","公司英文名称",levels));

                        if(list.size() >= endIndex){
                            break;
                        }
                    }else{
                        if(null!=nameEN && !"".equals(nameEN)){
                            nameEN = nameEN.toLowerCase();
                        }
                        if(null!=key && !"".equals(key)){
                            key = key.toLowerCase();
                        }
                        if(nameEN.contains(key)){
//		            	if (true){
                            crefoNo = d.get("crefoNo");
                            nameCN = d.get("nameCN");
                            nameEN = d.get("nameEN");
                            String creditcode = d.get("creditcode");
                            String streetcn = d.get("streetcn");
                            String streeten = d.get("streeten");
                            String citycn = d.get("citycn");
                            String cityen = d.get("cityen");
                            String postCode = d.get("postCode");
                            String provincecn = d.get("provincecn");
                            String provinceen = d.get("provinceen");
                            String reg = d.get("reg");
                            String tax = d.get("tax");
                            String org = d.get("org");
                            String level = d.get("level");
                            Integer levels = 0;
                            if(null!=level && !"".equals(level)){
                                levels = Integer.parseInt(level);
                            }
                            System.out.println("crefoNo=="+crefoNo+"--nameCN=="+nameCN+"--nameEN=="+nameEN+"--level=="+level+"----分数=="+hits[i].toString()+"----"+hits[i].doc);
                            list.add(new BasicInfos(crefoNo,nameCN,nameEN,"",citycn,provincecn,creditcode,reg,"","公司中文名称",levels));
                        }
                        if(list.size() >= endIndex){
                            break;
                        }
                    }
                }

            }

            if (flag.equals("1")){
                String resultKey = Tool.FilterString(key);
//			          for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {
                for (int i = 0; i < hits.length; i++) {
                    Document d = indexSearcher.doc(hits[i].doc);
                    crefoNo = d.get("crefoNo");
                    nameCN = d.get("nameCN");
                    nameEN = d.get("nameEN");
                    String creditcode = d.get("creditcode");
                    String streetcn = d.get("streetcn");
                    String streeten = d.get("streeten");
                    String citycn = d.get("citycn");
                    String cityen = d.get("cityen");
                    String postCode = d.get("postCode");
                    String provincecn = d.get("provincecn");
                    String provinceen = d.get("provinceen");
                    String reg = d.get("reg");
                    String tax = d.get("tax");
                    String org = d.get("org");
                    String level = d.get("level");
                    Integer levels = 0;
                    if(null!=level && !"".equals(level)){
                        levels = Integer.parseInt(level);
                    }
                    String updateTimes = d.get("updateTime");
                    System.out.println("crefoNo=="+crefoNo+"--nameCN=="+nameCN+"--nameEN=="+nameEN+"--level=="+level+"----分数=="+hits[i].toString()+"----"+hits[i].doc+"---updateTimes=="+updateTimes);
                    if (nameCN.contains(resultKey)){
                        //   System.out.println("crefoNo=="+crefoNo+"--nameCN=="+nameCN+"--nameEN=="+nameEN+"--level=="+level+"----分数=="+hits[i].toString()+"----"+hits[i].doc);
                        list.add(new BasicInfos(crefoNo,nameCN,nameEN,"",citycn,provincecn,creditcode,reg,"","公司中文名称",levels));
                    }else{
                        String nameCNs = Tool.FilterString(nameCN);
                        if (nameCNs.contains(resultKey)){
                            list.add(new BasicInfos(crefoNo,nameCN,nameEN,"",citycn,provincecn,creditcode,reg,"","公司中文名称",levels));
                        }
                    }


                    if(list.size() >= endIndex){
                        break;
                    }
                }
            }
            if(null==list || list.size()==0){
                System.out.println("结果为空，去除公司名分公司或厂后面的关键字");
                list = getIndexListByCodeNameCNAndEN_NotSort(key, pageNo, pageSize,query);
            }

            return list;
        } catch (Exception e) {
            log.info("索引失败：" + e);
            e.printStackTrace();
        }
        return null;
    }

    *//**
     *  * @author Alvin
     * @date 2018年9月13日
     * 搜索公司中文地址
     * @version 1.0
     * *//*
    public static List<BasicInfos> getIndexListByCodeAddressCN(String key, int pageNo, int pageSize) {
        long start = System.currentTimeMillis();
        IndexReader indexReader = null;
        String flag = "0";
        Tool tool = new Tool();
        try {
            Analyzer analyzer =  getAnalyzer();
            // 2、获取读取器
            indexReader = getIndexReader();
            // 3、创建搜索器
            IndexSearcher indexSearcher = getIndexSearcher(indexReader);

            Query query = null;
            Collector collector = null;
            ScoreDoc[] hits = null;
            if ((key != null) && (!key.trim().equals(""))) {
                key = tool.ios8859ByUtf8(key);
                // collector = TopScoreDocCollector.create(hitsPerPage);
                String resultKey = Tool.FilterString(key);
                query = new QueryParser("streetcn", analyzer).parse(resultKey);
                flag = "1";
                collector = TopScoreDocCollector.create(hitsPerPage);
                indexSearcher.search(query, collector);
                hits = ((TopDocsCollector)collector).topDocs().scoreDocs;
            }

            System.out.println("query =" + query);
            List<BasicInfos> list = new ArrayList<BasicInfos>();

            System.out.println("一共查询了 " + hits.length + " hits.");
            int endIndex = pageNo * pageSize;
            int len = hits.length;
            if (endIndex > len) {
                endIndex = len;
            }

            String crefoNo = "";
            String nameEN = "";
            String nameCN = "";


            if (flag.equals("1")){
                String resultKey = Tool.FilterString(key);
//			          for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {
                for (int i = 0; i < hits.length; i++) {
                    Document d = indexSearcher.doc(hits[i].doc);
                    crefoNo = d.get("crefoNo");
                    nameCN = d.get("nameCN");
                    nameEN = d.get("nameEN");
                    String creditcode = d.get("creditcode");
                    String streetcn = d.get("streetcn");
                    String streeten = d.get("streeten");
                    String citycn = d.get("citycn");
                    String cityen = d.get("cityen");
                    String postCode = d.get("postCode");
                    String provincecn = d.get("provincecn");
                    String provinceen = d.get("provinceen");
                    String reg = d.get("reg");
                    String tax = d.get("tax");
                    String org = d.get("org");
                    String level = d.get("level");
                    Integer levels = 0;
                    if(null!=level && !"".equals(level)){
                        levels = Integer.parseInt(level);
                    }
                    String updateTimes = d.get("updateTime");
                    System.out.println("crefoNo=="+crefoNo+"--nameCN=="+nameCN+"--nameEN=="+nameEN+"--level=="+level+"----分数=="+hits[i].toString()+"----"+hits[i].doc+"---updateTimes=="+updateTimes+"--streetcn=="+streetcn);
                    if (streetcn.contains(resultKey)){
                        //   System.out.println("crefoNo=="+crefoNo+"--nameCN=="+nameCN+"--nameEN=="+nameEN+"--level=="+level+"----分数=="+hits[i].toString()+"----"+hits[i].doc);
                        list.add(new BasicInfos(crefoNo,nameCN,nameEN,"",citycn,provincecn,creditcode,reg,"","公司地址中文",levels));
                    }else{
                        String nameCNs = Tool.FilterString(streetcn);
                        if (nameCNs.contains(resultKey)){
                            list.add(new BasicInfos(crefoNo,nameCN,nameEN,"",citycn,provincecn,creditcode,reg,"","公司地址中文",levels));
                        }
                    }


                    if(list.size() >= endIndex){
                        break;
                    }
                }
            }
            if(null==list || list.size()==0){
                System.out.println("结果为空，去除公司名分公司或厂后面的关键字");
                list = getIndexListByCodeNameCNAndEN_NotSort(key, pageNo, pageSize,query);
            }

            return list;
        } catch (Exception e) {
            log.info("索引失败：" + e);
            e.printStackTrace();
        }
        return null;
    }


    public static List<BasicInfos> getIndexListByCodeNameCNAndEN_NotSort(String key, int pageNo, int pageSize,Query query) {
        long start = System.currentTimeMillis();
        IndexReader indexReader = null;
        String flag = "0";
        String[] keyArry = null;
        Tool tool = new Tool();
        try {
// 3、创建搜索器
            IndexSearcher indexSearcher = getIndexSearcher(indexReader);

            Collector collector = null;
            ScoreDoc[] hits = null;
            if(null!=query){
                collector = TopScoreDocCollector.create(hitsPerPage);
                indexSearcher.search(query, collector);
                hits = ((TopDocsCollector)collector).topDocs().scoreDocs;
                flag = "2";
            }


            System.out.println("query =" + query);
            List<BasicInfos> list = new ArrayList<BasicInfos>();

            System.out.println("一共查询了 " + hits.length + " hits.");
            int endIndex = pageNo * pageSize;
            int len = hits.length;
            if (endIndex > len) {
                endIndex = len;
            }

            String crefoNo = "";
            String nameEN = "";
            String nameCN = "";
            if (flag.equals("2")) {
//		          for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {
                for (int i = 0; i < hits.length; i++) {
                    Document d = indexSearcher.doc(hits[i].doc);
                    nameEN = d.get("nameEN");
//		            nameEN = Tool.FilterString(nameEN);
                    if (nameEN.contains(key)){
//		            if (true){
                        crefoNo = d.get("crefoNo");
                        nameCN = d.get("nameCN");
                        nameEN = d.get("nameEN");
                        String creditcode = d.get("creditcode");
                        String citycn = d.get("citycn");
                        String provincecn = d.get("provincecn");
                        String reg = d.get("reg");
                        String level = d.get("level");
                        Integer levels = 0;
                        if(null!=level && !"".equals(level)){
                            levels = Integer.parseInt(level);
                        }
                        System.out.println("crefoNo=="+crefoNo+"--nameCN=="+nameCN+"--nameEN=="+nameEN+"--level=="+level+"----分数=="+hits[i].toString()+"----"+hits[i].doc);
                        list.add(new BasicInfos(crefoNo,nameCN,nameEN,"",citycn,provincecn,creditcode,reg,"","公司中文名称",levels));

                        if(list.size() >= endIndex){
                            break;
                        }
                    }else{
                        if(null!=nameEN && !"".equals(nameEN)){
                            nameEN = nameEN.toLowerCase();
                        }
                        if(null!=key && !"".equals(key)){
                            key = key.toLowerCase();
                        }
                        if(nameEN.contains(key)){
//		            	if (true){
                            crefoNo = d.get("crefoNo");
                            nameCN = d.get("nameCN");
                            nameEN = d.get("nameEN");
                            String creditcode = d.get("creditcode");
                            String streetcn = d.get("streetcn");
                            String streeten = d.get("streeten");
                            String citycn = d.get("citycn");
                            String cityen = d.get("cityen");
                            String postCode = d.get("postCode");
                            String provincecn = d.get("provincecn");
                            String provinceen = d.get("provinceen");
                            String reg = d.get("reg");
                            String tax = d.get("tax");
                            String org = d.get("org");
                            String level = d.get("level");
                            Integer levels = 0;
                            if(null!=level && !"".equals(level)){
                                levels = Integer.parseInt(level);
                            }
                            System.out.println("crefoNo=="+crefoNo+"--nameCN=="+nameCN+"--nameEN=="+nameEN+"--level=="+level+"----分数=="+hits[i].toString()+"----"+hits[i].doc);
                            list.add(new BasicInfos(crefoNo,nameCN,nameEN,"",citycn,provincecn,creditcode,reg,"","公司中文名称",levels));
                        }
                        if(list.size() >= endIndex){
                            break;
                        }
                    }
                }

            }


            return list;
        } catch (Exception e) {
            log.info("索引失败：" + e);
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * @author Alvin
     * @date 2018年9月13日
     * 搜索3ACN 注册号码 统一征信代码 组织机构代码
     * @version 1.0
     * @throws Exception 
     * */
    public static List<HanziIndex> getIndexListByCodeList(String key, int pageNo, int pageSize) throws IOException, Exception {
    	System.out.println("进212");
    	
        IndexReader indexReader = null;
        List<HanziIndex> list = new ArrayList<HanziIndex>();
        String searchSource = "";
        Query query = null;
        String[] keyNum = null;
        TopDocs topDocs = null;
        ScoreDoc[] hits = null;
        Tool tool = null;
        Collector collector = null;
        //获取读取器
        indexReader = getIndexReader();
        //创建搜索器
        IndexSearcher indexSearcher = getIndexSearcher(indexReader);
        /*if (key.length() == 18){
            //创建一个TermQuery（精准查询）对象，指定查询的域与查询的关键词
            query = new TermQuery(new Term("customerName", key));
            //执行查询第一个参数是查询对象，第二个参数是查询结果返回的最大值
            topDocs = indexSearcher.search(query, hitsPerPage);
            searchSource = "id";
        }else if (key.length() == 15){
            query = new TermQuery(new Term("customerName",key));
            topDocs = indexSearcher.search(query, hitsPerPage);
            searchSource = "地址";
        }else if (key.length() == 10){
            query = new TermQuery(new Term("customerName",key));
            topDocs = indexSearcher.search(query, hitsPerPage);
            searchSource = "3ACN";
        }else if (key.length() == 9){
            query = new TermQuery(new Term("customerName",key));
            topDocs = indexSearcher.search(query, hitsPerPage);
            searchSource = "组织机构代码";
        }else{*/
        	log.info("查这里 ");
        	
        	String resultKey = Tool.FilterString(key);
        	System.out.println("搜中文");
            //query = new QueryParser("hanzi", analyzer).parse(key);
       
        	
            query = new TermQuery(new Term("hanzi",key));
            topDocs = indexSearcher.search(query, hitsPerPage);
            searchSource = "汉字";
        //}
        collector = TopScoreDocCollector.create(hitsPerPage);
        indexSearcher.search(query, collector);
        hits = ((TopDocsCollector) collector).topDocs().scoreDocs;
        
        System.out.println("SearchLuceneUtil 505  一共查询了 " + hits.length + " hits.");
        //int hitsPerPage = 1000;
        //Collector collector = null;
        //查询结果的总条数
        System.out.println("查询结果 ："+ JSON.toJSONString(topDocs));
        System.out.println("查询结果的总条数："+ topDocs.totalHits);
        //遍历查询结果
        //topDocs.scoreDocs存储了document对象的id
        //ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        int endIndex = pageNo * pageSize;
        int len = hits.length;
        if (endIndex > len) {
            endIndex = len;
        }

        for (int i = 0; i < hits.length; i++) {
            Document d = indexSearcher.doc(hits[i].doc);
            String id = d.get("id");
            String hanzi = d.get("hanzi");
            String createDate = d.get("createDate");
            String updateTime = d.get("updateTime");
            String delFlag = d.get("delFlag");
            
            list.add(new HanziIndex(Integer.parseInt(id),hanzi,createDate,updateTime,delFlag));
            if (list.size() >= endIndex) {
                break;
            }
        }
        System.out.println("查询结果 list："+ JSON.toJSONString(list));
        return list;
    }

    /**
     *  * @author Alvin
     * @date 2018年9月13日
     * 搜索法人名称
     * @version 1.0
     * */
    /*public static List<BasicInfos> getIndexListByLegelName(String key,int pageNo, int pageSize) throws ParseException {
        IndexReader indexReader = null;
        String[] keyArray = null;
        String flog = "0";

        Analyzer analyzer = new IKAnalyzer(true);
        //StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_2);
        // 2、获取读取器
        indexReader = getIndexReader();
        // 3、创建搜索器
        IndexSearcher indexSearcher = getIndexSearcher(indexReader);
        String searchSource = "法人名称";
        Query query = null;
        Collector collector = null;
        ScoreDoc[] hits = null;
        String resultKey = Tool.FilterString(key);
        List<BasicInfos> list = new ArrayList<BasicInfos>();
        String flag = "2";
        try{
            if ((key != null) && (!key.trim().equals(""))) {
                collector = TopScoreDocCollector.create(hitsPerPage);
                System.out.println("搜法人名称:" + resultKey);
                query = new QueryParser("legelnamecn", analyzer).parse(resultKey);
                Sort sort = new Sort(new SortField(LEVEL, SortField.Type.INT, true));
                collector = TopFieldCollector.create(sort, hitsPerPage, false,false, false, false);
                indexSearcher.search(query, collector);
                hits = ((TopDocsCollector)collector).topDocs().scoreDocs;
            }

            System.out.println("query =" + query);
            System.out.println("一共查询了 " + hits.length + " hits.");
            int endIndex = pageNo * pageSize;
            int len = hits.length;
            if (endIndex > len) {
                endIndex = len;
            }

            String crefoNo = "";
            String nameEN = "";
            String nameCN = "";
            if (flag.equals("2")) {
//		          for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {
                for (int i = 0; i < hits.length; i++) {
                    Document d = indexSearcher.doc(hits[i].doc);
                    crefoNo = d.get("crefoNo");
                    nameCN = d.get("legelnamecn");
                    String legelnamecn = d.get("legelnamecn");
                    System.out.println("legelnamecn="+nameCN);
                    if(null!=nameCN &&!"".equals(nameCN) && nameCN.equals(key)){
                        nameCN = d.get("nameCN");
                        nameEN = d.get("nameEN");
                        String creditcode = d.get("creditcode");
                        String citycn = d.get("citycn");
                        String provincecn = d.get("provincecn");
                        String reg = d.get("reg");
                        String level = d.get("level");
                        Integer levels = 0;
                        if(null!=level && !"".equals(level)){
                            levels = Integer.parseInt(level);
                        }
                        list.add(new BasicInfos(crefoNo,nameCN,nameEN,"",citycn,provincecn,creditcode,reg,"",searchSource,legelnamecn,levels));
                    }
                    if(list.size()>= endIndex){
                        break;
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            return list;
        }

    }*/


    private static IndexReader indexReader = null;//读取器
    private static IndexSearcher indexSearcher = null;


    public static IndexReader getIndexReader() {

        if(null==indexReader){
            try{
                // 得到读取索引文件的路径
                Directory  fsDirectory = FSDirectory.open(Paths.get(Tool.luceneIndexPath));
                // 通过dir得到的路径下的所有的文件
                indexReader = DirectoryReader.open(fsDirectory);
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                return indexReader;
            }
        }else{
            return indexReader;
        }

    }

    public static IndexSearcher getIndexSearcher(IndexReader indexReader) {
        if(null==indexSearcher){
            indexSearcher = new IndexSearcher(indexReader);
        }
        return indexSearcher;
    }
    private static Analyzer analyzer = null;
    //获取分词器
    public static Analyzer getAnalyzer(){
        if(null==analyzer){
            //analyzer = new StandardAnalyzer();
            analyzer = new IKAnalyzer(true);
        }
        return analyzer;
    }

}
