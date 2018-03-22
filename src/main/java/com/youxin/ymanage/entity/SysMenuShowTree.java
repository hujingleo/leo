package com.youxin.ymanage.entity;

import java.io.Serializable;

public class SysMenuShowTree implements Serializable {
	private static final long serialVersionUID = -4320627988700985455L;
	private String id; // 权限id
	private String pId;// 父id
	private String name;// 权限名称
	private String open;// 打开状态
	private boolean checked;// 复选框

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}