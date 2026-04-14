package fintech.driver;

import java.util.*;
import fintech.model.*;
/**
 * @author 12S24019 Winda N.V. Sitorus
 */
public class Driver1 {
    
    public static void main(String[] _args) {
        ArrayList<Account> accountArray = new ArrayList<Account>();
        ArrayList<Transaction> transactionArray = new ArrayList<Transaction>();
        Scanner scan = new Scanner(System.in);

        while(true){
            String input = scan.nextLine();

            if(input.equals("---")){
                break;
            }

            String[] tempCommand = input.split("#");
            if(tempCommand[0].equals("create-account")) {
                // Membuat akun
                String tempOwner = tempCommand[1];
                String tempAccountName = tempCommand[2];

                // Buat akun
                Account account = new Account(tempOwner, tempAccountName);
                System.out.println(account.toString());
                accountArray.add(account);
            } else if(tempCommand[0].equals("create-transaction")) {
                // Membuat transaksi
                String tempAccountName = tempCommand[1];
                double tempAmount = Double.parseDouble(tempCommand[2]);
                String tempPostedAt = tempCommand[3];
                String tempNote = tempCommand[4];

                for (Account tempAccount : accountArray) {
                    if (tempAccount != null && tempAccount.getAccountName().equals(tempAccountName)) {
                        double tempBal = tempAccount.getBalance() + tempAmount;
                        if(tempBal > 0){
                            Transaction transaction = new Transaction(tempAccount, tempAmount, tempPostedAt, tempNote);
                            transactionArray.add(transaction);
                            break;
                        }
                    }                    
                }

            } else if(tempCommand[0].equals("show-account")){
                // Menampilkan akun
                String tempAccountName = tempCommand[1];
                for (Account account : accountArray) {
                    if(account.getAccountName().equals(tempAccountName)){
                        System.out.println(account.toString());
                        break;
                    }
                }
            } else if(tempCommand[0].equals("remove-account")) {
                String tempAccountName = tempCommand[1];

                for (int i = 0; i < transactionArray.size(); i++) {
                    if (transactionArray.get(i).getAccount().getAccountName().equals(tempAccountName)) {
                        Transaction transaction = transactionArray.get(i);
                        transactionArray.remove(transaction);
                    }
                }
                for (Account account : accountArray){
                    if (account.getAccountName().equals(tempAccountName)) {
                        accountArray.remove(account);
                        break;
                    }
                }

            } else if(tempCommand[0].equals("show-accounts")) {
                for (Account account : accountArray) {
                    System.out.println(account.toString());
                }
            }
        }
        ArrayList<Transaction> transactionsSort = new ArrayList<Transaction>();

        // Membuat pekerjaan urutan
        for (int i = 0; i < transactionArray.size(); i++) {
            for (int j = i + 1; j < transactionArray.size(); j++){
                Transaction trac;
                String posted1 = transactionArray.get(i).getPostedAt();
                String posted2 = transactionArray.get(j).getPostedAt();
                // Memisahkan antara tanggah dan waktu
                String[] tempPost1 = posted1.split(" ");
                String[] tempPost2 = posted2.split(" ");
                
                // Pemecahan untuk format tgl-bln-tahun
                String[] tmpTgl1 = tempPost1[0].split("/");
                int Thn1 = Integer.parseInt(tmpTgl1[0]);
                int Bln1 = Integer.parseInt(tmpTgl1[1]);
                int Tgl1 = Integer.parseInt(tmpTgl1[2]);
                // Pemecahan untuk format waktu
                String[] waktu1 = tempPost1[1].split(":");
                int jam1 = Integer.parseInt(waktu1[0]);
                int menit1 = Integer.parseInt(waktu1[1]);
                int detik1 = Integer.parseInt(waktu1[2]);

                // Pemecahan untuk format tgl-bln-tahun
                String[] tmpTgl2 = tempPost2[0].split("/");
                int Thn2 = Integer.parseInt(tmpTgl2[0]);
                int Bln2 = Integer.parseInt(tmpTgl2[1]);
                int Tgl2 = Integer.parseInt(tmpTgl2[2]);
                // Pemecahan untuk format waktu
                String[] waktu2 = tempPost2[1].split(":");
                int jam2 = Integer.parseInt(waktu2[0]);
                int menit2 = Integer.parseInt(waktu2[1]);
                int detik2 = Integer.parseInt(waktu2[2]);

                // Disini kita akan membandingkan tgl bln thn
                if((Thn1 > Thn2) && (Bln1 > Bln2) && (Tgl1 > Tgl2)){
                    trac = transactionArray.get(i);
                    // transactionArray.get(i) = transactionArray.get(j);
                    // transactionArray.get(j) = trac;
                } else if ((Thn1 == Thn2) && (Bln1 == Bln2) && (Tgl1 == Tgl2)){
                    if ((jam1 < jam2) && (menit1 < menit2) && (detik1 < detik2));
                }

            }
        }



        for(Transaction transaction : transactionsSort){
            System.out.println(transaction.toString());
        }

        scan.close();
        
    }


}