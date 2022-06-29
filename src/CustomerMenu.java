import java.util.Scanner;


public class CustomerMenu {

    public CustomerMenu(Account account) {
        String approval = "";
        do {
            if(approval.equals("")) {
                System.out.printf("Hello, '%s'! ", account.getOwnerFullName());
            }
            showMainMenu(account);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "a":
                    System.out.println("Your current balance is " + account.getBalance());
                    break;
                case "b":
                    withDrawIfAccountIsEligible(account, scanner);
                    break;
                case "c":
                    depositWhileCustomerApproves(account, scanner);
                    break;
                case "d":
                    System.out.println("Thank you for your visit!");
                    return;
                default:
                    System.out.println("You have to type: a, b, c or d!");
            }
            System.out.println();
            System.out.println("Do you want to make another operation?");
            approval = scanner.next();
        } while (approval.equalsIgnoreCase("yes"));
        System.out.println("Thank you for your visit!");
    }

    private void showMainMenu(Account account) {
        System.out.println("Please choose one of the below options(type a, b, c or d):");
        System.out.println("a) Show my balance");
        System.out.println("b) Withdraw");
        System.out.println("c) Deposit");
        System.out.println("d) Exit");
    }

    private void withDrawIfAccountIsEligible(Account account, Scanner scanner) {
        String approval = "";
        do {
            if (!account.isAccountEligibleForWithdraw()) {
                break;
            }
            System.out.println("Please type the amount you want to withdraw: ");
            int amount = scanner.nextInt();
            long initialBalance = account.getBalance();
            account.withdraw(amount);
            long afterWithDrawBalance = account.getBalance();
            while (initialBalance == afterWithDrawBalance) {
                System.out.println();
                System.out.println("Please type an amount greater than '0' and lower than your balance: " + afterWithDrawBalance);
                amount = scanner.nextInt();
                initialBalance = account.getBalance();
                account.withdraw(amount);
                afterWithDrawBalance = account.getBalance();
            }
            System.out.println("Do you want to make another withdraw?");
            approval = scanner.next();
        } while (approval.equalsIgnoreCase("yes"));
    }

    private void depositWhileCustomerApproves(Account account, Scanner scanner) {
        String approval = "";
        do {
            System.out.println("Please type the amount you want to deposit: ");
            int amount = scanner.nextInt();
            long initialBalance = account.getBalance();
            account.deposit(amount);
            long afterWithDrawBalance = account.getBalance();
            while (initialBalance == afterWithDrawBalance) {
                System.out.println();
                System.out.println("Please type an amount greater than '0' and lower than your limit to deposit: " + account.getLimitForDeposit());
                amount = scanner.nextInt();
                initialBalance = account.getBalance();
                account.deposit(amount);
                afterWithDrawBalance = account.getBalance();
            }
            System.out.println("Do you want to make another deposit?");
            approval = scanner.next();
        } while (approval.equalsIgnoreCase("yes"));
    }
}
