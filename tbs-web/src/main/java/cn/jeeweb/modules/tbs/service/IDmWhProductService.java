package cn.jeeweb.modules.tbs.service;

import java.util.List;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.tbs.entity.DmWhProduct;

/**   
 * @Title: 税银产品管理
 * @Description: 税银产品管理
 * @author QuHaiLong
 * @date 2017-08-30 14:51:23
 * @version V1.0   
 *
 */
public interface IDmWhProductService extends ICommonService<DmWhProduct> {
	
	public List<DmWhProduct> getProductList(DmWhProduct p);
	
	public DmWhProduct getProduct(DmWhProduct p);
}

