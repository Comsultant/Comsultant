package com.JPA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CsvColumnToErdColumnOrder {
	
	static public List<List<String>> readCSV(String part) {
    	String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\데이터\\부품데이터\\detail_" + part + ".csv";
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
        return csvList;
    }
	
	public static void main(String[] args) throws IOException {
		
		//XLSX 파일(erd컬럼명)
		FileOutputStream outStream = null;
		InputStream fis = null;
		String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\COMSULTANT_ERD.xlsx";
		fis = new FileInputStream(URL);
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // xlsx 파일 Open
		XSSFSheet sheet = workbook.getSheetAt(0);
		int[][] parts = {{30, 68}, {81, 144}, {148, 181}, {185, 260}, {264, 374}, {378, 460}, {464, 523}, {527, 559}, {563, 635}};
		int num = 3; //cpu(0), vga(1), ram(2), power(3), mb(4), cooler(5), case(6), hdd(7), ssd(8)
		int start = parts[num][0] - 1;
		int end = parts[num][1] - 1;
		
		//CSV 파일(csv컬럼명)
		String part = "power";
		List<String> csvlist = readCSV(part).get(0);
		int size = csvlist.size();
		if(size != end - start + 1) {
			System.out.println("ERROR: 컬럼명 개수가 다른뎁쇼?! 확인하세요!!");
		}
		
		List<String> result = new ArrayList<String>();
		System.out.println("N: erd한글명, csv한글명");
		for(int i = start; i<=end; i++) {
			int csvi = i - start;
			String csvname = csvlist.get(csvi);
			String explain = sheet.getRow(i).getCell(1).toString(); //주석(한글명)
			String name = sheet.getRow(i).getCell(2).toString(); //컬럼명
			String type = sheet.getRow(i).getCell(4).toString(); //데이터타입(mysql)
			String allow_null = sheet.getRow(i).getCell(4).toString(); //null값 허용 옵션
			String diff = "";
			if(!explain.equals(csvname)) diff = "  !!!!!!!!!!  ";
			System.out.println(csvi + ": " + explain + ", " + csvname + diff);
			result.add(name);
		}
		
		if(size != end - start + 1) {
			System.out.println("ERROR: 컬럼명 개수가 다른뎁쇼?! 확인하세요!!");
			System.out.println("erd size: " + (end - start + 1));
			System.out.println("csv size: " + size);
			System.out.println("-----------------------------------------------------");
			
		}
		else {
			System.out.println(result.toString().replace(" ", ""));
		}
	}
}