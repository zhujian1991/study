/**
 * projectName: glz-stock
 * fileName: EasyPoiUtil.java
 * packageName: com.glz.cloud.utils
 * date: 2019-11-25 11:31
 * copyright(c) 2017-2020 xxx公司
 */
package cn.zhu.test.utils;

import cn.afterturn.easypoi.csv.CsvExportUtil;
import cn.afterturn.easypoi.csv.entity.CsvExportParams;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @version: V1.0
 * @author: fendo
 * @className: EasyPoiUtil
 * @packageName: com.glz.cloud.utils
 * @description:
 * @data: 2019-11-25 11:31
 **/

public class EasyPoiUtil {
    /**
     *@title: 获取地址
     *@description:  获取相对项目的固定地址文件
     *@author: 721315 zhuyong
     *@date: 2019/11/26 8:39
     *@param: [filePath: 文件地址]
     *@return: java.lang.String
     *@throws:
     */
    public static String getWebRootPath(String filePath) {
        try {
            String path = PoiPublicUtil.class.getClassLoader().getResource("").toURI().getPath();
            path = path.replace("WEB-INF/classes/", "");
            path = path.replace("file:/", "");
            return path + filePath;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 功能描述：复杂导出Excel，包括文件名以及表名。创建表头
     *
     * @author 李家威
     * @date 2018/7/23 13:07
     * @param list 导出的实体类
     * @param title 表头名称
     * @param sheetName sheet表名
     * @param pojoClass 映射的实体类
     * @param isCreateHeader 是否创建表头
     * @param fileName
     * @param response
     * @return
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    /**
     *@title: exportFileExcel
     *@description:  导出Excel，包括文件名以及表名。创建表头,导出表放到文件里
     *@author: 721315 zhuyong
     *@date: 2019/12/5 15:12
     * @param list 导出的实体类
     * @param title 表头名称
     * @param sheetName sheet表名
     * @param pojoClass 映射的实体类
     * @param isCreateHeader 是否创建表头
     * @param outputStream 输出流
     *@return: void
     *@throws:
     */
    public static void exportFileExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader,OutputStream outputStream) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            try {
                workbook.write(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *@title: 导出csv文件
     *@description:  导出csv，包括文件名以及表名。创建表头,导出表放到文件里
     *@author: 721315 zhuyong
     *@date: 2019/12/5 15:12
     * @param list 导出的实体类
     * @param pojoClass 映射的实体类
     * @param isCreateHeader 是否创建表头
     * @param outputStream 输出流
     *@return: void
     *@throws:
     */
    public static void exportFileCsv(List<?> list,Class<?> pojoClass,boolean isCreateHeader,OutputStream outputStream) {
        CsvExportParams params = new CsvExportParams();
        params.setTextMark("\t");
        params.setEncoding("GBK");
        params.setCreateHeadRows(isCreateHeader);
        CsvExportUtil.exportCsv(params, pojoClass, list, outputStream);
    }
    /**
     * 功能描述：复杂导出Excel，包括文件名以及表名,不创建表头
     *
     * @author 李家威
     * @date 2018/7/23 13:07
     * @param list 导出的实体类
     * @param title 表头名称
     * @param sheetName sheet表名
     * @param pojoClass 映射的实体类
     * @param fileName
     * @param response
     * @return
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    /**
     * 功能描述：Map 集合导出
     *
     * @author 李家威
     * @date 2018/7/23 16:14
     * @param list 实体集合
     * @param fileName 导出的文件名称
     * @param response
     * @return
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        defaultExport(list, fileName, response);
    }

    /**
     * 功能描述：默认导出方法
     *
     * @author 李家威
     * @date 2018/7/23 15:33
     * @param list 导出的实体集合
     * @param fileName 导出的文件名
     * @param pojoClass pojo实体
     * @param exportParams ExportParams封装实体
     * @param response
     * @return
     */
    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    /**
     * 功能描述：Excel导出
     *
     * @author 李家威
     * @date 2018/7/23 15:35
     * @param fileName 文件名称
     * @param response
     * @param workbook Excel对象
     * @return
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new  RuntimeException(e);
        }
    }

    /**
     * 功能描述：默认导出方法
     *
     * @author 李家威
     * @date 2018/7/23 15:33
     * @param list 导出的实体集合
     * @param fileName 导出的文件名
     * @param response
     * @return
     */
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) ;
        downLoadExcel(fileName, response, workbook);
    }


    /**
     * 功能描述：根据文件路径来导入Excel
     *
     * @author 李家威
     * @date 2018/7/23 14:17
     * @param filePath 文件路径
     * @param titleRows 表标题的行数
     * @param headerRows 表头行数
     * @param pojoClass Excel实体类
     * @return
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<?> pojoClass) {
        //判断文件是否存在
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    /**
     * 功能描述：根据文件路径来导入Excel 多线程解析
     *
     * @author 李家威
     * @date 2018/7/23 14:17
     * @param filePath 文件路径
     * @param titleRows 表标题的行数
     * @param headerRows 表头行数
     * @param pojoClass Excel实体类
     * @return
     */
    public static <T> List<T> importCacheExcel(String filePath, Integer titleRows, Integer headerRows, Class<?> pojoClass,String[] headStrings) {
        //判断文件是否存在
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setConcurrentTask(true);
        params.setHeadRows(headerRows);
        params.setImportFields(headStrings);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    /**
     * 功能描述：根据接收的Excel文件来导入Excel,并封装成实体类 多线程解析
     *
     * @author 李家威
     * @date 2018/7/23 14:17
     * @param file 上传的文件
     * @param titleRows 表标题的行数
     * @param headerRows 表头行数
     * @param pojoClass Excel实体类
     * @return
     */
    public static <T> List<T> importCacheExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<?> pojoClass, String[] headStrings) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setConcurrentTask(true);
        params.setImportFields(headStrings);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }
        return list;
    }

    /**
     * 功能描述：根据接收的Excel文件来导入Excel,并封装成实体类
     *
     * @author 李家威
     * @date 2018/7/23 14:17
     * @param file 上传的文件
     * @param titleRows 表标题的行数
     * @param headerRows 表头行数
     * @param pojoClass Excel实体类
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<?> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }
        return list;
    }

    /**
     *@title:  file 校验
     *@description:  校验是否是xls或者是xlsx
     *@author: 721315 zhuyong
     *@date: 2019/11/26 11:55
     *@param: [file:传入文件]
     *@return: java.lang.Boolean
     *@throws:
     */
    public static Boolean checkInportIFExcel(MultipartFile file) {
        //首先判断是不是空的文件
        if (file.isEmpty()) {
            return false;
        }
        //对文文件的全名进行截取然后在后缀名进行删选。
        //获得文件后缀名
        String a = file.getOriginalFilename();
        if (StringUtils.isBlank(a)){
            return false;
        }
        //我这边需要的xlsx文件所以说我这边直接判断就是了
        if (a.endsWith(".xlsx")) {
            return true;
        }else if (a.endsWith(".xls")) {
            return true;
        }else {
            return false;
        }
    }

    /**
     *@title: 得到inputStream
     *@description:  对异常进行处理
     *@author: 721315 zhuyong
     *@date: 2019/11/26 13:29
     *@param: [file]
     *@return: java.io.InputStream
     *@throws:
     */
    public static InputStream getInputStream(MultipartFile file){
        InputStream inputStream =null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream ;
    }

    /**
     *@title: selectThreadNum
     *@description:  选择每一个线程执行的数据条数
     *@author: 721315 zhuyong
     *@date: 2019/11/27 19:39
     *@param: [integer: 数据总条数]
     *@return: java.lang.Integer
     *@throws:
     */
    public static Integer selectThreadNum(Integer integer){
        if (integer!=null && integer==0){
            return 1;
        }
        if (integer<=15){
            return 2;
        }
        if (integer <= 30000) {
            return integer/14;
        }else {
            return 2000;
        }
    }

    /**
     *@title: getMultipartFileName
     *@description:
     *@author: 721315 zhuyong
     *@date: 2019/12/3 13:50
     *@param: [name]
     *@return: java.lang.String
     *@throws:
     */
    public static String getMultipartFileName(String name){
        if(StringUtils.isNotBlank(name)){
            int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf>0) {
                return name.substring(0, lastIndexOf);
            }else {return "无";}
        }
        return name;
    }


}