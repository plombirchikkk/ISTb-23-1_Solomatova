import java.util.Scanner; // Импортируем класс для работы с вводом пользователя
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Основной класс для работы с меню
class MenuHandler {
    private static Scanner scanner = new Scanner(System.in); // Сканер для ввода данных пользователем
    private static FileManager fileManager = new FileManager(); // Объект для работы с файлами

    // Метод запуска программы с главным меню
    public static void run() {
        while (true) {
            System.out.println("\n--- Главное меню ---");
            System.out.println("1. Арифметические выражения");
            System.out.println("2. Работа со строками");
            System.out.println("3. Работа с файлами");
            System.out.println("0. Выход");
            System.out.print("Выберите категорию: ");
            int choice = getValidatedInteger();

            switch (choice) {
                case 1 -> arithmeticMenu();
                case 2 -> stringMenu();
                case 3 -> fileMenu();
                case 0 -> {
                    System.out.println("Выход из программы...");
                    return;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    // Подменю для работы с арифметическими выражениями
    private static void arithmeticMenu() {
        while (true) {
            System.out.println("\n--- Меню арифметических выражений ---");
            System.out.println("1. Простое выражение (a + b)");
            System.out.println("2. Сложное выражение (a * (b + c))");
            System.out.println("3. Подсчет количества нулей в строке");
            System.out.println("0. Назад");
            System.out.print("Выберите задачу: ");
            int choice = getValidatedInteger();

            switch (choice) {
                case 1 -> handleArithmeticExpression();
                case 2 -> handleComplexExpression();
                case 3 -> handleZeroCount();
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    // Подменю для работы со строками
    private static void stringMenu() {
        while (true) {
            System.out.println("\n--- Меню работы со строками ---");
            System.out.println("1. Проверка подстроки");
            System.out.println("0. Назад");
            System.out.print("Выберите задачу: ");
            int choice = getValidatedInteger();

            switch (choice) {
                case 1 -> handleSubstringCheck();
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    // Подменю для работы с файлами
    private static void fileMenu() {
        while (true) {
            System.out.println("\n--- Меню работы с файлами ---");
            System.out.println("1. Прочитать файл");
            System.out.println("2. Записать в файл");
            System.out.println("3. Очистить файл");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");
            int choice = getValidatedInteger();

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите имя файла для чтения: ");
                    String filename = scanner.nextLine();
                    fileManager.readFromFile(filename);
                }
                case 2 -> {
                    System.out.print("Введите имя файла для записи: ");
                    String filename = scanner.nextLine();
                    System.out.print("Введите данные для записи: ");
                    String data = scanner.nextLine();
                    fileManager.writeToFile(filename, data);
                }
                case 3 -> {
                    System.out.print("Введите имя файла для очистки: ");
                    String filename = scanner.nextLine();
                    fileManager.clearFile(filename);
                }
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    // Обработка простого арифметического выражения
    private static void handleArithmeticExpression() {
        System.out.print("Введите выражение (например, '5 + 3'): ");
        String input = scanner.nextLine();
        try {
            ArithmeticExpression expr = new ArithmeticExpression(input);
            String result = "Результат: " + expr.calculate();
            System.out.println(result);
            logToFile("Арифметическое выражение", input, result);
        } catch (Exception e) {
            String error = "Ошибка: " + e.getMessage();
            System.out.println(error);
            logToFile("Арифметическое выражение", input, error);
        }
    }

    // Обработка сложного арифметического выражения
    private static void handleComplexExpression() {
        System.out.print("Введите значения a, b и c через пробел: ");
        String input = scanner.nextLine();
        try {
            String[] values = input.split(" ");
            double a = Double.parseDouble(values[0]);
            double b = Double.parseDouble(values[1]);
            double c = Double.parseDouble(values[2]);
            ComplexExpression expr = new ComplexExpression(a, b, c);
            String result = expr.calculateAll();
            System.out.println(result);
            logToFile("Сложное выражение", input, result);
        } catch (NumberFormatException e) {
            String error = "Ошибка: введите корректные числа.";
            System.out.println(error);
            logToFile("Сложное выражение", input, error);
        } catch (ArrayIndexOutOfBoundsException e) {
            String error = "Ошибка: введите три значения.";
            System.out.println(error);
            logToFile("Сложное выражение", input, error);
        } catch (Exception e) {
            String error = "Ошибка: " + e.getMessage();
            System.out.println(error);
            logToFile("Сложное выражение", input, error);
        }
    }

    // Проверка, является ли одна строка подстрокой другой
    private static void handleSubstringCheck() {
        System.out.print("Введите первую строку: ");
        String str1 = scanner.nextLine();
        System.out.print("Введите вторую строку: ");
        String str2 = scanner.nextLine();
        SubstringChecker checker = new SubstringChecker(str1);
        String result = "Первая строка является подстрокой второй: " + checker.isSubstring(str2);
        System.out.println(result);
        logToFile("Проверка подстроки", "Строка 1: " + str1 + ", Строка 2: " + str2, result);
    }

    // Метод для обработки подсчета нулей
    private static void handleZeroCount() {
        System.out.print("Введите число для подсчета нулей: ");
        String input = scanner.nextLine();
        try {
            ZeroCounter original = new ZeroCounter(input);
            ZeroCounter clone = new ZeroCounter(original);
            String resultOriginal = "Количество нулей в числе: " + original.countZeros();
            String resultClone = "Количество нулей в клоне: " + clone.countZeros();
            System.out.println(resultOriginal);
            System.out.println(resultClone);
            logToFile("Подсчёт нулей", input, resultOriginal + ", " + resultClone);
        } catch (IllegalArgumentException e) {
            String error = "Ошибка: " + e.getMessage();
            System.out.println(error);
            logToFile("Подсчёт нулей", input, error);
        }
    }

    // Метод для безопасного получения целого числа
    private static int getValidatedInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: введите корректное целое число: ");
            }
        }
    }

    // Метод для записи действий в файл
    private static void logToFile(String action, String input, String result) {
        System.out.print("Записать это действие в файл? (да/нет): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        if (choice.equals("да")) {
            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            String logEntry = String.format(
                    "[%s] Действие: %s | Входные данные: %s | Результат: %s%n",
                    timestamp, action, input, result
            );

            fileManager.writeToFile("log.txt", logEntry);
            System.out.println("Действие записано в файл.");
        }
    }
}
