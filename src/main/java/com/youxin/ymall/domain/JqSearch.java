package com.youxin.ymall.domain;

import com.google.gson.Gson;

public class JqSearch {

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	private String filters;

	private Filter filterobj = null;

	public Filter getFilterObj() {
		if (filterobj != null)
			return filterobj;
		Gson gson = new Gson();
		try {

			Filter filter = gson.fromJson(this.filters, Filter.class);
			if (filter == null) {
				filterobj = new Filter();
				filterobj.setGroupOp("AND");
			} else
				filterobj = filter;
			return filterobj;
		} catch (Exception ex) {
			return new Filter();
		}
	}
}
