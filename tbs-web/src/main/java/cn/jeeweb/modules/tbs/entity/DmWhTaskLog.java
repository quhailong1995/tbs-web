package cn.jeeweb.modules.tbs.entity;

import java.util.Date;

import cn.jeeweb.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


@TableName("dm_wh_tasklog")
@SuppressWarnings("serial")
public class DmWhTaskLog  extends AbstractEntity<String> {
	
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	private String taskDm;
	@TableField(exist=false)
	private String taskMc;
	@TableField(value = "start_date")
	private Date startDate;
	@TableField(value = "end_date")
	private Date endDate;
	
	private String resultCode;
	private String resultMessage;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskDm() {
		return taskDm;
	}
	public void setTaskDm(String taskDm) {
		this.taskDm = taskDm;
	}
	public String getTaskMc() {
		return taskMc;
	}
	public void setTaskMc(String taskMc) {
		this.taskMc = taskMc;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		System.out.println(startDate);
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	
	

}
