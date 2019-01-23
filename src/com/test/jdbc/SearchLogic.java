package com.test.jdbc;


import java.io.File;   
import java.sql.Connection;   
import java.sql.ResultSet;   
import java.sql.Statement;   
import java.util.ArrayList;   
import java.util.List;   
import org.apache.lucene.analysis.Analyzer;   
import org.apache.lucene.document.Document;   
import org.apache.lucene.document.Field;   
import org.apache.lucene.index.IndexWriter;   
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;   
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/*import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;*/

public class SearchLogic {
	private static Connection conn = null;   
	private static Statement stmt = null;   
	private static  ResultSet rs = null;   
	private String searchDir = "D:\\lucene\\data";   
	private static File indexFile = null;   
	//private static Searcher searcher = null;   
	private static Analyzer analyzer = null;   
	/** 索引页面缓冲 */  
	private int maxBufferedDocs = 500;   
	/**  
	* 获取数据库数据  
	* @return ResultSet  
	* @throws Exception  
	*/  
  
 
/**  
* 为数据库检索数据创建索引  
* @param rs  
* @throws Exception  
*/  
	
	/**  
	* 搜索索引  
	* @param queryStr  
	* @return  
	* @throws Exception  
	*/  
	
	
	/**  
	* 返回结果并添加到List中  
	* @param scoreDocs  
	* @return  
	* @throws Exception  
	*/  
	
	
 


}
