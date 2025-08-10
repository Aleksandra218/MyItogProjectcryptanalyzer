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
    private FileManager fileManager;
    private static int randomKey;
    private String encryptedText;
    private static String origFilePathRead;
    private String decodingBrutForce;
    private String decodingText;
    private String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,:;?!() ";


    public Menu(CaesarCipher caesarCipher, SecureRandom secureRandom, FileManager fileManager, HacerBrutForce hacerBrutForce) {
        this.caesarCipher = caesarCipher;
        this.secureRandom = secureRandom;
        this.randomKey = secureRandom.nextInt(1, 43);
        this.fileManager = fileManager;
        this.hacerBrutForce = hacerBrutForce;
    }


    public String getOrigFilePathRead() {
        return origFilePathRead;
    }


    public void start() throws IOException {
        System.out.println("Добро пожаловать в криптоанализатор!\n");
        System.out.print("Введите путь к файлу для чтения: "); //путь без кавычек с обеих сторон иначе валидатор не примет
        fileManager.readLine(origFilePathRead = Validator.filePath());

        while (true) {
            outputMenu();
            int choice = Validator.choiceMenu();
            if (choice == 1) {
                encryptedText = caesarCipher.encrypt(fileManager.getTextFileRead(), randomKey, alphabet); //шифруем
                System.out.println("Файл успешно зашифрован, введите путь к файлу для записи. Пример: C:/files/text.txt");
                String pathWriteEncryptText = Validator.filePath();
                fileManager.writeLine(encryptedText, pathWriteEncryptText);
                System.out.println("Файл успешно записан!");
            } else if (choice == 2) {
                decodingText = caesarCipher.decrypt(encryptedText, randomKey, alphabet); //расшифровываем
                System.out.println("Файл успешно расшифрован, введите путь к файлу для записи. Пример: C:/files/text.txt");
                String pathWriteDecodText = Validator.filePath(); //путь к файлу для записи зашифрованного файла
                fileManager.writeLine(decodingText, pathWriteDecodText);
                System.out.println("Файл успешно записан!");
            } else if (choice == 3) {
                decodingBrutForce = hacerBrutForce.brutForce(encryptedText, fileManager.getTextFileRead(), alphabet); //метод брутфорс
                System.out.println("Введите путь к файлу для записи. Пример: C:/files/text.txt");
                String path = Validator.filePath();
                fileManager.writeLine(decodingBrutForce, path);
            } else if (choice == 4) {

            } else if (choice == 0) {
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
