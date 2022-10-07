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
import java.util.PriorityQueue;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ChangeCsvColumnName {
	
	static public List<List<String>> readCSV(String part) {
    	String URL = "D:\\" + part + ".csv";
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
		
		//CSV 파일(csv컬럼명)
		String part = "vga";
		List<List<String>> csvlist = readCSV(part);
		List<String> column = csvlist.get(0);
		List<String> erdcolumn = readCSV("erdorder\\"+part).get(0);
		int size = csvlist.size();
		System.out.print(column.toString().replace("[", "`").replace("]", "`").replace(", ", "`, `"));
	}
}