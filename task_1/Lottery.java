import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Lottery {

    private static ArrayList<Bauble> toys = new ArrayList<>();
    private static PriorityQueue<Bauble> prizes = new PriorityQueue<>();

    private static int idCounter = 0;

    public void addToy() {
        Scanner scan = new Scanner(System.in);
        String title;
        int frequency;
        while (true) {
            System.out.print("Введите название на латинец: ");
            title = scan.nextLine();
            if (title.isEmpty()) {
                System.out.println("Неправельный ввод. попрбуте ещё раз");
                break;
            }
            System.out.print("Введите чистату отсева: ");
            String value = scan.nextLine();
            if (isDigit(value)) {
                frequency = Integer.parseInt(value);
                if (frequency <= 0) {
                    System.out.println("Неправельный ввод. Попробуйте ещё раз");
                } else {
                    Bauble toy = new Bauble(idCounter, title, frequency);
                    if (!toys.contains(toy) || toys.size() == 0) {
                        idCounter++;
                        toys.add(toy);
                        System.out.println("Была добавлена новая игрушка");
                    } else {
                        System.out.println("Эта игрушка уже есть в призовом фонде.");
                    }
                }
            } else {
                System.out.println("Неправельный ввод. Попробуйте ещё раз.");
            }
            break;
        }
    }

    public void setFrequency() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите ID игрушки: ");
        String value = scan.nextLine();
        if (isDigit(value)) {
            int selectedId = Integer.parseInt(value);
            if (selectedId >= 0 && selectedId < toys.size()) {
                System.out.println("Игрушка " + toys.get(selectedId).getToyTitle() +
                        "Имеет частоту побед " + toys.get(selectedId).getToyVictoryFrequency());
                System.out.print("Введите новую частоту отсева: ");
                value = scan.nextLine();
                if (isDigit(value)) {
                    int newFrequency = Integer.parseInt(value);
                    toys.get(selectedId).setToyVictoryFrequency(newFrequency);
                    System.out.println("Частота была изменена.");
                } else {
                    System.out.println("Неправельный ввод. Попробуйте ещё раз.");
                }
            } else {
                System.out.println("В призовом фонде нет игрушки с таким ID.");
            }
        } else {
            System.out.println("Неправельный ввод. Попробуйте ещё раз.");
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Bauble getPrize() {
        if (prizes.size() == 0) {
            Random rnd = new Random();
            for (Bauble toy : toys) {
                for (int i = 0; i < toy.getToyVictoryFrequency(); i++) {
                    Bauble temp = new Bauble(toy.getToyId(), toy.getToyTitle(), rnd.nextInt(1, 10));
                    prizes.add(temp);
                }
            }
        }
        return prizes.poll();
    }

    public void raffle() {
        if (toys.size() >= 2) {
            Bauble prize = getPrize();
            System.out.println("Приз: " + prize.getToyTitle());
            saveResult(prize.getInfo());
        } else {
            System.out.println("Вы должны дабавить в призовой фонд как минимум две игрушки.");
        }
    }

    private void saveResult(String text) {
        File file = new File("List.txt");
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (FileWriter fw = new FileWriter("List.txt", true)) {
            fw.write(text + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}