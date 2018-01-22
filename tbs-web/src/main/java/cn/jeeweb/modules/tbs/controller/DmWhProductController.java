package cn.jeeweb.modules.tbs.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.tbs.entity.DmWhProduct;
import cn.jeeweb.modules.tbs.service.IDmWhProductService;

/**   
 * @Title: 税银产品管理
 * @Description: 税银产品管理
 * @author QuHaiLong
 * @date 2017-08-30 14:51:23
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/dmwhproduct")
@RequiresPathPermission("tbs:dmwhproduct")
public class DmWhProductController extends BaseCRUDController<DmWhProduct, String> {
	
	@Resource
	private IDmWhProductService productService;
	
	/**
	 * 查询所有的产品
	 * @param p
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllProduct")
	public List<DmWhProduct> getAllProducts(DmWhProduct p){
		
		List<DmWhProduct> list = productService.getProductList(p);
		return list;
 		
	}
	
}
