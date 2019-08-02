package com.util;
/**
 * @author Hanley 
 * 2019年8月1日下午2:33:20
 *
 *订单支付方式 (EnumOrderPayType ：1 未支付 、2 微信支付、3支付宝支付、4银联支付、5其他) 
 */
public enum EnumOrderPayType {

	orderPayType1("1","未支付"),
	orderPayType2("2","微信支付"),
	orderPayType3("3","支付宝支付 "),
	orderPayType4("4","银联支付"),
	orderPayType5("5","其他 "),
	;
	
	private String orderPayTypeCode;
	private String orderPayTypeDesc;
	private EnumOrderPayType(String orderPayTypeCode, String orderPayTypeDesc) {
		this.orderPayTypeCode = orderPayTypeCode;
		this.orderPayTypeDesc = orderPayTypeDesc;
	}
	private EnumOrderPayType() {
	}
	public String getOrderPayTypeCode() {
		return orderPayTypeCode;
	}
	public void setOrderPayTypeCode(String orderPayTypeCode) {
		this.orderPayTypeCode = orderPayTypeCode;
	}
	public String getOrderPayTypeDesc() {
		return orderPayTypeDesc;
	}
	public void setOrderPayTypeDesc(String orderPayTypeDesc) {
		this.orderPayTypeDesc = orderPayTypeDesc;
	}
	
	/**
	 * orderPayTypeCode获取orderPayTypeDesc
	 * @param code
	 * @return
	 */
	public static String getOrderPayTypeDescByCode(Integer code){
		String desc="-";
		String orderPayTypeCode = code.toString();
		for (EnumOrderPayType enumOrderPay : EnumOrderPayType.values()) {
			if(enumOrderPay.getOrderPayTypeCode().equals(orderPayTypeCode)){
				desc=enumOrderPay.getOrderPayTypeDesc();
				break;
			}
		}
		return desc;
	}
	
	
}
