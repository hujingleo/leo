package com.youxin.ymanage.entity;

import java.io.Serializable;

public class LanFQFactoryInfo implements Serializable {
	private static final long serialVersionUID = 7868980946996187202L;
	private String FTYID;// 工厂id
	private String FTYName;// 工厂名字
	private String FTYAddress;// 工厂地址

	public String getFTYID() {
		return FTYID;
	}

	public void setFTYID(String fTYID) {
		FTYID = fTYID;
	}

	public String getFTYName() {
		return FTYName;
	}

	public void setFTYName(String fTYName) {
		FTYName = fTYName;
	}

	public String getFTYAddress() {
		return FTYAddress;
	}

	public void setFTYAddress(String fTYAddress) {
		FTYAddress = fTYAddress;
	}

}
