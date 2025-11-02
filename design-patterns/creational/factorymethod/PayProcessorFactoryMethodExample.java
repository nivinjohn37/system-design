package creational.factorymethod;

public class PayProcessorFactoryMethodExample {
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

    // PaymentFactory no longer implements PaymentStrategy
    abstract static class PaymentFactory {

        // Abstract method to be implemented by subclasses
        abstract PaymentStrategy createPayment();

        // A template method that uses the created product
        public void doPay(double amount) {
            PaymentStrategy paymentStrategy = createPayment();
            paymentStrategy.pay(amount);
        }
    }

    static class CreditCardPaymentFactory extends PaymentFactory {
        @Override
        PaymentStrategy createPayment() {
            return new CreditCardPayment();
        }
    }
    
    // Example of how to use it
    public static void main(String[] args) {
        PaymentFactory factory = new CreditCardPaymentFactory();
        factory.doPay(100.50);
    }
}
