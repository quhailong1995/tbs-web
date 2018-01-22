package cn.jeeweb.modules.tbs.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.tbs.entity.TransactionLog;

/**   
 * @Title: 用户授权查询
 * @author hft
 * @date 2017-9-22 10:38:07
 * @version V1.0   
 *
 */
public interface ITransactionLogService extends ICommonService<TransactionLog> {
	
	public TransactionLog getByEntity(TransactionLog tl);
	public TransactionLog getOneByEntity(TransactionLog tl);
}

