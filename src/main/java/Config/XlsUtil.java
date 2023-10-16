package Config;

import entity.disastercode;
import entity.disasterinfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.python.antlr.ast.Str;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XlsUtil {



    /**
     * 获取解析后的文件内容
     *
     * @return
     */
    public static List<disasterinfo> readExcel(String filename) throws Exception {
        List<disasterinfo> list = new ArrayList<disasterinfo>();
        try {
            InputStream is=new FileInputStream(filename);
            System.out.println("werwerwer");
            // 获得Workbook工作薄对象
            Workbook workbook = new XSSFWorkbook(is);
            System.out.println("dfgsdgwer");
            int sheetSize = workbook.getNumberOfSheets();
            System.out.println(sheetSize);
            for (int i = 0; i < sheetSize; i++) {// 遍历sheet页
                // 获取第一个张表
                Sheet sheet = workbook.getSheetAt(0);
                System.out.println(sheet.getLastRowNum());
                // 获取每行中的字段
                for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                    Row row = sheet.getRow(j); // 获取行
                    disasterinfo dis = new disasterinfo();

                    dis.setLocation(row.getCell(0).getStringCellValue());
                    dis.setLongitude(row.getCell(1).getStringCellValue());
                    dis.setLatitude(row.getCell(2).getStringCellValue());
                    dis.setCategory(row.getCell(3).getStringCellValue());
                    dis.setSource(row.getCell(4).getStringCellValue());
                    dis.setGrade((int)row.getCell(5).getNumericCellValue());
                    dis.setMagnitude(row.getCell(6).getNumericCellValue());
                    dis.setCreated(Timestamp.valueOf(row.getCell(7).getStringCellValue()));
                    list.add(dis);
                }
            }
        } catch (Exception e) {
            throw new Exception("Excel导入失败！");
        }
        return list;
    }
    public static <T> List<T> readExcel2Bean(InputStream is, Class<T> tClass)
            throws IOException, IllegalAccessException, InstantiationException {
        List<List<String>> list = readExcel(is);
        //-----------------------遍历数据到实体集合开始-----------------------------------
        List<T> listBean = new ArrayList<T>();
        Field[] fields = tClass.getDeclaredFields();
        T uBean = null;
        for (int i = 1; i < list.size(); i++) {// i=1是因为第一行不要
            uBean = (T) tClass.newInstance();
            List<String> listStr = list.get(i);
            for (int j = 0; j < listStr.size(); j++) {
                if (j>=fields.length){
                    break;
                }
                Field field = fields[j+1];
                String datastring = listStr.get(j);
                field.setAccessible(true);
                if (datastring.length()>0&&datastring!=null) {
                    Class<?> type = field.getType();
                    // 只支持8中基本类型和String类型 如有其他类型 请自行添加
                    if (type==String.class){
                        field.set(uBean,datastring);
                    }else  if(type==Integer.class||type==int.class){
                        field.set(uBean,Integer.parseInt(datastring));
                    }else  if(type==Double.class||type==double.class){
                        field.set(uBean,Double.parseDouble(datastring));
                    } else  if(type==Float.class||type==float.class){
                        field.set(uBean,Float.parseFloat(datastring));
                    } else  if(type==Long.class||type==long.class){
                        field.set(uBean,Long.parseLong(datastring));
                    }else if (type==Boolean.class||type==boolean.class){
                        field.set(uBean,Boolean.parseBoolean(datastring));
                    }else if (type==Short.class||type==short.class){
                        field.set(uBean,Short.parseShort(datastring));
                    }else if (type==Byte.class||type==byte.class){
                        field.set(uBean,Byte.parseByte(datastring));
                    }else if (type==Character.class ||type==char.class){
                        field.set(uBean,datastring.charAt(0));
                    }
                    else if(type== Timestamp.class)
                        field.set(uBean,Timestamp.valueOf(datastring));
                }
            }
            listBean.add(uBean);
        }
        return listBean;
    }
    /**
     * Excel读取 操作,返回内容
     */
    private static List<List<String>> readExcel(InputStream is) throws IOException, IllegalAccessException, InstantiationException {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        /** 得到第一个sheet */
        Sheet sheet = wb.getSheetAt(0);
        /** 得到Excel的行数 */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数 */
        int totalCells = 0;
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 循环Excel的行 */
        for (int r = 0; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null)
                continue;
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    HSSFDataFormatter hSSFDataFormatter = new HSSFDataFormatter();
                    cellValue = hSSFDataFormatter.formatCellValue(cell);
                }
                rowLst.add(cellValue);
            }
            /** 保存第r行的第c列 */
            dataLst.add(rowLst);
        }
        return dataLst;
    }
    public static XSSFWorkbook getXSSFWorkbook(List<disasterinfo> list1,List<disastercode>list2){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet();

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        XSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();

        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式


        XSSFCell cell1 = row.createCell(0);
        cell1.setCellValue("编号");
        cell1.setCellStyle(style);

        XSSFCell cell2 = row.createCell(1);
        cell2.setCellValue("位置");
        cell2.setCellStyle(style);

        XSSFCell cell3 = row.createCell(2);
        cell3.setCellValue("经度");
        cell3.setCellStyle(style);

        XSSFCell cell4 = row.createCell(3);
        cell4.setCellValue("纬度");
        cell4.setCellStyle(style);

        XSSFCell cell5 = row.createCell(4);
        cell5.setCellValue("灾难类型");
        cell5.setCellStyle(style);

        XSSFCell cell6 = row.createCell(5);
        cell6.setCellValue("消息来源");
        cell6.setCellStyle(style);

        XSSFCell cell7 = row.createCell(6);
        cell7.setCellValue("灾难等级");
        cell7.setCellStyle(style);

        XSSFCell cell8 = row.createCell(7);
        cell8.setCellValue("震级");
        cell8.setCellStyle(style);

        XSSFCell cell9 = row.createCell(8);
        cell9.setCellValue("数据来源编码");
        cell9.setCellStyle(style);

        XSSFCell cell10 = row.createCell(9);
        cell10.setCellValue("灾情数据编码");
        cell10.setCellStyle(style);

        XSSFCell cell11 = row.createCell(10);
        cell11.setCellValue("基本震情数据编码");
        cell11.setCellStyle(style);

        XSSFCell cell12 = row.createCell(11);
        cell12.setCellValue("时间");
        cell12.setCellStyle(style);
        //创建内容
        for(int i=0;i<list1.size();i++){
            //第一行是表头，第二行开始插数据
            row = sheet.createRow(i + 1);
            //row.createCell(0).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            Cell cella = row.createCell(0);
            cella.setCellType(Cell.CELL_TYPE_NUMERIC);
            cella.setCellValue(list1.get(i).getDataid());
            row.createCell(1).setCellValue(list1.get(i).getLocation());
            row.createCell(2).setCellValue(list1.get(i).getLongitude());
            //插入的数据是数字
            row.createCell(3).setCellValue(list1.get(i).getLatitude());
            row.createCell(4).setCellValue(list1.get(i).getCategory());
            row.createCell(5).setCellValue(list1.get(i).getSource());
            Cell cellb = row.createCell(6);
            cellb.setCellType(Cell.CELL_TYPE_NUMERIC);
            cellb.setCellValue(list1.get(i).getGrade());
            Cell cellc = row.createCell(7);
            cellc.setCellType(Cell.CELL_TYPE_NUMERIC);
            cellc.setCellValue(list1.get(i).getMagnitude());
            row.createCell(8).setCellValue(list2.get(i).getSoucdata());
            row.createCell(9).setCellValue(list2.get(i).getInfodata());
            row.createCell(10).setCellValue(list2.get(i).getGeodata());
            row.createCell(11).setCellValue(list1.get(i).getCreated().toString());
        }
        return wb;
    }
    public static void export(String filename, List<disasterinfo>list1, List<disastercode>list2) {
        XSSFWorkbook wb = getXSSFWorkbook(list1,list2);
        try {
            File file = new File(filename);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();
            //this.setResponseHeader(response, fileName);
            OutputStream os =new FileOutputStream(file);
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 发送响应流方法
    /*public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

}
