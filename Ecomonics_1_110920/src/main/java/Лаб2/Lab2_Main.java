package Лаб2;

import Лаб1.MNK;
import Лаб1.Repository;

import java.util.ArrayList;

public class Lab2_Main {
    public static void main(String[] args) throws Exception {
        Repository repository = new Repository();
        repository.readRepFile();
        MNK mnk = new MNK();

        ArrayList<Double> C_array = repository.getCloseList(); // CLOSE
        ArrayList<Double> H_array = repository.getHighList();  // HIGH
        ArrayList<Double> O_array = repository.getOpenList();  // OPEN
        ArrayList<Double> L_array = repository.getLowList();   // LOW

        // Матрица L
        double[][] L_matrix = new double[L_array.size()][1];
        for (int i = 0; i < L_array.size(); i++) {
            L_matrix[i][0] = L_array.get(i);
        }
        Matrix L_matrix_class = new Matrix(L_matrix);

        // Матрица C
        double[][] C_matrix = new double[C_array.size()][1];
        for (int i = 0; i < C_array.size(); i++) {
            C_matrix[i][0] = C_array.get(i);
        }
        Matrix C_matrix_class = new Matrix(C_matrix);

        // Матрица H
        double[][] H_matrix = new double[H_array.size()][1];
        for (int i = 0; i < H_array.size(); i++) {
            H_matrix[i][0] = H_array.get(i);
        }
        Matrix H_matrix_class = new Matrix(H_matrix);

        // Матрица O
        double[][] O_matrix = new double[O_array.size()][1];
        for (int i = 0; i < O_array.size(); i++) {
            O_matrix[i][0] = O_array.get(i);
        }
        Matrix O_matrix_class = new Matrix(O_matrix);

        // Матрица X
        double[][] X_matrix = new double[C_array.size()][3];
        for (int i = 0; i < C_array.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    X_matrix[i][j] = 1;
                } else if (j == 1) {
                    X_matrix[i][j] = H_array.get(i);
                } else {
                    X_matrix[i][j] = O_array.get(i);
                }
            }
        }

        // Матрица X2
        double[][] X_matrix2 = new double[C_array.size()][4];
        for (int i = 0; i < C_array.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    X_matrix2[i][j] = 1;
                } else if (j == 1) {
                    X_matrix2[i][j] = H_array.get(i);
                } else if (j == 2) {
                    X_matrix2[i][j] = L_array.get(i);
                } else {
                    X_matrix2[i][j] = O_array.get(i);
                }
            }
        }

        // Единичная матрица
        //System.out.println(C_array.size());
        double[][] unit_matrix = new double[C_array.size()][C_array.size()];
        for (int i = 0; i < C_array.size(); i++) {
            for (int j = 0; j < C_array.size(); j++) {
                if (i == j) unit_matrix[i][j] = 1;
                else unit_matrix[i][j] = 0;
            }
        }

        // Матрица Y
        double[][] Y_matrix = new double[C_array.size()][1];
        for (int i = 0; i < C_array.size(); i++) {
                Y_matrix[i][0] = C_array.get(i);
        }

        // Процесс вычисления beta-коэффициентов для H O
        Matrix Y_matrixClass = new Matrix(Y_matrix); // Матрица Y
        Matrix X_matrixClass = new Matrix(X_matrix); // Матрица X
        Matrix X_transMatrixClass = X_matrixClass.transpose(); // Транспонированная матрица X
        Matrix times_MatrixClass = X_transMatrixClass.times(X_matrixClass); // Транспонированная матрица X на матрицу X
        double[][] times_Matrix = times_MatrixClass.data; // Произведение переводим в обычное представление
        Matrix X_invMatrixClass = new Matrix(Matrix.inversion(times_Matrix, unit_matrix, 3)); // Обратная матрица
        Matrix C = X_invMatrixClass.times(X_transMatrixClass); // (X^T * X) ^ (-1) * X^T
        Matrix BetaMatrix = C.times(Y_matrixClass);
        System.out.println("Матрица beta для H O:");
        BetaMatrix.show();

        // Вычисление Y (С) теоретическое
        Matrix bH_matrix_class = H_matrix_class.matrixTimesNumber(BetaMatrix.data[1][0]);
        Matrix bO_matrix_class = O_matrix_class.matrixTimesNumber(BetaMatrix.data[2][0]);
        Matrix C_array_th = bH_matrix_class.plus(bO_matrix_class).matrixPlusNumber(BetaMatrix.data[0][0]);

        // Запись e для H в файл
        mnk.writeEToFile("src\\main\\java\\eModelFiles\\eModel5_H.txt",
                C_array, C_array_th.dataMassiveToList(), H_matrix_class.dataMassiveToList());
        // Запись e для O в файл
        mnk.writeEToFile("src\\main\\java\\eModelFiles\\eModel5_O.txt",
                C_array, C_array_th.dataMassiveToList(), O_matrix_class.dataMassiveToList());

        // Вычисление R2 и R2 скор для 2 объясняющих переменных (модель 5)
        double Rpow2 = Y_matrixClass.calcR_pow2(Y_matrixClass.average(), C_array_th);

        // R2 в файл
        mnk.writeR2ToFile(Rpow2, "src\\main\\java\\eModelFiles\\R2Model5.txt");

        System.out.println("    R^2: " + Rpow2);
        double corRpow2 = 1 - (1 - Rpow2) * (((double) C_array_th.M - 1) /
                ((double) C_array_th.M - ((double) BetaMatrix.M - 1)));
        System.out.println("Cor R^2: " + corRpow2);

        // ------------------------------------------------------------------------------------------------
        System.out.println("----------------------------------------------------------------------------");
        // Процесс вычисления beta-коэффициентов для H L O
        Matrix X_matrixClass2 = new Matrix(X_matrix2);
        Matrix X_transMatrixClass2 = X_matrixClass2.transpose();
        Matrix times_MatrixClass2 = X_transMatrixClass2.times(X_matrixClass2);
        double[][] times_Matrix2 = times_MatrixClass2.data;
        Matrix X_invMatrixClass2 = new Matrix(Matrix.inversion(times_Matrix2, unit_matrix, 4));
        Matrix C2 = X_invMatrixClass2.times(X_transMatrixClass2);
        Matrix BetaMatrix2 = C2.times(Y_matrixClass);
        System.out.println("Матрица beta для H L O:");
        BetaMatrix2.show();

        // Вычисление Y (C) теоретическое
        Matrix bH_matrix_class2 = H_matrix_class.matrixTimesNumber(BetaMatrix2.data[1][0]);
        Matrix bL_matrix_class2 = L_matrix_class.matrixTimesNumber(BetaMatrix2.data[2][0]);
        Matrix bO_matrix_class2 = O_matrix_class.matrixTimesNumber(BetaMatrix2.data[3][0]);
        Matrix C_array_th2 = bH_matrix_class2.plus(bL_matrix_class2).plus(bO_matrix_class2).
                matrixPlusNumber(BetaMatrix2.data[0][0]);

        // Запись e для H в файл
        mnk.writeEToFile("src\\main\\java\\eModelFiles\\eModel6_H.txt",
                C_array, C_array_th2.dataMassiveToList(), H_matrix_class.dataMassiveToList());
        // Запись e для L в файл
        mnk.writeEToFile("src\\main\\java\\eModelFiles\\eModel6_L.txt",
                C_array, C_array_th.dataMassiveToList(), L_matrix_class.dataMassiveToList());
        // Запись e для O в файл
        mnk.writeEToFile("src\\main\\java\\eModelFiles\\eModel6_O.txt",
                C_array, C_array_th.dataMassiveToList(), O_matrix_class.dataMassiveToList());

        // Вычиление R2 и R2 скор для 3 объясняющих переменных (модель 6)
        double Rpow2_2 = Y_matrixClass.calcR_pow2(Y_matrixClass.average(), C_array_th2);

        // R2 в файл
        mnk.writeR2ToFile(Rpow2_2, "src\\main\\java\\eModelFiles\\R2Model6.txt");

        System.out.println("    R^2: " + Rpow2_2);
        double corRpow2_2 = 1 - (1 - Rpow2_2) * (((double) C_array_th2.M - 1) /
                ((double) C_array_th2.M - ((double) BetaMatrix2.M - 1)));
        System.out.println("Cor R^2: " + corRpow2_2);
    }
}
