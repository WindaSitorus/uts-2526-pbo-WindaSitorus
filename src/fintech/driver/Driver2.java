package fintech.driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import fintech.model.Account;

/**
 * @author 12S24019 Winda N.V Sitorus
 */
public class Driver2 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("---")) {
                break;
            }

            String[] parts = line.split("#");
            String command = parts[0];

            if (command.equals("create-account")) {
                accounts.add(new Account(parts[1], parts[2]));
            } else if (command.equals("deposit")) {
                String username = parts[1];
                double amount = Double.parseDouble(parts[2]);
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(username)) {
                        acc.deposit(amount);
                    }
                }
            } else if (command.equals("withdraw")) {
                String username = parts[1];
                double amount = Double.parseDouble(parts[2]);
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(username)) {
                        acc.withdraw(amount);
                    }
                }
            }
        }

        for (Account acc : accounts) {
            System.out.println(acc.getUsername() + "|" + acc.getName() + "|" + acc.getBalance());
        }
        sc.close();
    }
}