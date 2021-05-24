package MapReplace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class MapReplace {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));

            while (br.ready()) {
                String[] keyValue = br.readLine().split(",");
                map.put(keyValue[0], keyValue[1]);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error: Cannot read map file \"" + args[0] + "\". " + e.getMessage());
            System.exit(-1);
        }

        try {
            String fileName = args[0] + "-" + args[1];
            File outFile = new File(fileName);

            outFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
            BufferedReader br = new BufferedReader(new FileReader(args[1]));

            while (br.ready()) {
                String line = br.readLine();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    line = line.replaceAll(key, value);
                }
                bw.write(line);
            }

            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Error: Cannot read file \"" + args[1] + "\". " + e.getMessage());
            System.exit(-1);
        }
    }
}