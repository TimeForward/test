import java.util.Scanner;

class CalculatorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение между целқми числами от одного до десяти (например, 1+2 или 1 + 2):");
        String input = scanner.nextLine().trim(); // Получаем строку от пользователя

        try {
            // Вызов метода cal1.c для вычисления результата
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            // Вывод сообщения об ошибке
            System.out.println(e.getMessage());
        }

        scanner.close(); // Закрываем сканер
    }

    public static String calc(String input) throws Exception {
        int num1 = 0, num2 = 0;
        char operator = ' ';
        boolean foundOperator = false;
        StringBuilder firstNumber = new StringBuilder();
        StringBuilder secondNumber = new StringBuilder();

        // Проходим по строке для поиска оператора и разделения на числа
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // Если символ - цифра, добавляем его в соответствующее число
            if (Character.isDigit(currentChar)) {
                if (!foundOperator) {
                    firstNumber.append(currentChar);
                } else {
                    secondNumber.append(currentChar);
                }
            } 
            // Если символ - оператор, устанавливаем его
            else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                if (!foundOperator) {
                    operator = currentChar;
                    foundOperator = true;
                } else {
                    throw new Exception("Ошибка: найдено более одного оператора.");
                }
            } 
            // Если найден некорректный символ
            else if (!Character.isWhitespace(currentChar)) {
                throw new Exception("Ошибка: введён недопустимый символ.");
            }
        }

        // Проверяем, что оба числа были найдены
        if (firstNumber.length() == 0 || secondNumber.length() == 0) {
            throw new Exception("Ошибка: ввод должен быть в формате 'число оператор число'.");
        }

        try {
            // Преобразуем строки в целые числа
            num1 = Integer.parseInt(firstNumber.toString());
            num2 = Integer.parseInt(secondNumber.toString());

            // Проверка на деление на ноль
            if (operator == '/' && num2 == 0) {
                throw new Exception("Ошибка: деление на ноль недопустимо.");
            }

            // Проверяем, находятся ли числа в диапазоне (1-10)
            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                throw new Exception("Ошибка: оба числа должны быть в диапазоне от 1 до 10.");
            }

            // Выполняем арифметическую операцию
            int result;
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2; // Целочисленное деление, остаток отбрасывается
                    break;
                default:
                    throw new Exception("Ошибка: недопустимый оператор.");
            }

            // Возвращаем результат в виде строки
            return Integer.toString(result);

        } catch (NumberFormatException e) {
            // Исключение, если введено не целое число
            throw new Exception("Ошибка: оба операнда должны быть целыми числами.");
        }
    }
}
