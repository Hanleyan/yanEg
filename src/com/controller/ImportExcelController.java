package com.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.service.ImportService;
import com.service.ImportServiceImpl;

@Controller
@RequestMapping("/excel")
public class ImportExcelController {

	@Autowired
	public ImportService importService;
	
/**
 * 上传excel文件，将文件中的数据添加到mysql数据库
 * @param file
 * @param request
 * @param response
 * @param writer
 * @return
 */
	// 导入excel
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importExcel(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,PrintWriter writer) {
		//Map<String, Object> map = new HashMap<String, Object>();
		String result = importService.readExcelFile(file);
		//map.put("message", result);
		writer.print(result);
		return null;
	}

}
