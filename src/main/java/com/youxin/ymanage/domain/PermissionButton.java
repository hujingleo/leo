package com.youxin.ymanage.domain;

import com.youxin.ymanage.entity.SysMenu;

public class PermissionButton extends SysMenu {

	private boolean hasPermission = false;

	public boolean isHasPermission() {
		return hasPermission;
	}

	public void setHasPermission(boolean hasPermission) {
		this.hasPermission = hasPermission;
	}

	@Override
	public String toString() {
		super.toString();
		return "PermissionButton [hasPermission=" + hasPermission + "]";
	}

}
