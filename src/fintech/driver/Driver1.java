package fintech.driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import fintech.model.Account;

/**
 * @author 12S24019 Winda N.V Sitorus
 */
public class Driver1 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("---")) {
                break;
            }

            String[] parts = line.split("#");
            if (parts[0].equals("create-account")) {
                String name = parts[1];
                String username = parts[2];
                accounts.add(new Account(name, username));
            }
        }

        for (Account acc : accounts) {
            System.out.println(acc.getUsername() + "|" + acc.getName() + "|" + acc.getBalance());
        }
        sc.close();
    }
}
