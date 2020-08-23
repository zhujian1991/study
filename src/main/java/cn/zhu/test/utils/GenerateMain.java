package cn.zhu.test.utils;


/**
 * description 根据数据库表生成domain 、mapper 、jpa 等
 * @className GenerateMain
 * @author 程序老仁
 * @date 2020/6/2
 */   

public class GenerateMain {

	public static void main(String[] args) throws Exception {

		System.out.println(System.getProperty("java.io.tmpdir"));
		// generateGalanz();
		// generateAfter();
		// generateBasic();
//		generateFinanace();
		generateGMMSFinanace();
		// generateOrder();
		// generateStock();
	}

	private static void generateGMMSFinanace() throws Exception {
		GeneratePojo pojo = new GeneratePojo();
		pojo.setDb_driver("com.mysql.cj.jdbc.Driver"); // 驱动
		pojo.setDb_url("jdbc:mysql://localhost:3306/mytest?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC"); // 链接地址
		pojo.setDb_user("root"); // 用户名
		pojo.setDb_pwd("123456"); // 密码
		pojo.setDbName("mytest"); //
		pojo.setPackageDao("cn.zhu.test.dao");
		pojo.setPackageEntity("cn.zhu.test.entity");
		pojo.setPackageRepository("cn.zhu.test.jpa");
		pojo.setOutputPath("/springBootTest/");
		GenerateUtils tableEntity = new GenerateUtils();
		tableEntity.generateEntity(pojo);
	}

	public static void generateOrder() throws Exception {
		GeneratePojo pojo = new GeneratePojo();
		pojo.setDb_driver("com.mysql.jdbc.Driver"); // 驱动
		pojo.setDb_url("jdbc:mysql://172.1.10.24:3306/glz_order?useUnicode=true&characterEncoding=utf-8"); // 链接地址
		pojo.setDb_user("root"); // 用户名
		pojo.setDb_pwd("123456"); // 密码
		pojo.setDbName("glz_order"); //
		pojo.setPackageDao("com.glz.cloud.dao.mybatis.order");
		pojo.setPackageEntity("com.glz.cloud.bean.entity.order");
		pojo.setPackageRepository("com.glz.cloud.dao.jpa.order");
		pojo.setOutputPath("/galanz/data/order/");
		GenerateUtils tableEntity = new GenerateUtils();
		tableEntity.generateEntity(pojo);
	}

	public static void generateAfter() throws Exception {
		GeneratePojo pojo = new GeneratePojo();
		pojo.setDb_driver("com.mysql.jdbc.Driver"); // 驱动
		pojo.setDb_url("jdbc:mysql://172.1.10.24:3306/afterSale?useUnicode=true&characterEncoding=utf-8"); // 链接地址
		pojo.setDb_user("root"); // 用户名
		pojo.setDb_pwd("123456"); // 密码
		pojo.setDbName("afterSale"); //
		pojo.setPackageDao("com.glz.cloud.dao.mybatis.after");
		pojo.setPackageEntity("com.glz.cloud.bean.entity.after");
		pojo.setPackageRepository("com.glz.cloud.dao.jpa.after");
		pojo.setOutputPath("/galanz/data/after/");
		GenerateUtils tableEntity = new GenerateUtils();
		tableEntity.generateEntity(pojo);
	}

	public static void generateStock() throws Exception {
		GeneratePojo pojo = new GeneratePojo();
		pojo.setDb_driver("com.mysql.jdbc.Driver"); // 驱动
		pojo.setDb_url("jdbc:mysql://172.1.10.24:3306/glz_stock?useUnicode=true&characterEncoding=utf-8"); // 链接地址
		pojo.setDb_user("root"); // 用户名
		pojo.setDb_pwd("123456"); // 密码
		pojo.setDbName("glz_stock"); //
		pojo.setPackageDao("com.glz.cloud.dao.mybatis.stock");
		pojo.setPackageEntity("com.glz.cloud.bean.entity.stock");
		pojo.setPackageRepository("com.glz.cloud.dao.jpa.stock");
		pojo.setOutputPath("/galanz/data/stock/");
		GenerateUtils tableEntity = new GenerateUtils();
		tableEntity.generateEntity(pojo);
	}

	public static void generateBasic() throws Exception {
		GeneratePojo pojo = new GeneratePojo();
		pojo.setDb_driver("com.mysql.jdbc.Driver"); // 驱动
		pojo.setDb_url("jdbc:mysql://172.1.10.24:3306/glz_basic?useUnicode=true&characterEncoding=utf-8"); // 链接地址
		pojo.setDb_user("root"); // 用户名
		pojo.setDb_pwd("123456"); // 密码
		pojo.setDbName("glz_basic"); //
		pojo.setPackageDao("com.glz.cloud.dao.mybatis.basic");
		pojo.setPackageEntity("com.glz.cloud.bean.entity.basic");
		pojo.setPackageRepository("com.glz.cloud.dao.jpa.basic");
		pojo.setOutputPath("/galanz/data/basic/");
		GenerateUtils tableEntity = new GenerateUtils();
		tableEntity.generateEntity(pojo);
	}

	public static void generateFinanace() throws Exception {
		GeneratePojo pojo = new GeneratePojo();
		pojo.setDb_driver("com.mysql.cj.jdbc.Driver"); // 驱动
		pojo.setDb_url("jdbc:mysql://172.1.10.24:3306/glz_finance?useUnicode=true&characterEncoding=utf-8"); // 链接地址
		pojo.setDb_user("root"); // 用户名
		pojo.setDb_pwd("123456"); // 密码
		pojo.setDbName("glz_finance"); //
		pojo.setPackageDao("com.glz.cloud.dao.mybatis.finance");
		pojo.setPackageEntity("com.glz.cloud.bean.entity.finance");
		pojo.setPackageRepository("com.glz.cloud.dao.jpa.finance");
		pojo.setOutputPath("/galanz/data/finance/");
		GenerateUtils tableEntity = new GenerateUtils();
		tableEntity.generateEntity(pojo);
	}

	public static void generateGalanz() throws Exception {
		GeneratePojo pojo = new GeneratePojo();
		pojo.setDb_driver("com.mysql.jdbc.Driver"); // 驱动
		pojo.setDb_url("jdbc:mysql://172.1.10.24:3306/galanz?useUnicode=true&characterEncoding=utf-8"); // 链接地址
		pojo.setDb_user("root"); // 用户名
		pojo.setDb_pwd("123456"); // 密码
		pojo.setDbName("galanz"); //
		pojo.setPackageDao("com.glz.cloud.dao.mybatis.galanz");
		pojo.setPackageEntity("com.glz.cloud.bean.entity.galanz");
		pojo.setPackageRepository("com.glz.cloud.dao.jpa.galanz");
		pojo.setOutputPath("/galanz/data/galanz/");
		GenerateUtils tableEntity = new GenerateUtils();
		tableEntity.generateEntity(pojo);
	}
}