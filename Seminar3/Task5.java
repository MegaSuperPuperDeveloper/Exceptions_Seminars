package Seminar3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
Напишите метод, на вход которого подаётся двумерный строковый массив размером 3х3.
При подаче массива другого размера необходимо бросить исключение MyArraySizeException.

1. Далее метод должен пройтись по всем элементам массива, преобразовать в число и просуммировать.
Если в каком-то элементе массива преобразование не удалось
(например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException
с детализацией, в какой именно ячейке лежат неверные данные.

2. В методе main() вызвать полученный метод, обработать возможные исключения
MyArraySizeException и MyArrayDataException и вывести результат расчета
(сумму элементов, при условии, что подали на вход корректный массив).
*/

class Task5 {
    static String[][] arr = new String[][] {
        {"1", "z5", "1.5"},
        {"1", "z", "1.5"},
        {"1", "1.5", "1.5"}
    };
    
    
    public static void main(String[] args) {
        System.out.println(sum2dV2(arr));
    }
    
    public static double sum2d(String[][] arr) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != arr.length) throw new MyArraySizeException();
        }
        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Double.parseDouble(arr[i][j]);
                } catch(NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
    
    public static double sum2dV2(String[][] arr) {
        double sum = 0;
        Map<Point2D, String> errorCache = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != arr.length) throw new MyArraySizeException();
        }
        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Double.parseDouble(arr[i][j]);
                } catch(NumberFormatException e) {
                    errorCache.put(new Point2D(i, j), arr[i][j]);
                }
            }
        }
        if (!errorCache.isEmpty()) throw new MyArrayDataException(errorCache);
        return sum;
    }

}

class Point2D {

    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("%d; %d", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point2D)) return false;
        Point2D point2d = (Point2D) obj;
        return x == point2d.x && y == point2d.y;
    }

    public int HashCode() {
        return Objects.hash(x, y);
    }

}

class MyArraySizeException extends ArrayIndexOutOfBoundsException {
    public MyArraySizeException() {
        super("Size of array must be 3x3.");
    }
}

class MyArrayDataException extends IllegalArgumentException {
    public MyArrayDataException(int a, int b) {
        super(
            String.format("Incorrect argument in cell [%d][%d].", a, b)
        );
    }

    public MyArrayDataException(Map<Point2D, String> errorsCache) {
        super(String.format("Invalid dates: %s", errorsCache));
    }

    private String prepareData(Map<Point2D, String> errorCache) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Point2D, String> entry: errorCache.entrySet()) {
            sb.append("Coordinates: ")
              .append(entry.getKey())
              .append(",  meaning: ")
              .append(entry.getValue())
              .append(", \n");
        }
        return sb.toString();
    }

}