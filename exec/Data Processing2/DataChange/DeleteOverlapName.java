package com.DataChange;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DeleteOverlapName {
	public static void main(String[] args) throws IOException {
		String[] partlist = new String[] {"HDD", "SSD"}; //1~9월까지
//		String[] partlist = new String[] { "CPU", "RAM", "SSD", "HDD", "MAINBOARD", "VGA", "COOLER" }; // 1~9월까지
		int startMonth = 1;
//		String[] partlist = new String[] {"CASE","POWER"}; //6~9월까지
//		int startMonth = 6;

		for (String part : partlist) {
			for (int month = startMonth; month <= 9; month++) {
				System.out.println(part + "  " +month+"월");
				FileOutputStream outStream = null;
				InputStream fis = null;
				try {
					String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\4차 가공\\"+part+"_2022_"+month+".xlsx";
					fis = new FileInputStream(URL);

					XSSFWorkbook workbook = new XSSFWorkbook(fis); // xlsx 파일 Open

					XSSFSheet sheet = workbook.getSheetAt(0);
					int R = sheet.getLastRowNum();
					int C = sheet.getRow(0).getLastCellNum();
					
					// 모든 데이터 반복
					for (int row = 1; row <= R; row++) {
						if(sheet.getRow(row) == null) continue;
						
						String id = sheet.getRow(row).getCell(1).toString();
						for(int i=row+1; i<=R; i++) {
							if(sheet.getRow(i) == null) continue;
							
							//중복된 상품을 찾으면
							if(sheet.getRow(i).getCell(1).toString().equals(id)) {
								System.out.println("중복된 상품(앞idx):" + row);
								System.out.println("중복된 상품(뒤idx):" + i);
								int cnt1 = 0;
								int cnt2 = 0;
								//첫번째 상품의  시세데이터 null개수 세기
								for(int j=1; j<C; j++) {
									if(sheet.getRow(row).getCell(j).toString() == "0.0" || sheet.getRow(row).getCell(j).toString() == "0") {
										cnt1++;
									}
								}
								//중복된 두번째 데이터의 시세데이터 null개수 세기
								for(int j=1; j<C; j++) {
									if(sheet.getRow(i).getCell(j).toString() == "0.0" || sheet.getRow(i).getCell(j).toString() == "0") {
										cnt2++;
									}
								}
								
								if(cnt1 > cnt2) sheet.removeRow(sheet.getRow(row));
								else sheet.removeRow(sheet.getRow(i));
							}
						}
					}

					// 출력파일
					String URL2 = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\5차 가공\\"+part+"_2022_"+month+".xlsx";
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
	}
}
