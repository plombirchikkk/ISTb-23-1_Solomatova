class ComplexExpression {
    private double a; // Значение переменной a
    private double b; // Значение переменной b
    private double c; // Значение переменной c

    // Конструктор, инициализирующий значения a, b и c.
    public ComplexExpression(double a, double b, double c) {
        if (Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c)) {
            throw new IllegalArgumentException("Значения переменных не могут быть NaN.");
        }
        if (Double.isInfinite(a) || Double.isInfinite(b) || Double.isInfinite(c)) {
            throw new IllegalArgumentException("Значения переменных не могут быть бесконечностью.");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Конструктор клонирования
    public ComplexExpression(ComplexExpression original) {
        if (original == null) {
            throw new IllegalArgumentException("Исходный объект не может быть null.");
        }
        this.a = original.a;
        this.b = original.b;
        this.c = original.c;
    }

    // Метод для вычисления и вывода всех возможных сложных выражений
    public String calculateAll() {
        String result = ""; // Начальная пустая строка

        try {
            result += "a * (b + c) = " + (a * (b + c)) + "\n";
            result += "a * (b - c) = " + (a * (b - c)) + "\n";
            result += "(a + b) * c = " + ((a + b) * c) + "\n";
            result += "(a - b) * c = " + ((a - b) * c) + "\n";

            // Проверка деления на ноль для выражений с делением
            if (b + c != 0) {
                result += "a / (b + c) = " + (a / (b + c)) + "\n";
            } else {
                result += "a / (b + c): Деление на ноль!\n";
            }

            if (b - c != 0) {
                result += "a / (b - c) = " + (a / (b - c)) + "\n";
            } else {
                result += "a / (b - c): Деление на ноль!\n";
            }

            if (c != 0) {
                result += "(a + b) / c = " + ((a + b) / c) + "\n";
                result += "(a - b) / c = " + ((a - b) / c) + "\n";
            } else {
                result += "(a + b) / c: Деление на ноль!\n";
                result += "(a - b) / c: Деление на ноль!\n";
            }
        } catch (ArithmeticException e) {
            result += "Ошибка арифметической операции: " + e.getMessage() + "\n";
        }

        // Проверка на NaN или бесконечность в результатах
        if (result.contains("NaN") || result.contains("Infinity")) {
            throw new ArithmeticException("Невозможная арифметическая операция: результат содержит NaN или бесконечность.");
        }

        return result; // Возвращаем результат как строку
    }
}
