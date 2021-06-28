package Лаб3;

import Лаб1.Repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Lab3_Main {
    // Вычисление отношений дисперсий от 0 до k к от n - k до n
    public static double calcDispersion(ArrayList<Double> list, int k) {
        double S1 = 0;
        double S3 = 0;
        for (int i = 0; i < k; i++) {
            S1 += Math.pow(list.get(i), 2);
        }
        for (int i = 0; i < list.size() - k; i++) {
            S3 += Math.pow(list.get(i), 2);
        }
        return S3 / S1;
    }

    // Считать значение R2 из файла
    public static double readR2FromFile(String path) throws IOException {
        List<String> list = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        double R2 = 0;
        for (String line : list) {
            R2 = Double.parseDouble(line);
        }
        return R2;
    }

    // Считать значения E из файла
    public static ArrayList<Double> readEFromFile(String path) throws IOException {
        List<String> list = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        int i = 0;
        ArrayList<Double> eList = new ArrayList<>();
        for (String line : list) {
            if (i == 0) {
                i++;
                continue;
            }
            eList.add(Double.parseDouble(line));
        }
        return eList;
    }

    // Вычисление критерия Дарбина-Уотсена
    public static double theDurbinWatsonCriterion(ArrayList<Double> eList) {
        double sumTop = 0;
        double sumBot = 0;
        for (int i = 1; i < eList.size(); i++) {
            sumTop += Math.pow(eList.get(i) - eList.get(i - 1), 2);
            sumBot += Math.pow(eList.get(i), 2);
        }
        return sumTop / sumBot;
    }

    // Обработка результата критерия
    public static void criterionResult(double number) {
        double epsilon = 1; // Насколько близко значения могут находиться к 0, 2 и 4. А если вне этих диапазонов?
        if (number < 0 || number > 4) System.out.println("Значение критерия Дарбина-Уотсена: " + number + "\n" +
                "Ошибка - вне диапозона [0,4]");
        else if (number <= epsilon) {
            System.out.println("Значение критерия Дарбина-Уотсена: " + number + "\n" +
                    "Результат - положительная автокорреляция");
        }
        else if (number >= 4 - epsilon) System.out.println("Значение критерия Дарбина-Уотсена: " + number + "\n" +
                "Результат - отрицательная автокорреляция");
        else if (number >= 2 - epsilon && number <= 2 + epsilon) {
            System.out.println("Значение критерия Дарбина-Уотсена: " +
                    number + "\n" +
                    "Результат - автокорреляция отсутствует");
        }
    }

    public static void main(String[] args) throws IOException {
        Repository repository = new Repository();
        repository.readRepFile();
        int k = 11 * repository.getCloseList().size() / 30; // Вычисление k: k / n = 11 / 30

        /*
        ВЫЧИСЛЕНИЕ ДЛЯ КАЖДОЙ МОДЕЛИ КРИТЕРИЙ ДАРБИНА-УОТСЕНА, ОБРАБОТКА РЕЗЛЬТАТА
        ВЫЧИСЛЕНИЕ ОТНОШЕНИЯ ДИСПЕРСИЙ С ОБРАБОТКОЙ
        ВЫЧИСЛЕНИЕ КРИТЕРИЙ ФИШЕРА И СРАВНЕНИЕ С ОТНОШЕНИЕМ ДИСПЕРСИЙ, ПО НЕМУ ДЕЛАЕТСЯ ВЫВОД О НАЛИИЧИИ
        ГЕТЕРОСКЕДАТИЧНОСТИ
         */
        System.out.println("Модель 1");
        ArrayList<Double> eModel1 = readEFromFile("src\\main\\java\\eModelFiles\\eModel1.txt");
        criterionResult(theDurbinWatsonCriterion(eModel1));
        System.out.println("F = S3 / S1 = " + calcDispersion(eModel1, k));
        double R2 = readR2FromFile("src\\main\\java\\eModelFiles\\R2Model1.txt");
        double FCrit = (R2 / (1 - R2)) * ((k - 1 - 1) / 1);
        System.out.println("FCrit: " + FCrit);
        if (calcDispersion(eModel1, k) > FCrit) System.out.println("Гетероскедатичность присутствует");
        else System.out.println("Гетероскедатичность отсутствует");

        System.out.println("\nМодель 2");
        ArrayList<Double> eModel2 = readEFromFile("src\\main\\java\\eModelFiles\\eModel2.txt");
        criterionResult(theDurbinWatsonCriterion(eModel2));
        System.out.println("F = S3 / S1 = " + calcDispersion(eModel2, k));
        R2 = readR2FromFile("src\\main\\java\\eModelFiles\\R2Model2.txt");
        FCrit = (R2 / (1 - R2)) * ((k - 1 - 1) / 1);
        System.out.println("FCrit: " + FCrit);
        if (calcDispersion(eModel2, k) > FCrit) System.out.println("Гетероскедатичность присутствует");
        else System.out.println("Гетероскедатичность отсутствует");

        System.out.println("\nМодель 3");
        ArrayList<Double> eModel3 = readEFromFile("src\\main\\java\\eModelFiles\\eModel3.txt");
        criterionResult(theDurbinWatsonCriterion(eModel3));
        System.out.println("F = S3 / S1 = " + calcDispersion(eModel3, k));
        R2 = readR2FromFile("src\\main\\java\\eModelFiles\\R2Model3.txt");
        FCrit = (R2 / (1 - R2)) * ((k - 1 - 1) / 1);
        System.out.println("FCrit: " + FCrit);
        if (calcDispersion(eModel3, k) > FCrit) System.out.println("Гетероскедатичность присутствует");
        else System.out.println("Гетероскедатичность отсутствует");

        System.out.println("\nМодель 4");
        ArrayList<Double> eModel4 = readEFromFile("src\\main\\java\\eModelFiles\\eModel4.txt");
        criterionResult(theDurbinWatsonCriterion(eModel4));
        System.out.println("F = S3 / S1 = " + calcDispersion(eModel4, k));
        R2 = readR2FromFile("src\\main\\java\\eModelFiles\\R2Model4.txt");
        FCrit = (R2 / (1 - R2)) * ((k - 1 - 1) / 1);
        System.out.println("FCrit: " + FCrit);
        if (calcDispersion(eModel4, k) > FCrit) System.out.println("Гетероскедатичность присутствует");
        else System.out.println("Гетероскедатичность отсутствует");

        System.out.println("\nМодель 5 для H");
        ArrayList<Double> eModel5_H = readEFromFile("src\\main\\java\\eModelFiles\\eModel5_H.txt");
        criterionResult(theDurbinWatsonCriterion(eModel5_H));
        System.out.println("F = S3 / S1 = " + calcDispersion(eModel5_H, k));
        R2 = readR2FromFile("src\\main\\java\\eModelFiles\\R2Model5.txt");
        FCrit = (R2 / (1 - R2)) * ((k - 2 - 1) / 2);
        System.out.println("FCrit: " + FCrit);
        if (calcDispersion(eModel5_H, k) > FCrit) System.out.println("Гетероскедатичность присутствует");
        else System.out.println("Гетероскедатичность отсутствует");

        System.out.println("\nМодель 5 для O");
        ArrayList<Double> eModel5_O = readEFromFile("src\\main\\java\\eModelFiles\\eModel5_O.txt");
        criterionResult(theDurbinWatsonCriterion(eModel5_O));
        System.out.println("F = S3 / S1 = " + calcDispersion(eModel5_O, k));
        R2 = readR2FromFile("src\\main\\java\\eModelFiles\\R2Model5.txt");
        FCrit = (R2 / (1 - R2)) * ((k - 2 - 1) / 2);
        System.out.println("FCrit: " + FCrit);
        if (calcDispersion(eModel5_O, k) > FCrit) System.out.println("Гетероскедатичность присутствует");
        else System.out.println("Гетероскедатичность отсутствует");

        System.out.println("\nМодель 6 для H");
        ArrayList<Double> eModel6_H = readEFromFile("src\\main\\java\\eModelFiles\\eModel6_H.txt");
        criterionResult(theDurbinWatsonCriterion(eModel6_H));
        System.out.println("F = S3 / S1 = " + calcDispersion(eModel6_H, k));
        R2 = readR2FromFile("src\\main\\java\\eModelFiles\\R2Model6.txt");
        FCrit = (R2 / (1 - R2)) * ((k - 3 - 1) / 3);
        System.out.println("FCrit: " + FCrit);
        if (calcDispersion(eModel6_H, k) > FCrit) System.out.println("Гетероскедатичность присутствует");
        else System.out.println("Гетероскедатичность отсутствует");

        System.out.println("\nМодель 6 для L");
        ArrayList<Double> eModel6_L = readEFromFile("src\\main\\java\\eModelFiles\\eModel6_L.txt");
        criterionResult(theDurbinWatsonCriterion(eModel6_L));
        System.out.println("F = S3 / S1 = " + calcDispersion(eModel6_L, k));
        R2 = readR2FromFile("src\\main\\java\\eModelFiles\\R2Model6.txt");
        FCrit = (R2 / (1 - R2)) * ((k - 3 - 1) / 3);
        System.out.println("FCrit: " + FCrit);
        if (calcDispersion(eModel6_L, k) > FCrit) System.out.println("Гетероскедатичность присутствует");
        else System.out.println("Гетероскедатичность отсутствует");

        System.out.println("\nМодель 6 для O");
        ArrayList<Double> eModel6_O = readEFromFile("src\\main\\java\\eModelFiles\\eModel6_O.txt");
        criterionResult(theDurbinWatsonCriterion(eModel6_O));
        System.out.println("F = S3 / S1 = " + calcDispersion(eModel6_O, k));
        R2 = readR2FromFile("src\\main\\java\\eModelFiles\\R2Model6.txt");
        FCrit = (R2 / (1 - R2)) * ((k - 3 - 1) / 3);
        System.out.println("FCrit: " + FCrit);
        if (calcDispersion(eModel6_O, k) > FCrit) System.out.println("Гетероскедатичность присутствует");
        else System.out.println("Гетероскедатичность отсутствует");
    }
}
