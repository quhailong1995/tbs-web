package cn.jeeweb.core.quartz.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.jeeweb.core.quartz.data.ScheduleJob;
import cn.jeeweb.core.utils.SpringContextHolder;
import cn.jeeweb.modules.task.utils.MonitorTriggerListener;

public class TaskUtils {
	public final static Logger log = Logger.getLogger(TaskUtils.class);
	private static String resultMessage="";

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(ScheduleJob scheduleJob) {
		Object object = null;
		Class<?> clazz = null;
		if (StringUtils.isNotBlank(scheduleJob.getSpringBean())) {
			object = SpringContextHolder.getBean(scheduleJob.getSpringBean());
		} else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				resultMessage = resultMessage+e.getMessage();
				MonitorTriggerListener.returnResultMessage("4", resultMessage);
				e.printStackTrace();
			}
		}
		if (object == null) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
			resultMessage = ","+resultMessage+"任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！";
			MonitorTriggerListener.returnResultMessage("4", resultMessage);
			return;
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
			resultMessage = resultMessage+"任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！";
			MonitorTriggerListener.returnResultMessage("4", resultMessage);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
