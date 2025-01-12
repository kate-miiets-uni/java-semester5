public class Main {
    public static void main(String[] args) {
        try {
            // Визначення матриць A і B
            int[][] A = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
            };

            int[][] B = {
                    {9, 8, 7},
                    {6, 5, 4},
                    {3, 2, 1}
            };

            // Обчислення матриці C = A * B
            int[][] C = multiplyMatrices(A, B);

            // Виведення матриці C
            System.out.println("Матриця C:");
            printMatrix(C);

            // Обчислення суми найбільших елементів кожного стовпця матриці C
            int sum = sumMaxElementsOfColumns(C);

            // Виведення результату
            System.out.println("Сума найбільших елементів кожного стовпця: " + sum);
        } catch (IllegalArgumentException e) {
            System.err.println("Помилка: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Помилка: Один з масивів є нульовим.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Помилка: Індекс виходить за межі масиву.");
        } catch (Exception e) {
            System.err.println("Непередбачувана помилка: " + e.getMessage());
        }
    }

    // Метод для множення двох матриць
    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException("Кількість стовпців у матриці A повинна дорівнювати кількості рядків у матриці B.");
        }

        int[][] C = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    // Метод для обчислення суми найбільших елементів кожного стовпця
    public static int sumMaxElementsOfColumns(int[][] matrix) {
        int cols = matrix[0].length;
        int sum = 0;
        for (int j = 0; j < cols; j++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            sum += max;
        }
        return sum;
    }

    // Метод для виведення матриці
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
