import java.util.*;
/*
Author: Buchnev Vyacheslav 02421
tg: @SlavaBuchnev
project: https://github.com/Slava55555/mobile-platforms-course
*/

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Map<String, Integer>> customers = new TreeMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }

            String[] parts = line.split(" ");
            if (parts.length < 3) {
                continue;
            }

            String customer = parts[0];
            String product = parts[1];
            int quantity;

            try {
                quantity = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                continue;
            }

            customers.putIfAbsent(customer, new TreeMap<>());
            Map<String, Integer> products = customers.get(customer);
            products.put(product, products.getOrDefault(product, 0) + quantity);
        }

        for (Map.Entry<String, Map<String, Integer>> customerEntry : customers.entrySet()) {
            System.out.println(customerEntry.getKey() + ":");
            Map<String, Integer> products = customerEntry.getValue();

            for (Map.Entry<String, Integer> productEntry : products.entrySet()) {
                System.out.println(productEntry.getKey() + " " + productEntry.getValue());
            }
        }

        scanner.close();
    }
}