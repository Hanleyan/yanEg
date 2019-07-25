package com.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.entity.power.ActionType;
import org.springframework.util.Assert;

import com.entity.power.Action;
import com.entity.power.Position;
import com.entity.power.Users;


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
	 * 获取保存在Session中的用户职位对象
	 * 
	 * @param request
	 * @return
	 */
	protected Position getSessionUserPosition(HttpServletRequest request) {
		return (Position) request.getSession().getAttribute(
				CommonConstant.USER_POSITION);
	}
   
	/**
	 * 保存用户职位对象到Session中
	 * @param request
	 */
	protected void setSessionUserPosition(HttpServletRequest request,Position position) {
		request.getSession().setAttribute(CommonConstant.USER_POSITION,
				position);
	}
	
	/**
	 * 保存用户权限List到Session中
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected List<Action> getUserRight(HttpServletRequest request) {
		return (List<Action>) request.getSession().getAttribute(CommonConstant.USER_RIGHT);
	}
	
	protected void setUserRight(HttpServletRequest request,List<Action> list) {
		request.getSession().setAttribute(CommonConstant.USER_RIGHT,
				list);
	}

	/**
	 * 保存用户权限分类List到Session中
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected List<ActionType> getUserRightType(HttpServletRequest request) {
		return (List<ActionType>) request.getSession().getAttribute(CommonConstant.USER_RIGHT_TYPE);
	}

	protected void setUserRightType(HttpServletRequest request,List<ActionType> list) {
		request.getSession().setAttribute(CommonConstant.USER_RIGHT_TYPE,
				list);
	}
	/**
	 * 保存用户权限数量到Session中
	 * @param request
	 */
	protected Integer getUserRightSize(HttpServletRequest request) {
		return (Integer) request.getSession().getAttribute(CommonConstant.USER_RIGHT_SIZE);
	}
	
	protected void setUserRightSize(HttpServletRequest request,Integer userRightSize) {
		request.getSession().setAttribute(CommonConstant.USER_RIGHT_SIZE,
				userRightSize);
	}
	

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
