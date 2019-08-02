package com.util;
/**
 * @author Hanley 
 * 2019年8月1日下午2:21:47
 *
 */
public enum EnumOrderSource {

	orderSource1("1","立即下单 "),
	orderSource2("2","购物车下单 "),
	;
	
	private String orderSourceCode;
	private String orderSourceDesc;
	
	private EnumOrderSource(String orderSourceCode, String orderSourceDesc) {
		this.orderSourceCode = orderSourceCode;
		this.orderSourceDesc = orderSourceDesc;
	}
	
	private EnumOrderSource() {
	}

	public String getOrderSourceCode() {
		return orderSourceCode;
	}
	public void setOrderSourceCode(String orderSourceCode) {
		this.orderSourceCode = orderSourceCode;
	}
	public String getOrderSourceDesc() {
		return orderSourceDesc;
	}
	public void setOrderSourceDesc(String orderSourceDesc) {
		this.orderSourceDesc = orderSourceDesc;
	}
	
	/**
	 * orderSourceCode获取orderSourceDesc
	 * @param code
	 * @return
	 */
	public static String getOrderSourceDescByCode(Integer code){
		String desc="-";
		String orderSourceCode = code.toString();
		for (EnumOrderSource enumOrderSource : EnumOrderSource.values()) {
			if(enumOrderSource.getOrderSourceCode().equals(orderSourceCode)){
				desc=enumOrderSource.getOrderSourceDesc();
				break;
			}
		}
		return desc;
	}
	
	
}
