package cn.zhu.test.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

public class ToolUtils {

	private static final int ROTATION = 999;
	/**
	 * 阿里特殊字符串数据过滤
	 *
	 * @param str
	 *            字符串
	 * @return 过滤后的字符串
	 */
	public static Pattern replaceBlankPattern = Pattern.compile("\\s*|\t|\r|\n");
	public static Pattern replaceGeneratePattern = Pattern.compile("\\s*|\t|\r|\n");
	/**
	 * @Title: isContainChinese @Description: 判断字符串里面是否含有中文 @param @param str @param @return 设定文件 @return boolean 返回类型 @throws
	 */
	public static Pattern isContainChinesePattern = Pattern.compile("[\u4e00-\u9fa5]");
	/**
	 * 阿里特殊字符串数据过滤
	 *
	 * @param str
	 *            字符串
	 * @return 过滤后的字符串
	 */
	public static Pattern aliReplaceBlankPattern = Pattern.compile(",\\s*|\t|\r|\n");
	public static Pattern strParams = Pattern.compile("\\s*|\t|\r|\n");
	private static int seq = 0;

	public static String getNameWithoutExtension(String file) {
		checkNotNull(file);
		String fileName = new File(file).getName();
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
	}

	public static String getFileExtension(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return "";
		}
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}

	public static List<String> getStrParams(String dest) {
		Matcher m = strParams.matcher(dest);
		dest = m.replaceAll("");
		if (dest.endsWith(",")) {
			dest = dest.substring(0, dest.length() - 1);
		}
		String[] strings = dest.split(",");
		List<String> strsToList = new ArrayList<>();
		Collections.addAll(strsToList, strings);
		return strsToList;
	}

	public static String getSqlIn(String dest) {
		Matcher m = strParams.matcher(dest);
		dest = m.replaceAll("");
		if (dest.endsWith(",")) {
			dest = dest.substring(0, dest.length() - 1);
		}
		if (dest.contains("'")) {
			return dest;
		}
		String[] strings = dest.split(",");
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (String s : strings) {
			if (index == 0) {
				sb.append("'").append(s).append("'");
			} else {
				sb.append(",'").append(s).append("'");
			}
			index++;
		}
		return sb.toString();
	}

	public static String replaceQuote(String dest) {
		if (StringUtils.isNotBlank(dest)) {
			if (dest.indexOf("\"") != -1) {
				dest = dest.replaceAll("\"", "");
			}
		}
		return dest;
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (StringUtils.isNotBlank(str)) {

			Matcher m = replaceBlankPattern.matcher(str);
			dest = m.replaceAll("");
		}

		return dest;
	}

	public static String replaceExportString(String str) {
		String dest = "";
		if (StringUtils.isNotBlank(str)) {
			Matcher m = replaceGeneratePattern.matcher(str);
			dest = m.replaceAll("");
			if (dest.indexOf(",") != -1) {
				dest = dest.replaceAll(",", "，");
			}
			if (dest.indexOf("\"") != -1) {
				dest = dest.replaceAll("\"", "");
			}
		}

		return dest;
	}

	public static String replaceGenerate(String str) {
		String dest = "";
		if (StringUtils.isNotBlank(str)) {
			Matcher m = replaceGeneratePattern.matcher(str);
			dest = m.replaceAll("");
			if (dest.indexOf("\"") != -1) {
				dest = dest.replaceAll("\"", "");
			}
		}

		return dest;
	}

	public static String getRepUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	public static Map<String, Object> convertBean(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Class<? extends Object> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				if (readMethod == null) {
					continue;
				}
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					Class<?> returnType = readMethod.getReturnType();
					if (Date.class.equals(returnType)) {
						// 时间格式化
						String s = dateFormat((Date) result, "yyyy-MM-dd HH:mm:ss");
						returnMap.put(propertyName, s);
					} else {
						returnMap.put(propertyName, result);
					}
				} else {
					returnMap.put(propertyName, result);
				}
			}
		}
		return returnMap;
	}

	public static String dateFormat(Date date, String format) {
		if (StringUtils.isEmpty(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return sdf.format(date);
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 随机生成指定字符串
	 *
	 * @param length
	 *            字符串长度
	 * @return 随机生成字符串
	 */
	public static String getRandomString(int length) {
		// length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 数字生成随机字符串<br/>
	 * &nbsp;&nbsp;1.需要指定长度生成，默认为16位长度； <br/>
	 * &nbsp;&nbsp;2.随机生成字符串规则，需要根据指定加密基数
	 */
	public static String getRandomNum() {
		// length表示生成字符串的长度
		String base = "0123456789";
		return randomStr(0, base);
	}

	/**
	 * 小写、数字生成随机字符串<br/>
	 * &nbsp;&nbsp;1.需要指定长度生成，默认为16位长度； <br/>
	 * &nbsp;&nbsp;2.随机生成字符串规则，需要根据指定加密基数
	 */
	public static String getRandomLower() {
		// length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		return randomStr(0, base);
	}

	/**
	 * 大写、小写、数字生成随机字符串<br/>
	 * &nbsp;&nbsp;1.需要指定长度生成，默认为16位长度； <br/>
	 * &nbsp;&nbsp;2.随机生成字符串规则，需要根据指定加密基数
	 */
	public static String getRandomUpper() {
		// length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		return randomStr(0, base);
	}

	/**
	 * 特殊字符串、大写、小写、数字生成随机字符串<br/>
	 * &nbsp;&nbsp;1.需要指定长度生成，默认为16位长度； <br/>
	 * &nbsp;&nbsp;2.随机生成字符串规则，需要根据指定加密基数
	 */
	public static String getRandomSpecial() {
		// length表示生成字符串的长度
		String base = "!@#$%^&*ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		return randomStr(0, base);
	}

	/**
	 * 数字生成随机字符串<br/>
	 * &nbsp;&nbsp;1.需要指定长度生成，默认为16位长度； <br/>
	 * &nbsp;&nbsp;2.随机生成字符串规则，需要根据指定加密基数
	 */
	public static String getRandomNum(int length) {
		// length表示生成字符串的长度
		String base = "0123456789";
		return randomStr(length, base);
	}

	/**
	 * 小写、数字生成随机字符串<br/>
	 * &nbsp;&nbsp;1.需要指定长度生成，默认为16位长度； <br/>
	 * &nbsp;&nbsp;2.随机生成字符串规则，需要根据指定加密基数
	 */
	public static String getRandomLower(int length) {
		// length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		return randomStr(length, base);
	}

	/**
	 * 大写、小写、数字生成随机字符串<br/>
	 * &nbsp;&nbsp;1.需要指定长度生成，默认为16位长度； <br/>
	 * &nbsp;&nbsp;2.随机生成字符串规则，需要根据指定加密基数
	 */
	public static String getRandomUpper(int length) {
		// length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		return randomStr(length, base);
	}

	/**
	 * 特殊字符串、大写、小写、数字生成随机字符串<br/>
	 * &nbsp;&nbsp;1.需要指定长度生成，默认为16位长度； <br/>
	 * &nbsp;&nbsp;2.随机生成字符串规则，需要根据指定加密基数
	 */
	public static String getRandomSpecial(int length) {
		// length表示生成字符串的长度
		String base = "!@#$%^&*ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		return randomStr(length, base);
	}

	/**
	 * 随机生成字符串
	 *
	 * @param length
	 * @param base
	 * @return
	 */
	private static String randomStr(int length, String base) {
		if (length == 0) {
			length = 16;
		}
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * Checks if a CharSequence is whitespace, empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * StringUtils.isBlank("  null  ") = true
	 * </pre>
	 *
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
	 */
	public static boolean isBlank(String string) {
		if (StringUtils.isBlank(string) || "null".equalsIgnoreCase(string)) {
			return true;
		}
		return false;
	}

	public static boolean isNotBlank(String string) {
		if (StringUtils.isNotBlank(string) && !"null".equalsIgnoreCase(string)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isContainChinese(String str) {
		Matcher m = isContainChinesePattern.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 将经纬度的字符串转换为double数组
	 *
	 * @param position
	 *            字符串
	 * @return 经纬度坐标数组
	 */
	public static double[] changePosition(String position) {
		String[] temp = position.split(",");
		double[] response = new double[] { new BigDecimal(temp[0]).doubleValue(), new BigDecimal(temp[1]).doubleValue() };
		return response;
	}

	/**
	 * 将经纬度的字符串转换为double数组
	 *
	 * @param position
	 *            字符串
	 * @return 经纬度坐标数组
	 */
	public static double[] changePosition1(String position) {
		String[] temp = position.split(",");
		double[] response = new double[] { new BigDecimal(temp[0]).doubleValue() };
		return response;
	}

	public static BigDecimal decimalIsBank(Object object) {
		if (object == null) {
			return new BigDecimal("0.00");
		} else {
			return new BigDecimal(object.toString());
		}
	}

	public static BigDecimal aliDecimal(Object object) {
		if (object == null) {
			return new BigDecimal("0.00");
		} else {
			return new BigDecimal(aliReplaceBlank(object.toString()));
		}
	}

	public static BigDecimal wxDecimal(Object object) {
		if (object == null) {
			return new BigDecimal("0.00");
		} else {
			return new BigDecimal(wxReplaceRegex(object.toString()));
		}
	}

	public static BigDecimal jdDecimal(Object object) {
		if (object == null) {
			return new BigDecimal("0.00");
		} else {
			return new BigDecimal(object.toString());
		}
	}

	public static String wxReplaceRegex(String str) {
		if (str.contains("`")) {
			str = str.replaceAll("`", "");
		}
		return str;
	}

	public static String jdReplaceRegex(String str) {
		if (StringUtils.isBlank("=\"\"")) {
			return null;
		}
		if (StringUtils.isBlank(str)) {
			return null;
		}
		if (str.startsWith("=")) {
			str = str.substring(2, str.length() - 1);
		}
		return str;
	}

	public static String aliReplaceBlank(String str) {
		String dest = "";
		if (StringUtils.isNotBlank(str)) {
			Matcher m = aliReplaceBlankPattern.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 表头的特殊字符串过滤
	 *
	 * @param str
	 *            字符串
	 * @return 响应结果
	 */
	public static String headReplaceBlank(String str) {
		// 京东账单表头
		// 订单编号 单据编号 单据类型 商品编号 商户订单号 商品名称 费用发生时间 费用计费时间 费用结算时间 费用项 金额 币种 商家应收/应付 钱包结算备注 店铺号 京东门店编号 品牌门店编号 门店名称 备注 收支方向 账单日期
		// 支付宝账务明细表头
		// 账务流水号 业务流水号 商户订单号 商品名称 发生时间 对方账号 收入金额（+元） 支出金额（-元） 账户余额（元） 交易渠道 业务类型 备注
		// 支付宝业务明细表头
		// 支付宝交易号 商户订单号 业务类型 商品名称 创建时间 完成时间 门店编号 门店名称 操作员 终端号 对方账户 订单金额（元） 商家实收（元） 支付宝红包（元） 集分宝（元） 支付宝优惠（元） 商家优惠（元） 券核销金额（元） 券名称 商家红包消费金额（元） 卡消费金额（元） 退款批次号/请求号 服务费（元） 分润（元） 备注
		// 微信资金账单
		// 记账时间 微信支付业务单号 资金流水单号 业务名称 业务类型 收支类型 收支金额(元) 账户结余(元) 资金变更提交申请人 备注 业务凭证号
		// 微信交易账单
		// 交易时间 公众账号ID 商户号 特约商户号 设备号 微信订单号 商户订单号 用户标识 交易类型 交易状态 付款银行 货币种类 应结订单金额 代金券金额 微信退款单号 商户退款单号 退款金额 充值券退款金额 退款类型 退款状态 商品名称 商户数据包 手续费 费率 订单金额 申请退款金额 费率备注
		if (StringUtils.isNotBlank(str)) {
			if (str.indexOf("（元）") != -1) {
				str = str.replaceAll("（元）", "");
			}
			if (str.indexOf("（+元）") != -1) {
				str = str.replaceAll("（[+]元）", "");
			}
			if (str.indexOf("（-元）") != -1) {
				str = str.replaceAll("（-元）", "");
			}
			if (str.indexOf("(元)") != -1) {
				str = str.replaceAll("[(元)]", "");
			}
		}
		return str;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static <T> List<List<T>> averageAssign(List<T> source, int n) {
		List<List<T>> result = new ArrayList<List<T>>();
		int remaider = source.size() % n;
		// (先计算出余数)
		int number = source.size() / n;
		// 然后是商
		int offset = 0;// 偏移量
		for (int i = 0; i < n; i++) {
			List<T> value = null;
			if (remaider > 0) {
				value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
				remaider--;
				offset++;
			} else {
				value = source.subList(i * number + offset, (i + 1) * number + offset);
			}
			result.add(value);
		}
		return result;
	}

	public static List<String> setToList(Set<String> idSets) {
		List<String> idList = new ArrayList<>();
		idList.addAll(idSets);
		return idList;
	}

	public static String getNaturalMonth(String type) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		switch (type) {
		case "first":
			c.set(Calendar.DAY_OF_MONTH, 1);
			return df.format(c.getTime()) + " 00:00:00";
		case "last":
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			return df.format(c.getTime()) + " 23:59:59";
		default:
			return null;
		}
	}

	public static synchronized String next() {
		if (seq > ROTATION) {
			seq = 0;
		}
		String str = getCurTime() + (seq++);
		return str;
	}


	public static String getCurTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		return sdf.format(new Date());
	}


}
