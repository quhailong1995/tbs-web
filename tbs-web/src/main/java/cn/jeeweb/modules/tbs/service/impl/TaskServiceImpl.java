package cn.jeeweb.modules.tbs.service.impl;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.quartz.QuartzManager;
import cn.jeeweb.core.quartz.data.ScheduleJob;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.modules.sys.security.shiro.realm.UserRealm.Principal;
import cn.jeeweb.modules.sys.utils.UserUtils;
import cn.jeeweb.modules.tbs.entity.Task;
import cn.jeeweb.modules.tbs.helper.CronExpressionUtil;
import cn.jeeweb.modules.tbs.helper.TaskUtils;
import cn.jeeweb.modules.tbs.mapper.TaskMapper;
import cn.jeeweb.modules.tbs.service.ITaskService;

/**   
 * @Title: 定时任务管理
 * @Description: 定时任务管理
 * @author QuHaiLong
 * @date 2017-09-08 14:38:37
 * @version V1.0   
 *
 */
@Transactional
@Service("taskService")
public class TaskServiceImpl extends CommonServiceImpl<TaskMapper,Task> implements ITaskService  {
	
	private QuartzManager quartzManager;

	
	
	
	@Override
	public boolean insert(Task entity) {
		// TODO Auto-generated method stub
		Principal principal = UserUtils.getPrincipal();
		entity.setCreatName(principal.getUsername());
		entity.setYxbz("Y");
		entity.setXybz("N");
		entity.setTaskState(entity.getXybz().equals("Y")?"1":"0");
		entity.setCreatDate(new Date());
		entity.setTaskGroup(entity.getTaskMc());
		return super.insert(entity);
	}
	@Override
	public boolean insertOrUpdate(Task task) {
		// TODO Auto-generated method stub
		Principal principal = UserUtils.getPrincipal();
		task.setUpdateName(principal.getUsername());
		task.setUpdateDate(new Date());
		task.setTaskGroup(task.getTaskMc());
		if(task.getTaskState()==null){
			task.setTaskState(task.getXybz().equals("Y")?"1":"0");
		}
		return super.insertOrUpdate(task);
	}
	@Override
	public void initSchedule() throws SchedulerException {
		// TODO Auto-generated method stub
		quartzManager = new QuartzManager();
		List<Task> taskList = selectList(new EntityWrapper<Task>());
		for(Task t : taskList){
			quartzManager.addJob(TaskUtils.entityToData(t));
		}
		
	}

	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		for(Serializable id : idList){
			try {
				Task task = selectById(id);
				quartzManager.deleteJob(TaskUtils.entityToData(task));
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		
		return super.deleteBatchIds(idList);
	}
	
	@Override
	public Page<Task> selectPage(Page<Task> page, Wrapper<Task> wrapper) {
		// TODO Auto-generated method stub
		page =super.selectPage(page, wrapper);
		List<Task> taskList = page.getRecords();
		//List<Task> newtaskList = new ArrayList<>();
		for(Task task : taskList){
			if(CronExpressionUtil.isExpire(task.getCronExpression())){
				task.setTaskState("-1");
				task.setXybz("N");
				insertOrUpdate(task);
			}
			//newtaskList.add(task);
			if(task.getTaskType().equals("2")){
				task.setCronExpression(CronExpressionUtil.parseToDate(task.getCronExpression()));
			}
			
		}
		
		page.setRecords(taskList);


		return page;
	}
	

	
	
	@Override
	public void updateTaskStatus(Task task) throws SchedulerException{
		Task ntask = selectById(task.getId());
		if (ntask == null) {
			return;
		}
		String oldXybz = ntask.getXybz();
		if(task.getXybz()!=null){
			ntask.setXybz(task.getXybz());
		}
		if(task.getCronExpression()!=null){
			ntask.setCronExpression(task.getCronExpression());
		}
		if ("N".equals(task.getXybz())) {
			quartzManager.deleteJob(TaskUtils.entityToData(ntask));
		} else if ("Y".equals(task.getXybz())) {
			quartzManager.addJob(TaskUtils.entityToData(ntask));
		}
		if (ScheduleJob.STATUS_RUNNING.equals(oldXybz.equals("Y")?"1":"0")) {
			if(ScheduleJob.STATUS_RUNNING.equals(task.getXybz().equals("Y")?"1":"0")){
				quartzManager.updateJobCron(TaskUtils.entityToData(ntask));
			}
		}
		

	}
	
	@Override
	public void runTaskNow(String taskId) throws SchedulerException{
		Task task = selectById(taskId);
		task.setXybz("Y");
		task.setTaskMc("tempTask");
		ScheduleJob scheduleJob = TaskUtils.entityToData(task);
		quartzManager.runAJobNowOnlyOne(scheduleJob);
		
	}
	
}
