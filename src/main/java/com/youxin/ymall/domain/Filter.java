package com.youxin.ymall.domain;

import java.util.ArrayList;
import java.util.List;

public class Filter {

	public Filter() {

		this.rules = new ArrayList<Rule>();
	}

	/**
	 * OR 或者 AND
	 */
	private String groupOp;

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	private List<Rule> rules;

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public static String parseOp(String op) {
		if ("eq".equalsIgnoreCase(op)) {
			return "=";
		} else if ("ne".equalsIgnoreCase(op)) {
			return "!=";
		} else if ("lt".equalsIgnoreCase(op)) {
			return "lt";
		} else if ("gt".equalsIgnoreCase(op)) {
			return "gt";
		} else if ("bw".equalsIgnoreCase(op)) {
			return "gt";
		} else if ("ew".equalsIgnoreCase(op)) {
			return "lt";
		} else if ("cn".equalsIgnoreCase(op)) {
			return "like";
		} else {
			return "";
		}
	}
}
