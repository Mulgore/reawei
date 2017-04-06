package com.reawei.controller.sys;


import com.baomidou.framework.controller.SuperController;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.encrypt.SaltEncoder;
import com.baomidou.kisso.common.util.RandomUtil;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.reawei.common.MyCaptcha;
import com.reawei.common.enums.UserType;
import com.reawei.entity.RwUser;
import com.reawei.service.IRwUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 * 账户相关操作
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-13
 */
@Controller
@RequestMapping("/account")
public class AccountController extends SuperController {
	
	//锁定用户标记
	private static final String LOCKSCREEN_USER_FLAG = "LockscreenUserFlag";
	
	@Autowired
	protected IRwUserService userService;

	/**
	 * 登录
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/login")
	public String index(Model model) {
		if (isPost()) {
			String errorMsg = "用户名或密码错误";
			/**
			 * 过滤 XSS SQL 注入
			 */
			WafRequestWrapper wr = new WafRequestWrapper(request);
			String ctoken = wr.getParameter("ctoken");
			String captcha = wr.getParameter("captcha");
			if (StringUtils.isNotBlank(ctoken) 
					&& StringUtils.isNotBlank(captcha)
					&& MyCaptcha.getInstance().verification(wr, ctoken, captcha)) {
				String loginName = wr.getParameter("loginName"); 
				String password = wr.getParameter("password");
				/**
				 * <p>
				 * 用户存在，签名合法，登录成功
				 * <br>
				 * 进入后台
				 * </p>
				 */
				RwUser user = userService.selectByLoginName(loginName);
				if (user != null && SaltEncoder.md5SaltValid(loginName, user.getPassword(), password)) {
					SSOToken st = new SSOToken(request);
					st.setId(user.getId());
					st.setData(loginName);
					
					// 记住密码，设置 cookie 时长 1 周 = 604800 秒
					String rememberMe = wr.getParameter("rememberMe");
					if ("on".equals(rememberMe)) {
						request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
					}
					
					SSOHelper.setSSOCookie(request, response, st, true);
					return redirectTo("/index.html");
				}
			} else {
				errorMsg = "验证码错误";
			}
			model.addAttribute("errorMsg", errorMsg);
		}
		model.addAttribute(MyCaptcha.CAPTCHA_TOKEN, RandomUtil.get32UUID());
		return "/login";
	}


}
