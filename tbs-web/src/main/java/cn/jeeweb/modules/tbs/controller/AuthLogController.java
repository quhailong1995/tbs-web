package cn.jeeweb.modules.tbs.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.tbs.entity.AuthLog;
import cn.jeeweb.modules.tbs.entity.TransactionLog;
import cn.jeeweb.modules.tbs.service.ITransactionLogService;

/**   
 * @Title: 授权查询日志
 * @Description: 授权查询日志
 * @author hongft
 * @date 2017-9-26
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/query/authLog")
@RequiresPathPermission("tbs:query:authLog")
public class AuthLogController extends BaseCRUDController<AuthLog, String>{
	@Resource
	ITransactionLogService transactionLogService;
	
	@RequestMapping("showDetail")
	public String showDetail(TransactionLog tl,Model model){
		model.addAttribute("transactionLog", transactionLogService.getOneByEntity(tl));
		return "modules/tbs/query/authlog/detail";
	}
}
