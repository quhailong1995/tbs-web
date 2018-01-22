package cn.jeeweb.modules.tbs.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.jeeweb.core.utils.SpringContextHolder;
import cn.jeeweb.modules.tbs.service.ITaskService;

public class TaskInitListener implements ApplicationListener<ContextRefreshedEvent> {

	protected ITaskService taskService = SpringContextHolder.getApplicationContext()
			.getBean(ITaskService.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		try {
			taskService.initSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
