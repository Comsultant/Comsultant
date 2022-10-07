package com.DataChange;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DeleteNoneRow {
	public static void main(String[] args) throws IOException {
		String[] partlist = new String[] {"HDD", "SSD"}; //1~9월까지
//		String[] partlist = new String[] { "CPU", "RAM", "SSD", "HDD", "MAINBOARD", "VGA", "COOLER" }; // 1~9월까지
		int startMonth = 1;
//		String[] partlist = new String[] {"CASE","POWER"}; //6~9월까지
//		int startMonth = 6;

		for (String part : partlist) {
			for (int month = startMonth; month <= 9; month++) {
				FileOutputStream outStream = null;
				InputStream fis = null;
				try {
					String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\5차 가공\\"+part+"_2022_"+month+".xlsx";
					fis = new FileInputStream(URL);

					XSSFWorkbook workbook = new XSSFWorkbook(fis); // xlsx 파일 Open

					XSSFSheet sheet = workbook.getSheetAt(0);
					int R = sheet.getLastRowNum();
					int C = sheet.getRow(0).getLastCellNum();
					
					//getrow()가 null이면 copyrow가 제대로 작동하지 않아 null인 row는 createrow()를 함
					for (int row = 1; row <= R; row++) {
						if (sheet.getRow(row) == null)
							sheet.createRow(row);
					}
					
					// 모든 데이터 반복
					for (int row = 1; row <= R; row++) {
						// id가 없으면 삭제
						if (sheet.getRow(row).getCell(1) == null) {
							//빈셀 개수 세기
							int temp = row;							
							while (sheet.getRow(temp).getCell(1) == null) {
								temp++;
								if (temp == R) break;
							}
							System.out.println("빈셀 개수:    " + (temp - row));
							
							//복사하고 이어붙이기 (뒤 남는 row들은 삭제)
							sheet.copyRows(temp, R, row, new CellCopyPolicy());
							for (int i = R; i > R - (temp - row); i--) {
								if (sheet.getRow(i) != null)
									sheet.removeRow(sheet.getRow(i));
							}
							R = R - (temp - row);
						}
					}

					// 출력파일
					String URL2 = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\6차 가공\\"+part+"_2022_"+month+".xlsx";
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
