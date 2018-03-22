package com.youxin.ymall.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.rsclouds.base.SimpleListObject;
import com.rsclouds.base.SimpleNetObject;
import com.rsclouds.util.DateUtil;
import com.rsclouds.util.StringTool;
import com.sslkg.domain.MyBatisWhere;
import com.youxin.ymall.cache.CacheEvict;
import com.youxin.ymall.cache.Cacheable;
import com.youxin.ymall.dao.SettingMapper;
import com.youxin.ymall.entity.Setting;
import com.youxin.ymanage.domain.JqQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SettingService {

	public static String DATATYPE_INT = "int";
	public static String DATATYPE_FLOAT = "float";
	public static String DATATYPE_STRING = "string";
	public static String DATATYPE_TEXT = "text";
	public static String DATATYPE_BOOLEAN = "boolean";
	public static String DATATYPE_LDATE = "ldate"; // 短时间类型
	public static String DATATYPE_SDATE = "sdate"; // 长时间类型

	public static String KEY_GGK_DAYBUYNUM = "GGK_DAYBUYNUM"; // 刮刮卡一天内最多购买次数
	@Autowired
	private SettingMapper settingmapper;

	private Logger logger = LoggerFactory.getLogger(SettingService.class);

	public SimpleNetObject add(Setting entity) {
		SimpleNetObject sno = new SimpleNetObject();
		try {
			sno.setResult(this.settingmapper.add(entity));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			sno.setResult(99);
			sno.setMessage("添加失败");
		}
		return sno;
	}

	public Object getSettingValue(String key, Object defaultVal) {

		Setting setting = this.settingmapper.getById(key);
		if (setting == null) {
			return defaultVal;

		}

		String datatype = setting.getData_type();
		String datavalue = setting.getValue();
		try {
			if (DATATYPE_INT.equalsIgnoreCase(datatype)) {

				return Integer.valueOf(datavalue);
			} else if (DATATYPE_TEXT.equalsIgnoreCase(datatype) || DATATYPE_STRING.equalsIgnoreCase(datatype)) {
				return datavalue;
			} else if (DATATYPE_FLOAT.equalsIgnoreCase(datatype)) {
				return Float.valueOf(datavalue);
			} else if (DATATYPE_BOOLEAN.equalsIgnoreCase(datatype)) {
				return Boolean.valueOf(datavalue);
			} else if (DATATYPE_SDATE.equalsIgnoreCase(datatype)) {
				return DateUtil.parseDate(datavalue, "yyyy-MM-dd");
			} else if (DATATYPE_LDATE.equalsIgnoreCase(datatype)) {
				return DateUtil.parseDate(datavalue, "yyyy-MM-dd HH:mm:ss");
			} else
				return datavalue;
		} catch (Exception ex) {
			logger.error("设置键【{}】数据值{}转换错误", new Object[] { key, datavalue });
			return defaultVal;
		}

	}

	@CacheEvict(key = "setting", fieldKey = "#entity.oname")
	public SimpleNetObject update(Setting entity) {
		SimpleNetObject sno = new SimpleNetObject();
		try {
			entity.setModified_datetime(new Date());
			int result = this.settingmapper.update(entity);
			if (result == 1) {
				sno.setMessage("更新成功");
			} else {
				sno.setMessage("未找到该条记录，更新失败");
			}
			sno.setResult(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			sno.setResult(99);
			sno.setMessage("更新失败");
		}
		return sno;
	}

	@Transactional
	public SimpleNetObject delete(String oids) {
		SimpleNetObject sno = new SimpleNetObject();
		try {
			String[] arrOids = oids.split(",");
			for (int i = 0; i < arrOids.length; i++) {

				String id = arrOids[i];

				try {
					String oid = id;
					this.settingmapper.delete(oid);
				} catch (Exception ex) {
				}

			}
			sno.setResult(1);
			sno.setMessage("删除成功");

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			sno.setResult(99);
			sno.setMessage("删除失败");
		}
		return sno;
	}

	public List<Setting> list(Setting entity, PageBounds pageBounds) {

		try {
			return this.settingmapper.list(entity, pageBounds);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

	public Setting getById(String oname) {
		try {
			return this.settingmapper.getById(oname);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

	@Cacheable(key = "getByIdFormCache", fieldKey = "#oname")
	public Setting getByIdFormCache(String oname) {
		try {
			return this.settingmapper.getById(oname);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

	public List<Setting> list(MyBatisWhere where, PageBounds pageBounds) {

		try {
			return this.settingmapper.select(where, pageBounds);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

	/**
	 * 检查基础应用设置是否有设置
	 * 
	 * @return
	 */
	public int checksetting() {
		// Setting setting=this.getById(POINTS_CONVERT_RATIO);
		// if(setting==null){
		// logger.error(POINTS_CONVERT_RATIO+"未设置");
		//
		// return 0;
		// }
		// SettingService.POINTS_CONVERT_RATIO_VALUE=Integer.parseInt(setting.getValue());
		// Setting
		// youfensalaryRatio=this.getById(CommonValue.SETTING_YOUFEN_SALARY_RATIO);
		// if(youfensalaryRatio==null){
		// logger.error(CommonValue.SETTING_YOUFEN_SALARY_RATIO+"未设置");
		//
		// return 0;
		// }
		//
		// SettingService.YOUFEN_SALARY_RATIO=new
		// BigDecimal(youfensalaryRatio.getValue());
		//
		// Setting
		// youfenfactory=this.getById(CommonValue.SETTING_YOUFEN_FACTORY_UPDATE);
		// if(youfenfactory==null){
		// logger.error(CommonValue.SETTING_YOUFEN_FACTORY_UPDATE+"未设置");
		//
		// return 0;
		// }
		// SettingService.YOUFEN_FACTORY_UPDATE=youfenfactory.getValue();
		// /**
		// * weup开户默认套餐id号
		// */
		// Setting
		// defaultPackageId=this.getById(CommonValue.SETTING_WEUP_DEFAULT_PACKAGEID);
		// if(defaultPackageId==null){
		// logger.error(CommonValue.SETTING_WEUP_DEFAULT_PACKAGEID+"未设置");
		//
		// return 0;
		// }
		// try
		// {
		// SettingService.WEUP_DEFAULT_PACKAGEID=Integer.parseInt(defaultPackageId.getValue());
		// }
		// catch(NumberFormatException nfe){
		// logger.error(CommonValue.SETTING_WEUP_DEFAULT_PACKAGEID+"设置非整型值");
		// return 0;
		// }
		return 1;
	}

	/**
	 * 获取全部Boolean的数据
	 * 
	 * @return
	 */
	public SimpleListObject<Setting> list_setting(JqQuery query) {
		try {
			PageBounds pagebounds = new PageBounds();
			pagebounds.setLimit(query.getLimit());
			pagebounds.setPage(query.getPage());
			SimpleListObject<Setting> slno = new SimpleListObject<Setting>();
			if (StringTool.isNullOrEmpty(query.getOrderfield())) {
				query.setOrderfield("oname");
				query.setOrdertype("asc");
			}
			PageList<Setting> lstResults = settingmapper.queryAllBoolean(query, pagebounds);
			slno.setRows(lstResults);
			slno.setResult(1);
			slno.setRecords(lstResults.getPaginator().getTotalCount());
			slno.setTotal(lstResults.getPaginator().getTotalPages());
			return slno;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

}