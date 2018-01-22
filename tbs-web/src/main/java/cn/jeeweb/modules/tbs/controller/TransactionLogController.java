package cn.jeeweb.modules.tbs.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.tbs.entity.TransactionLog;
import cn.jeeweb.modules.tbs.service.ITransactionLogService;

/**   
 * @Title: 平台交易日志查询
 * @author hongft
 * @date 2017-9-22
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/query/transactionLog")
@RequiresPathPermission("tbs:query:transactionLog")
public class TransactionLogController extends BaseCRUDController<TransactionLog, String>{
	
	@Resource
	ITransactionLogService transactionLogService;
	
	@RequestMapping("showDetail")
	public String showDetail(String xh,Model model){
		TransactionLog tl = new TransactionLog();
		tl.setId(xh);
		model.addAttribute("transactionLog", transactionLogService.getByEntity(tl));
		return "modules/tbs/query/transactionlog/detail";
	}
}
