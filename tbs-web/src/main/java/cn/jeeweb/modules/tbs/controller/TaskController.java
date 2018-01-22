package cn.jeeweb.modules.tbs.controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.tbs.entity.Task;
import cn.jeeweb.modules.tbs.helper.CronExpressionUtil;
import cn.jeeweb.modules.tbs.helper.ReflectionUtils;
import cn.jeeweb.modules.tbs.service.ITaskService;
import cn.jeeweb.modules.tbs.task.TestTask;

/**   
 * @Title: 定时任务管理
 * @Description: 定时任务管理
 * @author QuHaiLong
 * @date 2017-09-08 17:58:07
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/dmwhtask")
@RequiresPathPermission("tbs:dmwhtask")
public class TaskController extends BaseCRUDController<Task, String> {

	@Autowired
	private ITaskService taskService;

	@RequestMapping(value = "/changeJobStatus", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson changeJobStatus(Task task, HttpServletRequest request,
			HttpServletResponse response) {
		String cmd = request.getParameter("cmd");
		AjaxJson ajaxJson = new AjaxJson();
		String label = "停止";
		if (cmd.equals("start")) {
			task.setXybz("Y");
			label = "启动";
		} else {
			task.setXybz("N");
			label = "停止";
		}
		ajaxJson.success("任务" + label + "成功");
		try {
			taskService.updateTaskStatus(task);
			taskService.insertOrUpdate(task);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("任务" + label + "失败" + e.getMessage());
		}
		return ajaxJson;
	}
/*
	@RequestMapping(value = "/updateCron", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson updateCron(Task task, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("任务更新成功");
		try {
			taskService.updateCron(task.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("任务更新失败" + e.getMessage());
		}
		return ajaxJson;
	}
	
	@RequestMapping(value = "/saveScheduleJob", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveScheduleJob(Task task, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("保存成功");
		if (!CronExpression.isValidExpression(task.getCronExpression())) {
			ajaxJson.fail("cron表达式格式不对");
	    	return ajaxJson;
		}
		try {
			 if (ObjectUtils.isNullOrEmpty(task.getId())) {
				commonService.insert(task);
			} else {
				// FORM NULL不更新
				Task oldEntity = commonService.selectById(task.getId());
				MyBeanUtils.copyBeanNotNull2Bean(task, oldEntity);
				commonService.insertOrUpdate(oldEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("保存失败"+e.getMessage());
		}
		return ajaxJson;
	}*/
	@Override
	public AjaxJson doSave(Task entity, HttpServletRequest request,
			HttpServletResponse response, BindingResult result) {
		// TODO Auto-generated method stub
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("保存成功");
		if(entity.getTaskType().equals("1")){
			if (!CronExpression.isValidExpression(entity.getCronExpression())) {
				ajaxJson.fail("cron表达式格式不对");
		    	return ajaxJson;
			}
			
			if(entity.getTaskType().equals("1")&&CronExpressionUtil.isJTSJ(entity.getCronExpression())){
					ajaxJson.fail("cron表达式与执行类型不符");
					return ajaxJson;
			}
			if(entity.getTaskType().equals("2")&&!CronExpressionUtil.isJTSJ(entity.getCronExpression())){
				ajaxJson.fail("cron表达式与执行类型不符");
				return ajaxJson;
			}
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
			try {
				sdf.parse(entity.getCronExpression());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				ajaxJson.fail("时间格式有误！！请按照yyyy-MM-dd HH:mm:ss输入");
				
				e.printStackTrace();
				return ajaxJson;
			}
			
			entity.setCronExpression(CronExpressionUtil.parseToCroExpression(entity.getCronExpression()));
			
			
			
		}
		
		
		
		
		return super.doSave(entity, request, response, result);
	}
	@Override
	public AjaxJson update(Model model, Task entity, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("更新成功");
		Task old = taskService.selectById(entity.getId());
		entity.setXybz(old.getXybz());
		if(old.getXybz().equals("Y")){
			ajaxJson.fail("请把该任务先停用，更改成功后再开启！！！！");
			return ajaxJson;
		}
		
		return super.update(model, entity, result, request, response);
	}
	
	
	
	@RequestMapping(value = "/runTaskNow", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public AjaxJson runTaskNow(Task task,HttpServletResponse response){
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("执行成功！");
		try{
			taskService.runTaskNow(task.getId());
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.fail("执行失败"+e.toString());
			return ajaxJson;
		}

		return ajaxJson;
	}
	
	
	@Override
	public void preEdit(Task entity, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println();
		if(entity.getId()!=null&&entity.getTaskType().equals("2")){
			entity.setCronExpression(CronExpressionUtil.parseToDate(entity.getCronExpression()));
		}
		
		super.preEdit(entity, model, request, response);
	}
	
	
	
	
	
}
