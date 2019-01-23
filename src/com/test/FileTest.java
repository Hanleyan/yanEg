package com.test;

import java.io.File;
import java.io.IOException;

/**
 * @author Hanley 
 * 2018年11月15日下午1:26:57
 *
 */
public class FileTest {

	public static void main(String[] args) throws IOException {
        File file = new File("D:/file.txt");
        File directory = new File("D:/hust/hk");
        File dir = new File("D:/hank/hu/file.txt");
        if (!directory.exists()) {
            System.out.println(directory.mkdir());
        }
        if (!dir.exists()) {
            System.out.println(dir.mkdirs());
        }
        if (!file.exists()) {
            //System.out.println(":" + file.createNewFile());
        	file.createNewFile();
        }
    }

}
