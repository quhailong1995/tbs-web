package cn.jeeweb.modules.tbs.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.tbs.mapper.TbPayWayMapper;
import cn.jeeweb.modules.tbs.entity.TbPayWay;
import cn.jeeweb.modules.tbs.service.ITbPayWayService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 支付方式管理
 * @Description: 支付方式管理
 * @author quhailong
 * @date 2017-11-13 11:40:20
 * @version V1.0   
 *
 */
@Transactional
@Service("tbPayWayService")
public class TbPayWayServiceImpl  extends CommonServiceImpl<TbPayWayMapper,TbPayWay> implements  ITbPayWayService {
	
	
	@Override
	public Page<TbPayWay> selectPage(Page<TbPayWay> page,
			Wrapper<TbPayWay> wrapper) {
		// TODO Auto-generated method stub
		page = super.selectPage(page, wrapper);
		for(TbPayWay tbPayWay:page.getRecords()){
			tbPayWay.setXybz(tbPayWay.getXybz().equals("Y")?"开启":"停用");
		}
		return page;
	}

}
