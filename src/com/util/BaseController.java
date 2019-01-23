package com.util;

import org.springframework.util.Assert;

import com.entity.power.Users;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * <br>
 * <b>类描述:</b>
 * 
 * <pre>
 * 所有Controller的基类
 * </pre>
 * 
 * @see
 * @since
 */
public class BaseController {
	protected static final String ERROR_MSG_KEY = "errorMsg";

	/**
	 * 获取保存在Session中的用户对象
	 * 
	 * @param request
	 * @return
	 */
	protected Users getSessionUser(HttpServletRequest request) {
		return (Users) request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT);
	}
   
	/**
	 * 保存用户对象到Session中
	 * @param request
	 * @param DBUser
	 */
	protected void setSessionUser(HttpServletRequest request,Users DBUser) {
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT,
                DBUser);
	}
	
	/**
	 * 保存用户权限到Session中
	 * @param request
	 * @param DBUser
	 */
	/*protected List<ActionGroups> getUserRight(HttpServletRequest request) {
		return (List<ActionGroups>) request.getSession().getAttribute(CommonConstant.USER_RIGHT);
	}*/
	
	/*protected void setUserRight(HttpServletRequest request,List<ActionGroups> list) {
		request.getSession().setAttribute(CommonConstant.USER_RIGHT,
				list);
	}*/
	
	

	/**
	 * 获取基于应用程序的url绝对路径
	 * 
	 * @param request
	 * @param url
	 *            以"/"打头的URL地址
	 * @return 基于应用程序的url绝对路径
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}
	
	
}
