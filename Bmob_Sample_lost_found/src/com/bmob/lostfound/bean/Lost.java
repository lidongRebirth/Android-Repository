package com.bmob.lostfound.bean;

import cn.bmob.v3.BmobObject;

/** 创建失物对象
  * @ClassName: Lost
  * @Description: TODO
  * @author smile
  * @date 2014-5-21 上午11:27:03
  */
public class Lost extends BmobObject{

	private String title;//标题
	private String describe;//描述
	private String phone;//联系手机
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
