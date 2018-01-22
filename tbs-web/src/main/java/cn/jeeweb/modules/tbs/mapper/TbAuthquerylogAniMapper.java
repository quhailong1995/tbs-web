package cn.jeeweb.modules.tbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.jeeweb.modules.tbs.entity.TbAuthquerylogAni;
 
/**   
 * @Title: TB_AUTHQUERYLOG_ANI数据库控制层接口
 * @Description: TB_AUTHQUERYLOG_ANI数据库控制层接口
 * @author quhl
 * @date 2018-01-02 10:08:00
 * @version V1.0   
 *
 */
public interface TbAuthquerylogAniMapper extends BaseMapper<TbAuthquerylogAni> {
	
	
	public List<TbAuthquerylogAni> myselectPage(@Param("ew")Wrapper<TbAuthquerylogAni> arg1);
	
	
    
}