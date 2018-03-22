package com.youxin.ymanage.entity;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Date;

public class ComparatorListForPyqFactoryNotice implements Comparator {
	private String orderfield;
	private String ordertype;

	public ComparatorListForPyqFactoryNotice() {
	}

	public ComparatorListForPyqFactoryNotice(String orderfield, String ordertype) {
		this.orderfield = orderfield;
		this.ordertype = ordertype;
	}

	public String getOrderfield() {
		return orderfield;
	}

	public void setOrderfield(String orderfield) {
		this.orderfield = orderfield;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	/**
	 * -1, 0, or 1 方法如果这个字符串是等参数字符串那么​返回值0， 如果这个字符串是按字典顺序小于字符串参数那么返回小于0的值，
	 * 如果此字符串是按字典顺序大于字符串参数那么一个大于0的值
	 */
	@Override
	public int compare(Object o1, Object o2) {
		int flag2 = 0;
		try {
			// MyServiceNetBuluoListEntity
			// buluo1=(MyServiceNetBuluoListEntity)o1;
			// MyServiceNetBuluoListEntity
			// buluo2=(MyServiceNetBuluoListEntity)o2;

			// 反射拿到bean的属性
			// MyServiceNetBuluoListEntity b = new
			// MyServiceNetBuluoListEntity();
			Class bean = o1.getClass();
			// Field[] fieldArray = bean.getFields();
			Field tmpField = bean.getField(orderfield);

			// System.out.println(fieldArray.length);
			if (null != tmpField) {
				// System.out.println(fname);
				try {
					String type = tmpField.getType().getName();
					// System.out.println(type);
					int flag = 0;
					if ("java.lang.Integer".equals(type)) {
						Integer value1 = (Integer) tmpField.get(o1);
						Integer value2 = (Integer) tmpField.get(o2);
						flag = value1.compareTo(value2);
					} else if ("java.lang.String".equals(type)) {
						String value1 = (String) tmpField.get(o1);
						String value2 = (String) tmpField.get(o2);
						flag = value1.compareTo(value2);
					} else if ("java.lang.Double".equals(type)) {
						Double value1 = (Double) tmpField.get(o1);
						Double value2 = (Double) tmpField.get(o2);
						flag = value1.compareTo(value2);
					} else if ("java.lang.Float".equals(type)) {
						Float value1 = (Float) tmpField.get(o1);
						Float value2 = (Float) tmpField.get(o2);
						flag = value1.compareTo(value2);
					} else if ("java.util.Date".equals(type)) {
						Long value1 = ((Date) tmpField.get(o1)).getTime();
						Long value2 = ((Date) tmpField.get(o2)).getTime();
						flag = value1.compareTo(value2);
					}

					if ("升".equals(ordertype)) {
						return flag;
					} else {
						if (flag > 0) {
							flag2 = -1;
						} else if (flag < 0) {
							flag2 = 1;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag2;
	}
}
