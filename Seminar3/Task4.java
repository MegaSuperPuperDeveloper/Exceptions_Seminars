package Seminar3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Task4 {

    public static void main(String[] args) {
        // divide(1, 0);
        try {
            fileNotFound("null");
        } catch(NonExistedFileException e) {
            System.out.println("It's ready");
            e.printStackTrace();
        }
    }

    public static double divide(int a, int b) {
        if (b == 0) throw new DivideByZeroException();
        return a / b;
    }

    public static void fileNotFound(String pathfile) throws NonExistedFileException {
        try (FileReader fr = new FileReader(pathfile)) {
            // наш код
        } catch(IOException e) {
            throw new NonExistedFileException();
        }
    }

}

/**
Задание 4.1
Создайте класс исключения, который будет выбрасываться при делении на 0.
Исключение должно отображать понятное для пользователя сообщение об ошибке.
*/

class DivideByZeroException extends ArithmeticException {

    public DivideByZeroException() {
        super("Divide by Zero is forbidden!");
    }

    public DivideByZeroException(String message) {
        super(message);
    }

}

/**
* Задание 4.2
* Создайте класс исключения, которое будет возникать при попытке открыть несуществующий файл.
* Исключение должно отображать понятное для пользователя сообщение об ошибке.
*/

class NonExistedFileException extends FileNotFoundException {
    
    public NonExistedFileException() {
        super();
    }

    public NonExistedFileException(String s) {
        super(s);
    }

}


