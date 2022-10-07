package com.mongoDB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hpsf.Array;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertPrice {
	
	public static void main(String[] args) {
		String[] partlist = new String[] {"CPU", "RAM", "SSD", "HDD", "MAINBOARD", "VGA", "COOLER", "CASE", "POWER"};
		
		for(String part: partlist) {
			//몽고DB 설정
	        String ip = "localhost";
	        int port = 27017;
	        String db = "comsultant";
	        String table = part.toLowerCase();
	        MongoClient mongoClient = new MongoClient(ip, port);
	        MongoDatabase mdb = mongoClient.getDatabase(db);
	        if(mdb.getCollection(table) == null) mdb.createCollection(table);
	        MongoCollection<Document> mcollection = mdb.getCollection(table);
	        
	        
	        //csv 데이터 파일 읽기
	        List<List<String>> list = readCSV(part);
	        
	        //Date 형식 바꾸기. 6/7/2022 10:11 -> 20220607
	        List<Integer> datelist = new ArrayList<Integer>();
	        datelist.add(0);
	        datelist.add(0);
	        for(int i=2; i<list.get(0).size(); i++) {
	        	String[] strs = list.get(0).get(i).split("/");
	        	if(strs[0].length() < 2) strs[0] = "0".concat(strs[0]); // month: 6 -> 06
	        	if(strs[1].length() < 2) strs[1] = "0".concat(strs[1]); // date: 7 -> 07
	        	String str = "2022".concat(strs[0]).concat(strs[1]);
	        	datelist.add(Integer.parseInt(str));
	        }
	        for(int i=1; i<list.size(); i++) {
	        	List<String> item = list.get(i);
	        	Document doc = new Document("_id", item.get(0)); //ID 필드 추가
	        	doc.append("name", item.get(1)); //NAME 필드 추가
	        	
	        	List<List<Integer>> dateItem = new ArrayList<List<Integer>>();
	        	for(int j=2; j<list.get(i).size(); j++) {
	        		List<Integer> datePrice = new ArrayList<Integer>();
	        		datePrice.add(datelist.get(j)); //날짜
	        		if(item.get(j).equals("")) continue;
	        		if(item.get(j).equals("가격비교중지")) continue;
	        		if(item.get(j).equals("가격비교 예정")) continue; //가격 정보가 없는 날이면 패스
	        		datePrice.add(Integer.parseInt(item.get(j).replace(".0", ""))); // 50000.0 -> 50000으로 바꾼 가격
	        		dateItem.add(datePrice);
	        	}
	        	doc.append("date", dateItem); //DATE 필드 추가 (이중배열)
	        	try {
	        		mcollection.insertOne(doc); //아이템 하나 추가
	        		
	            } catch (Exception e) {
					System.out.println(part + " 삽입중복 id : " + item.get(0) + " 이 무시되었습니다.");
				}
	        }
			
	        System.out.println(part + " 완료");
		}

        System.out.println("프로그램 종료");
	}
	
	static public List<List<String>> readCSV(String part) {
		String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\데이터\\시세데이터\\최종csv파일\\시세병합작업\\결과\\" + part + "_price.csv";
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
