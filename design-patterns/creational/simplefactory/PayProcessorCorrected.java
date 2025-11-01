package creational.simplefactory;

import java.util.Optional;

public class PayProcessorCorrected {


    // Step 1️⃣: Define Strategy Interface
    interface PaymentStrategy {
        void pay(double amount);
    }

    // Step 2️⃣: Concrete Implementations
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

    // Step 3️⃣: Use Enum for Payment Types (Optional but recommended)
    enum PaymentType {
        CREDIT_CARD,
        UPI,
        PAYPAL
    }

    // Step 4️⃣: Factory Class
    static class PaymentProcessorFactory {

        public static Optional<PaymentStrategy> createPaymentStrategy(PaymentType type) {
            return switch (type) {
                case CREDIT_CARD -> Optional.of(new CreditCardPayment());
                case UPI -> Optional.of(new UPIPayment());
                case PAYPAL -> Optional.of(new PaypalPayment());
            };
        }
    }

    // Step 5️⃣: Client
    public static void main(String[] args) {
        PaymentStrategy creditCard = PaymentProcessorFactory.createPaymentStrategy(PaymentType.CREDIT_CARD)
                .orElseThrow();
        creditCard.pay(500);

        PaymentStrategy upi = PaymentProcessorFactory.createPaymentStrategy(PaymentType.UPI)
                .orElseThrow();
        upi.pay(300);

        PaymentStrategy paypal = PaymentProcessorFactory.createPaymentStrategy(PaymentType.PAYPAL)
                .orElseThrow();
        paypal.pay(700);
    }
}
