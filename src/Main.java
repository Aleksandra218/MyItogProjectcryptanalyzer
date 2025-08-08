import core.CaesarCipher;
import core.FileManager;
import core.HacerBrutForce;
import ui.Menu;

import java.io.IOException;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) throws IOException {
        SecureRandom secureRandom = new SecureRandom();
        FileManager fileManager = new FileManager();

        CaesarCipher caesarCipher = new CaesarCipher();
        HacerBrutForce hacerBrutForce = new HacerBrutForce();
        Menu menu = new Menu(caesarCipher, secureRandom, fileManager, hacerBrutForce);
        menu.start();
    }
}