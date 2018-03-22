package com.youxin.ymall.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.sslkg.domain.MyBatisWhere;
import com.youxin.ymall.entity.Buslog;
import com.youxin.ymanage.domain.JqQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuslogMapper {
	public int add(Buslog entity);

	public List<Buslog> list(Buslog entity, PageBounds pageBounds);

	public List<Buslog> query(@Param("jqQuery") JqQuery query, PageBounds pageBounds);

	public List<Buslog> select(MyBatisWhere where, PageBounds pageBounds);

	public List<Buslog> listLogs(@Param("entity") Buslog log, @Param("startdate") String startdate,
                                 @Param("enddate") String enddate, PageBounds pageBounds);

	public int delete(int oid);

	public int update(Buslog entity);

	public Buslog getById(int oid);
}