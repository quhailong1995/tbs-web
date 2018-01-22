package cn.jeeweb.modules.tbs.service;
import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.tbs.entity.TbAuthService;

/**   
 * @Title: 系统服务授权
 * @Description: 系统服务授权
 * @author QuHaiLong
 * @date 2017-08-13 14:42:37
 * @version V1.0   
 *
 */
public interface ITbAuthServiceService extends ICommonService<TbAuthService> {

	//自定义分页查询
	Page<TbAuthService> myList(Queryable queryable);
	
}

