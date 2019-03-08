package com.test.xc;
/**
 * @author Hanley 
 * 2019年3月8日上午11:50:38
 *
 */
public class TicketTest {

	/**
	 * 此例体现出了  实现了Runable接口的话，则很容易的实现资源共享。
	 * @param args
	 */
	public static void main(String[] args) {
	    Ticket ticket = new Ticket(100);
	    /*Thread window01 = new Thread(ticket, "窗口01");
	    Thread window02 = new Thread(ticket, "窗口02");
	    Thread window03 = new Thread(ticket, "窗口03");*/
	    
	    new Thread(ticket, "窗口01").start();
	    new Thread(ticket, "窗口02").start();
	    new Thread(ticket, "窗口03").start();
	   
	    //window01.start();
	    //window02.start();
	    //window03.start();
	    
	   }
}
