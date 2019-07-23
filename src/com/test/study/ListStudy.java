package com.test.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;

import com.alibaba.fastjson.JSON;

/**
 * @author Hanley 
 * 2019年4月8日下午3:43:50
 * 
 * import java.util.List中所有方法study
 *
 */
public class ListStudy {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		/*list.add(1);
		list.add(2);
		list.add(3);*/
		list.add("你好");
		list.add("是的");
		list.add("今天");
		list.add("下雨");
		System.out.println("size():"+list.size());
		System.out.println("isEmpty():"+list.isEmpty());
		System.out.println("contains():"+list.contains("1"));  
		System.out.println("iterator():"+JSON.toJSON(list.iterator()));
		System.out.println("toArray():"+JSON.toJSON(list.toArray()));
		System.out.println("list:"+JSON.toJSON(list));
		
		String[] a = list.toArray(new String[0]);
		System.out.println("T[] toArray(T[] a):"+JSON.toJSON(a));
		
		list.add(null);
		System.out.println(JSON.toJSON(list.toArray()));
		
		String b = list.remove(1);//按下标移除
		System.out.println("b:"+b+" "+JSON.toJSON(list.toArray()));
		System.out.println("list.get(0):"+list.get(0)+" "+"list.get(1):"+list.get(1)+" "+"list.get(2):"+list.get(2));
		
		
		List<String> list2 = new ArrayList<String>(); 
		list2.add("5");
		list2.add("6");
		//如果list中包含指定Collection中所有元素,则返回true;否则false;
		boolean b1 = list.containsAll(list2);
		System.out.println("b1："+b1);
		
		//将传入参数中包含的所有元素都添加到list中,顺序和它们在原Collection中迭代时一致
		boolean b2 = list.addAll(list2);
		System.out.println("b2："+b2+" "+JSON.toJSON(list.toArray()));
		
		List<String> list3 = new ArrayList<String>(); 
		list3.add("7");
		list3.add("1");
		//将传入参数插入到指定list的位置,将原来该位置及其以后的元素进行后移.插入到list中的元素顺序和它们
		boolean b3 = list.addAll(3, list3);
		System.out.println("b3："+b3+" "+JSON.toJSON(list.toArray()));
		
		List<String> list4 = new ArrayList<String>(); 
		list4.add("1");
		list4.add("5");
		list4.add("6");
		list4.add("7");
		list4.add("今天");
		list4.add("你好");
		//删除list中和指定集合中相同的元素.
		//boolean b4 = list.removeAll(list4);
		//删除list中和指定集合不相同的元素.
		//boolean b4 = list.retainAll(list4);
		//System.out.println("b4："+b4+" "+JSON.toJSON(list.toArray()));
		
		//移除null元素
		list.remove(null);
		//移除元素"下雨"
		list.remove("下雨");
		//根据给定比较器,对list中元素排序
		Collections.sort(list);
		System.out.println("sort():"+JSON.toJSON(list.toArray()));
		
		//移除list中的所有元素.
		/*list.clear();
		System.out.println(JSON.toJSON(list.toArray()));*/
		
		boolean b5 = list.equals(list4);
		System.out.println("b5:"+b5+" "+JSON.toJSON(list.toArray()));
		
		//返回list的hash值.    两个list.equals为true时,其hash值必定一致
		int hashcode = list.hashCode();
		int hashcode4 = list4.hashCode();
		System.out.println("hashcode:"+hashcode+"  hashcode4:"+hashcode4);
		
		System.out.println(list4.set(2, "设置为0"));
		System.out.println("list4: "+JSON.toJSON(list4.toArray()));
		list4.add(0, "插入为0");
		System.out.println("list4: "+JSON.toJSON(list4.toArray()));
		
		list4.add("今天");
		System.out.println("list4: "+JSON.toJSON(list4.toArray()));
		
		 
		//返回list中从指定区间的元素,范围:[fromIndex,toIndex)
		List<String> subList = list4.subList(2, 4);
		 System.out.println("list4  subList(): "+JSON.toJSON(subList));
		 
		/**
	     * 查找元素首次在list中出现的位置.
	     * 如果list中不存在,则返回-1.
	     */
		int indexof = list4.indexOf("今天1");
		System.out.println(indexof);
		
		/**
	     * 返回查找元素在list中最后一次出现的索引.
	     * 如果list中不存在这个元素,则返回-1.
	     */
	    int lastIndexOf = list4.lastIndexOf("今天");
	    System.out.println(lastIndexOf);
	    
	    /*-------list迭代器------*/
	    ListIterator<String> l  = list4.listIterator();
	    System.out.println("list4  ListIterator(): "+JSON.toJSON(l));
	    
	    /*-------返回一个从指定位置开始迭代的迭代器.------*/
	    ListIterator<String> l1  = list4.listIterator(3);
	    System.out.println("list4  ListIterator(): "+JSON.toJSON(l1));
	    
	   
	}

}
