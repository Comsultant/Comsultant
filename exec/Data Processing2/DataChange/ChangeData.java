package com.DataChange;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ChangeData {
	public static void main(String[] args) throws IOException {
		String[] partlist = new String[] {"CPU", "RAM", "SSD", "HDD", "MAINBOARD", "VGA", "COOLER"}; //1~9월까지
//		String[] partlist = new String[] {"CASE","POWER"}; //6~9월까지
		int datarownum = 0;
		int datatotnum = 0;
		for(String part : partlist) {
			for(int month=1; month<=9; month++) {
				FileOutputStream outStream = null;
				InputStream fis = null;
				try {
					String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\1차 가공\\"+part+"_2022_"+month+".xlsx";
					fis = new FileInputStream(URL);
		
					XSSFWorkbook workbook = new XSSFWorkbook(fis); // xlsx 파일 Open
		
					XSSFSheet sheet = workbook.getSheetAt(0);
					int R = sheet.getLastRowNum();
					int C = sheet.getRow(49).getLastCellNum();
					System.out.println("row 수 : " + R); //데이터 row 수 찾기. 인덱스 0부터. [0~48은 쓰레기값. 49는 컬럼명. 50부터 데이터]
					System.out.println("column 수 : " + C); //데이터 column 수 찾기. 인덱스 0부터. [index, id, name, 날짜들]
					datarownum += R - 49;
					datatotnum += (R-49)*(C-3);
					
					//모든 데이터 반복
					String tempname = null;
					for(int row=50; row<=R; row++) {
						String name = null;
						if(sheet.getRow(row).getCell(2) == null) name = tempname;
						else {
							name = sheet.getRow(row).getCell(2).toString();
							tempname = name;
						}
						//상품명 뒤에 붙일 type. (정품) (중고) ...etc
						StringBuilder sb = new StringBuilder();
						sb.append(name);
						System.out.println("상품명 : "+ name);
										
						//모든 컬럼 반복
						int col = 2;
						boolean flag = true;
						while(col++ < C-1) {
							//값이 없으면 패스
							if(sheet.getRow(row).getCell(col) == null) {
								sheet.getRow(row).createCell(col).setCellValue("0");
								continue;
							}
							if(sheet.getRow(row).getCell(col).toString().equals("0.0")
									|| sheet.getRow(row).getCell(col).toString().equals("0")) continue;
							
							//처음만 상품명 뒤에 붙일 type을 가공
							//타입이 없는 가격만 표시된 상품이면
							if(flag && sheet.getRow(row).getCell(col).toString().split(" - ").length == 1) flag = false;
							//타입이 있는 상품이면
							if(flag) {
								sb.append(" (");
								sb.append(sheet.getRow(row).getCell(col).toString().split(" - ")[0]);
								sb.append(")");
								flag = false;
							}
							
							//값 바꾸기. 8gb, 16gb같은 type은 " - "가 여러 개이므로 고려해서 split의 마지막을 값으로 넣음 
							int size = sheet.getRow(row).getCell(col).toString().split(" - ").length;
							String val = sheet.getRow(row).getCell(col).toString().split(" - ")[size-1];
							if(val.equals("가격비교예정") || val.equals("일시품절")) val = "0";
							sheet.getRow(row).getCell(col).setCellValue(val);
						}
						//상품명 뒤에 type 붙이기
						System.out.println("변환 후 : "+ sb.toString());
						if(sheet.getRow(row).getCell(2) == null) sheet.getRow(row).createCell(2);
						sheet.getRow(row).getCell(2).setCellValue(sb.toString());
					}
		
					//필요없는 0~48 row값은 버리기
					sheet.copyRows(49, R, 0, new CellCopyPolicy());
					for(int i=R-49+1; i<=R; i++) {
						sheet.removeRow(sheet.getRow(i));
					}
					//출력파일
					String URL2 = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\2차 가공\\"+part+"_2022_"+month+".xlsx";
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
		System.out.println("총 데이터  수: " + datatotnum);
	}
}