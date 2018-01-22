package cn.jeeweb.modules.tbs.task;

import java.util.Date;

import org.apache.log4j.Logger;

import cn.jeeweb.modules.task.utils.MonitorTriggerListener;

public class TestTask {
	public final Logger log = Logger.getLogger(this.getClass());
	public void start1(){
		for(int i=0;i<10;i++){
			log.info(i+" start1_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+" + (new Date()));
		}
		MonitorTriggerListener.returnResultMessage("1", "方法start1  执行完毕");
	}
	public void start2(){
		for(int i=0;i<10;i++){
			log.info(i+" start2_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+" + (new Date()));
		}
		MonitorTriggerListener.returnResultMessage("2", "方法start2  执行完毕");
	}

}
