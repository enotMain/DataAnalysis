package Лаб6;

import Лаб1.MNK;
import Лаб2.Matrix;

import java.io.IOException;
import java.util.ArrayList;

public class Lab6_Main_result82 {
    public static void main(String[] args) throws IOException {
        Repository_lab6_result82 repository = new Repository_lab6_result82();
        repository.readRepFile();
        MNK mnk = new MNK();

        System.out.println("\nФайл result82");
        System.out.println("------------------------------------------");

        /*
        --------------------------------------------------------------------------------------------------------------
          kmeans зависит от money
         */
        System.out.println("\nРабота с kmeans и money");
        // Вычисление b1 и b0 для money
        double b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.kMeansList, repository.moneyList),
                mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.moneyList),
                mnk.calcAverage1Pow2element(repository.moneyList));
        double b0 = mnk.calc_b0(mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.moneyList),
                        b1);

        // b0 и b1
        System.out.println("b0: " + b0 + " b1: " + b1);

        // R2
        double R2 = mnk.calcR_pow2(repository.kMeansList.size(),
                mnk.calc1elementTheory(repository.moneyList, b0, b1),
                repository.kMeansList,
                mnk.calcAverage1element(repository.kMeansList));
        System.out.println("R2: " + R2);

        /*
        --------------------------------------------------------------------------------------------------------------
          kmeans зависит от avg_salary
         */
        System.out.println("\nРабота с kmeans и avg_salary");
        // Вычисление b1 и b0 для avg_salary
        b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.kMeansList, repository.avgSalaryList),
                mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.avgSalaryList),
                mnk.calcAverage1Pow2element(repository.avgSalaryList));
        b0 = mnk.calc_b0(mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.avgSalaryList),
                b1);

        // b0 и b1
        System.out.println("b0: " + b0 + " b1: " + b1);

        // R2
        R2 = mnk.calcR_pow2(repository.kMeansList.size(),
                mnk.calc1elementTheory(repository.avgSalaryList, b0, b1),
                repository.kMeansList,
                mnk.calcAverage1element(repository.kMeansList));
        System.out.println("R2: " + R2);

        /*
        --------------------------------------------------------------------------------------------------------------
          kmeans зависит от last_job_diff_cat
         */
        System.out.println("\nРабота с kmeans и last_job_diff_cat");
        // Вычисление b1 и b0 для last_job_diff_cat
        b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.kMeansList, repository.lastJobDiffCatList),
                mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.lastJobDiffCatList),
                mnk.calcAverage1Pow2element(repository.lastJobDiffCatList));
        b0 = mnk.calc_b0(mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.lastJobDiffCatList),
                b1);

        // b0 и b1
        System.out.println("b0: " + b0 + " b1: " + b1);

        // R2
        R2 = mnk.calcR_pow2(repository.kMeansList.size(),
                mnk.calc1elementTheory(repository.lastJobDiffCatList, b0, b1),
                repository.kMeansList,
                mnk.calcAverage1element(repository.kMeansList));
        System.out.println("R2: " + R2);

        /*
        --------------------------------------------------------------------------------------------------------------
          kmeans зависит от sal_cat
         */
        System.out.println("\nРабота с kmeans и sal_cat");
        // Вычисление b1 и b0 для sal_cat
        b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.kMeansList, repository.salCatList),
                mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.salCatList),
                mnk.calcAverage1Pow2element(repository.salCatList));
        b0 = mnk.calc_b0(mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.salCatList),
                b1);

        // b0 и b1
        System.out.println("b0: " + b0 + " b1: " + b1);

        // R2
        R2 = mnk.calcR_pow2(repository.kMeansList.size(),
                mnk.calc1elementTheory(repository.salCatList, b0, b1),
                repository.kMeansList,
                mnk.calcAverage1element(repository.kMeansList));
        System.out.println("R2: " + R2);

        /*
        ---------------------------------------------------------------------------------------------------------------
        ЗАВИСИМОСТЬ ОТ 2 ЗНАЧЕНИЙ
         */

        /*
        ---------------------------------------------------------------------------------------------------------------
        kmeans зависит от money и cat_exp
         */

        ArrayList<Double> kMeansList = repository.kMeansList;
        ArrayList<Double> moneyList = repository.moneyList;
        ArrayList<Double> catExpList = repository.catExpList;

        // Матрица kmeans
        double[][] kMeansMatrix = new double[kMeansList.size()][1];
        for (int i = 0; i < kMeansList.size(); i++) {
            kMeansMatrix[i][0] = kMeansList.get(i);
        }
        Matrix kMeansMatrixClass = new Matrix(kMeansMatrix);

        // Матрица money
        double[][] moneyMatrix = new double[moneyList.size()][1];
        for (int i = 0; i < moneyList.size(); i++) {
            moneyMatrix[i][0] = moneyList.get(i);
        }
        Matrix moneyMatrixClass = new Matrix(moneyMatrix);

        // Матрица cat_exp
        double[][] catExpMatrix = new double[catExpList.size()][1];
        for (int i = 0; i < catExpList.size(); i++) {
            catExpMatrix[i][0] = catExpList.get(i);
        }
        Matrix catExpMatrixClass = new Matrix(catExpMatrix);

        // Матрица X
        double[][] X_matrix = new double[kMeansList.size()][3];
        for (int i = 0; i < kMeansList.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    X_matrix[i][j] = 1;
                } else if (j == 1) {
                    X_matrix[i][j] = moneyList.get(i);
                } else {
                    X_matrix[i][j] = catExpList.get(i);
                }
            }
        }

        // Единичная матрица
        double[][] unit_matrix = new double[kMeansList.size()][kMeansList.size()];
        for (int i = 0; i < kMeansList.size(); i++) {
            for (int j = 0; j < kMeansList.size(); j++) {
                if (i == j) unit_matrix[i][j] = 1;
                else unit_matrix[i][j] = 0;
            }
        }

        // Матрица Y
        double[][] Y_matrix = new double[kMeansList.size()][1];
        for (int i = 0; i < kMeansList.size(); i++) {
            Y_matrix[i][0] = kMeansList.get(i);
        }

        // Процесс вычисления beta-коэффициентов для money и cat_exp
        Matrix Y_matrixClass = new Matrix(Y_matrix); // Матрица Y
        Matrix X_matrixClass = new Matrix(X_matrix); // Матрица X
        Matrix X_transMatrixClass = X_matrixClass.transpose(); // Транспонированная матрица X
        Matrix times_MatrixClass = X_transMatrixClass.times(X_matrixClass); // Транспонированная матрица X на матрицу X
        double[][] times_Matrix = times_MatrixClass.data; // Произведение переводим в обычное представление
        Matrix X_invMatrixClass = new Matrix(Matrix.inversion(times_Matrix, unit_matrix, 3)); // Обратная матрица
        Matrix C = X_invMatrixClass.times(X_transMatrixClass); // (X^T * X) ^ (-1) * X^T
        Matrix BetaMatrix = C.times(Y_matrixClass);
        System.out.println("\nМатрица beta для money и cat_exp:");
        BetaMatrix.show();

        // Вычисление Y (С) теоретическое
        Matrix bMoney_matrix_class = moneyMatrixClass.matrixTimesNumber(BetaMatrix.data[1][0]);
        Matrix bCatExp_matrix_class = catExpMatrixClass.matrixTimesNumber(BetaMatrix.data[2][0]);
        Matrix kMeans_th = bMoney_matrix_class.plus(bCatExp_matrix_class).matrixPlusNumber(BetaMatrix.data[0][0]);

        // R2
        double Rpow2 = Y_matrixClass.calcR_pow2(Y_matrixClass.average(), kMeans_th);
        System.out.println("R2: " + Rpow2);
    }
}
