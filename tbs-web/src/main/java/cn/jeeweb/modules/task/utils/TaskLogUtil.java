package cn.jeeweb.modules.task.utils;



import cn.jeeweb.modules.tbs.entity.DmWhTaskLog;
import cn.jeeweb.modules.tbs.entity.Task;

public class TaskLogUtil {
	
	private DmWhTaskLog dmWhTaskLog;
	private Task task;
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public DmWhTaskLog getDmWhTaskLog() {
		return dmWhTaskLog;
	}
	public void setDmWhTaskLog(DmWhTaskLog dmWhTaskLog) {
		this.dmWhTaskLog = dmWhTaskLog;
	}

	@Override
	public String toString() {
		return "TaskLogUtil [dmWhTaskLog=" + dmWhTaskLog + ", task=" + task
				+  "]";
	}

	
	
	

	
	
	
	
	

}
