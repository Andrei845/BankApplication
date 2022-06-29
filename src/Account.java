public class Account {
    private long idForAccount;
    private String cnp;
    private String ownerFullName;
    private long balance;
    private int limitForDeposit;

    public Account(String ownerFullName, String cnp) {
        this.idForAccount = System.currentTimeMillis();
        this.cnp = cnp;
        this.ownerFullName = ownerFullName;
        this.balance = 500;
        this.limitForDeposit = 5000;
    }

    public long getBalance() {
        return balance;
    }

    public String getCnp() {
        return cnp;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public int getLimitForDeposit() {
        return limitForDeposit;
    }

    public void setLimitForDeposit(int limit) {
        this.limitForDeposit = limit;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        } else if (amount < 0) {
            System.out.printf("You can not deposit '%s', the amount to deposit has to be higher than '0'!", amount);
        } else if (amount > limitForDeposit) {
            System.out.printf("You can not deposit '%s', the max amount to deposit on your account is '%s', please set it higher!", amount, limitForDeposit);
        }
    }

    public void withdraw(int amount) {
        if (!isAccountEligibleForWithdraw()) {
            System.out.printf("Your balance is '%s', it has to be 10 or more in order to withdraw!", balance);
        } else if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            if (amount < 0) {
                System.out.printf("You can not withdraw '%s', the amount to withdraw has to be higher than '0'!", amount);
            } else {
                System.out.printf("Insufficient founds! Available balance: '%s'! ", balance);
            }
        }
    }

    public boolean isAccountEligibleForWithdraw() {
        if (balance == 0 || balance < 10) {
            System.out.printf("Your account is not eligible for withdraw. Your balance '%s' is less than 10. Please make a deposit first!", balance);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account with id: [" + this.idForAccount + "] belongs to '" + this.ownerFullName + "' and has balance: " + balance;
    }
}
