package cn.jeeweb.modules.tbs.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.tbs.mapper.TbUserMapper;
import cn.jeeweb.modules.tbs.entity.TbUser;
import cn.jeeweb.modules.tbs.service.ITbUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: TB_USER
 * @Description: TB_USER
 * @author quhailong
 * @date 2017-12-11 17:25:49
 * @version V1.0   
 *
 */
@Transactional
@Service("tbUserService")
public class TbUserServiceImpl  extends CommonServiceImpl<TbUserMapper,TbUser> implements  ITbUserService {

}
