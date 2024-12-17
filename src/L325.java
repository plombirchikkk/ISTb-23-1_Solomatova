public class L325 {
    public static void main(String[] args) {
        try {
            MenuHandler.run(); // Пытаемся запустить меню
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace(); // Выводим информацию об ошибке в консоль
        }
    }
}
