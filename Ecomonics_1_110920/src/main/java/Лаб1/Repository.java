package Лаб1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Репозиторий для хранения данных об акциях
public class Repository {
    ArrayList<Double> openList = new ArrayList<>();
    ArrayList<Double> highList = new ArrayList<>();
    ArrayList<Double> lowList = new ArrayList<>();
    ArrayList<Double> closeList = new ArrayList<>();
    //ArrayList<Double> volList = new ArrayList<>();
    //ArrayList<Double> timeList = new ArrayList<>();
    ArrayList<Double> eList = new ArrayList<>();

    String repPath = "src\\main\\java\\Лаб1\\1.csv";

    // Чтение с файла
    public void readRepFile() throws IOException {
        List<String> list = Files.readAllLines(Paths.get(repPath), StandardCharsets.UTF_8);
        int i = 0;
        for (String line : list) {
            if (i == 0) {
                i++;
                continue;
            }
            String[] words = line.split(",");
            openList.add(Double.parseDouble(words[4]));
            highList.add(Double.parseDouble(words[5]));
            lowList.add(Double.parseDouble(words[6]));
            closeList.add(Double.parseDouble(words[7]));
            //volList.add(Double.parseDouble(words[6]));
        }
        //for (int j = 0; j < openList.size(); j++) {
            //timeList.add((double) j);
        //}
    }

    public ArrayList<Double> getOpenList() {
        return openList;
    }

    public void setOpenList(ArrayList<Double> openList) {
        this.openList = openList;
    }

    public ArrayList<Double> getHighList() {
        return highList;
    }

    public void setHighList(ArrayList<Double> highList) {
        this.highList = highList;
    }

    public ArrayList<Double> getLowList() {
        return lowList;
    }

    public void setLowList(ArrayList<Double> lowList) {
        this.lowList = lowList;
    }

    public ArrayList<Double> getCloseList() {
        return closeList;
    }

    public void setCloseList(ArrayList<Double> closeList) {
        this.closeList = closeList;
    }
}
