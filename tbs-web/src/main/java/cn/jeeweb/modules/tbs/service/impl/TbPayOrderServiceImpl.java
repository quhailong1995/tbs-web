package cn.jeeweb.modules.tbs.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.tbs.mapper.TbPayOrderMapper;
import cn.jeeweb.modules.tbs.entity.TbPayOrder;
import cn.jeeweb.modules.tbs.service.ITbPayOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 支付订单管理
 * @Description: 支付订单管理
 * @author quhailong
 * @date 2017-11-13 11:41:36
 * @version V1.0   
 *
 */
@Transactional
@Service("tbPayOrderService")
public class TbPayOrderServiceImpl  extends CommonServiceImpl<TbPayOrderMapper,TbPayOrder> implements  ITbPayOrderService {

}
