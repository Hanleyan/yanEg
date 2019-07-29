package com.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


/**
 * @author Hanley 
 * 2019年7月29日上午10:45:02
 *
 */
public class MyServerSocket {

	public static void main(String[] args) {

		new ServerListen().start();
	}

}
