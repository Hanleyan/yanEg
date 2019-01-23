package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.service.AppService;

@Controller
public class AppController extends HttpServlet {

	private static final Log log = LogFactory.getLog(AppController.class);

	@Autowired
	public AppService appService;

	@RequestMapping("/queryCustomer.do")
	public String queryCustomer(HttpServletRequest request, ModelMap map,
			String customerName, String customerMail, String customerPhone,
			String customerAddress) {
		customerName = "hanley1999002";
		Boolean b = appService.queryCustomer(map, null, customerName,
				customerMail, customerPhone, customerAddress);
		if (b) {
			return "customer";
		} else {
			return null;
		}

	}

	/*@RequestMapping("/addFile.do")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FileItemFactory factory = new DiskFileItemFactory();
		// 创建文件上传处理器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 开始解析请求信息
		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		// 对所有请求信息进行判断
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			// 信息为普通的格式
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				String value = item.getString();
				request.setAttribute(fieldName, value);
			}
			// 信息为文件格式
			else {
				String fileName = item.getName();
				System.out.println(fileName);
				int index = fileName.lastIndexOf("\\");
				fileName = fileName.substring(index + 1);
				request.setAttribute("realFileName", fileName);
				String basePath = request.getRealPath("/images");
				File file = new File(basePath, fileName);
				try {
					item.write(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("msg", "文件上传成功!");
			getServletContext().getRequestDispatcher("/jsp/success.jsp")
					.forward(request, response);
		}
	}*/

	/**
	 * 文件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/upload.do")
	public String upload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		/*CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());*/
		MultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile((String) iter.next());
				if (file != null) {
					String fileName = file.getOriginalFilename();
					/*String path1 = Thread.currentThread()
							.getContextClassLoader().getResource("").getPath()
							+ "download" + File.separator;*/
					String path1 = "D:/testFile/";
					// 下面的加的日期是为了防止上传的名字一样
					String path = path1
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date()) +"-"+ fileName;
					File localFile = new File(path);
					file.transferTo(localFile);
					
					System.out.println(file.getContentType()+"-"+file.getName()+"-"+file.getSize());
				}
			}
		}
		request.setAttribute("msg", "文件上传成功!");
		return "addFile";
	}

	@RequestMapping("/toUpload.do")
	public String toUpload() {
		return "addFile";
	}

	/**
	 * 文件下载
	 * @param fileName
	 * @param request
	 * @param response
	 */
	@RequestMapping("/download")
	public void download(String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		try {
			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath()
					+ "download";// 这个download目录为啥建立在classes下的
			/*InputStream inputStream = new FileInputStream(new File(path + File.separator + fileName));*/
			InputStream inputStream = new FileInputStream(new File("D:/testFile/" + fileName));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
