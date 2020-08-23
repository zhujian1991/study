package cn.zhu.test.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static cn.zhu.test.utils.ToolUtils.*;

public class GenerateUtils {

	private Logger log = LoggerFactory.getLogger(GenerateUtils.class);

	public static List<Map<String, Object>> querySql(Connection mysql_conn, String sql) {
		List<Map<String, Object>> list = new ArrayList<>();
		Statement psmt = null;
		ResultSet rs = null;
		try {
			psmt = mysql_conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			rs = psmt.executeQuery(sql);
			ResultSetMetaData rsmt = rs.getMetaData();
			if (!rs.isLast()) {
				while (rs.next()) {
					Map<String, Object> ht = new HashMap<String, Object>();
					for (int i = 1; i <= rsmt.getColumnCount(); i++) {
						ht.put(rsmt.getColumnName(i).toLowerCase(), "" + rs.getString(i));
					}
					list.add(ht);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (psmt != null) {
					psmt.close();
					psmt = null;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static String getDateStr() {
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		return dateSdf.format(new Date());
	}

	public static boolean isBlank(String string) {
		if (StringUtils.isBlank(string) || string.equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	public void generateEntity(GeneratePojo pojo) throws Exception {
		Connection mysql_conn = null;
		try {
			log.info("*******************加载数据库配置*********************");
			log.info("连接地址：{}", pojo.getDb_url());
			log.info("用户名：{}", pojo.getDb_user());
			log.info("*******************结束数据库配置*********************");
			Class.forName(pojo.getDb_driver());
			mysql_conn = DriverManager.getConnection(pojo.getDb_url(), pojo.getDb_user(), pojo.getDb_pwd());
			dropFile(pojo);
			createEntity(pojo, mysql_conn);
			createRepository(pojo, mysql_conn);
			createDao(pojo, mysql_conn);
			createXml(pojo, mysql_conn);
			// 提供下载压缩
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mysql_conn.close();
		}
	}

	private void dropFile(GeneratePojo pojo) {
		String filename = pojo.getOutputPath();
		File file = new File(filename);
		if (!file.exists()) {
			file.mkdirs();
		}
		dropFile(file);
	}

	private void dropFile(File file) {
		File[] files = file.listFiles();
		for (File file2 : files) {
			if (file2.isDirectory()) {
				if (file2.listFiles().length > 0) {
					dropFile(file2);
				} else {
					file2.delete();
				}
			} else if (file2.isFile()) {
				file2.delete();
			}
		}
		file.delete();
	}

	private void createEntity(GeneratePojo vo, Connection connection) throws Exception {
		String filename = vo.getOutputPath() + "entity" + File.separator;
		File file = new File(filename);
		if (!file.exists()) {
			file.mkdirs();
		}
		File[] files = file.listFiles();
		for (File file2 : files) {
			file2.delete();
		}
		// 先读出所有的表
		String tableSql = "select table_name,table_comment from information_schema.tables where table_schema='" + vo.getDbName() + "'";
		List<Map<String, Object>> listTables = querySql(connection, tableSql);
		for (Map<String, Object> map : listTables) {
			Set<String> listImport = new HashSet<>();
			String tableName = (String) map.get("table_name");
			if (!isBlank(vo.getTableName())) {
				if (!tableName.equals(vo.getTableName())) {
					continue;
				}
			}
			String tableComment = map.get("table_comment").toString();
			if (StringUtils.isNotBlank(tableComment)) {
				tableComment = replaceGenerate(tableComment);
			}
			String className = convertJavaKey(tableName, true) + "Entity";
			// 1.包的引入
			listImport.add("");
			listImport.add("import com.baomidou.mybatisplus.annotation.*;");
			listImport.add("import com.alibaba.fastjson.JSONObject;");
			listImport.add("import javax.persistence.Column;");
			listImport.add("import javax.persistence.Entity;");
			listImport.add("import javax.persistence.Table;");
			listImport.add("import org.hibernate.annotations.DynamicInsert;");
			listImport.add("import org.hibernate.annotations.DynamicUpdate;");
			listImport.add("import com.fasterxml.jackson.annotation.JsonFormat;");
			listImport.add("import io.swagger.annotations.*;");

			List<String> listAnnotation = new ArrayList<>();
			listAnnotation.add("");
			listAnnotation.add("/**");
			listAnnotation.add(" * <p>");
			listAnnotation.add(" * " + tableComment);
			listAnnotation.add(" * </p>");
			listAnnotation.add(" * ");
			listAnnotation.add(" * @version 1.0");
			listAnnotation.add(" * @author 程序老仁");
			listAnnotation.add(" * @since " + getDateStr());
			listAnnotation.add(" */");
			listAnnotation.add("@Entity");
			listAnnotation.add("@DynamicInsert");
			listAnnotation.add("@DynamicUpdate");
			listAnnotation.add("@Table(name = \"" + tableName + "\")");
			listAnnotation.add("@TableName(value =\" " + tableName + "\", schema = \"" + vo.getDbName() + "\")");
			listAnnotation.add("@ApiModel(\"" + tableComment + "\")");
			listAnnotation.add("public class " + className + " implements java.io.Serializable {");
			listAnnotation.add("");
			listAnnotation.add("	private static final long serialVersionUID = -1L;");
			listAnnotation.add("");
			String columnSql = "select ordinal_position,is_nullable,column_name,data_type,character_maximum_length,column_type,column_key,extra,column_comment from information_schema.columns where table_name='"
					+ tableName + "' and table_schema='" + vo.getDbName() + "'";
			List<Map<String, Object>> listColumns = querySql(connection, columnSql);
			List<String> listParams = new ArrayList<>();
			List<String> listMethod = new ArrayList<>();
			List<String> listToString = new ArrayList<>();
			listToString.add("	@Override");
			listToString.add("	public String toString() {");
			listToString.add("		JSONObject object = new JSONObject();");
			for (Map<String, Object> mapColumns : listColumns) {
				String column_name = mapColumns.get("column_name").toString();
				String data_type = mapColumns.get("data_type").toString();
				String column_key = mapColumns.get("column_key").toString();
				String extra = mapColumns.get("extra").toString();
				String is_nullable = mapColumns.get("is_nullable").toString();
				String column_type = mapColumns.get("column_type").toString();
				String column_comment = mapColumns.get("column_comment").toString();
				if (StringUtils.isNotBlank(column_comment)) {
					column_comment = replaceGenerate(column_comment);
				}

				// id
				listParams.add("	/**");
				listParams.add("	 * " + column_comment);
				listParams.add("	 */");
				String column = "@Column(name = \"" + column_name + "\"";
				if (is_nullable.equals("NO")) {
					column += ", nullable = false";
				}
				if (column_key.equals("PRI")) {
					listParams.add("	@Id");
					column += ", unique = true";

					listImport.add("import javax.persistence.Id;");
				} else {
					listParams.add("	@TableField(\"" + column_name + "\")");
				}
				if (column_type.indexOf("(") != -1) {
					String lengths = column_type.substring(column_type.indexOf("(") + 1, column_type.lastIndexOf(")"));
					int length = 0;
					if (lengths.indexOf(",") != -1) {
						int precision = Integer.parseInt(lengths.substring(0, lengths.indexOf(",")));
						int scale = Integer.parseInt(lengths.substring(lengths.indexOf(",") + 1, lengths.length()));
						column += ", precision = " + precision + ", scale = " + scale;
					} else {
						length = Integer.parseInt(lengths);
						column += ", length = " + length;
					}

				}
				column += ")";
				if (extra.equals("auto_increment")) {
					listParams.add("	@GeneratedValue(strategy = GenerationType.IDENTITY)");
					listParams.add("	@TableId(value=\"" + column_name + "\",type=IdType.AUTO)");
					listImport.add("import javax.persistence.GeneratedValue;");
					listImport.add("import javax.persistence.GenerationType;");
				}

				if (data_type.equals("datetime") || data_type.equals("timestamp") || data_type.equals("date")) {
					listParams.add("	@Temporal(TemporalType.TIMESTAMP)");
					listParams.add("	@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\",timezone = \"Asia/Shanghai\")");

					listImport.add("import java.util.Date;");
					listImport.add("import javax.persistence.Temporal;");
					listImport.add("import javax.persistence.TemporalType;");

				}

				listParams.add("	" + column);
				String dataType = getDataType(data_type, listImport);
				if (dataType.equalsIgnoreCase("long") || dataType.equalsIgnoreCase("BigDecimal")) {
					listParams.add("	@JsonSerialize(using = ToStringSerializer.class)");

					listImport.add("import com.fasterxml.jackson.databind.annotation.JsonSerialize;");
					listImport.add("import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;");
				}
				String columnName = convertJavaKey(column_name, false);

				listParams.add("	@ApiModelProperty(value = \"" + column_comment + "\")");
				listParams.add("	private " + dataType + " " + columnName + ";");
				listParams.add("");

				// 组装方法
				String columnMethodName = convertJavaKey(column_name, true);
				// 组装set方法
				listMethod.add("	/**");
				listMethod.add("	 * 属性的公用set方法<br/>");
				listMethod.add("	 * " + column_comment);
				listMethod.add("	 */");
				listMethod.add("	public void set" + columnMethodName + "(" + dataType + " value) {");
				listMethod.add("		this." + columnName + " = value;");
				listMethod.add("    }");
				listMethod.add("");
				listMethod.add("	/**");
				listMethod.add("	 * 属性的公用get方法<br/>");
				listMethod.add("	 * " + column_comment);
				listMethod.add("	 */");
				listMethod.add("    public " + dataType + " get" + columnMethodName + "() {");
				listMethod.add("		return this." + columnName + ";");
				listMethod.add("	}");
				listMethod.add("");
				listToString.add("		object.put(\"" + columnName + "\", get" + columnMethodName + "());");
			}
			listToString.add("		return object.toJSONString();");
			listToString.add("	}");
			List<String> listEnd = new ArrayList<>();
			listEnd.add("package " + vo.getPackageEntity() + ";");
			listEnd.addAll(listImport);
			listEnd.addAll(listAnnotation);
			listEnd.addAll(listParams);
			listEnd.addAll(listMethod);
			listEnd.addAll(listToString);
			listEnd.add("");
			listEnd.add("}");

			File fout = new File(filename + className + ".java");
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (String string : listEnd) {
				bw.write(string);
				bw.newLine();
			}
			bw.close();
			System.out.println("表：" + tableName + " 构建成功！");
		}
	}

	private void createRepository(GeneratePojo pojo, Connection connection) throws Exception {
		String filename = pojo.getOutputPath() + "repository" + File.separator;
		File file = new File(filename);
		if (!file.exists()) {
			file.mkdirs();
		}
		File[] files = file.listFiles();
		for (File file2 : files) {
			file2.delete();
		}
		// 先读出所有的表
		String tableSql = "select table_name,table_comment from information_schema.tables where table_schema='" + pojo.getDbName() + "'";
		List<Map<String, Object>> listTables = querySql(connection, tableSql);
		for (Map<String, Object> map : listTables) {
			Set<String> listImport = new HashSet<>();
			String tableName = (String) map.get("table_name");
			if (!isBlank(pojo.getTableName())) {
				if (!tableName.equals(pojo.getTableName())) {
					continue;
				}
			}
			String tableComment = map.get("table_comment").toString();
			String entityName = convertJavaKey(tableName, true) + "Entity";
			String className = "I" + convertJavaKey(tableName, true) + "Repository";
			List<String> list = new ArrayList<>();
			String columnSql = "select ordinal_position,is_nullable,column_name,data_type,character_maximum_length,column_type,column_key,extra,column_comment from information_schema.columns where table_name='"
					+ tableName + "' and table_schema='" + pojo.getDbName() + "'";
			List<Map<String, Object>> listColumns = querySql(connection, columnSql);
			String dataType = "";
			for (Map<String, Object> mapColumns : listColumns) {
				String data_type = mapColumns.get("data_type").toString();
				String column_key = mapColumns.get("column_key").toString();
				if (column_key.equals("PRI")) {
					dataType = getDataType(data_type, listImport);
				}
			}
			list.add("package " + pojo.getPackageRepository() + ";");
			list.add("");
			listImport.add("import org.springframework.data.jpa.repository.JpaRepository;");
			listImport.add("import org.springframework.data.jpa.repository.JpaSpecificationExecutor;");
			listImport.add("");
			listImport.add("import " + pojo.getPackageEntity() + "." + entityName + ";");
			list.addAll(listImport);
			list.add("");
			list.add("/**");
			list.add(" * <p>");
			list.add(" * " + tableComment);
			list.add(" * </p>");
			list.add(" * ");
			list.add(" * @version 1.0");
			list.add(" * @author 程序老仁");
			list.add(" * @since " + getDateStr());
			list.add(" */");
			list.add("public interface " + className + " extends JpaRepository<" + entityName + ", " + dataType + ">, JpaSpecificationExecutor<" + entityName
					+ "> {");
			list.add("");
			list.add("}");
			File fout = new File(filename + className + ".java");
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (String string : list) {
				bw.write(string);
				bw.newLine();
			}
			bw.close();
		}

	}

	private void createDao(GeneratePojo pojo, Connection connection) throws Exception {
		String filename = pojo.getOutputPath() + "dao" + File.separator;
		File file = new File(filename);
		if (!file.exists()) {
			file.mkdirs();
		}
		File[] files = file.listFiles();
		for (File file2 : files) {
			file2.delete();
		}
		// 先读出所有的表
		String tableSql = "select table_name,table_comment from information_schema.tables where table_schema='" + pojo.getDbName() + "'";
		List<Map<String, Object>> listTables = querySql(connection, tableSql);
		for (Map<String, Object> map : listTables) {
			Set<String> listImport = new HashSet<>();
			String tableName = (String) map.get("table_name");
			if (!isBlank(pojo.getTableName())) {
				if (!tableName.equals(pojo.getTableName())) {
					continue;
				}
			}
			String tableComment = map.get("table_comment").toString();
			String entityName = convertJavaKey(tableName, true) + "Entity";
			String className = convertJavaKey(tableName, true) + "Dao";
			List<String> list = new ArrayList<>();
			list.add("package " + pojo.getPackageDao() + ";");
			list.add("");
			listImport.add("import com.baomidou.mybatisplus.core.mapper.BaseMapper;");
			listImport.add("");
			listImport.add("import " + pojo.getPackageEntity() + "." + entityName + ";");
			listImport.add("import java.util.List;");
			list.addAll(listImport);
			list.add("");
			list.add("/**");
			list.add(" * <p>");
			list.add(" * " + tableComment);
			list.add(" * </p>");
			list.add(" * ");
			list.add(" * @version 1.0");
			list.add(" * @author 程序老仁");
			list.add(" * @since " + getDateStr());
			list.add(" */");
			list.add("public interface I" + className + " extends BaseMapper<" + entityName + ">{");
			list.add("");
			list.add("\tInteger insertBatch(List<" + entityName + "> list);");
			list.add("}");
			File fout = new File(filename + "I" + className + ".java");
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (String string : list) {
				bw.write(string);
				bw.newLine();
			}
			bw.close();
		}

	}

	private void createXml(GeneratePojo pojo, Connection connection) throws Exception {
		String filename = pojo.getOutputPath() + "resources" + File.separator;
		File file = new File(filename);
		if (!file.exists()) {
			file.mkdirs();
		}
		File[] files = file.listFiles();
		for (File file2 : files) {
			file2.delete();
		}
		// 先读出所有的表
		String tableSql = "select table_name,table_comment from information_schema.tables where table_schema='" + pojo.getDbName() + "'";
		List<Map<String, Object>> listTables = querySql(connection, tableSql);
		for (Map<String, Object> map : listTables) {
			String tableName = (String) map.get("table_name");
			if (!isBlank(pojo.getTableName())) {
				if (!tableName.equals(pojo.getTableName())) {
					continue;
				}
			}
			String className = convertJavaKey(tableName, true) + "Mapper";
			String daoName = "I" + convertJavaKey(tableName, true) + "Dao";
			List<String> list = new ArrayList<>();
			list.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			list.add("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
			list.add("<mapper namespace=\"" + pojo.getPackageDao() + "." + daoName + "\">");
			list.add("");
			String entityName = convertJavaKey(tableName, true) + "Entity";
			list.add("    <resultMap id=\"BaseResultMap\" type=\"" + pojo.getPackageEntity() + "." + entityName + "\">");
			String columnSql = "select ordinal_position,is_nullable,column_name,data_type,character_maximum_length,column_type,column_key,extra,column_comment from information_schema.columns where table_name='"
					+ tableName + "' and table_schema='" + pojo.getDbName() + "'";
			List<Map<String, Object>> listColumns = querySql(connection, columnSql);
			StringBuffer sb = new StringBuffer();
			StringBuffer sbInsert = new StringBuffer();
			StringBuffer sbParam = new StringBuffer();
			for (Map<String, Object> mapColumns : listColumns) {
				String column_name = mapColumns.get("column_name").toString();
				String data_type = mapColumns.get("data_type").toString();
				String dataType = getDataType(data_type);
				String columnName = convertJavaKey(column_name, false);
				list.add("        <result column=\"" + column_name + "\" property=\"" + columnName + "\" jdbcType=\"" + dataType + "\"/>");
				if (sb.toString().equalsIgnoreCase("")) {
					sb.append(column_name);
				} else {
					sb.append(",").append(column_name);
				}
				if (column_name.equalsIgnoreCase("id") || column_name.equalsIgnoreCase("is_stop") || column_name.equalsIgnoreCase("gmt_create")
						|| column_name.equalsIgnoreCase("gmt_update")) {
					continue;
				}
				if (sbInsert.toString().equalsIgnoreCase("")) {
					sbInsert.append(column_name);
				} else {
					sbInsert.append(",").append(column_name);
				}
				if (sbParam.toString().equalsIgnoreCase("")) {
					sbParam.append("#{item.").append(columnName).append("}");
				} else {
					sbParam.append(",").append("#{item.").append(columnName).append("}");
				}
			}
			list.add("    </resultMap>");
			list.add("    <!-- 查询基础字段-->");
			list.add("    <sql id=\"Base_Column_List\">");
			list.add("        " + sb.toString());
			list.add("    </sql>");
			list.add("    <!-- 插入基础字段-->");
			list.add("    <sql id=\"Base_Insert_Column_List\">");
			list.add("        " + sbInsert.toString());
			list.add("    </sql>");
			list.add("    <!-- 批量插入-->");
			list.add("    <insert id=\"insertBatch\" parameterType=\"java.util.List\">");
			list.add("        insert into " + tableName);
			list.add("        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
			list.add("            <include refid=\"Base_Insert_Column_List\"/>");
			list.add("        </trim>");
			list.add("        values");
			list.add("        <foreach collection=\"list\" item=\"item\" separator=\",\">");
			list.add("            <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
			list.add("                " + sbParam.toString());
			list.add("            </trim>");
			list.add("        </foreach>");
			list.add("    </insert>");
			list.add("</mapper>");
			File fout = new File(filename + className + ".xml");
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (String string : list) {
				bw.write(string);
				bw.newLine();
			}
			bw.close();
		}

	}

	private String getDataType(String data_type, Set<String> listImport) {
		String dataType = "";
		switch (data_type) {
		case "datetime":
			dataType = "Date";
			listImport.add("import java.util.Date;");
			break;
		case "bigint":
			dataType = "Long";
			break;
		case "varchar":
			dataType = "String";
			break;
		case "decimal":
			dataType = "BigDecimal";
			listImport.add("import java.math.BigDecimal;");
			break;
		case "double":
			dataType = "Double";
			break;
		case "int":
			dataType = "Integer";
			break;
		case "tinyint":
			dataType = "Integer";
			break;
		case "smallint":
			dataType = "Integer";
			break;
		case "mediumint":
			dataType = "Integer";
			break;
		case "float":
			dataType = "Float";
			break;
		case "text":
			dataType = "String";
			break;
		case "longtext":
			dataType = "String";
			break;
		case "mediumtext":
			dataType = "String";
			break;
		case "tinytext":
			dataType = "String";
			break;
		case "blob":
			dataType = "byte[]";
			break;
		case "char":
			dataType = "String";
			break;
		case "date":
			dataType = "Date";
			break;
		case "timestamp":
			dataType = "Date";
			break;

		case "longblob":
			dataType = "byte[]";
			break;
		case "mediumblob":
			dataType = "byte[]";
			break;
		case "tinyblob":
			dataType = "byte[]";
			break;
		case "bit":
			dataType = "Boolean";
			break;
		default:
			break;
		}
		return dataType;
	}

	private String getDataType(String data_type) {
		String dataType = "";
		switch (data_type) {
		case "datetime":
			dataType = "TIMESTAMP";
			break;
		case "bigint":
			dataType = "INTEGER";
			break;
		case "varchar":
			dataType = "VARCHAR";
			break;
		case "decimal":
			dataType = "NUMERIC";
			break;
		case "double":
			dataType = "DOUBLE";
			break;
		case "int":
			dataType = "INTEGER";
			break;
		case "tinyint":
			dataType = "TINYINT";
			break;
		case "smallint":
			dataType = "SMALLINT";
			break;
		case "mediumint":
			dataType = "BIGINT";
			break;
		case "float":
			dataType = "FLOAT";
			break;
		case "text":
			dataType = "LONGVARCHAR";
			break;
		case "longtext":
			dataType = "LONGVARCHAR";
			break;
		case "mediumtext":
			dataType = "LONGVARCHAR";
			break;
		case "tinytext":
			dataType = "VARCHAR";
			break;
		case "blob":
			dataType = "BLOB";
			break;
		case "char":
			dataType = "CHAR";
			break;
		case "date":
			dataType = "TIMESTAMP";
			break;
		case "timestamp":
			dataType = "TIMESTAMP";
			break;
		case "longblob":
			dataType = "BLOB";
			break;
		case "mediumblob":
			dataType = "BLOB";
			break;
		case "tinyblob":
			dataType = "BLOB";
			break;
		case "bit":
			dataType = "BOOLEAN";
			break;
		default:
			break;
		}
		return dataType;
	}

	private String convertJavaKey(String key, boolean capitalize) {
		String beanKey = toUnderscoreName(key);
		String[] strs = beanKey.toLowerCase().split("_");
		StringBuffer result = new StringBuffer();
		String preStr = "";
		for (int i = 0; i < strs.length; i++) {
			if (preStr.length() == 1) {
				result.append(strs[i]);
			} else {
				result.append(changeFirstCharacterCase(strs[i], true));
			}
			preStr = strs[i];
		}
		return changeFirstCharacterCase(result.toString(), capitalize);
	}

	private String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

	private String toUnderscoreName(String name) {
		if (name == null) {
			return null;
		}
		String filteredName = name;
		if (filteredName.indexOf("_") >= 0 && filteredName.equals(filteredName.toUpperCase())) {
			filteredName = filteredName.toLowerCase();
		}
		if (filteredName.indexOf("_") == -1 && filteredName.equals(filteredName.toUpperCase())) {
			filteredName = filteredName.toLowerCase();
		}

		StringBuffer result = new StringBuffer();
		if (filteredName != null && filteredName.length() > 0) {
			result.append(filteredName.substring(0, 1).toLowerCase());
			for (int i = 1; i < filteredName.length(); i++) {
				String preChart = filteredName.substring(i - 1, i);
				String c = filteredName.substring(i, i + 1);
				if (c.equals("_")) {
					result.append("_");
					continue;
				}
				if (preChart.equals("_")) {
					result.append(c.toLowerCase());
					continue;
				}
				if (c.matches("\\d")) {
					result.append(c);
				} else if (c.equals(c.toUpperCase())) {
					result.append("_");
					result.append(c.toLowerCase());
				} else {
					result.append(c);
				}
			}
		}
		return result.toString();
	}

}
