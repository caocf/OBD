package com.hgsoft.carowner.api;

import java.io.BufferedReader;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import com.hgsoft.carowner.entity.OBDStockInfo;
import com.hgsoft.carowner.entity.OBDTimeParams;
import com.hgsoft.carowner.entity.ObdApiRecord;
import com.hgsoft.carowner.entity.ObdState;
import com.hgsoft.carowner.service.OBDStockInfoService;
import com.hgsoft.carowner.service.ObdApiRecordService;
import com.hgsoft.carowner.service.ObdSateService;
import com.hgsoft.carowner.service.ObdSettingService;
import com.hgsoft.carowner.service.ObdTimeParamsService;
import com.hgsoft.common.action.BaseAction;
import com.hgsoft.common.utils.IDUtil;
import com.hgsoft.common.utils.MD5Coder;
import com.hgsoft.common.utils.PropertiesUtil;
import com.hgsoft.obd.service.ServerSettingService;
import com.hgsoft.obd.util.RequestForwardUtil;

/**
 * 车主通平台与客户端接口:设备自动唤醒
 * 
 * @author ljl
 */
@Controller
@Scope("prototype")
public class WakeupQueryAction extends BaseAction {

	private static Logger obdApiLogger = LogManager.getLogger("obdApiLogger");

	/** 接收的json数据 */
	private String jsonStr;
	/** 权限检验-成功编码 */
	private final String CHECK_SUCCESS = "01";
	/** 权限检验-失败编码 */
	private final String CHECK_ERROR = "02";

	@Resource
	private OBDStockInfoService obdStockInfoService;
	@Resource
	private ObdSettingService obdSettingService;
	@Resource
	private ObdApiRecordService obdApiRecordService;
	@Resource
	private ObdSateService obdSateService;
	@Resource
	private ServerSettingService serverSettingService;
	@Resource
	private RequestForwardUtil requestForwardUtil;
	@Resource
	private ObdTimeParamsService obdTimeParamsService;

	/**
	 * 接口入口，权限校验
	 */
	public void entryInterface() {
		ObdApiRecord oar = new ObdApiRecord();
		oar.setId(IDUtil.createID());
		oar.setCreateTime(new Date());
		long begin = System.currentTimeMillis();
		JSONObject returnJson = null;
		HttpServletRequest request = null;
		// 请求用户
		String requestUser = "";
		String dev = null;
		try {
			request = ServletActionContext.getRequest();
			// POST时获得参数
			if (null == jsonStr || "".equals(jsonStr)) {
				// 获取POST参数值
				BufferedReader br = request.getReader();
				String buffer = null;
				StringBuffer buff = new StringBuffer();
				while ((buffer = br.readLine()) != null) {
					buff.append(buffer);
				}
				br.close();
				jsonStr = buff.toString();
			}
			if (null != deviceId && !"".equals(deviceId)) {
				JSONObject json = new JSONObject();
				json.put("deviceId", deviceId);
				json.put("username", username);
				json.put("time", time);
				json.put("sign", sign);
				jsonStr = json.toString();
			}

			String path = request.getRequestURI();

			if (StringUtils.isEmpty(jsonStr)) {
				this.outMessage(createReturnJson(-1, "请求失败.").toString());
				return;
			}
			JSONObject jo = JSONObject.fromObject(jsonStr);
			if (!jo.containsKey("deviceId")) {
				this.outMessage(createReturnJson(-1, "请求失败.").toString());
				return;
			}
			dev = jo.optString("deviceId");
			if (StringUtils.isEmpty(dev) || "null".equals(dev)) {
				this.outMessage(createReturnJson(-1, "请求失败.").toString());
				return;
			}
			oar.setObdMsn(dev);
			oar.setUrl(path);
			oar.setParam(jsonStr);
			oar.setMethod("wakeup");

			obdApiLogger.info("----------【请求路径】：" + path + "----------【请求基本参数】：" + jsonStr);

			// 校验权限
			obdApiLogger.info("----------【校验权限】----------");
			JSONObject jso = checkPermissions(jsonStr);
			requestUser = jso.optString("username");
			if (jso.optString("checkCode").equals(CHECK_SUCCESS)) {
				obdApiLogger.info("----------【校验权限】通过----------");
				JSONObject recJson = jso.getJSONObject("recJson");
				returnJson = this.wakeupQuery(recJson);
			} else {
				String errorStr = "it is not pass the permissions check,换句话说：就是权限校验没过，自己看着办";
				returnJson = createReturnJson(-1, "请求失败.");
				returnJson.put("errorInfo", errorStr);
				obdApiLogger.info("----------【校验权限】失败----------");
			}
		} catch (Exception e) {
			returnJson = createReturnJson(-1, "请求失败.");
			returnJson.put("errorInfo", e.getMessage());
			e.printStackTrace();
			obdApiLogger.error("----------【电信接口异常】自动唤醒开关和自动唤醒时间查询:" + e);
		}
		// 返回json数据
		if (returnJson == null) {
			returnJson = new JSONObject();
		}
		obdApiLogger.info("~obdApiLogger~:【" + request.getRemoteHost() + "】-设备:" + dev + "-" + requestUser + "-"
				+ request.getRequestURI() + "->" + (System.currentTimeMillis() - begin));
		oar.setReturnMsg(returnJson.toString());
		boolean flag = obdApiRecordService.irSave(oar);
		obdApiLogger.error("----------【电信接口操作记录保存结果】:自动唤醒开关和自动唤醒时间查询" + flag);
		this.outMessage(returnJson.toString());
	}

	/**
	 * 校验请求的权限
	 * 
	 * @param jsonStr
	 *            请求的json格式字符串
	 * @return 请求参数转换的json数据
	 */
	private JSONObject checkPermissions(String jsonStr) throws Exception {
		JSONObject jso = new JSONObject();
		JSONObject recJson = new JSONObject();
		recJson = JSONObject.fromObject(jsonStr);
		obdApiLogger.info("----------【校验权限】请求参数：" + recJson);
		// 权限校验的结果编码：01-失败 02-成功
		String checkCode = CHECK_SUCCESS;
		// OBD设备唯一标识
		String deviceId = recJson.optString("deviceId");
		// 平台分配的用户
		String username = recJson.optString("username");
		// 时间戳
		String time = recJson.optString("time");
		// MD5 32位(userId+ carUser + time+ password)password为系统分配的密码
		String sign = recJson.optString("sign");
		obdApiLogger.info("参数：【设备号】->" + deviceId + ",【时间】->" + time + ",【sign】->" + sign);
		// 读取账号密码
		String password = PropertiesUtil.getInstance("accounts.properties").readProperty(username);
		String mySign = MD5Coder.encodeMD5Hex(deviceId + username + time + password);
		obdApiLogger.info("----------【校验权限】MD5加密：" + mySign);
		if (StringUtils.isEmpty(deviceId) || StringUtils.isEmpty(username) || StringUtils.isEmpty(time)
				|| !mySign.equals(sign)) {
			checkCode = CHECK_ERROR;
		}
		jso.put("recJson", recJson);
		jso.put("checkCode", checkCode);
		return jso;
	}

	/**
	 * 创建返回的json对象，里面包含公共的返回参数
	 * 
	 * @param code
	 *            返回结果标识，0成功，其它失败
	 * @param desc
	 *            设备唯一标识
	 * @return json对象
	 */
	private JSONObject createReturnJson(int code, String desc) {
		JSONObject retJso = new JSONObject();
		retJso.put("code", code);
		retJso.put("desc", desc);
		return retJso;
	}

	/**
	 * 车辆型号接口 liujialin b.根据type进行逻辑处理
	 * 
	 */
	private JSONObject wakeupQuery(JSONObject jso) throws Exception {
		obdApiLogger.info("----------【自动唤醒开关和自动唤醒时间查询】---");
		if (!StringUtils.isEmpty(type)) {
			jso.put("type", type);
		}

		String type = jso.optString("type");// 操作类别

		obdApiLogger
				.info("----------【自动唤醒开关和自动唤醒时间查询】设备号:" + deviceId + "---操作类型:" + type + "---请求参数:" + jso.toString());

		JSONObject retJso = null;
		switch (type) {
		case "0":
			retJso = type0(jso);
			break;
		case "1":
			retJso = type1(jso);
			break;
		default:
			obdApiLogger.info("----------【自动唤醒开关和自动唤醒时间查询】---" + deviceId + "---请求类型参数有误---" + type);
			return resultJson(-1, -1, "请求类型参数有误.", null, null);
		}

		return retJso;
	}

	/**
	 * 自动开关 设备在线： 做集群转发 设置成功：查询obdstate状态 设置失败：返回失败，且不做离线设置，需用户重新设置
	 * 
	 * 设备离线： 直接存离线记录，并且不更新obdstate表的状态。
	 * 
	 * @param deviceId
	 * @param jso
	 * @return
	 */
	private JSONObject type0(JSONObject jso) {
		obdApiLogger.info("----------【自动唤醒开关和自动唤醒时间查询】开关---" + deviceId + "---请求参数:" + jso.toString());
		int code = 0;
		int state = 0;
		String desc = "操作成功.";
		String wakeupState = "-1";// 自动唤醒开关状态
		try {
			String deviceId = jso.optString("deviceId");
			// 先判断设备是否存在
			OBDStockInfo obd = obdStockInfoService.queryByObdMSN(deviceId);
			if (obd == null) {
				obdApiLogger.info("----------【自动唤醒开关和自动唤醒时间查询】开关---" + deviceId + "---设备不存在.");
				return resultJson(-1, -1, "设备不存在,请联系管理员.", null, null);
			}
			String obdSN = obd.getObdSn();
			ObdState obdState = obdSateService.queryByObdSn(obdSN);
			if (obdState != null) {
				String wakeup2 = obdState.getWakeup();
				if (!StringUtils.isEmpty(wakeup2)) {
					wakeupState = wakeup2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			obdApiLogger.info("----------【自动唤醒开关和自动唤醒时间查询】开关---" + deviceId + "---异常信息---" + e);
			code = -1;
			state = -1;
			desc = "服务器异常,请联系管理员---" + e;
		}
		return resultJson(code, state, desc, wakeupState, null);
	}

	/**
	 * 自动唤醒时间 设备在线： 做集群转发 设置成功：查询obdstate状态 设置失败：返回失败，且不做离线设置，需用户重新设置
	 * 
	 * 设备离线： 直接存离线记录，并且不更新obdstate表的状态。
	 * 
	 * @param deviceId
	 * @param jso
	 * @return
	 */
	private JSONObject type1(JSONObject jso) {
		obdApiLogger.info("----------【自动唤醒开关和自动唤醒时间查询】时间---" + deviceId + "---请求参数:" + jso.toString());
		int code = 0;
		int state = 0;
		String desc = "操作成功.";
		Integer wakeupTime = -1;// 自动唤醒时间状态
		try {
			String deviceId = jso.optString("deviceId");
			// 先判断设备是否存在
			OBDStockInfo obd = obdStockInfoService.queryByObdMSN(deviceId);
			if (obd == null) {
				obdApiLogger.info("----------【自动唤醒开关和自动唤醒时间查询】时间---" + deviceId + "---设备不存在.");
				return resultJson(-1, -1, "设备不存在,请联系管理员.", null, null);
			}
			String obdSN = obd.getObdSn();
			OBDTimeParams otp = obdTimeParamsService.getObdTimeParamsBySn(obdSN);
			if (otp != null) {
				wakeupTime = otp.getObdOfflineTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
			obdApiLogger.info("----------【自动唤醒开关和自动唤醒时间查询】时间---" + deviceId + "---异常信息---" + e);
			code = -1;
			state = -1;
			desc = "服务器异常,请联系管理员---" + e;
		}
		return resultJson(code, state, desc, null, wakeupTime);
	}

	private JSONObject resultJson(Integer code, Integer state, String desc, String wakeupSwitch, Integer wakeupTime) {
		JSONObject retJso = createReturnJson(code, desc);
		JSONObject resultJso = new JSONObject();
		// 当前状态：0成功 -1失败
		resultJso.put("state", state);
		if (!StringUtils.isEmpty(wakeupSwitch)) {
			resultJso.put("wakeupSwitch", wakeupSwitch);
		}
		if (wakeupTime != null) {
			resultJso.put("wakeupTime", wakeupTime);
		} 
		retJso.put("result", resultJso);
		return retJso;
	}

	private String deviceId;
	private String username;
	private String time;
	private String sign;

	private String type;// 操作类别

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
