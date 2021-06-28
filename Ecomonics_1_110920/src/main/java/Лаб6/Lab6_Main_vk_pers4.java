package Лаб6;

import Лаб1.MNK;
import Лаб2.Matrix;

import java.io.IOException;
import java.util.ArrayList;

public class Lab6_Main_vk_pers4 {
    public static void main(String[] args) throws IOException {
        Repository_lab6_vk_pers4 repository = new Repository_lab6_vk_pers4();
        repository.readRepFile();
        MNK mnk = new MNK();

        System.out.println("\nФайл vk_pers4");
        System.out.println("-----------------------------------------");

        /*
        --------------------------------------------------------------------------------------------------------------
          kmeans зависит от friends
         */
        System.out.println("\nРабота с kmeans и friends");
        // Вычисление b1 и b0 для friends
        double b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.kMeansList, repository.friendsList),
                mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.friendsList),
                mnk.calcAverage1Pow2element(repository.friendsList));
        double b0 = mnk.calc_b0(mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.friendsList),
                b1);

        // b0 и b1
        System.out.println("b0: " + b0 + " b1: " + b1);

        // R2
        double R2 = mnk.calcR_pow2(repository.kMeansList.size(),
                mnk.calc1elementTheory(repository.friendsList, b0, b1),
                repository.kMeansList,
                mnk.calcAverage1element(repository.kMeansList));
        System.out.println("R2: " + R2);

        /*
        --------------------------------------------------------------------------------------------------------------
          kmeans зависит от followers
         */
        System.out.println("\nРабота с kmeans и followers");
        // Вычисление b1 и b0 для followers
        b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.kMeansList, repository.followersList),
                mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.followersList),
                mnk.calcAverage1Pow2element(repository.followersList));
        b0 = mnk.calc_b0(mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.followersList),
                b1);

        // b0 и b1
        System.out.println("b0: " + b0 + " b1: " + b1);

        // R2
        R2 = mnk.calcR_pow2(repository.kMeansList.size(),
                mnk.calc1elementTheory(repository.followersList, b0, b1),
                repository.kMeansList,
                mnk.calcAverage1element(repository.kMeansList));
        System.out.println("R2: " + R2);

        /*
        --------------------------------------------------------------------------------------------------------------
          kmeans зависит от photos
         */
        System.out.println("\nРабота с kmeans и photos");
        // Вычисление b1 и b0 для photos
        b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.kMeansList, repository.photosList),
                mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.photosList),
                mnk.calcAverage1Pow2element(repository.photosList));
        b0 = mnk.calc_b0(mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.photosList),
                b1);

        // b0 и b1
        System.out.println("b0: " + b0 + " b1: " + b1);

        // R2
        R2 = mnk.calcR_pow2(repository.kMeansList.size(),
                mnk.calc1elementTheory(repository.photosList, b0, b1),
                repository.kMeansList,
                mnk.calcAverage1element(repository.kMeansList));
        System.out.println("R2: " + R2);

        /*
        --------------------------------------------------------------------------------------------------------------
          kmeans зависит от videos
         */
        System.out.println("\nРабота с kmeans и videos");
        // Вычисление b1 и b0 для videos
        b1 = mnk.calc_b1(mnk.calcAverage2elements(repository.kMeansList, repository.videosList),
                mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.videosList),
                mnk.calcAverage1Pow2element(repository.videosList));
        b0 = mnk.calc_b0(mnk.calcAverage1element(repository.kMeansList),
                mnk.calcAverage1element(repository.videosList),
                b1);

        // b0 и b1
        System.out.println("b0: " + b0 + " b1: " + b1);

        // R2
        R2 = mnk.calcR_pow2(repository.kMeansList.size(),
                mnk.calc1elementTheory(repository.videosList, b0, b1),
                repository.kMeansList,
                mnk.calcAverage1element(repository.kMeansList));
        System.out.println("R2: " + R2);

        /*
        ---------------------------------------------------------------------------------------------------------------
        ЗАВИСИМОСТЬ ОТ 2 ЗНАЧЕНИЙ
         */

        /*
        ---------------------------------------------------------------------------------------------------------------
        kmeans зависит от friends и photos
         */

        ArrayList<Double> kMeansList = repository.kMeansList;
        ArrayList<Double> friendsList = repository.friendsList;
        ArrayList<Double> photosList = repository.photosList;

        // Матрица kmeans
        double[][] kMeansMatrix = new double[kMeansList.size()][1];
        for (int i = 0; i < kMeansList.size(); i++) {
            kMeansMatrix[i][0] = kMeansList.get(i);
        }
        Matrix kMeansMatrixClass = new Matrix(kMeansMatrix);

        // Матрица friends
        double[][] friendsMatrix = new double[friendsList.size()][1];
        for (int i = 0; i < friendsList.size(); i++) {
            friendsMatrix[i][0] = friendsList.get(i);
        }
        Matrix friendsMatrixClass = new Matrix(friendsMatrix);

        // Матрица photos
        double[][] photosMatrix = new double[photosList.size()][1];
        for (int i = 0; i < photosList.size(); i++) {
            photosMatrix[i][0] = photosList.get(i);
        }
        Matrix photosMatrixClass = new Matrix(photosMatrix);

        // Матрица X
        double[][] X_matrix = new double[kMeansList.size()][3];
        for (int i = 0; i < kMeansList.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    X_matrix[i][j] = 1;
                } else if (j == 1) {
                    X_matrix[i][j] = friendsList.get(i);
                } else {
                    X_matrix[i][j] = photosList.get(i);
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

        // Процесс вычисления beta-коэффициентов для friends и photos
        Matrix Y_matrixClass = new Matrix(Y_matrix); // Матрица Y
        Matrix X_matrixClass = new Matrix(X_matrix); // Матрица X
        Matrix X_transMatrixClass = X_matrixClass.transpose(); // Транспонированная матрица X
        Matrix times_MatrixClass = X_transMatrixClass.times(X_matrixClass); // Транспонированная матрица X на матрицу X
        double[][] times_Matrix = times_MatrixClass.data; // Произведение переводим в обычное представление
        Matrix X_invMatrixClass = new Matrix(Matrix.inversion(times_Matrix, unit_matrix, 3)); // Обратная матрица
        Matrix C = X_invMatrixClass.times(X_transMatrixClass); // (X^T * X) ^ (-1) * X^T
        Matrix BetaMatrix = C.times(Y_matrixClass);
        System.out.println("\nМатрица beta для friends и photos:");
        BetaMatrix.show();

        // Вычисление Y (С) теоретическое
        Matrix bFriends_matrix_class = friendsMatrixClass.matrixTimesNumber(BetaMatrix.data[1][0]);
        Matrix bPhotos_matrix_class = photosMatrixClass.matrixTimesNumber(BetaMatrix.data[2][0]);
        Matrix kMeans_th = bFriends_matrix_class.plus(bPhotos_matrix_class).matrixPlusNumber(BetaMatrix.data[0][0]);

        // R2
        double Rpow2 = Y_matrixClass.calcR_pow2(Y_matrixClass.average(), kMeans_th);
        System.out.println("R2: " + Rpow2);
    }
}
