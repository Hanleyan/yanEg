package  com.util;

/**
 * 
 * Created by 
 */
public enum EnumMessageCode {
	code0("0","服务繁忙"),//服务异常
	code1("1","成功"),
	code2("2","失败"),
	code3("3","您没有操作权限或有输入项为空！"),
	code4("4","您不能下单或其他原因！"),
	code5("5","接口不存在"),
	code6("6","账号或密码不正确"),
	code7("7","参数不正确"),
	code8("8","验证码无效"),
	code9("9","账户已存在"),
	code10("10","账户不存在"),
	code11("11","密码修改失败"),
	code12("12","您的身份证号码已被使用"),
	code13("13","数据不存在"),
	code14("14","身份证照片未核实"),
	code15("15","重复操作"),
	code16("16","user登录失败，用户名或密码错误"),
	code17("17","密码或验证码错误"),

	;

    private String id;
    private String desc;

    EnumMessageCode(String id, String desc) {
    	this.setId(id);
        this.setDesc(desc);
        
    }


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	public static String getDescById(String id){
		String desc = "";
		for (EnumMessageCode obj : EnumMessageCode.values()) {
			if(obj.getId().equals(id)){
				desc = obj.getDesc();
				break;
			}
		}
		return desc;
	}
}
