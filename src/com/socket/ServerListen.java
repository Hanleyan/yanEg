package com.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * @author Hanley 
 * 2019年7月29日上午11:05:27
 *
 */
public class ServerListen extends Thread {
	
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			
			while(true){
				Socket socket =  serverSocket.accept();
				
				JOptionPane.showMessageDialog(null, "有客户端连接到了本机的12345端口。");
				
				//将socket传递
				new ChatServer(socket).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
