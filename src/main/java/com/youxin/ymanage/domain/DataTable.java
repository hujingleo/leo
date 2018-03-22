package com.youxin.ymanage.domain;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 数据表
 * 
 * @author yukun
 *
 */
public class DataTable {

	private List<DataRow> rows;

	public List<DataRow> getRows() {
		return rows;
	}

	public void setRows(List<DataRow> rows) {
		this.rows = rows;
	}

	public List<DataColumn> columns;

	public List<DataColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DataColumn> columns) {
		this.columns = columns;
	}

}
