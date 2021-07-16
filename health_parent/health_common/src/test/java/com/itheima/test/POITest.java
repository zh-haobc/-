package com.itheima.test;

import com.alibaba.dubbo.common.serialize.support.fst.FstObjectOutput;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class POITest {
    // 使用poi读取Excel文件中的数据
    @Test
    public void test01() throws Exception{
        // 加载指定文件，创建一个Excel对象
        XSSFWorkbook excel =  new XSSFWorkbook(new FileInputStream(new File("D:\\poi.xlsx")));
        //读取Excel文件中第一个sheet标签页
        XSSFSheet sheetAt = excel.getSheetAt(0);
        //遍历标签页，获得每一行数据
        for (Row row : sheetAt) {
            //遍历行，获得每一行的数据
            for (Cell cell : row) {
                System.out.println(cell.getRichStringCellValue());
            }
        }
        //关闭资源
        excel.close();
    }
    @Test
    public void test02() throws Exception{
        // 加载指定文件，创建一个Excel对象
        XSSFWorkbook excel =  new XSSFWorkbook(new FileInputStream(new File("D:\\poi.xlsx")));
        //读取Excel文件中第一个sheet标签页
        XSSFSheet sheetAt = excel.getSheetAt(0);
        int lastRowNum = sheetAt.getLastRowNum();
        for (int i=0;i<lastRowNum;i++){
            XSSFRow row = sheetAt.getRow(i);
            for (int j= 0 ;j<lastRowNum;j++){
                XSSFCell cell = row.getCell(j);
                System.out.println(cell.getStringCellValue());
            }


        }
        //关闭资源
        excel.close();
    }
    @Test
    //使用poi向Excel文件写入数据，并且通过输出流将创建的文件保存到本地磁盘
    public void test03() throws  Exception{
        //在内存中创建一个excel文件
        XSSFWorkbook excel = new XSSFWorkbook();
         //创建一个工作对象、
        XSSFSheet sheet = excel.createSheet("传智播客");
        //在工作表中创建行对象
        XSSFRow title = sheet.createRow(0);
        //在行中创建单元格对象
        title.createCell(0).setCellValue("姓名");
        title.createCell(1).setCellValue("地址");
        title.createCell(2).setCellValue("年龄");

        XSSFRow dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("张三");
        dataRow.createCell(1).setCellValue("北京");
        dataRow.createCell(2).setCellValue("20");

        //创建一个输出流，通过输出流将内存中的Excel文件写到磁盘
        FileOutputStream out = new FileOutputStream(new File("D:\\hello.xlsx"));
        excel.write(out);
        out.flush();
        excel.close();


    }
}
