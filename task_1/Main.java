import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Lottery r = new Lottery();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("""
                    Главное меню:
                    1 - Добавьте новую игрушку в призовой фонд
                    2 - Измените частоту выпадения какой-нибудь игрушки
                    3 - Проведите розыгрыш и сохраните результаты
                    0 - выход
                    -\s""");
            var selection = sc.next();
            switch (selection) {
                case "1" -> r.addToy();
                case "2" -> r.setFrequency();
                case "3" -> r.raffle();
                case "0" -> {
                    System.out.println("Досвидание");
                    System.exit(0);
                }
                default -> System.out.println("Неправельный выбор. попробуте ещё раз");
            }
        }
    }
}