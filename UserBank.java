package codsoft;
class UserBank {

    double balance = 1000;

    public void addMoney(double money) {

        balance = balance + money;
    }

    public boolean takeMoney(double money) {

        if (money > balance)
        {
            return false;
        }

        balance = balance - money;

        return true;
    }

    public double getBalance() {

        return balance;
    }
}