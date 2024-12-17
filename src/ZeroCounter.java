class ZeroCounter {
    private String number; // Строка, представляющая число

    // Конструктор по умолчанию
    public ZeroCounter() {
        this.number = "0"; // Инициализация значением по умолчанию
    }

    // Конструктор с параметром
    public ZeroCounter(String number) {
        setNumber(number); // Установка числа через метод
    }

    // Конструктор клонирования
    public ZeroCounter(ZeroCounter other) {
        if (other == null) { // Проверка на null
            throw new IllegalArgumentException("Переданный объект не может быть null.");
        }
        this.number = other.number; // Копирование значения number
    }

    // Метод для установки числа
    private void setNumber(String number) {
        if (number == null) { // Проверка на null
            throw new IllegalArgumentException("Значение не может быть null.");
        }
        if (number.isEmpty()) { // Проверка на пустую строку
            throw new IllegalArgumentException("Значение не может быть пустой строкой.");
        }
        if (!number.matches("\\d*")) { // Проверка, что строка содержит только цифры
            throw new IllegalArgumentException("Значение должно содержать только цифры.");
        }
        this.number = number; // Установка значения
    }

    // Метод для подсчета количества нулей в числе
    public int countZeros() {
        int count = 0; // Счетчик нулей
        for (char c : number.toCharArray()) { // Перебор символов строки
            if (c == '0') {
                count++; // Увеличение счетчика, если символ равен '0'
            }
        }
        return count; // Возвращение количества нулей
    }

    // Метод для получения текущего числа
    public String getNumber() {
        return number;
    }
}