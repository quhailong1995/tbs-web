package cn.jeeweb.modules.tbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.tbs.entity.DmWhTaskLog;


@Controller
@RequestMapping("${admin.url.prefix}/tbs/query/dmwhtaskLog")
@RequiresPathPermission("tbs:query:dmwhtaskLog")
public class DmWhTaskLogController  extends BaseCRUDController<DmWhTaskLog, String>{


}
