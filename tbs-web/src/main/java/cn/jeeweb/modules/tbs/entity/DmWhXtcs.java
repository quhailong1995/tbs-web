package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;

/**   
 * @Title: 系统参数维护
 * @Description: 系统参数维护
 * @author QuHaiLong
 * @date 2017-08-30 14:53:25
 * @version V1.0   
 *
 */
@TableName("dm_wh_xtcs")
@SuppressWarnings("serial")
public class DmWhXtcs extends AbstractEntity<String> {

    /**系统参数ID*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**系统参数代码*/
    @TableField(value = "xtcs_dm")
	private String xtcsDm;
    /**系统参数名称*/
    @TableField(value = "xtcs_mc")
	private String xtcsMc;
    /**系统参数内容*/
    @TableField(value = "xtcs_nr")
	private String xtcsNr;
    /**备注*/
    @TableField(value = "bz")
	private String bz;
    /**有效标志*/
    @TableField(value = "yxbz")
	private String yxbz;
    /**选用标志*/
    @TableField(value = "xybz")
	private String xybz;
	
	/**
	 * 获取  id
	 *@return: String  系统参数ID
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  系统参数ID
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  xtcsDm
	 *@return: String  系统参数代码
	 */
	public String getXtcsDm(){
		return this.xtcsDm;
	}

	/**
	 * 设置  xtcsDm
	 *@param: xtcsDm  系统参数代码
	 */
	public void setXtcsDm(String xtcsDm){
		this.xtcsDm = xtcsDm;
	}
	/**
	 * 获取  xtcsMc
	 *@return: String  系统参数名称
	 */
	public String getXtcsMc(){
		return this.xtcsMc;
	}

	/**
	 * 设置  xtcsMc
	 *@param: xtcsMc  系统参数名称
	 */
	public void setXtcsMc(String xtcsMc){
		this.xtcsMc = xtcsMc;
	}
	/**
	 * 获取  xtcsNr
	 *@return: String  系统参数内容
	 */
	public String getXtcsNr(){
		return this.xtcsNr;
	}

	/**
	 * 设置  xtcsNr
	 *@param: xtcsNr  系统参数内容
	 */
	public void setXtcsNr(String xtcsNr){
		this.xtcsNr = xtcsNr;
	}
	/**
	 * 获取  bz
	 *@return: String  备注
	 */
	public String getBz(){
		return this.bz;
	}

	/**
	 * 设置  bz
	 *@param: bz  备注
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
	
}
