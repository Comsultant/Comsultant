package com.DataChange;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FindItemId {
	public static void main(String[] args) throws IOException {
		String[] partlist = new String[] {"HDD", "SSD"}; //1~9월까지
//		String[] partlist = new String[] {"CPU", "RAM", "SSD", "HDD", "MAINBOARD", "VGA", "COOLER"}; //1~9월까지
		int startMonth = 1;
//		String[] partlist = new String[] {"CASE","POWER"}; //6~9월까지
//		int startMonth = 6;
		
		int datarownum = 0;
		for(String part : partlist) {
			List<List<String>> csvlist = readCSV(part);
			for(int month=startMonth; month<=9; month++) {
				FileOutputStream outStream = null;
				InputStream fis = null;
				try {
					String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\2차 가공\\"+part+"_2022_"+month+".xlsx";
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
						
						//csv 파일에서 id찾아서 넣기
						int size = csvlist.size();
						for(int i=1; i<size; i++) {
							//아이디가 같으면
							if(sheet.getRow(row).getCell(2).toString().equals(csvlist.get(i).get(1))) {
								String id = csvlist.get(i).get(0);
								if(sheet.getRow(row).getCell(1) == null) sheet.getRow(row).createCell(1);
								sheet.getRow(row).getCell(1).setCellValue(id);
							}
						}
					}
		
					//출력파일
					String URL2 = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\3차 가공\\"+part+"_2022_"+month+".xlsx";
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
	
    static public List<List<String>> readCSV(String part) {
    	String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\새 폴더\\시세데이터\\부품데이터(다나와크롤링)\\" + part + ".csv";
        List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File(URL);
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) { 
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(csvList);
        return csvList;
    }
}
