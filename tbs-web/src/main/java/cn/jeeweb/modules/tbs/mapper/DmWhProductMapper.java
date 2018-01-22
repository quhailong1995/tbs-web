package cn.jeeweb.modules.tbs.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeeweb.modules.tbs.entity.DmWhProduct;
 
/**   
 * @Title: 税银产品管理数据库控制层接口
 * @Description: 税银产品管理数据库控制层接口
 * @author QuHaiLong
 * @date 2017-08-30 14:51:23
 * @version V1.0   
 *
 */
public interface DmWhProductMapper extends BaseMapper<DmWhProduct> {
    
	public List<DmWhProduct> findProductList(DmWhProduct p);
	
	public DmWhProduct findProduct(DmWhProduct p);

}