package com.youxin.ymall.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.sslkg.domain.MyBatisWhere;
import com.youxin.ymall.entity.Setting;
import com.youxin.ymanage.domain.JqQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SettingMapper {
	public int add(Setting entity);

	public List<Setting> list(Setting entity, PageBounds pageBounds);

	public List<Setting> select(MyBatisWhere where, PageBounds pageBounds);

	public int delete(String oname);

	public int update(Setting entity);

	public Setting getById(String oname);

	public int updateByKey(@Param("key") String key, @Param("value") String value);

	public PageList<Setting> queryAllBoolean(@Param("jqQuery") JqQuery query, PageBounds pageBounds);

}