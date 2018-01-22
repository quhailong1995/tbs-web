package cn.jeeweb.modules.tbs.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.tbs.mapper.TbAuthquerylogAniMapper;
import cn.jeeweb.modules.tbs.entity.TbAuthquerylogAni;
import cn.jeeweb.modules.tbs.service.ITbAuthquerylogAniService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: TB_AUTHQUERYLOG_ANI
 * @Description: TB_AUTHQUERYLOG_ANI
 * @author quhl
 * @date 2018-01-02 10:08:00
 * @version V1.0   
 *
 */
@Transactional
@Service("tbAuthquerylogAniService")
public class TbAuthquerylogAniServiceImpl  extends CommonServiceImpl<TbAuthquerylogAniMapper,TbAuthquerylogAni> implements  ITbAuthquerylogAniService {

	@Override
	public Page<TbAuthquerylogAni> selectPage(Page<TbAuthquerylogAni> page,
			Wrapper<TbAuthquerylogAni> wrapper) {
		// TODO Auto-generated method stub
		
		page.setRecords(baseMapper.myselectPage(wrapper));
		return page;
	}
}
