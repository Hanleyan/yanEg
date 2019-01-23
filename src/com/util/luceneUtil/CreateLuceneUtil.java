package com.util.luceneUtil;


import com.util.Tool;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
/**
 *
 *TODO  Lucene工具类
 * @author Alvin
 * @date 2018年8月28日
 * @version 1.0
 */
public class CreateLuceneUtil extends  isNullUtil{

    private static final Logger log = Logger.getLogger(CreateLuceneUtil.class);

    //索引所在目录的路径
    //public static String reportIndexPath = "D:\\lucene\\luceneTest";
  //  public  String reportIndexPath = null;

    protected static final String ID = "id";
    protected static final String hanzi = "hanzi";
    protected static final String createDate = "createDate";
    protected static final String updateTime = "updateTime";
    protected static final String delFlag = "delFlag";
 
     
    public static IndexWriter writer = null;


    /**
     * 构造方法 实例化IndexWriter
     *

     * @throws IOException
     */
	public static IndexWriter getIndexWriter() {
		if (writer == null) {

			Directory fsDirectory = null;
			try {
				System.out.println("Tool.luceneIndexPath=="
						+ Tool.luceneIndexPath);
				File indexFile = new File(Tool.luceneIndexPath);
				if (!indexFile.exists())
					indexFile.mkdirs();

				fsDirectory = FSDirectory.open(Paths.get(Tool.luceneIndexPath));
				// 得到索引所在目录的路径
				// Directory directory =
				// FSDirectory.open(Paths.get(reportIndexPath));
				// 标准分词器
				Analyzer analyzer = getAnalyzer();
				// 保存用于创建IndexWriter的所有配置。
				IndexWriterConfig iwConfig = new IndexWriterConfig(analyzer);
				// 实例化IndexWriter
				writer = new IndexWriter(fsDirectory, iwConfig);
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error(ex);
			} finally {
				return writer;
			}
		} else {
			return writer;
		}

	}

    /**
     * 删除整个索引库
     *
     * @return
     */
    public static boolean deleteAllIndex() {
        try {
            getIndexWriter().deleteAll();
            System.out.println("删除索引文件");
            log.info("lucene delete all success.");
            return true;
        } catch (Exception e) {
            log.error("lucene delete all failure.", e);
            return false;
        } finally {
            if (getIndexWriter() != null) {
                try {
                    getIndexWriter().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 关闭写索引
     *
     * @throws Exception
     * @return 索引了多少个文件
     */
    public void close() throws IOException {
        getIndexWriter().close();
    }


    /**
     * 创建对象
     *
     * @param
     * @return
     */
    public static Document createIndexMainDocument(IndexWriter indexWriter,ResultSet rs) {
        Document doc = new Document();
        try {
            doc.add(new StringField(ID, rs.getInt("id") + "", Field.Store.YES));
            doc.add(new TextField(hanzi, isStrNull(rs.getString("hanzi")), Field.Store.YES));
            doc.add(new TextField(createDate, isStrNull(rs.getString("createDate")), Field.Store.YES));
            doc.add(new TextField(updateTime, isStrNull(rs.getString("updateTime")), Field.Store.YES));
            doc.add(new TextField(delFlag, isStrNull(rs.getString("delFlag")), Field.Store.YES));
           
            try {
                indexWriter.addDocument(doc);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
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

    private static Long DateToLong(String str){
        Long returnLong = 0L;
       try{
           if(!StringUtils.isEmpty(str)){
               /**
                * 先用SimpleDateFormat.parse() 方法将日期字符串转化为Date格式
                * 通过Date.getTime()方法，将其转化为毫秒数
                */
               //String date = "2001-03-15 15-37-05";
               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
               returnLong = simpleDateFormat.parse(str).getTime();
               //System.out.println("returnLong=="+returnLong);
           }
       }catch (Exception ex){
           returnLong = 0L;
       }finally {
            return returnLong;
       }

    }


    /**
     * 更新索引
     *
     * @throws Exception
     */
   /* public static void update(){
        try{
            Date date1 = new Date();
            Document doc = new Document();
            doc.add(new StringField(ID, rs.getInt("id") + "", Field.Store.YES));
            doc.add(new StringField(CREFONO, isStrNull(rs.getString("crefo_no")), Field.Store.YES));
            doc.add(new TextField(NAMECN, isStrNull(rs.getString("name_cn")), Field.Store.YES));
            doc.add(new TextField(NAMEEN, isStrNull(rs.getString("name_en")), Field.Store.YES));
            doc.add(new TextField(CREDITCODE, isStrNull(rs.getString("creditcode")), Field.Store.YES));
            doc.add(new TextField(LEGELNAMECN, isStrNull(rs.getString("legelnamecn")), Field.Store.YES));
            doc.add(new TextField(LEGELNAMECN, isStrNull(rs.getString("legelnameen")), Field.Store.YES));
            doc.add(new NumericDocValuesField(LEVEL, rs.getLong("index_level")));//新版本Lucene排序字段
            // doc.add(new NumericDocValuesField(NAMECN, rs.getString("name_cn")));//新版本Lucene排序字段
            doc.add(new NumericDocValuesField(UPDATETIME, DateToLong(rs.getString("update_time"))));//新版本Lucene排序字段
            //doc.add(new TextField("updateTimes", isStrNull(DateToLong(rs.getString("update_time"))+""), Field.Store.YES));
            doc.add(new TextField(REG, isStrNull(rs.getString("reg")), Field.Store.YES));
            doc.add(new TextField(ORG, isStrNull(rs.getString("org")), Field.Store.YES));
            doc.add(new TextField(STREETCN, isStrNull(rs.getString("streetCN")), Field.Store.YES));

            writer.updateDocument(new Term(ID,"text1"), doc);
            writer.close();
            Date date2 = new Date();
            System.out.println("更新索引耗时：" + (date2.getTime() - date1.getTime()) + "ms\n");
        }catch (Exception ex){

        }
    }*/
}
