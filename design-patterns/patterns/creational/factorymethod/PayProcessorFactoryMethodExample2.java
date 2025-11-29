package patterns.creational.factorymethod;


public class PayProcessorFactoryMethodExample2 {
    interface PaymentStrategy {
        void pay(double amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {
            System.out.println("Paid ₹" + amount + " using Credit Card");
        }
    }

    static class UPIPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {
            System.out.println("Paid ₹" + amount + " using UPI");
        }
    }

    static class PaypalPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {
            System.out.println("Paid ₹" + amount + " using PayPal");
        }
    }

    abstract static class PaymentFactory{

        abstract PaymentStrategy createPayment();

        public void doPay(double amount) {
            PaymentStrategy paymentStrategy = createPayment();
            paymentStrategy.pay(amount);
        }
    }

    static class CreditCardPaymentFactory extends PaymentFactory{

        PaymentStrategy createPayment() {
            return new CreditCardPayment();
        }
    }

    static class UPIPaymentFactory extends PaymentFactory{
        PaymentStrategy createPayment() {
            return new UPIPayment();
        }
    }

    static class PaypalPaymentFactory extends PaymentFactory{
        PaymentStrategy createPayment() {
            return new PaypalPayment();
        }
    }

    public static void main(String[] args) {
        PaymentFactory factory = new CreditCardPaymentFactory();
        factory.doPay(100.50);
    }
}
