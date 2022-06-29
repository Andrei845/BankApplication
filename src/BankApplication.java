import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApplication {

    private static final List<Account> accountList = new ArrayList<>();

    public void registerCustomer() {
        System.out.println("Started register process! Please enter your full name:");
        Scanner scanner = new Scanner(System.in);
        String fullName = scanner.nextLine();
        System.out.println("Started register process! Please enter your cnp:");
        String cnp = scanner.nextLine();
        Account account = new Account(fullName, cnp);
        accountList.add(account);
    }

    public Account getAccountForUserWithCnp(String cnp) {
        return accountList
                .stream()
                .filter(account->account.getCnp().equals(cnp))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("There is no account with cnp: "+cnp));
    }

    public static void main(String[] args) throws ParseException {

//        BankApplication bankApplication = new BankApplication();
//        bankApplication.registerCustomer();
//        System.out.println(bankApplication.getAccountForUserWithCnp("1950517225911"));
        Account account = new Account("Andrei Ungureanu", "1950517225911");
        new CustomerMenu(account);
    }
}
