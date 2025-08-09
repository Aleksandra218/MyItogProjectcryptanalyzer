package ui;

import core.CaesarCipher;
import core.FileManager;
import core.HacerBrutForce;
import util.Validator;

import java.io.IOException;
import java.security.SecureRandom;

public class Menu {
    private static HacerBrutForce hacerBrutForce;

    private static CaesarCipher caesarCipher;
    private SecureRandom secureRandom;
    private static FileManager fileManager;
    private static int randomKey;
    private static String encryptedText;
    private static String origFilePathRead;
    private static String decodingBrutForce;
    private static String decodingText;
    private static String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,:;?!() ";


    public Menu(CaesarCipher caesarCipher, SecureRandom secureRandom, FileManager fileManager, HacerBrutForce hacerBrutForce) {
        this.caesarCipher = caesarCipher;
        this.secureRandom = secureRandom;
        this.randomKey = secureRandom.nextInt(1, 43);
        this.fileManager = fileManager;
        this.hacerBrutForce = hacerBrutForce;
    }


    public static String getOrigFilePathRead() {
        return origFilePathRead;
    }

    public static void start() throws IOException {
        System.out.println("Добро пожаловать в криптоанализатор!\n");
        System.out.print("Введите путь к файлу для чтения: "); //без кавычек с обеих сторон иначе не примет валидатор
        fileManager.readLine(origFilePathRead = Validator.filePath());

        while (true) {
            outputMenu();
            int choice = Validator.choiceMenu();
            if (choice == 1) {
                encryptedText = caesarCipher.encrypt(FileManager.getTextFileRead(), randomKey, alphabet);
                System.out.println("Файл успешно зашифрован, введите путь к файлу для записи. Пример: C:/files/text.txt");
                String pathWriteEncryptText = Validator.filePath();
                fileManager.writeLine(encryptedText, pathWriteEncryptText);
                System.out.println("Файл успешно записан!");
            } else if (choice == 2) {
                decodingText = caesarCipher.decrypt(encryptedText, randomKey, alphabet);
                System.out.println("Файл успешно расшифрован, введите путь к файлу для записи. Пример: C:/files/text.txt");
                String pathWriteDecodText = Validator.filePath();
                fileManager.writeLine(decodingText, pathWriteDecodText);
                System.out.println("Файл успешно записан!");
            } else if (choice == 3) {
                decodingBrutForce = hacerBrutForce.brutForce(encryptedText, FileManager.getTextFileRead(), alphabet);
                System.out.println("Введите путь к файлу для записи. Пример: C:/files/text.txt");
                String path = Validator.filePath();
                fileManager.writeLine(decodingBrutForce, path);
            }  else if (choice == 0) {
                System.out.println("Программа завершена! Всего доброго!");
                return;
            }
        }
    }

    private static void outputMenu() {
        System.out.print("Меню\n" +
                "1. Шифрование\n" +
                "2. Расшифровка ключом\n" +
                "3. Brute force\n" +
                "0. Выход\n");
    }
}
