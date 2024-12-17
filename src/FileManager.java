import java.io.*; // Импортируем классы для работы с файлами

class FileManager {

    // Метод записи текста в файл
    public void writeToFile(String filename, String data) {
        if (filename == null || filename.isEmpty()) {
            System.out.println("Ошибка: имя файла не может быть пустым.");
            return;
        }

        if (data == null) {
            System.out.println("Ошибка: данные для записи не могут быть null.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(data); // Записываем данные в файл
            writer.newLine(); // Переход на новую строку
            System.out.println("Данные успешно записаны в файл: " + filename); // Подтверждаем успешную запись
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage()); // Обрабатываем ошибку записи
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка при записи в файл: " + e.getMessage());
        }
    }

    // Метод чтения текста из файла
    public void readFromFile(String filename) {
        if (filename == null || filename.isEmpty()) {
            System.out.println("Ошибка: имя файла не может быть пустым.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("Содержимое файла: " + filename); // Выводим информацию о считываемом файле
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Выводим каждую строку на экран
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage()); // Обрабатываем ошибку, если файл не найден
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage()); // Обрабатываем ошибку чтения
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка при чтении файла: " + e.getMessage());
        }
    }

    // Новый метод для очистки файла
    public void clearFile(String filename) {
        if (filename == null || filename.isEmpty()) {
            System.out.println("Ошибка: имя файла не может быть пустым.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Открываем файл в режиме записи, но без дозаписи, что очищает файл
            System.out.println("Файл очищен: " + filename); // Подтверждаем, что файл был очищен
        } catch (IOException e) {
            System.out.println("Ошибка очистки файла: " + e.getMessage()); // Обрабатываем ошибку очистки
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка при очистке файла: " + e.getMessage());
        }
    }
}
