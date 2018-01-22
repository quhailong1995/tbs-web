package cn.jeeweb.modules.task.utils;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.utils.SpringContextHolder;
import cn.jeeweb.modules.tbs.entity.DmWhTaskLog;
import cn.jeeweb.modules.tbs.entity.Task;
import cn.jeeweb.modules.tbs.service.IDmWhTaskLogService;
import cn.jeeweb.modules.tbs.service.ITaskService;

/**
 * 定时任务监听器
 * @author quhailong
 *
 */
public class MonitorTriggerListener implements TriggerListener {

	private final static Logger logger=Logger.getLogger(MonitorTriggerListener.class);
   
    private static  ThreadLocal<TaskLogUtil> localLog = new ThreadLocal<TaskLogUtil>();
    private static IDmWhTaskLogService dmWhTaskLogService = SpringContextHolder.getBean(IDmWhTaskLogService.class);
    private ITaskService taskService = SpringContextHolder.getBean(ITaskService.class);
	

    private Date sdate;
    
    public MonitorTriggerListener(){
    	this.sdate = new Date();
    	
    }
    
    
    @Override  
    public String getName() {  
        return "MonitorTriggerListener";  
    }  

    
    /**
     * 任务执行完成后结果回调
     * @param resultCode
     * @param resultMessage
     */
    public static void returnResultMessage(String resultCode , String resultMessage){
    	//DmWhTaskLog quartzLog=dmWhTaskLogService.selectById(localLog.get().getDmWhTaskLog().getId());
    	
    	TaskLogUtil taskLogUtil = localLog.get();
    	
    	final DmWhTaskLog quartzLog = taskLogUtil.getDmWhTaskLog();
    	
    	logger.info("任务方法执行完成，得到结果 resultCode="+resultCode+"，resultMessage="+resultMessage);

    	 quartzLog.setResultMessage(resultMessage);
    	 quartzLog.setResultCode(resultCode);
    	 logger.info("日志信息："+quartzLog);
    	 logger.info("开始记录返回信息");
    	 dmWhTaskLogService.update(quartzLog, new Wrapper<DmWhTaskLog>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return "where id = '"+quartzLog.getId()+"'";
			}
		});
    	 logger.info("返回信息记录完毕");
    }
    
    
    
    
    
    /**
     * 当与监听器相关联的Trigger被触发，Job上的execute()方法将被执行时，Scheduler就调用该方法。
     */
	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
		logger.info("任务:"+trigger.getJobKey().getName()+"------------被触发"+ trigger.getKey().getName());
		 
		
		
		Task task = taskService.selectOne(new EntityWrapper<Task>().eq("task_dm", trigger.getJobKey().getName()));
		DmWhTaskLog quartzLog = new DmWhTaskLog();
		quartzLog.setTaskDm(task.getTaskDm());
		
		quartzLog.setStartDate(trigger.getStartTime());
		

		TaskLogUtil taskLogUtil = new TaskLogUtil();
		taskLogUtil.setDmWhTaskLog(quartzLog);
		taskLogUtil.setTask(task);
				
		if(!trigger.getJobKey().getGroup().equals("tempTask")){
	
		 if(task.getTaskType().equals("1")&&(new Date().getTime()- sdate.getTime()) > (5*60*1000)){
			
				logger.info("开始记录日志+++++++++++++++++++++++");
				
				logger.info(taskLogUtil.getDmWhTaskLog().getStartDate()+"日志信息:"+quartzLog);
				
				Date current = new Date();
				
				quartzLog.setStartDate(current);
	
				sdate = current;
				
				dmWhTaskLogService.insert(quartzLog);
				
				taskLogUtil.setDmWhTaskLog(quartzLog);
				
				localLog.set(taskLogUtil);

		 }
		 
		 if(task.getTaskType().equals("2")){
				logger.info("日志记录完毕");
				dmWhTaskLogService.insert(quartzLog);
			}
		 
		 
		 
		}else{
			logger.info("开始记录日志_____________________________");
			
			logger.info("日志信息:"+quartzLog);
			dmWhTaskLogService.insert(quartzLog);
			
			taskLogUtil.setDmWhTaskLog(quartzLog);
			
			localLog.set(taskLogUtil);
		}
		
		
		
		
		
		
		logger.info("日志记录完毕");
		taskLogUtil.setDmWhTaskLog(quartzLog);
		
		localLog.set(taskLogUtil);
		
		
	}

	/**
	 * 在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法。TriggerListener
	 *  给了一个选择去否决 Job 的执行。假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行。
	 */
	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
		 logger.info(trigger.getJobKey().getName()+"将要执行");

		return false;
	}

	/**
	 * Scheduler 调用这个方法是在 Trigger 错过触发时。你应该关注此方法中持续时间长的逻辑：
	 * 在出现许多错过触发的 Trigger 时，长逻辑会导致骨牌效应。你应当保持这上方法尽量的小。
	 */
	@Override
	public void triggerMisfired(Trigger trigger) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Trigger 被触发并且完成了 Job 的执行时，Scheduler 调用这个方法。
	 */
	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		// TODO Auto-generated method stub
	
		//TaskLogUtil taskLogUtil = localLog.get();
		TaskLogUtil taskLogUtil = localLog.get();
		logger.info("________________________________________________"+taskLogUtil);
		
		logger.info("任务:"+trigger.getJobKey().getName()+"------------执行完毕，开始记录日志"+",当前线程："+Thread.currentThread().getName());
		 try {  
			 final DmWhTaskLog quartzLog=taskLogUtil.getDmWhTaskLog();
	            if(quartzLog==null) return ;  
           
	            if(taskLogUtil.getTask().getTaskType().equals("2")||trigger.getJobKey().getGroup().equals("tempTask")){

	            	//quartzLog = dmWhTaskLogService.selectById(localLog.get().getDmWhTaskLog().getId());
	            	quartzLog.setEndDate(new Date());
	            	logger.info("日志信息:"+quartzLog);
	            	logger.info("开始记录任务结束时间");
	            	dmWhTaskLogService.update(quartzLog, new Wrapper<DmWhTaskLog>() {
	        			
	        			/**
	        			 * 
	        			 */
	        			private static final long serialVersionUID = 1L;

	        			@Override
	        			public String getSqlSegment() {
	        				// TODO Auto-generated method stub
	        				return " where id = '"+quartzLog.getId()+"'";
	        			}
	        		});
	            	logger.info("任务结束时间 更新完毕");
	                if(trigger.getJobKey().getGroup().equals("tempTask")){
	                	
	                	context.getScheduler().deleteJob(trigger.getJobKey());
	                	logger.info("立即执行一次的任务，已经执行完一次");
	                }
	            	
	            }
	            
	            

	        } catch (Exception e) {  
	            logger.error("记录job结束时间异常",e);  
	        }catch (Throwable e) {  
	            logger.error("记录job结束时间出错",e);  
	        }  
		 logger.info("任务:"+trigger.getJobKey().getName()+"------------记录日志完毕");
	}  
    

 
} 