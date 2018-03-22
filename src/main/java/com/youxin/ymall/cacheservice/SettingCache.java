package com.youxin.ymall.cacheservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsclouds.base.SimpleNetObject;
import com.rsclouds.util.StringTool;
import com.youxin.ymall.cache.CacheEvict;
import com.youxin.ymall.cache.Cacheable;
import com.youxin.ymall.entity.Setting;
import com.youxin.ymall.service.SettingService;
import com.youxin.ymall.utils.CommonValue;

@Service
public class SettingCache {
	/**
	 * 每日签到赠送金币
	 */
	public static String DAILY_SIGN_BOUNUS = "sign.per.point";
	/**
	 * 注册赠送的金币数
	 */
	public static String REG_BONUS = "point.reg.bouns";

	public static BigDecimal YOUFEN_SALARY_RATIO = null;

	public static String YOUFEN_FACTORY_UPDATE = "";
	public static int WEUP_DEFAULT_PACKAGEID = 1;

	public static int POINTS_CONVERT_RATIO_VALUE = 100;
	/**
	 * 金币兑换金钱比例
	 */
	public static String POINTS_CONVERT_RATIO = "point.ratio";

	private Logger logger = LoggerFactory.getLogger(SettingCache.class);
	@Autowired
	private SettingService settingService;

	@Cacheable(key = "setting", fieldKey = "#key")
	public String getStringValueByKey(String key) {
		Setting setting = this.settingService.getById(key);
		if (setting != null) {
			return setting.getValue();
		} else
			return null;
	}

	@Cacheable(key = "setting", fieldKey = "#key")
	public Integer getIntValueByKey(String key) {
		Setting setting = this.settingService.getById(key);
		if (setting != null) {
			return Integer.valueOf(setting.getValue());
		} else
			return null;
	}

	@Cacheable(key = "setting", fieldKey = "#key")
	public BigDecimal getNumerValueByKey(String key) {
		Setting setting = this.settingService.getById(key);
		if (setting != null) {
			return new BigDecimal(setting.getValue());
		} else
			return null;
	}

	@Cacheable(key = "setting", fieldKey = "#key")
	public Float getFloatValueByKey(String key) {
		Setting setting = this.settingService.getById(key);
		if (setting != null) {
			return Float.valueOf(setting.getValue());
		} else
			return null;
	}

	@CacheEvict(key = "setting", fieldKey = "#key")
	public SimpleNetObject updateValueCache(String key, String value) {
		Setting setting = new Setting();
		setting.setOname(key);
		setting.setValue(value);

		return this.settingService.update(setting);
	}

}
