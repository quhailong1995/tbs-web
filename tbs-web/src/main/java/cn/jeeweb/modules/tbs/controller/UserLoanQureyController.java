package cn.jeeweb.modules.tbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.tbs.entity.UserLoan;

/**   
 * @Title: 用户贷款查询
 * @Description: 用户贷款查询
 * @author hongft
 * @date 2017-8-14
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/query/userLoan")
@RequiresPathPermission("tbs:query:userLoan")
public class UserLoanQureyController extends BaseCRUDController<UserLoan, String>{
	
	/**
	 * 贷款到期查询页面
	 * @return
	 */
	@RequestMapping("expire")
	public String expire(){
		
		return "modules/tbs/query/loanexpire/list";
	}
}
