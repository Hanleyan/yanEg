package com.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author Hanley 
 * 2019年7月29日上午11:15:47
 *
 */
public class ChatServer extends Thread {
	
	Socket socket;

	public ChatServer(Socket socket) {
		this.socket = socket;
	}
	
	public void out(String out) {
		try {
			socket.getOutputStream().write(out.getBytes("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void run() {
		
		try {
			/*BufferedWriter bw = 
					new BufferedWriter(
							new OutputStreamWriter(
									socket.getOutputStream()));*/
			int count = 0;
			while(true){
				count++;
				out("loop:"+count);
				//bw.write("loop:"+count);
				sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
