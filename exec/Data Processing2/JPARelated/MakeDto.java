package com.JPA;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MakeDto {
	
	  public static String upperCaseFirst(String val) {
		  StringBuilder sb = new StringBuilder();
		  String[] strs = val.split("_");
		  int size = strs.length;
		  sb.append(strs[0]);
		  for(int i=1; i<size; i++) {
			  char[] arr = strs[i].toCharArray();
			  arr[0] = Character.toUpperCase(arr[0]);
			  strs[i] = new String(arr);
			  sb.append(strs[i]);
		  }
	      return sb.toString();
	   }
	  
	public static void main(String[] args) throws IOException {
		FileOutputStream outStream = null;
		InputStream fis = null;
		String URL = "C:\\Users\\SSAFY\\Desktop\\싸피\\특화\\COMSULTANT_ERD.xlsx";
		fis = new FileInputStream(URL);

		XSSFWorkbook workbook = new XSSFWorkbook(fis); // xlsx 파일 Open

		XSSFSheet sheet = workbook.getSheetAt(0);
		int[][] parts = {{30, 68}, {81, 144}, {148, 181}, {185, 260}, {264, 371}, {375, 457}, {461, 520}, {524, 556}, {560, 632}};
		int num = 6; //cpu(0), vga(1), ram(2), power(3), mb(4), cooler(5), case(6), hdd(7), ssd(8)
		int start = parts[num][0] - 1;
		int end = parts[num][1] - 1;
		for(int i = start; i<=end; i++) {
			String explain = sheet.getRow(i).getCell(1).toString(); //주석(한글명)
			
			String name = sheet.getRow(i).getCell(2).toString(); //컬럼명
			String jpaname = name;
			if(name.contains("_"))
				jpaname = upperCaseFirst(name);
			
			String type = sheet.getRow(i).getCell(4).toString(); //데이터타입(mysql)
			String jpatype = type;
			if(type.contains("VARCHAR")) jpatype = "String";
			else if(type.equals("TINYINT(1)")) jpatype = "boolean";
			else if(type.equals("INT")) jpatype = "int";
			else if(type.equals("DOUBLE")) jpatype = "double";
			else if(type.equals("DATETIME")) jpatype = "LocalDateTime";
			
			//기본키
			if(i==start) {
				String PK = "\r\n" + 
						"    private long idx;\r\n";
				System.out.println(PK);
				continue;
			}
			//나머지컬럼
			String column = "\r\n" + 
					"    private "+ jpatype + " " + jpaname + "; //" + explain;
			System.out.println(column);
		}
	}
}

/*

    @Id
    @Column(name = "product_idx", nullable = false)
    private long idx;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "product_idx",  columnDefinition = "BIGINT(20) UNSIGNED")
    private Product product;

    @Column(name = "name", unique = true, nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "img_cnt", columnDefinition = "INT UNSIGNED")
    private String imgCnt;

    @Column(name = "corp", columnDefinition = "VARCHAR(255)")
    private String corp;

    @Column(name = "registed_at", columnDefinition = "VARCHAR(255)")
    private String registedAt;
 */