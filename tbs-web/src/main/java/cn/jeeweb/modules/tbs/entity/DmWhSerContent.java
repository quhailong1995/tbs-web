package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;

import java.util.Date;

/**   
 * @Title: 
 * @Description: 
 * @author QuHaiLong
 * @date 2017-08-13 13:55:29
 * @version V1.0   
 *
 */
@TableName("dm_wh_ser_content")
@SuppressWarnings("serial")
public class DmWhSerContent extends AbstractEntity<String> {

    /**维护服务内容（主键）*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**服务ID*/
    @TableField(value = "service_id",el="serviceId.id")
	private DmWhService serviceId;
    /**数据表*/
    @TableField(value = "table_name")
	private String tableName;
    /**字段名*/
    @TableField(value = "column_name")
	private String columnName;
    /**字段解释*/
    @TableField(value = "column_comment")
	private String columnComment;
    /**节点代码*/
    @TableField(value = "node_name")
	private String nodeName;
    /**节点中文描述*/
    @TableField(value = "bz")
	private String bz;
    /**有效标志*/
    @TableField(value = "yxbz")
	private String yxbz;
    /**选用标志*/
    @TableField(value = "xybz")
	private String xybz;
    /**创建人*/
    @TableField(value = "create_name")
	private String createName;
    /**创建日期*/
    @TableField(value = "create_date")
	private Date createDate;
    /**修改人*/
    @TableField(value = "update_name")
	private String updateName;
    /**修改日期*/
    @TableField(value = "update_date")
	private Date updateDate;
    /**顺序*/
    @TableField(value = "sort")
    private Integer sort;
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取  id
	 *@return: String  维护服务内容（主键）
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  维护服务内容（主键）
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  serviceId
	 *@return: DmWhService  服务ID
	 */
	public DmWhService getServiceId(){
		return this.serviceId;
	}

	/**
	 * 设置  serviceId
	 *@param: serviceId  服务ID
	 */
	public void setServiceId(DmWhService serviceId){
		this.serviceId = serviceId;
	}
	/**
	 * 获取  tableName
	 *@return: String  数据表
	 */
	public String getTableName(){
		return this.tableName;
	}

	/**
	 * 设置  tableName
	 *@param: tableName  数据表
	 */
	public void setTableName(String tableName){
		this.tableName = tableName;
	}
	/**
	 * 获取  columnName
	 *@return: String  字段名
	 */
	public String getColumnName(){
		return this.columnName;
	}

	/**
	 * 设置  columnName
	 *@param: columnName  字段名
	 */
	public void setColumnName(String columnName){
		this.columnName = columnName;
	}
	/**
	 * 获取  columnComment
	 *@return: String  字段解释
	 */
	public String getColumnComment(){
		return this.columnComment;
	}

	/**
	 * 设置  columnComment
	 *@param: columnComment  字段解释
	 */
	public void setColumnComment(String columnComment){
		this.columnComment = columnComment;
	}
	/**
	 * 获取  nodeName
	 *@return: String  节点代码
	 */
	public String getNodeName(){
		return this.nodeName;
	}

	/**
	 * 设置  nodeName
	 *@param: nodeName  节点代码
	 */
	public void setNodeName(String nodeName){
		this.nodeName = nodeName;
	}
	/**
	 * 获取  bz
	 *@return: String  节点中文描述
	 */
	public String getBz(){
		return this.bz;
	}

	/**
	 * 设置  bz
	 *@param: bz  节点中文描述
	 */
	public void setBz(String bz){
		this.bz = bz;
	}
	/**
	 * 获取  yxbz
	 *@return: String  有效标志
	 */
	public String getYxbz(){
		return this.yxbz;
	}

	/**
	 * 设置  yxbz
	 *@param: yxbz  有效标志
	 */
	public void setYxbz(String yxbz){
		this.yxbz = yxbz;
	}
	/**
	 * 获取  xybz
	 *@return: String  选用标志
	 */
	public String getXybz(){
		return this.xybz;
	}

	/**
	 * 设置  xybz
	 *@param: xybz  选用标志
	 */
	public void setXybz(String xybz){
		this.xybz = xybz;
	}
	/**
	 * 获取  createName
	 *@return: String  创建人
	 */
	public String getCreateName(){
		return this.createName;
	}

	/**
	 * 设置  createName
	 *@param: createName  创建人
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 * 获取  createDate
	 *@return: Date  创建日期
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  updateName
	 *@return: String  修改人
	 */
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 * 设置  updateName
	 *@param: updateName  修改人
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  修改日期
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  修改日期
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	
}
