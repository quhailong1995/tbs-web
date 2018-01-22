package cn.jeeweb.modules.tbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.tbs.entity.DmWhXtcs;

/**   
 * @Title: 系统参数维护
 * @Description: 系统参数维护
 * @author QuHaiLong
 * @date 2017-08-30 14:53:25
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/dmwhxtcs")
@RequiresPathPermission("tbs:dmwhxtcs")
public class DmWhXtcsController extends BaseCRUDController<DmWhXtcs, String> {

}
