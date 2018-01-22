package cn.jeeweb.modules.tbs.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.modules.tbs.mapper.TbPayProductMapper;
import cn.jeeweb.modules.tbs.entity.TbPayProduct;
import cn.jeeweb.modules.tbs.entity.TbPayWay;
import cn.jeeweb.modules.tbs.service.ITbPayProductService;
import cn.jeeweb.modules.tbs.service.ITbPayWayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 支付产品管理
 * @Description: 支付产品管理
 * @author quhailong
 * @date 2017-11-13 11:37:50
 * @version V1.0   
 *
 */
@Transactional
@Service("tbPayProductService")
public class TbPayProductServiceImpl  extends CommonServiceImpl<TbPayProductMapper,TbPayProduct> implements  ITbPayProductService {
	
	@Autowired
	private ITbPayWayService tbPayWayService;
	
	
	
	@Override
	public Page<TbPayProduct> selectPage(Page<TbPayProduct> page,
			Wrapper<TbPayProduct> wrapper) {
		// TODO Auto-generated method stub
		
		page =  super.selectPage(page, wrapper);
		for(TbPayProduct tbPayProduct:page.getRecords()){
			String wayMc="";
			if(tbPayProduct.getWayDm()!=null){
				for(String wayDm:tbPayProduct.getWayDm().split(",")){
					TbPayWay tbPayWay = tbPayWayService.selectOne(new EntityWrapper<TbPayWay>().eq("way_dm", wayDm));
					if(tbPayWay!=null){
						wayMc = wayMc+tbPayWay.getWayMc()+",";
					}
				
				}
			}
			
			if(wayMc.length()>0){
				tbPayProduct.setWayDm(wayMc.substring(0, wayMc.length()-1));
			}
			
		}
		return page;
	}
	

}
