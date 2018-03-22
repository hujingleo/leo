package com.youxin.ymall.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.rsclouds.base.SimpleNetObject;
import com.rsclouds.util.DateUtil;
import com.rsclouds.util.StringTool;
import com.sslkg.domain.MyBatisWhere;
import com.youxin.ymall.dao.BuslogMapper;
import com.youxin.ymall.entity.Buslog;
import com.youxin.ymanage.domain.JqCondition;
import com.youxin.ymanage.domain.JqQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BuslogService {

	@Autowired
	private BuslogMapper buslogmapper;

	private Logger logger = LoggerFactory.getLogger(BuslogService.class);

	public SimpleNetObject add(Buslog entity) {
		SimpleNetObject sno = new SimpleNetObject();
		try {
			sno.setResult(this.buslogmapper.add(entity));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			sno.setResult(99);
			sno.setMessage("添加失败");
		}
		return sno;
	}

	/**
	 * 
	 * @param oper
	 * @param module
	 * @param ofunc
	 * @param msg
	 * @param oclass
	 * @return
	 */
	public SimpleNetObject info(String oper, String module, String ofunc, String msg) {
		Buslog log = new Buslog();
		log.setOperator(oper);
		log.setModule(module);
		log.setOdate(new Date());
		log.setMsg(msg);
		log.setOfunc(ofunc);
		log.setOclass("SUCCESS");
		return this.add(log);
	}

	/**
	 * 
	 * @param oper
	 * @param module
	 * @param ofunc
	 * @param msg
	 * @param oclass
	 * @return
	 */
	public SimpleNetObject warn(String oper, String module, String ofunc, String msg) {
		Buslog log = new Buslog();
		log.setOperator(oper);
		log.setModule(module);
		log.setOdate(new Date());
		log.setMsg(msg);
		log.setOfunc(ofunc);
		log.setOclass("WARN");
		return this.add(log);
	}

	/**
	 * 
	 * @param oper
	 * @param module
	 * @param ofunc
	 * @param msg
	 * @param oclass
	 * @return
	 */
	public SimpleNetObject error(String oper, String module, String ofunc, String msg) {
		Buslog log = new Buslog();
		log.setOperator(oper);
		log.setModule(module);
		log.setOdate(new Date());
		log.setMsg(msg);
		log.setOfunc(ofunc);
		log.setOclass("ERROR");
		return this.add(log);
	}

	public SimpleNetObject update(Buslog entity) {
		SimpleNetObject sno = new SimpleNetObject();
		try {
			int result = this.buslogmapper.update(entity);
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
					int oid = Integer.parseInt(id);
					this.buslogmapper.delete(oid);
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

	public List<Buslog> list(Buslog entity, PageBounds pageBounds) {

		try {
			return this.buslogmapper.list(entity, pageBounds);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

	public List<Buslog> list(JqQuery query, PageBounds pageBounds) {

		try {
			if (null != query.getQuery() && query.getQuery().size() > 0) {
				List<JqCondition> list = query.getQuery();
				int flag = 0;
				for (int i = 0; i < list.size(); i++) {
					JqCondition jq = list.get(i);
					if ("odate".equals(jq.getKey())) {
						flag = 1;
					}
				}
				if (0 == flag) {
					JqCondition condition = new JqCondition();
					condition.setKey("odate");
					condition.setOperator("~");
					condition.setDatatype("~");
					condition.setValue(
							DateUtil.getFormatDate(DateUtil.getFirstDayOfMonth(new Date()), "yyyy/MM/dd HH:mm:ss") + "-"
									+ DateUtil.getFormatDate(new Date(), "yyyy/MM/dd HH:mm:ss"));
					query.getQuery().add(condition);
				}
			}

			if (query.getQuery() == null || query.getQuery().size() == 0) {
				JqCondition condition = new JqCondition();
				condition.setKey("odate");
				condition.setOperator("bw");
				condition.setDatatype("date");
				condition.setValue(DateUtil.getFormatDate(new Date(), "yyyy/MM/dd"));
				query.getQuery().add(condition);
			}
			if (StringTool.isNullOrEmpty(query.getOrderfield())) {
				query.setOrderfield("odate");
				query.setOrdertype("desc");
			}
			return this.buslogmapper.query(query, pageBounds);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

	public Buslog getById(int oid) {
		try {
			return this.buslogmapper.getById(oid);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

	public List<Buslog> list(MyBatisWhere where, PageBounds pageBounds) {

		try {
			return this.buslogmapper.select(where, pageBounds);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}
}