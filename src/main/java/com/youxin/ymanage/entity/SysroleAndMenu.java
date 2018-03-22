package com.youxin.ymanage.entity;

import java.io.Serializable;

/**
 * Description:t_sys_rolemenupermission表
 * 
 * @author ljw 2016-3-18
 * @version v1.0
 */
public class SysroleAndMenu implements Serializable {
	private static final long serialVersionUID = -3509961250700450834L;
	private String rolecode;
	private String menucode;//

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getMenucode() {
		return menucode;
	}

	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}

}
