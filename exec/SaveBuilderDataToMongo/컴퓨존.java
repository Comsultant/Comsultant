
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static com.mongodb.client.model.Filters.eq;

public class 컴퓨존 {
    static MongoCollection<Document> mcollection;
    static MongoCollection<Document> caseCollection;
    static MongoCollection<Document> coolerCollection;
    static MongoCollection<Document> cpuCollection;
    static MongoCollection<Document> hddCollection;
    static MongoCollection<Document> mainboardCollection;
    static MongoCollection<Document> powerCollection;
    static MongoCollection<Document> ramCollection;
    static MongoCollection<Document> ssdCollection;
    static MongoCollection<Document> vgaCollection;
    static List<String> datas = new ArrayList<>();
    static int tmp = 0;

    public static void main(String[] args) throws IOException {
        init();
        readFile();
        parseString();
    }

    public static void init() {
        String ip = "localhost";
        int port = 27017;

        String db = "comsultant";
        String table = "builder";


//        MongoClient mongoClient =  MongoClients.create("mongodb://ssafy:ssafy@localhost:27017/?authSource=admin");
        MongoClient mongoClient =  new MongoClient(ip, port);
        MongoDatabase mdb = mongoClient.getDatabase(db);
        mcollection = mdb.getCollection(table);
        caseCollection = mdb.getCollection("case");
        coolerCollection = mdb.getCollection("cooler");
        cpuCollection = mdb.getCollection("cpu");
        hddCollection = mdb.getCollection("hdd");
        mainboardCollection = mdb.getCollection("mainboard");
        powerCollection = mdb.getCollection("power");
        ramCollection = mdb.getCollection("ram");
        ssdCollection = mdb.getCollection("ssd");
        vgaCollection = mdb.getCollection("vga");


    }

    public static void readFile() throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("/home/hadoop/Project/comsultant_out.txt"));
        BufferedReader br = new BufferedReader(new FileReader("E:\\SSAFY_BIGDATA\\mongo\\compuzone.csv"));
        String str;
        while ((str = br.readLine()) != null) {
            datas.add(str);
        }
        br.close();
    }

    public static int[] getPrices(int prodCategory, String prodidx) {
        Document pdoc;
        prodCategory+=1;
        if(prodCategory == 1) {
            pdoc = cpuCollection.find(eq("_id", prodidx)).first();
        } else if (prodCategory == 2) {
            pdoc = ramCollection.find(eq("_id", prodidx)).first();
        } else if (prodCategory == 3) {
            pdoc = hddCollection.find(eq("_id", prodidx)).first();
        } else if (prodCategory == 4) {
            pdoc = ssdCollection.find(eq("_id", prodidx)).first();
        } else if (prodCategory == 5) {
            pdoc = powerCollection.find(eq("_id", prodidx)).first();
        } else if (prodCategory == 6) {
            pdoc = coolerCollection.find(eq("_id", prodidx)).first();
        } else if (prodCategory == 7) {
            pdoc = caseCollection.find(eq("_id", prodidx)).first();
        } else if (prodCategory == 8) {
            pdoc = mainboardCollection.find(eq("_id", prodidx)).first();
        } else {
            pdoc = vgaCollection.find(eq("_id", prodidx)).first();
        }
        if(pdoc == null ){
            return new int[]{0, 0};
        } else {
//            System.out.println(pdoc.get("date"));
            ArrayList<ArrayList<Integer>> list = (ArrayList<ArrayList<Integer>>) pdoc.get("date");
            int last_idx = list.size()-1;
            return new int[]{list.get(last_idx).get(0), list.get(last_idx).get(1)};
        }

    }

    public static void parseString() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\SSAFY_BIGDATA\\mongo\\compuzone.txt"));


        for(String data : datas) {
            PriorityQueue<Integer> pq[] = new PriorityQueue[9];
            for(int i=0; i<9; i++) {
                pq[i] = new PriorityQueue<>();
            }

            String splitData[] = data.split(",");

            // 0 번은 아이디
            // 1 번은 용도
            // 2 번은 프로그램
            // 3 번은 CPU
            // 4 번은 그래픽카드
            // 5 번은 메인보드
            // 6 번은 RAM
            // 7 번은 파워
            // 8 번은 HDD
            // 9 번은 SSD
            // 10 번은 케이스
            // 11 번 쿨러
            // 12번도 쿨러
            for (int i=0; i<13; i++) {
                String d = splitData[i].trim();
                if(d.length() == 0) {
                    continue;
                }

                if(d.indexOf(";") != -1) {
                    String pidx[] = d.split(";");
                    for(String p : pidx) {
                        if(p.trim().equals("0") || p.trim().equals("-")) {
                            continue;
                        }
                        if(i == 3) {
                            pq[0].add(Integer.parseInt(p));
                        } else if ( i == 4) {
                            pq[8].add(Integer.parseInt(p));
                        } else if ( i == 5) {
                            pq[7].add(Integer.parseInt(p));
                        } else if ( i == 6 ) {
                            pq[1].add(Integer.parseInt(p));
                        } else if ( i == 7 ) {
                            pq[4].add(Integer.parseInt(p));
                        } else if ( i == 8 ) {
                            pq[2].add(Integer.parseInt(p));
                        } else if ( i == 9 ) {
                            pq[3].add(Integer.parseInt(p));
                        } else if ( i == 10 ) {
                            pq[6].add(Integer.parseInt(p));
                        } else if ( i == 11 ) {
                            pq[5].add(Integer.parseInt(p));
                        } else if ( i == 12 ) {
                            pq[5].add(Integer.parseInt(p));
                        }
                    }
                } else {
                    if ( d.equals("0") ||  d.equals("-")) {
                        continue;
                    }
                    if(i == 3) {
                        pq[0].add(Integer.parseInt(d));
                    } else if ( i == 4) {
                        pq[8].add(Integer.parseInt(d));
                    } else if ( i == 5) {
                        pq[7].add(Integer.parseInt(d));
                    } else if ( i == 6 ) {
                        pq[1].add(Integer.parseInt(d));
                    } else if ( i == 7 ) {
                        pq[4].add(Integer.parseInt(d));
                    } else if ( i == 8 ) {
                        pq[2].add(Integer.parseInt(d));
                    } else if ( i == 9 ) {
                        pq[3].add(Integer.parseInt(d));
                    } else if ( i == 10 ) {
                        pq[6].add(Integer.parseInt(d));
                    } else if ( i == 11 ) {
                        pq[5].add(Integer.parseInt(d));
                    } else if ( i == 12) {
                        pq[5].add(Integer.parseInt(d));
                    }
                }



            }

            // 모아둔 데이터 String화

            int idx_order[] = new int[]{1,2,5,8,3,4,6,7,9};
            ArrayList<Integer> prices = new ArrayList<>();
            ArrayList<Integer> dates = new ArrayList<>();
            StringBuilder finalSb = new StringBuilder();
            for(int i : idx_order) {
                int category = i - 1;
                if(pq[category].size() == 0) {
                    continue;
                } else {
                    StringBuilder sb = new StringBuilder();
                    while(!pq[category].isEmpty()) {
                        int top = pq[category].poll();
                        int priceArr[] = getPrices(category, String.valueOf(top));
                        dates.add(priceArr[0]);
                        prices.add(priceArr[1]);
                        sb.append(i).append("_").append(top);

                        if(!pq[category].isEmpty() && top == pq[category].peek()) {
                            // 다음번에 똑같은게 나옴 ( 가격 구해줄 필요 X )
                            dates.remove(dates.size() - 1);
                            prices.remove(prices.size() - 1);
                            sb.append(";");
                        } else {
                            sb.append(",");
                        }
                    }
                    sb.setLength(sb.length()-1);
                    sb.append(",");
                    finalSb.append(sb.toString());
                }
            }

            finalSb.setLength(finalSb.length() - 1);
            finalSb.append("|");
            for(int i=0; i<prices.size(); i++) {
                finalSb.append(prices.get(i));
                finalSb.append(",");
            }
            finalSb.setLength(finalSb.length() - 1);

            finalSb.append("|").append(splitData[1]);
            finalSb.append("|").append(splitData[2]).append("|");

            for(int i=0; i<dates.size(); i++) {
                finalSb.append(dates.get(i));
                finalSb.append(",");
            }

            finalSb.setLength(finalSb.length() - 1);
            finalSb.append("|").append("0\n");
//            System.out.println(finalSb.toString());

            bw.write(finalSb.toString());

        }

        bw.flush();
        bw.close();
    }
}
