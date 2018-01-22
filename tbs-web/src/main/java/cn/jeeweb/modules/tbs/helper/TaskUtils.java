package cn.jeeweb.modules.tbs.helper;
import cn.jeeweb.core.quartz.data.ScheduleJob;
import cn.jeeweb.modules.tbs.entity.Task;

public class TaskUtils {
    public static ScheduleJob entityToData(Task scheduleJobEntity){
   	 cn.jeeweb.core.quartz.data.ScheduleJob scheduleJob=new cn.jeeweb.core.quartz.data.ScheduleJob();
   	 scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
   	 scheduleJob.setDescription(scheduleJobEntity.getTaskRemark());
   	 scheduleJob.setJobName(scheduleJobEntity.getTaskDm());
   	scheduleJob.setJobGroup(scheduleJobEntity.getTaskMc());
   	 scheduleJob.setJobStatus(scheduleJobEntity.getXybz().equals("Y")?"1":"0");
   	scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
   	 scheduleJob.setMethodName(scheduleJobEntity.getMethodName());
   	 return scheduleJob;
    }

}
