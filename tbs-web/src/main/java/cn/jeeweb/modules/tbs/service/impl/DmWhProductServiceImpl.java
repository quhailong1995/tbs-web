package cn.jeeweb.modules.tbs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.CacheUtils;
import cn.jeeweb.modules.sys.security.shiro.realm.UserRealm.Principal;
import cn.jeeweb.modules.sys.utils.UserUtils;
import cn.jeeweb.modules.tbs.mapper.DmWhProductMapper;
import cn.jeeweb.modules.tbs.entity.DmWhChannel;
import cn.jeeweb.modules.tbs.entity.DmWhProduct;
import cn.jeeweb.modules.tbs.entity.DmWhService;
import cn.jeeweb.modules.tbs.service.IDmWhChannelService;
import cn.jeeweb.modules.tbs.service.IDmWhProductService;
import cn.jeeweb.modules.tbs.service.IDmWhServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 税银产品管理
 * @Description: 税银产品管理
 * @author QuHaiLong
 * @date 2017-08-30 14:51:23
 * @version V1.0   
 *
 */
@Transactional
@Service("dmWhProductService")
public class DmWhProductServiceImpl  extends CommonServiceImpl<DmWhProductMapper,DmWhProduct> implements  IDmWhProductService {

	@Autowired
	private IDmWhChannelService dmWhChannelService;
	@Autowired
	private IDmWhServiceService dmWhServiceService;
	
	@Resource
	private DmWhProductMapper productMapper;
	
	@Override
	public Page<DmWhProduct> selectPage(Page<DmWhProduct> page,
			Wrapper<DmWhProduct> wrapper) {
		// TODO Auto-generated method stub
		Page<DmWhProduct> dmWhProductPage = super.selectPage(page, wrapper);
		for(DmWhProduct dmWhProduct : dmWhProductPage.getRecords()){
			DmWhChannel dmWhChannel = dmWhChannelService.selectById(dmWhProduct.getChannelId());
			DmWhService dmWhService = dmWhServiceService.selectById(dmWhProduct.getServiceId());
			dmWhProduct.setDmWhChannel(dmWhChannel);
			dmWhProduct.setDmWhService(dmWhService);
		}
		return dmWhProductPage;
	}
	
	@Override
	public boolean insert(DmWhProduct entity) {
		// TODO Auto-generated method stub
		Principal principal = UserUtils.getPrincipal();
		entity.setCreateName(principal.getUsername());
		entity.setCreateDate(new Date());
/*		entity.setXybz("Y");
		entity.setYxbz("Y");*/
		return super.insert(entity);
	}
	
	@Override
	public boolean insertOrUpdate(DmWhProduct arg0) {
		// TODO Auto-generated method stub
		Principal principal = UserUtils.getPrincipal();
		arg0.setUpdateName(principal.getUsername());
		arg0.setUpdateDate(new Date());
		CacheUtils.remove("dmWhProductCache", "id_"+arg0.getId());
		return super.insertOrUpdate(arg0);
	}
	
	@Override
	public DmWhProduct selectById(Serializable id) {
		// TODO Auto-generated method stub
		DmWhProduct dmWhProduct = null;
		dmWhProduct = (DmWhProduct) CacheUtils.get("dmWhProductCache", "id_"+id);
		if(dmWhProduct!=null){return dmWhProduct;}
		dmWhProduct = super.selectById(id);
		CacheUtils.put("dmWhProductCache", "id_"+id, dmWhProduct);
		return dmWhProduct;
	}
	
	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		for(Serializable id : idList){
			CacheUtils.remove("dmWhProductCache", "id_"+id);
		}
		return super.deleteBatchIds(idList);
	}

	@Override
	public List<DmWhProduct> getProductList(DmWhProduct p) {
		
		return productMapper.findProductList(p);
	}

	@Override
	public DmWhProduct getProduct(DmWhProduct p) {
		return productMapper.findProduct(p);
	}
	
}
