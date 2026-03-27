package fintech.driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import fintech.model.*;

/**
 * @author 12S24019 Winda N.V Sitorus
 */
public class Driver4 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
        // Gunakan list String untuk menyimpan riwayat yang sudah diformat agar bisa dicetak
        List<String> transactionHistory = new ArrayList<>();
        int transactionIdCounter = 1;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("---")) {
                break;
            }

            String[] parts = line.split("#");
            String command = parts[0];

            try {
                if (command.equals("create-account")) {
                    accounts.add(new Account(parts[1], parts[2]));
                } else if (command.equals("deposit")) {
                    String user = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String time = parts[3];
                    String desc = parts[4];
                    
                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(user)) {
                            acc.deposit(amount);
                            // Simpan format: id|type|amount|timestamp|description
                            transactionHistory.add(user + "#" + transactionIdCounter++ + "|deposit|" + amount + "|" + time + "|" + desc);
                        }
                    }
                } else if (command.equals("withdraw")) {
                    String user = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String time = parts[3];
                    String desc = parts[4];
                    
                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(user)) {
                            if (acc.withdraw(amount)) {
                                // Jika withdraw berhasil, simpan dengan amount NEGATIF sesuai permintaan Task 04
                                transactionHistory.add(user + "#" + transactionIdCounter++ + "|withdraw|-" + amount + "|" + time + "|" + desc);
                            } else {
                                // Task 04: Bangkitkan exception jika saldo tidak cukup
                                throw new NegativeBalanceException("Insufficient balance");
                            }
                        }
                    }
                } else if (command.equals("show-account")) {
                    String targetUser = parts[1];
                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(targetUser)) {
                            // Cetak info akun
                            System.out.println(acc.getUsername() + "|" + acc.getName() + "|" + acc.getBalance());
                            
                            // Cetak riwayat transaksi milik user ini
                            for (String history : transactionHistory) {
                                if (history.startsWith(targetUser + "#")) {
                                    // Hilangkan prefix username sebelum dicetak
                                    System.out.println(history.substring(history.indexOf("#") + 1));
                                }
                            }
                        }
                    }
                }
            } catch (NegativeBalanceException e) {
                // Program tidak berhenti, lanjut ke input berikutnya
            }
        }
        sc.close();
    }
}
