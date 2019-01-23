package com.util.luceneUtil;



import java.io.File;



public class IndexUtil {
	
	/**
	 * 判断索引库是否已创建
	 * 
	 * @return true:存在，false：不存在
	 * @throws Exception
	 */
	public boolean existsIndex(String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String indexSufix = "/segments.gen";
		// 根据索引文件segments.gen是否存在判断是否是第一次创建索引
		File indexFile = new File(path + indexSufix);
		return indexFile.exists();
	}

	// 设置总页数
	public static int getTotalPage(int everyPage, int totalCount) {
		int totalPage = 0;
		if (totalCount % everyPage == 0) {
			totalPage = totalCount / everyPage;
		} else {
			totalPage = totalCount / everyPage + 1;
		}
		return totalPage;

	}

	// 设置起始点，需要每页显示多少，当前页
	public static int getEveryPage(int threadCount, int totalCount) {
		int everyPage = 0;
		if (totalCount % threadCount == 0) {
			everyPage = totalCount / threadCount;
		} else {
			everyPage = totalCount / threadCount + 1;
		}
		return everyPage;
	}

	// 设置起始点，需要每页显示多少，当前页
	public static int getBeginIndex(int everyPage, int currentPage) {
		currentPage = currentPage == 0 ? 1 : currentPage;
		return (currentPage - 1) * everyPage;
	}

}
