package cn.jeeweb.modules.tbs.service;

import org.quartz.SchedulerException;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.tbs.entity.Task;

/**   
 * @Title: 定时任务管理
 * @Description: 定时任务管理
 * @author QuHaiLong
 * @date 2017-09-08 14:38:37
 * @version V1.0   
 *
 */
public interface ITaskService extends ICommonService<Task> {

/**
	 * 
	 * @title: initSchedule
	 * @description: 初始化任务
	 * @throws SchedulerException
	 * @return: void
	 */
	public void initSchedule() throws SchedulerException;

	/**
	 * 更改状态
	 * 
	 * @throws SchedulerException
	 */
	public void updateTaskStatus(Task task) throws SchedulerException;

	/**
	 * 更改任务 cron表达式
	 * 
	 * @throws SchedulerException
	 */
//	public void updateCron(String jobId) throws SchedulerException;
	
	
	public void runTaskNow(String taskId) throws SchedulerException;
}
