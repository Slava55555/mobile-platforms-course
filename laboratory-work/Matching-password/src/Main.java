package src;

import java.util.Objects;
import java.util.zip.CRC32;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/*
Author: Buchnev Vyacheslav 02421
tg: @SlavaBuchnev
project: https://github.com/Slava55555/mobile-platforms-course
*/

public class Main {

    public static void main(String[] args) {
        try {
            long targetHash = 0x0BA02B6E1L;
            List<String> words = loadWords();
            String foundPassword = findPassword(targetHash, words);
            System.out.println(Objects.requireNonNullElse(foundPassword, "Password not found"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> loadWords() throws Exception { // Загрузка словаря из GitHub
        List<String> words = new ArrayList<>();
        URL url = new URL("https://raw.githubusercontent.com/danielmiessler/SecLists/master/Passwords/Common-Credentials/10k-most-common.txt");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        }
        return words;
    }

    private static long calculateCRC32(String input) { // Вычисление CRC32
        CRC32 crc32 = new CRC32();
        crc32.update(input.getBytes());
        return crc32.getValue();
    }

    private static String findPassword(long targetHash, List<String> words) { // Поиск пароля
        for (String word : words) {
            for (int number = 1; number <= 9999; number++) {
                String password = word + number;
                if (calculateCRC32(password) == targetHash) {
                    return password;
                }
            }
        }
        return null;
    }
}
