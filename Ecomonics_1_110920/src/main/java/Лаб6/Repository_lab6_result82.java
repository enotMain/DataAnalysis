package Лаб6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Repository_lab6_result82 {
    ArrayList<Double> idList = new ArrayList<>();
    ArrayList<Double> moneyList = new ArrayList<>();
    ArrayList<Double> avgSalaryList = new ArrayList<>();
    ArrayList<Double> salCatList = new ArrayList<>();
    ArrayList<Double> catExpList = new ArrayList<>();
    ArrayList<Double> lastJobDiffCatList = new ArrayList<>();
    ArrayList<Double> saveDateDiffCat = new ArrayList<>();
    ArrayList<Double> kMeansList = new ArrayList<>();

    String repPath = "src\\main\\java\\Лаб6\\result82.csv";

    public void readRepFile() throws IOException {
        List<String> list = Files.readAllLines(Paths.get(repPath), StandardCharsets.UTF_8);
        int i = 0;
        for (String line : list) {
            if (i == 0) {
                i++;
                continue;
            }
            String[] words = line.split(";");
            idList.add(Double.parseDouble(words[0]));
            moneyList.add(Double.parseDouble(words[1]));
            avgSalaryList.add(Double.parseDouble(words[2]));
            salCatList.add(Double.parseDouble(words[3]));
            catExpList.add(Double.parseDouble(words[4]));
            lastJobDiffCatList.add(Double.parseDouble(words[5]));
            saveDateDiffCat.add(Double.parseDouble(words[6]));
            kMeansList.add(Double.parseDouble(words[7]));
        }
    }
}
