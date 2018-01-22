package cn.jeeweb.modules.tbs.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.tbs.entity.TbAuthContent;
import cn.jeeweb.modules.tbs.entity.TbAuthService;

/**   
 * @Title: 授权内容管理
 * @Description: 授权内容管理
 * @author QuHaiLong
 * @date 2017-08-14 14:38:37
 * @version V1.0   
 *
 */
public interface ITbAuthContentService extends ICommonService<TbAuthContent> {

	/**
	 * 
	 * @description :   更新和保存授权信息的公共方法
	 * @author  	: QuHaiLong
	 * @date		: 2017年8月16日 下午4:37:08
	 * @param entity	
	 *
	 */
	void saveOrUpdateTbAuthContent(final TbAuthContent entity , TbAuthService tbAuthService);
}

