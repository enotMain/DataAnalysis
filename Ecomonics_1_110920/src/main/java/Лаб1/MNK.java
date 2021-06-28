package Лаб1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// МНК и R-квадрат
public class MNK {
    // Среднее арифметическое одного списка
    public Double calcAverage1element(ArrayList<Double> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum / list.size();
    }

    // Среднее арифметическое произведение пар соотвественных элементов двух списков
    public Double calcAverage2elements(ArrayList<Double> Q, ArrayList<Double> P) {
        if (Q.size() != P.size()) {
            return null;
        }
        double sum = 0;
        for (int i = 0; i < Q.size(); i++) {
            sum += Q.get(i) * P.get(i);
        }
        return sum / Q.size();
    }

    // Среднее квадратов одного списка
    public Double calcAverage1Pow2element(ArrayList<Double> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += Math.pow(list.get(i), 2);
        }
        return sum / list.size();
    }

    // Теоретическое значение для 1 переменной
    public ArrayList<Double> calc1elementTheory(ArrayList<Double> P, double b0, double b1) {
        ArrayList<Double> Q_th = new ArrayList<>();
        for (int i = 0; i < P.size(); i++) {
            Q_th.add(b0 + b1 * P.get(i));
        }
        return Q_th;
    }

    // Вычисление бета 1
    public Double calc_b1(double QP_Streak, double Q_streak, double P_streak, double P_pow2_streak) {
        if (Math.pow(P_streak, 2) == P_pow2_streak) {
            return null;
        }
        return (QP_Streak - Q_streak * P_streak) / (P_pow2_streak - Math.pow(P_streak, 2));
    }

    // Вычисление бета 2
    public Double calc_b0(double Q_streak, double P_streak, double b1) {
        return Q_streak - b1 * P_streak;
    }

    // Вычисление R-квадрата
    public Double calcR_pow2(int n, ArrayList<Double> Q_th, ArrayList<Double> Q_stat, double Q_aver) {
        if (n > Q_th.size() || n > Q_stat.size()) {
            return null;
        }

        double sumBottom = 0;
        for (int i = 0; i < n; i++) {
            sumBottom += Math.pow(Q_stat.get(i) - Q_aver, 2);
        }
        if (sumBottom == 0) return null;

        double sumTop = 0;
        for (int i = 0; i < n; i++) {
            sumTop += Math.pow(Q_stat.get(i) - Q_th.get(i), 2);
        }

        return 1 - sumTop / sumBottom;
    }

    // Выяснение качества обработки тренда
    public void findOutR2(double R2) {
        if (R2 > 1 || R2 < 0) {
            System.out.println("R2 = " + R2 + " > 1 или < 0 - ошибка в вычислениях");
        } else if (R2 <= 0.6) {
            System.out.println("R2 = " + R2 + " <= 0.6 - завершение исследования");
        } else {
            System.out.println("R2 = " + R2 + " >= 0.6 - качество подлежит дальнейшему исследованию");
        }
    }

    // Выяснение типа тренда
    public void findOutTrend(double b1) {
        if (b1 > 0.05) {
            System.out.println("b1 = " + b1 + " > 0.05 - восходящий тренд");
        } else if (b1 < -0.05) {
            System.out.println("b1 = " + b1 + " < -0.05 - нисходящий тренд");
        } else {
            System.out.println("-0.05 <= b1 = " + b1 + " <= 0.05 - боковой тренд");
        }
    }

    // Запись в файл значений E для 1 аргумента с сортировкой по убыванию аргумента
    public void writeEToFile(String path, ArrayList<Double> cList, ArrayList<Double> modelArgList,
                             double b0, double b1) {
        // Сортировка пузырьком
        for (int i = modelArgList.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (modelArgList.get(j) > modelArgList.get(j + 1)) {
                    double temp = modelArgList.get(j);
                    double temp2 = cList.get(j);

                    modelArgList.set(j, modelArgList.get(j + 1));
                    cList.set(j, cList.get(j + 1));

                    modelArgList.set(j + 1, temp);
                    cList.set(j + 1, temp2);
                }
            }
        }
        // Обратный порядок
        for (int i = 0; i < modelArgList.size() / 2; i++) {
            double temp = modelArgList.get(i);
            double temp2 = cList.get(i);

            modelArgList.set(i, modelArgList.get(modelArgList.size() - i - 1));
            cList.set(i, cList.get(cList.size() - i - 1));

            modelArgList.set(modelArgList.size() - i - 1, temp);
            cList.set(cList.size() - i - 1, temp2);
        }
        // Запись в файл
        try (FileWriter writer = new FileWriter(path, false)){
            for (int i = 0; i < cList.size(); i++) {
                if (i != cList.size() - 1) {
                    writer.write((cList.get(i) - b0 - b1 * modelArgList.get(i)) + "\n");
                } else {
                    writer.write(String.valueOf(cList.get(i) - b0 - b1 * modelArgList.get(i)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Запись в файл значений E для 2 и более аргументов с сортировкой по убыванию выбранного аргумента
    public void writeEToFile(String path, ArrayList<Double> cListStat, ArrayList<Double> cListTh,
                             ArrayList<Double> argumentList) {

        for (int i = 0; i < argumentList.size(); i++) {
            argumentList.set(i, (double) i + 1);
        }
        // Сортировка пузырьком
        for (int i = argumentList.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (argumentList.get(j) > argumentList.get(j + 1)) {
                    double temp = cListTh.get(j);
                    double temp2 = cListStat.get(j);
                    double temp3 = argumentList.get(j);

                    cListTh.set(j, cListTh.get(j + 1));
                    cListStat.set(j, cListStat.get(j + 1));
                    argumentList.set(j, argumentList.get(j + 1));

                    cListTh.set(j + 1, temp);
                    cListStat.set(j + 1, temp2);
                    argumentList.set(j + 1, temp3);
                }
            }
        }
        // Обратный порядок
        for (int i = 0; i < argumentList.size() / 2; i++) {
            double temp = cListTh.get(i);
            double temp2 = cListStat.get(i);
            double temp3 = argumentList.get(i);

            cListTh.set(i, cListTh.get(cListTh.size() - i - 1));
            cListStat.set(i, cListStat.get(cListStat.size() - i - 1));
            argumentList.set(i, argumentList.get(argumentList.size() - i - 1));

            cListTh.set(cListTh.size() - i - 1, temp);
            cListStat.set(cListStat.size() - i - 1, temp2);
            argumentList.set(argumentList.size() - i - 1, temp3);
        }
        // Запись в файл
        try (FileWriter writer = new FileWriter(path, false)){
            for (int i = 0; i < cListStat.size(); i++) {
                if (i != cListStat.size() - 1) {
                    writer.write((cListStat.get(i) - cListTh.get(i)) + "\n");
                } else {
                    writer.write(String.valueOf(cListStat.get(i) - cListTh.get(i)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Запись в файл R2
    public void writeR2ToFile(double R2, String path) {
        try (FileWriter writer = new FileWriter(path, false)){
            writer.write(String.valueOf(R2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
