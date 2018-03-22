package com.youxin.ymall.utils;

import com.sslkg.domain.MyBatisWhere;
import com.sslkg.domain.SqlWhere;
import com.sslkg.domain.WhereBuilder;
import com.youxin.ymall.domain.Filter;
import com.youxin.ymall.domain.JqSearch;
import com.youxin.ymall.domain.Rule;

public class CustomSqlWhereUitl {
	public static MyBatisWhere getSqlWhere(JqSearch filter) {
		Filter filterObj = filter.getFilterObj();
		MyBatisWhere where = new MyBatisWhere();
		if (filterObj != null) {
			String groupop = filterObj.getGroupOp();
			if ("AND".equalsIgnoreCase(groupop)) {
				SqlWhere sqlWhere = new SqlWhere();
				for (int i = 0; i < filterObj.getRules().size(); i++) {
					Rule rule = filterObj.getRules().get(i);
					String op = Filter.parseOp(rule.getOp());
					if ("gt".equals(op)) {
						op = ">";
					} else if ("lt".equals(op)) {
						op = "<";
					}
					sqlWhere.appendCondition(rule.getField(), op, rule.getData());
				}
				where.addWhere(sqlWhere);
			} else if ("OR".equalsIgnoreCase(groupop)) {
				for (int i = 0; i < filterObj.getRules().size(); i++) {
					SqlWhere sqlWhere = new SqlWhere();
					Rule rule = filterObj.getRules().get(i);
					String op = Filter.parseOp(rule.getOp());
					sqlWhere.appendCondition(rule.getField(), op, rule.getData());
					where.addWhere(sqlWhere);
				}
			}
		}
		return where;
	}
}
