package cn.jeeweb.modules.tbs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.tbs.entity.UserAuth;
import cn.jeeweb.modules.tbs.mapper.UserAuthMapper;
import cn.jeeweb.modules.tbs.service.IUserAuthService;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

@Transactional
@Service("userAuthService")
public class UserAuthServiceImpl  extends CommonServiceImpl<UserAuthMapper,UserAuth> implements  IUserAuthService {
	
	
	@Override
	public Page<UserAuth> selectPage(Page<UserAuth> page, Wrapper<UserAuth> wrapper) {
		//wrapper.orderBy("u.auth_date", false);
		if(isHistory(wrapper)){
			page.setRecords(baseMapper.findHistoryList(page, wrapper));
		}else {
			page.setRecords(baseMapper.findList(page, wrapper));
		}
		
		return page;
	}
	
	
	@Override
	public List<UserAuth> selectList(Wrapper<UserAuth> wrapper) {
		//wrapper.orderBy("u.auth_date", false);
		if(isHistory(wrapper)){
			return baseMapper.findHistoryList(wrapper);
		}else {
			return baseMapper.findList(wrapper);
		}
	};
	
	
	
	/**
	 * 根据wrapper里的信息判断是否需要查询历史信息
	 * @param wrapper
	 * @return
	 */
	private boolean isHistory(Wrapper<UserAuth> wrapper) {
		String sqlStr = wrapper.getSqlSegment();
		Map<String, Object> map = wrapper.getParamNameValuePairs();
		int index = sqlStr.indexOf("history");
		//存在history，说明不是第一次加载
		if(index > 0){
			String key = sqlStr.substring(index + 35, index + 44);
			String value = (String)map.get(key);
			sqlStr = sqlStr.replace("history", value);
			//history对应值为1，说明是要查历史信息
			if("1".equals(value)){
				wrapper.setSqlSelect(sqlStr);
				return true;
			}
		}
		//第一次加载或者history值不为1，都不查历史信息
		wrapper.setSqlSelect(sqlStr);
		return false;

	}
	
}
