package fintech.driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import fintech.model.Account;

/**
 * @author 12S24019 Winda N.V Sitorus
 */
public class Driver3 {

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
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(parts[1])) acc.deposit(Double.parseDouble(parts[2]));
                }
            } else if (command.equals("transfer")) {
                String senderU = parts[1];
                String receiverU = parts[2];
                double amount = Double.parseDouble(parts[3]);
                Account sender = null, receiver = null;

                for (Account acc : accounts) {
                    if (acc.getUsername().equals(senderU)) sender = acc;
                    if (acc.getUsername().equals(receiverU)) receiver = acc;
                }

                if (sender != null && receiver != null && sender.withdraw(amount)) {
                    receiver.deposit(amount);
                }
            }
        }

        for (Account acc : accounts) {
            System.out.println(acc.getUsername() + "|" + acc.getName() + "|" + acc.getBalance());
        }
        sc.close();
    }
}