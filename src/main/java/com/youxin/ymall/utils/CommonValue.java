package com.youxin.ymall.utils;

import com.rsclouds.util.DateUtil;
import com.rsclouds.util.StringTool;

public class CommonValue {

	/**
	 * 9元20天套餐id
	 */
	public static int PACKAGE920GROUPID = 3001;

	/**
	 * 新手任务一元一天套餐id
	 */
	public static int PACKAGE11GROUPID = 3011;
	/**
	 * 双十一狂欢套餐id
	 */
	public static int PACKGE1111GROUPID = 1111;
	/**
	 * 双十一狂欢套餐id
	 */
	public static int PACKGE5710GROUPID = 5710;
	/**
	 * 双十一狂欢套餐id
	 */
	public static int PACKGE5711GROUPID = 5711;
	/**
	 * 设备码验证字符串，手机app也保存一份，youxin888的md5值
	 */
	public static String USERDEVICECODEMIYAO = "45aec738f28cb300b027f80fc3c21745";

	/**
	 * 注册用户线下购买充值卡数据开关,1为显示数据，0为不显示
	 */
	public static int REGUSER_OFFLINERECHARGE_SWITCH = 0;

	/**
	 * 注册用户线下购买充值卡，每次的金额
	 */
	public static double REGUSER_OFFLINERECHARGE_MONEY = 5.00;

	/**
	 * 金币下载时间间隔
	 */
	public static int VALID_DOWNLOAD_INTERVAL = 3 * 60 * 60 * 1000;
	/**
	 * android设备类型
	 */
	public static String STR_DEVICETYPE_ANDROID = "ANDROID";

	/**
	 * ios设备类型
	 */
	public static String STR_DEVICETYPE_IOS = "IOS";

	/**
	 * app主页广告
	 */
	public static String STR_ADVTYPE_HOME = "HOMEPAGE";
	/**
	 * app热门活动广告
	 */
	public static String STR_ADVTYPE_ACTIVITY = "ACTIVITY";

	public static String TRANS_STATUS_WAITPAY = "WAITPAY";
	public static String TRANS_STATUS_SUCCESS = "SUCCESS";
	public static String TRANS_STATUS_WAITCHARGE = "WAITCHARGE";
	public static String TRANS_STATUS_CHARGESUCCESS = "CHARGESUCCESS";
	public static String TRANS_OTYPE_IN = "IN"; // 收入
	public static String TRANS_OTYPE_OUT = "OUT"; // 支出

	public static String TRANS_TRANSTYPE_BUY = "BUY"; // 该账单代表购买
	public static String TRANS_TRANSTYPE_CHARGE = "CHARGE"; // 充值
	public static String TRANS_TRANSTYPE_CHARGECARD_CHARGE = "CHARGECARD"; // 充值
	public static String TRANS_TRANSTYPE_BUY_RECHARGE = "BUY_RECHARGE"; // 代表购买过程中钱不够，进行充值，当该笔充值状态变更后需要同步更改流水号为assflowid的状态
	public static String TRANS_TRANSTYPE_TRANSFER = "TRANSFER"; // 系统转账
	public static String TRANS_TRANSTYPE_EXCHANGE = "EXCHANGE"; // 兑换礼品需要支付余额

	public static String ORDER_STATUS_WAITPAY = "WAITPAY"; // 等待支付
	public static String ORDER_STATUS_SUCCESS = "SUCCESS"; // 交易成功
	public static String ORDER_STATUS_CLOSE = "CLOSE"; // 交易关闭
	public static String ORDER_STATUS_WAITSEND = "WAITSEND"; // 等待发货
	public static String ORDER_STATUS_WAITCONFIRM = "WAITCONFIRM"; // 等待商家确认
	public static String ORDER_STATUS_SENDING = "SENDING"; // 配送中
	public static String ORDER_BUT_TYPE_EXCHANGE = "EXCHANGE"; // 兑换
	public static String ORDER_BUT_TYPE_BUYPACKAGE = "BUYPACKAGE"; // 购买套餐
	public static String ORDER_BUT_TYPE_BUYGIFT = "BUYGIFT"; // 购买礼包
	public static String ORDER_BUT_TYPE_BUYBALANCE = "BUYBALANCE"; // 代理商充值余额
	public static String ORDER_BUT_TYPE_TRANSFER = "TRANSFER"; // 转账
	public static String ORDER_BUT_TYPE_HELPBUY = "HELPBUY"; // 转账
	public static String ORDER_BUT_TYPE_HALFYEARPACKAGEHUIKUI = "HALFYEARPACKAGEHUIKUI"; // 购买半年回馈套餐
	public static String ORDER_BUT_TYPE_YEARPACKAGEHUIKUI = "YEARPACKAGEHUIKUI"; // 购买一年回馈套餐
	public static String ORDER_BUT_TYPE_DISCOUNT = "DISCOUNT"; // 购买砍价年套餐
	public static String SETTING_POINTS_CONVERT_RATIO = "point.ratio";

	/**
	 * 有粉佣金比例
	 */
	public static String SETTING_YOUFEN_SALARY_RATIO = "YOUFEN.SALARY.RATIO";
	/**
	 * 有粉工厂最近更新时间
	 */
	public static String SETTING_YOUFEN_FACTORY_UPDATE = "YOUFEN.FACTORY.UPDATE";

	/**
	 * weup开户默认套餐id
	 */
	public static String SETTING_WEUP_DEFAULT_PACKAGEID = "WEUP.DEFAULT.PACKAGEID";
	/**
	 * 每日签到获取金币数
	 */
	public static String SETTING_DAILY_SIGN_BOUNUS = "sign.per.point";

	/**
	 * 注册赠送金币数
	 */
	public static String SETTING_REG_BOUNUS = "point.reg.bouns";

	/********************* 所有的金币消耗和赚取类型 ********************************************/
	/**
	 * 签到
	 */
	public static String POINT_MAKE_TYPE_DAILY_SIGN = "DAILY_SIGN";
	/**
	 * 下载
	 */
	public static String POINT_MAKE_TYPE_APP_DOWN = "APP_DOWN";
	public static String POINT_MAKE_TYPE_YOUMI = "APP_DOWN";
	public static String POINT_MAKE_TYPE_YOUMI_APPDOWN = "有米应用积分墙";
	/**
	 * 初次登录奖励
	 */
	public static String POINT_MAKE_TYPE_BONUS = "BONUS";
	public static String POINT_MAKE_TYPE_FRISTLOGIN = "首次登陆";
	public static String POINT_MAKE_YOUFEN_CHARGE = "BONUS_YOUFEN_CHARGE";

	public static String POINT_MAKE_YOUFEN_REGUSER = "REGUSER";
	public static String POINT_MAKE_YQZCBCZ = "邀请他人注册并充值";
	public static String POINT_MAKE_REDBAG = "红包";
	public static String POINT_MAKE_ZPCJJL = "转盘抽奖中奖";
	public static String POINT_MAKE_FPCJJL = "翻牌抽奖奖励";
	public static String POINT_MAKE_ZPCJ = "转盘抽奖";
	public static String POINT_MAKE_TYPE_YFBCBONUS = "有粉帮充奖励";
	/**
	 * 使用积分购买套餐
	 */
	public static String POINT_CONSUME_TYPE_BUY_PACKAGE = "BUY_PACKAGE";
	/**
	 * 帮助他人充值抵扣
	 */
	public static String POINT_CONSUME_TYPE_HELP_PAY = "HELP_PAY";
	/**
	 * 兑换网费
	 */
	public static String POINT_CONSUME_TYPE_REDEEM = "REDEEM";
	/**
	 * 朋友圈完善资料奖励
	 */
	public static String POINT_CONSUME_TYPE_PYQINFO = "朋友圈完善资料奖励";

	/**
	 * 天会活动奖励
	 */
	public static final String POINT_MAKE_TYPE_TIAN_HUI = "天会活动奖励";

	/**
	 * 一键加速
	 */
	public static final String POINT_WIFI_SPEEDUP = "一键加速";

	/**
	 * 金币充值
	 */
	public static final String POINT_WIFI_BUYGOLD = "金币充值";

	/**
	 * 新手任务赠送金币
	 */
	public static final String POINT_WIFI_XINSHOULOGINZENGSONG = "新手任务赠送金币";

	/**
	 * 首冲礼包赠送金币
	 */
	public static final String POINT_WIFI_GIFTZENGSONG = "首冲礼包赠送金币";

	/**
	 * 达到活跃度开奖赠送金币
	 */
	public static final String POINT_WIFI_VITALITYZENGSONG = "达到活跃度开奖赠送金币";
	/**
	 * 十二月活动开始时间
	 */
	public static final String Decmber_Activity_Startdate = "2017-12-01";
	/**
	 * 十二月活动结束时间
	 */
	public static final String Decmber_Activity_Enddate = "2018-01-01";
	/**
	 * 砍价活动开始时间
	 */
	public static final String Activitypackage_Startdate = "2017-12-23";
	/**
	 * 砍价活动结束时间
	 */
	public static final String Activitypackage_Enddate = "2017-12-26";
	/**
	 * 回馈套餐开始时间
	 */
	public static final String Packagegrade_Startdate = "2017-12-26";
	/**
	 * 回馈套餐结束时间
	 */
	public static final String Packagegrade_Enddate = "2018-01-01";
	/**
	 * 回馈套餐开始时间
	 */
	public static final String Flopaward_Startdate = "2018-01-01";
	/**
	 * 回馈套餐结束时间
	 */
	public static final String Flopaward_Enddate = "2018-03-01";
	/**
	 * 生成usertrans支付流水号
	 * 
	 * @return
	 */
	public static String getTransFlowid() {
		return "T" + DateUtil.getCurrentDate("yyMMddHHmmss") + StringTool.getRamCode(6);
	}

	/**
	 * 生成订单流水号
	 * 
	 * @return
	 */
	public static String getOrderCode() {
		return "O" + DateUtil.getCurrentDate("yyMMddHHmmss") + StringTool.getRamCode(6);
	}

	/**
	 * 生成夺宝订单流水号
	 * 
	 * @return
	 */
	public static String getDuobaoOrderCode() {
		return "DB" + DateUtil.getCurrentDate("yyMMddHHmmss") + StringTool.getRamCode(6);
	}

	/**
	 * 生成秒杀订单流水号
	 * 
	 * @return
	 */
	public static String getMiaoshaOrderCode() {
		return "MS" + DateUtil.getCurrentDate("yyMMddHHmmss") + StringTool.getRamCode(6);
	}

	/**
	 * 生成App消息批次号
	 * 
	 * @return
	 */
	public static String getAppMsgGroupId() {
		return "M" + DateUtil.getCurrentDate("yyMMddHHmmss") + StringTool.getRamCode(6);
	}

	/**
	 * 生成话费套餐充值订单号。
	 * 
	 * @return
	 */
	public static String getPhonePackageOrderId() {
		return "ppo" + DateUtil.getCurrentDate("yyMMddHHmmss") + StringTool.getRamCode(6);
	}

	/**
	 * 生成红包订单流水号
	 * 
	 * @return
	 */
	public static String getRedOrderCode() {
		return "RED" + DateUtil.getCurrentDate("yyMMddHHmmss") + StringTool.getRamCode(6);
	}

	/**
	 * 生成购买金币订单流水号
	 * 
	 * @return
	 */
	public static String getBuyGoldOrderCode() {
		return "GMJB" + DateUtil.getCurrentDate("yyMMddHHmmss") + StringTool.getRamCode(6);
	}
}
