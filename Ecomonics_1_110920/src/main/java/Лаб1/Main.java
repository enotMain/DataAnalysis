package Лаб1;

import java.io.IOException;

/*
Примечание
    Направление высчитывается через b1
    Коэффициент детерминации - это R2
 */

public class Main {
    public static void main(String[] args) throws IOException {
        // Подготовка к использованию инструментов и репозитория
        Repository repository = new Repository();
        repository.readRepFile();
        MNK mnk = new MNK();

        System.out.println("\nЦена акций предприятия КамАЗ");
        System.out.println("-------------------------------------------");

        /*
        Работа с C и t (TIME)
         *//*
        System.out.println("\nРабота с C и t (TIME)");
        // Вычисляем b1 и b0 для t
        double b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.closeList, repository.timeList),
                mnk.calcAverage1element(repository.closeList),
                mnk.calcAverage1element(repository.timeList),
                mnk.calcAverage1Pow2element(repository.timeList));
        double b0 = mnk.calc_b0(mnk.calcAverage1element(repository.closeList),
                mnk.calcAverage1element(repository.timeList),
                b1);

        // b0
        System.out.println("b0 = " + b0);

        // Запись e в файл
        mnk.writeEToFile("src\\main\\java\\eModelFiles\\eModel1.txt",
                repository.getCloseList(), repository.getTimeList(), b0, b1);

        // Выяснение тренда t
        mnk.findOutTrend(b1);

        // Вычисляем R2 для t
        double R2 = mnk.calcR_pow2(repository.closeList.size(),
                mnk.calc1elementTheory(repository.timeList, b0, b1),
                repository.closeList,
                mnk.calcAverage1element(repository.closeList));

        // R2 в файл
        mnk.writeR2ToFile(R2, "src\\main\\java\\eModelFiles\\R2Model1.txt");

        // Качество обработки тренда t
        mnk.findOutR2(R2);
        System.out.println("-------------------------------------------");*/

        /*
        Работа с C и O (OPEN)
         */
        System.out.println("\nРабота с C и O (OPEN)");
        // Вычисляем b1 и b0 для O
        double b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.closeList, repository.openList),
                mnk.calcAverage1element(repository.closeList),
                mnk.calcAverage1element(repository.openList),
                mnk.calcAverage1Pow2element(repository.openList));
        double b0 = mnk.calc_b0(mnk.calcAverage1element(repository.closeList),
                mnk.calcAverage1element(repository.openList),
                b1);

        // b0
        System.out.println("b0 = " + b0);

        // Запись e в файл
        mnk.writeEToFile("src\\main\\java\\eModelFiles\\eModel2.txt",
                repository.getCloseList(), repository.getOpenList(), b0, b1);

        // Выяснение тренда O
        mnk.findOutTrend(b1);

        // Вычисляем R2 для O
        double R2 = mnk.calcR_pow2(repository.closeList.size(),
                mnk.calc1elementTheory(repository.openList, b0, b1),
                repository.closeList,
                mnk.calcAverage1element(repository.closeList));

        // R2 в файл
        mnk.writeR2ToFile(R2, "src\\main\\java\\eModelFiles\\R2Model2.txt");

        // Качество обработки тренда O
        mnk.findOutR2(R2);
        System.out.println("-------------------------------------------");

        /*
        Работа с C и L (LOW)
         */
        System.out.println("\nРабота с C и L (LOW)");
        // Вычисляем b1 и b0 для L
        b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.closeList, repository.lowList),
                mnk.calcAverage1element(repository.closeList),
                mnk.calcAverage1element(repository.lowList),
                mnk.calcAverage1Pow2element(repository.lowList));
        b0 = mnk.calc_b0(mnk.calcAverage1element(repository.closeList),
                mnk.calcAverage1element(repository.lowList),
                b1);

        // b0
        System.out.println("b0 = " + b0);

        // Запись e в файл
        mnk.writeEToFile("src\\main\\java\\eModelFiles\\eModel3.txt",
                repository.getCloseList(), repository.getLowList(), b0, b1);

        // Выяснение тренда L
        mnk.findOutTrend(b1);

        // Вычисляем R2 для L
        R2 = mnk.calcR_pow2(repository.closeList.size(),
                mnk.calc1elementTheory(repository.lowList, b0, b1),
                repository.closeList,
                mnk.calcAverage1element(repository.closeList));

        // R2 в файл
        mnk.writeR2ToFile(R2, "src\\main\\java\\eModelFiles\\R2Model3.txt");

        // Качество обработки тренда L
        mnk.findOutR2(R2);
        System.out.println("-------------------------------------------");

        /*
        Работа с C и H (HIGH)
         */
        System.out.println("\nРабота с C и H (HIGH)");
        // Вычисляем b1 и b0 для H
        b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.closeList, repository.highList),
                mnk.calcAverage1element(repository.closeList),
                mnk.calcAverage1element(repository.highList),
                mnk.calcAverage1Pow2element(repository.highList));
        b0 = mnk.calc_b0(mnk.calcAverage1element(repository.closeList),
                mnk.calcAverage1element(repository.highList),
                b1);

        // b0
        System.out.println("b0 = " + b0);

        // Запись e в файл
        mnk.writeEToFile("src\\main\\java\\eModelFiles\\eModel4.txt",
                repository.getCloseList(), repository.getHighList(), b0, b1);

        // Выяснение тренда H
        mnk.findOutTrend(b1);

        // Вычисляем R2 для H
        R2 = mnk.calcR_pow2(repository.closeList.size(),
                mnk.calc1elementTheory(repository.highList, b0, b1),
                repository.closeList,
                mnk.calcAverage1element(repository.closeList));

        // R2 в файл
        mnk.writeR2ToFile(R2, "src\\main\\java\\eModelFiles\\R2Model4.txt");

        // Качество обработки тренда H
        mnk.findOutR2(R2);
        System.out.println("-------------------------------------------");
    }
}
