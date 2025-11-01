//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Composition {
    interface Payment{
        void pay(double amount);
    }

    static class CreditCard implements Payment{

        @Override
        public void pay(double amount) {
            System.out.println("Credit card account paying " + amount);
        }
    }

    static class DebitCard implements Payment{
        @Override
        public void pay(double amount) {
            System.out.println("Debit card account paying " + amount);
        }
    }

    static class PaymentProcessor {
        private final Payment payment;

        public PaymentProcessor(Payment payment) {
            this.payment = payment;
        }
        public void pay(double amount) {
            payment.pay(amount);
            System.out.println("Processed payment -> " + amount);
        }
    }


    public static void main(String[] args) {

        PaymentProcessor processor = new PaymentProcessor(new CreditCard());
        processor.pay(10);

        processor = new PaymentProcessor(new DebitCard());
        processor.pay(20);
    }
}

/**
 * S - Class should have only 1 reason to change - meaning 1 responsibility
 * O - Software entities should be open for extension and closed for modification
 * L - Sub Types should be subtitutable for base types and should not change the correctness of the program
 * I - Classes should not be forced to implement the interfaces that it does not use
 * D - High level modules should not depend on low level modules. Rather it should depend on abstractions.
 *     Abstraction should not depend upon details. Details should depend up on abstractions.
 */