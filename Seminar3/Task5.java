package Seminar3;

import java.util.HashMap;
import java.util.Map;

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
        {"1", "1.5", "1.5"},
        {"1", "1.5", "1.5"},
        {"1", "1.5", "1.5"}
    };
    
    
    public static void main(String[] args) {
        System.out.println(sum2d(arr));
    }
    
    public static double sum2d(String[][] arr) {
        double sum = 0;
        Map<Point2D, String> map = new HashMap<>();
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

}

class MyArraySizeException extends ArrayIndexOutOfBoundsException {
    public MyArraySizeException() {
        super("Size of array must be 3x3.");
    }
}

class MyArrayDataException extends IllegalArgumentException {
    public MyArrayDataException(int a, int b) {
        super(
            String.format("Incorrect argument in cell [%d][%d]", a, b)
        );
    }
}