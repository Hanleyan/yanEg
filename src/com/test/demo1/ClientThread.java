package com.test.demo1;
/**
 * @author Hanley 
 * 2019年7月29日上午11:47:55
 *
 *客户端线程类
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
 
public class ClientThread extends Thread {
	private String IP;
	private int port;
	private boolean OVER_FLAG = false;
	public ClientThread(String IP ,int port) {
		this.IP = IP;
		this.port = port;
	}
 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		OutputStream os =null;
		PrintWriter pw = null;
		
		try {
			//1.建立客户端Socket  并指定发送服务器端的地址（IP + port ）
			
			//2.获取输出流
			 
			while(!OVER_FLAG) {
			 Socket socket = new Socket(IP,port);
			 os = socket.getOutputStream();
			 pw = new PrintWriter(os);
			//3.发送数据
			
			System.out.print("客户端：");
		    Scanner scanner = new Scanner(System.in);
		    String data1 = scanner.nextLine();
		 
			pw.write(data1);
			pw.flush();
			//4.关闭资源
			socket.shutdownOutput();	
			//接受来自服务器的反馈
			InputStream is = socket.getInputStream();  
			InputStreamReader  isr = new InputStreamReader(is);  //字节流包装为字符流
			BufferedReader bf = new BufferedReader(isr);   //添加进缓存
			//4.从缓存中读取数据
			String info = null;
			while((info=bf.readLine()) != null) {
				System.out.println("服务器说："+info);
				if("OVER".equals(info)) {
		        	OVER_FLAG = true;
		        	break;
		        }
			}
			  
			/***
			 * 如果获取的内容是空的 说明服务器端还没有发送数据
			 */
 
			// 5 关闭资源
			os.close();
			pw.close();
			is.close();
			bf.close();
//			socket.shutdownInput();
		}
			System.out.println("对话结束");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
