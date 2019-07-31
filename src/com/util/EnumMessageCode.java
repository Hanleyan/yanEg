package  com.util;

/**
 * 
 * Created by 
 */
public enum EnumMessageCode {
	code0("0","请与经销商联系"),//服务异常
	code1("1","成功"),
	code2("2","失败"),
	code3("3","您没有操作权限或有输入项为空！"),
	code000("000","接口不存在"),
	code001("001","账号或密码不正确"),
	code002("002","参数不正确"),
	code003("003","验证码无效"),
	code004("004","账户已存在"),
	code005("005","账户不存在"),
	code006("006","密码修改失败"),
	code007("007","您的身份证号码已被使用"),
	code008("008","数据不存在"),
	code009("009","身份证照片未核实"),
	code010("010","重复操作"),
	code011("011","中国人民银行征信中心无法进行注册，登录名已被占用"),
	code012("012","中国人民银行征信中心无法进行注册，密码过于简单"),
	code013("013","请输入您在个人信用信息服务平台本次申请信用报告的动态码"),
	code014("014","请回答验证问题"),
	code015("015","中国人民银行征信中心登录失败，登录名或密码错误"),
	code016("016","已超过3次邀请担保人机会"),

	code017("017","您还有任务未完成"),
	code018("018","运营商登入成功"),

	code019("019","user用户名或密码为空"),
	code020("020","user登录失败，用户名或密码错误"),
	code021("021","密码或验证码错误"),
	code022("022","请填入合理的担保人信息"),
	code023("023","央行报告处理中"),
	code024("024","请更换银行卡"),
	code025("025","服务密码错误"),
	code026("026","请输入正确的授权码"),
	code027("027","此设备使用超出限制"),
	code028("028","您已经修改过了"),
	code029("029","身份重复"),
	code030("030","查询失败"),
	code031("031","账号或密码不正确"),
	code032("032","服务器维护"),
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
