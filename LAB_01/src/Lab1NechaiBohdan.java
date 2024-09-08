import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lab1NechaiBohdan {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int length;
        char fill;
        File dataFile = new File("Lab1.txt");
        PrintWriter fout = new PrintWriter(dataFile);

        System.out.print("Введіть розмір квадратної матриці: ");
        length = scan.nextInt();
        System.out.print("Введіть символ: ");
        scan.nextLine(); // Очищаємо буфер
        fill = scan.next().charAt(0);

        // Масив не містить порожніх елементів
        char[][] array = new char[length][length];

        // Виведення візерунку з матриці

            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {

                    // Логіка виведення символів:
                    if (i == 0 || i == length - 1 || j == 0 || j == length - 1 ||
                            (i > 1 && i < length - 2 && j > 1 && j < length - 2)) {
                        array[i][j] = fill;
                        System.out.print(array[i][j] + " ");
                        fout.print(array[i][j] + " ");

                    } else {
                        System.out.print("  ");
                        fout.print("  ");// Пропуск місця для відступу
                    }
                }
                System.out.println();
                fout.print("\n");
            }
            fout.flush();
            fout.close();


    }
}
