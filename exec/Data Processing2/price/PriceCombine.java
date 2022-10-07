package com.price;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PriceCombine {
	public static void main(String[] args) throws IOException {

		String[] partlist = new String[] {"CPU", "RAM", "SSD", "HDD", "MAINBOARD", "VGA", "COOLER"}; //1~9월까지
		int startMonth = 1;
//		String[] partlist = new String[] {"CASE","POWER"}; //6~9월까지
//		int startMonth = 6;

		for(String part : partlist) {
			//기존배열
			String[][] arr = new String[10000][1000];
			List<List<String>> csvlist = readCSV(part, startMonth);
			int row = csvlist.size();
			int col = csvlist.get(0).size();
			for(int i=0; i<row; i++) {
				arr[i] = csvlist.get(i).toArray(arr[i]);
			}
			
			for(int month=startMonth+1; month<=9; month++) {
				//새배열 삽입 (컬럼만)
				List<List<String>> next = readCSV(part, month);
				int nrow = next.size();
				int ncol = next.get(0).size();
				String[][] brr = new String[nrow][ncol];
				for(int i=0; i<nrow; i++) {
					for(int j=0; j<ncol; j++) {
						brr[i][j] = next.get(i).get(j);
					}
				}
				for(int nc=3; nc<ncol; nc++) {
					arr[0][col+nc-3] = brr[0][nc];
				}
				
				//새로운 데이터 하나하나씩
				label: for(int nr=1; nr<nrow; nr++) {
					String str = brr[nr][1];
					//기존 데이터와 비교해서
					for(int r=1; r<row; r++) {
						//같은 id가 있으면
						if(arr[r][1].equals(str)) {
							//기존 데이터 옆에 추가 
							for(int nc=3; nc<ncol; nc++) {
								arr[r][col+nc-3] = brr[nr][nc];
							}
							continue label;
						}
					}
					//기존 데이터에서 찾을 수 없으면 새 데이터를 기존 데이터 밑에 추가하고 row 갱신
					for(int c=0; c<3; c++) {
						arr[row][c] = brr[nr][c];
					}
					for(int c=3; c<col; c++) {
						arr[row][c] = "0";
					}
					for(int nc=3; nc<ncol; nc++) {
						arr[row][col+nc-3] = brr[nr][nc];
					}
					row++;
				}
				col += ncol-3;
			}
			
			//파일 저장
			for(int r=0; r<row; r++) {
				for(int c=0; c<col; c++) {
					if(arr[r][c] == null || arr[r][c].equals("0")) arr[r][c] = "";
				}
			}
			saveCSV(arr, row, col, part);
			System.out.println(part + " 종료");
		}
		System.out.println("종료");
	}
	
	static public void saveCSV(String[][] arr, int row, int col, String part) throws IOException {
		String url = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\데이터\\시세데이터\\최종csv파일\\시세병합작업\\결과\\" + part + "_price.csv";
		BufferedWriter bw = null;
		try {
		    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(url), "UTF-8"));
		    for(int i=0; i<row; i++) {
		    	for(int j=1; j<col; j++) {
		    		bw.write(arr[i][j]);
		    		if(j != col-1) bw.write(",");
		    	}
		    	bw.write("\n");
		    }
		} catch (IOException e) {
		    throw e;
		} finally {
		    bw.close();
		}

	}
	
	static public List<List<String>> readCSV(String part, int month) {
    	String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\데이터\\시세데이터\\최종csv파일\\시세병합작업\\" + part +"_2022_"+ month +".csv";
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
}
