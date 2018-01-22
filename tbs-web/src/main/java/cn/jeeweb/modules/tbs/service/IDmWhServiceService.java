package cn.jeeweb.modules.tbs.service;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.tbs.entity.DmWhService;

/**   
 * @Title: 系统服务管理
 * @Description: 系统服务管理
 * @author QuHaiLong
 * @date 2017-08-13 13:55:29
 * @version V1.0   
 *
 */
public interface IDmWhServiceService extends ICommonService<DmWhService> {

	List<DmWhService> selectListBySType(Wrapper<DmWhService> wrapper);
	
	List<DmWhService> selectListNoAuth(String channelId);
	

}

