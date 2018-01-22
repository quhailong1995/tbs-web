package cn.jeeweb.modules.tbs.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import cn.jeeweb.core.common.entity.AbstractEntity;

@TableName("dmwhtask")
@SuppressWarnings("serial")
public class Task extends AbstractEntity<String> {

	/**ID*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;

    private String taskMc;
    private String taskGroup;
    private String taskType;
    private String xybz;
    private String taskRemark;

    private String cronExpression;
    private String methodName;
    private String beanClass;
    private String bz;
    private String creatName;
    private Date creatDate;
    private String updateName;
    private Date updateDate;
    private String yxbz;
    private String taskState;
    private String taskDm;
    
    
    
/*    private String status;
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}*/
    
    
	public String getTaskState() {
		return taskState;
	}

	public String getTaskDm() {
		return taskDm;
	}

	public void setTaskDm(String taskDm) {
		this.taskDm = taskDm;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getId() {
		return id;
	}

	public String getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}

	public String getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	public void setId(String id) {
		this.id = id;
	}


	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getXybz() {
		return xybz;
	}
	public void setXybz(String xybz) {
		this.xybz = xybz;
	}

	public String getTaskMc() {
		return taskMc;
	}
	public void setTaskMc(String taskMc) {
		this.taskMc = taskMc;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskRemark() {
		return taskRemark;
	}
	public void setTaskRemark(String taskRemark) {
		this.taskRemark = taskRemark;
	}


	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCreatName() {
		return creatName;
	}
	public void setCreatName(String creatName) {
		this.creatName = creatName;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
    

    

}
