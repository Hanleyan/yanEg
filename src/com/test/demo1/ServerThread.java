
/**
 * @author Hanley
 *
 * 2019年7月29日上午11:43:06
 */
package com.test.demo1;

//*******************服务器端线程类********************//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread {
	private int port;
	private boolean OVER_FLAG = false;
	public ServerThread(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
      /**
       * 使用无线循环或者有限循环来实现客户端与服务器的对话
       */
		try {
			//1.创建连接服务器的Socket 并绑定端口号
			ServerSocket ssocket = new ServerSocket(port);
			while(!OVER_FLAG) {
			//2.监听来自客户端的请求   并返回Socket实例
			Socket socket = ssocket.accept();
			//3.获取输入流  
			InputStream is = socket.getInputStream();  
			InputStreamReader  isr = new InputStreamReader(is);  //字节流包装为字符流
			BufferedReader bf = new BufferedReader(isr);   //添加进缓存
			//4.从缓存中读取数据
			String info = null;
			if("OVER".equals(info)) {
	        	OVER_FLAG = true;
	        	break;
	        }
			while((info=bf.readLine()) != null) {
				System.out.println("客户端说："+info);
				if("OVER".equals(info)) {
		        	OVER_FLAG = true;
		        	break;
		        }
			}
			
			
			socket.shutdownInput();
			String data = null;
			System.out.print("服务端：");
			Scanner scanner = new Scanner(System.in);
	        String data1 = scanner.nextLine();
	        
			//回馈给客户端
			OutputStream os = socket.getOutputStream();
			 PrintWriter pw = new PrintWriter(os);
			//3.发送数据
			pw.write(data1);
			pw.flush();
			//4.关闭资源
			os.close();
			pw.close();
			is.close();
			bf.close();
//			socket.shutdownOutput();
			}
			System.out.println("对话结束");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}