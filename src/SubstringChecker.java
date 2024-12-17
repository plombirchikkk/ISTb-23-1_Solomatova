// Класс для проверки, является ли одна строка подстрокой другой
class SubstringChecker {
    private String str1; // Первая строка

    //Конструктор для установки первой строки.
    public SubstringChecker(String str1) {
        this.str1 = str1;
    }

    //Конструктор клонирования.
    public SubstringChecker(SubstringChecker other) {
        this.str1 = other.str1;
    }


    //Метод для проверки, является ли первая строка подстрокой второй.
    public boolean isSubstring(String str2) {
        int len1 = str1.length(); // Длина первой строки
        int len2 = str2.length(); // Длина второй строки

        // Перебираем возможные начальные позиции подстроки в str2
        for (int i = 0; i <= len2 - len1; i++) {
            int j;

            // Проверяем, совпадают ли символы str1 с подстрокой в str2
            for (j = 0; j < len1; j++) {
                if (str2.charAt(i + j) != str1.charAt(j)) {
                    break; // Если символы не совпадают, выходим из внутреннего цикла
                }
            }

            // Если все символы совпали, значит подстрока найдена
            if (j == len1) {
                return true;
            }
        }

        // Если перебрали все возможные позиции и не нашли совпадения
        return false;
    }
}
