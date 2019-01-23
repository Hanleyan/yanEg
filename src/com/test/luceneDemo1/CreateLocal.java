package com.test.luceneDemo1;

import java.util.Arrays;

public class CreateLocal {

	public static void main(String[] args) {
		/*String[] strArray = new String[] { "red", "yellow", "Black", "Green" };*/
		/*String[] strArray = new String[] { "粤 A88888", "02" ,"ysls" };
		String t = null;
		System.out.println("排序前");
		for (String s : strArray)
			System.out.print(s + "\t");
		int i, j, k;
		for (i = 0; i < strArray.length - 1; i++) {
			k = i;
			for (j = i + 1; j < strArray.length; j++) {
				Character c1 = Character.valueOf(strArray[j].charAt(0));
				Character c2 = Character.valueOf(strArray[k].charAt(0));
				if (c1.compareTo(c2) < 0)
					k = j;
			}
			if (i != k) {
				t = strArray[i];
				strArray[i] = strArray[k];
				strArray[k] = t;
			}
		}
		System.out.println("\n排序后:");
		for (String s : strArray)
			System.out.print(s + "\t");
	}*/
	
	String temp="";
	String[] strArray = new String[] {"粤A88888", "02","ysls"};
	Arrays.sort(strArray);  
	 for(String i:strArray){  
		 temp=temp+i;
         //System.out.print(i+"  ");  
     }  
	 System.out.print(temp);  
	}
}