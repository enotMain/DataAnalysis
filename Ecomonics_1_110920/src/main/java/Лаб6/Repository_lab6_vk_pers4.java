package Лаб6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Repository_lab6_vk_pers4 {
    ArrayList<Double> kMeansList = new ArrayList<>();
    ArrayList<Double> friendsList = new ArrayList<>();
    ArrayList<Double> followersList = new ArrayList<>();
    ArrayList<Double> photosList = new ArrayList<>();
    ArrayList<Double> videosList = new ArrayList<>();

    String repPath = "src\\main\\java\\Лаб6\\vk_pers4.csv";

    public void readRepFile() throws IOException {
        List<String> list = Files.readAllLines(Paths.get(repPath), StandardCharsets.UTF_8);
        int i = 0;
        for (String line : list) {
            if (i == 0) {
                i++;
                continue;
            }
            String[] words = line.split(";");
            kMeansList.add(Double.parseDouble(words[0]));
            friendsList.add(Double.parseDouble(words[1]));
            followersList.add(Double.parseDouble(words[2]));
            photosList.add(Double.parseDouble(words[3]));
            videosList.add(Double.parseDouble(words[4]));
        }
    }
}
