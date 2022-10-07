package com.DataChange;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DeleteNoneId {
	public static void main(String[] args) throws IOException {
		String[] partlist = new String[] {"HDD", "SSD"}; //1~9월까지
//		String[] partlist = new String[] {"CPU", "RAM", "SSD", "HDD", "MAINBOARD", "VGA", "COOLER"}; //1~9월까지
		int startMonth = 1;
//		String[] partlist = new String[] {"CASE","POWER"}; //6~9월까지
//		int startMonth = 6;
		
		int datarownum = 0;
		for(String part : partlist) {
			for(int month=startMonth; month<=9; month++) {
				FileOutputStream outStream = null;
				InputStream fis = null;
				try {
					String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\3차 가공\\"+part+"_2022_"+month+".xlsx";
					fis = new FileInputStream(URL);
		
					XSSFWorkbook workbook = new XSSFWorkbook(fis); // xlsx 파일 Open
		
					XSSFSheet sheet = workbook.getSheetAt(0);
					int R = sheet.getLastRowNum();
					int C = sheet.getRow(0).getLastCellNum();
					System.out.println("row 수 : " + R); //데이터 row 수 찾기. 인덱스 0부터. [0~48은 쓰레기값. 49는 컬럼명. 50부터 데이터]
					System.out.println("column 수 : " + C); //데이터 column 수 찾기. 인덱스 0부터. [index, id, name, 날짜들]
					datarownum += R;
					
					//모든 데이터 반복
					for(int row=1; row<=R; row++) {
						String name = sheet.getRow(row).getCell(2).toString();
						System.out.println("상품명 : "+ name);
						
						//id가 없으면 삭제
						if(sheet.getRow(row).getCell(1) == null || sheet.getRow(row).getCell(1).equals("")) sheet.removeRow(sheet.getRow(row));
						System.out.println("삭제 상품명 :" + name);
					}
		
					//출력파일
					String URL2 = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\4차 가공\\"+part+"_2022_"+month+".xlsx";
					outStream = new FileOutputStream(URL2);
					workbook.write(outStream);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					fis.close();
					outStream.close();
					System.out.println("파일 변환 완료!");
				}
			}
		}
		System.out.println("총 데이터 row 수: " + datarownum);
		
	}
}
