package ssafy;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Comsultant {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
        if ( otherArgs.length != 2 ) {
            System.err.println("Usage: <in> <out>");
            System.exit(2);
        }
        FileSystem hdfs = FileSystem.get(conf);
        Path output = new Path(otherArgs[1]);
        if (hdfs.exists(output))
            hdfs.delete(output, true);
        Job job = new Job(conf,"comsultant");
        job.setJarByClass(Comsultant.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setNumReduceTasks(3);
        FileInputFormat.addInputPath(job,new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1 );
    }

    public static class Map extends Mapper<Object, Text, Text, Text> {
        //각 부품 아이디(,)|각 부품 가격(,)|용도|프로그램|가격날짜|출생년도(있으면 넣고 없거나 비회원인 경우 0)
        private Text data = new Text();
        private Text val = new Text();
        static List<String[]> list = null;

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] s = value.toString().split("\\|");
            String[] id = s[0].split(",");
            String[] price = s[1].split(",");
            String[] price_date = s[4].split(",");
            String[][] arr = new String[id.length][3];
            for (int i = 0; i < id.length; i++) {
                arr[i][0] = id[i];
                arr[i][1] = price[i];
                arr[i][2] = price_date[i];
            }
            list = new ArrayList<>();
            if (arr[arr.length - 1][0].charAt(0) == '8') {
                int total = 0;
                for (int i = 0; i < arr.length; i++) {
                    total += Integer.parseInt(arr[i][1]);
                }
                String res = s[0]+"," + "|" + s[2] + "|" + s[3] + "|" + s[5];
                String res2 = total + "|" + s[4] + "|1";
                data.set(res);
                val.set(res2);
                context.write(data, val);
            } else {
                int idx = -1;
                String a = "";
                String b = "";
                int total = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i][0].charAt(0) == '8') {
                        idx = i+1;
                        a += arr[i][0] + ",";
                        b += arr[i][2] + ",";
                        total += Integer.parseInt(arr[i][1]);
                        break;
                    } else {
                        a += arr[i][0] + ",";
                        b += arr[i][2] + ",";
                        total += Integer.parseInt(arr[i][1]);
                    }
                }
                set(arr, idx, new boolean[arr.length]);
                for (int i = 0; i < list.size(); i++) {
                    String res = a + list.get(i)[0] + "|" + s[2] + "|" + s[3] + "|" + s[5];
                    int sum = total + Integer.parseInt(list.get(i)[1]);
                    String res2 = sum + "|" + b + list.get(i)[2] + "|1";
                    data.set(res);
                    val.set(res2);
                    context.write(data, val);
                }
            }
        }

        static void set(String[][] arr, int idx, boolean[] sel) {
            if (idx == arr.length) {
                String str = "";
                String pd = "";
                int total = 0;
                for (int i = 0; i < sel.length; i++) {
                    if (sel[i]) {
                        str += arr[i][0] + ",";
                        total += Integer.parseInt(arr[i][1]);
                        pd += arr[i][2]+",";
                    }
                }
                String[] result = {str, total + "", pd};
                list.add(result);
                return;
            }
            sel[idx]=true;
            set(arr, idx+1, sel);
            sel[idx]=false;
            set(arr, idx+1, sel);
        }

    }

    public static class Reduce extends Reducer<Text,Text,Text,Text> {

        private Text result = new Text();

        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

            int sum = 0;
            String str = "";
            for ( Text val : values ) {
                String[] s = val.toString().split("\\|");
                sum += Integer.parseInt(s[2]);
                str = "*"+s[0] +"|"+ s[1] + "|" + sum;
            }
            result.set(str);
            context.write(key,result);

        }
    }
}


