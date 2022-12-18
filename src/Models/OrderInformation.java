package Models;

public class OrderInformation {
    private final String paymentMethod;
    private final String userName;
    private double totalPrice;

    public OrderInformation(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        userName = AccountsModel.authorizedUserName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public String getUserName() {
        return userName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "User: " + userName + "\nPayment method: " + paymentMethod + "\nTotal price: " + totalPrice;
    }
}
