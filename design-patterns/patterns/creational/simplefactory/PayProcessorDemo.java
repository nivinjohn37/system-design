package patterns.creational.simplefactory;


import java.util.Optional;

public class PayProcessorDemo {
    interface PaymentStrategy {
        void pay(double amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {}
    }

    static class UPIPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {}
    }

    static class PaypalPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {}
    }

    static class PaymentProcessorFactory {

        public static Optional<PaymentStrategy> createPaymentStrategy(String paymentType) {
            if(paymentType.isEmpty()){
                throw new IllegalArgumentException("Illegal payment type");
            }else{
                switch (paymentType) {
                    case "CreditCardPayment" -> {
                        return Optional.of(new CreditCardPayment());
                    }
                    case "UPIPayment" -> {
                        return Optional.of(new UPIPayment());
                    }
                    case "PaypalPayment" -> {
                        return Optional.of(new PaypalPayment());
                    }
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        PaymentStrategy creditCardPayment = PaymentProcessorFactory.createPaymentStrategy("CreditCardPayment").get();
        creditCardPayment.pay(500);

        PaymentStrategy upiPayemnt = PaymentProcessorFactory.createPaymentStrategy("UPIPayment").get();
        upiPayemnt.pay(500);

        PaymentStrategy paypalPayment = PaymentProcessorFactory.createPaymentStrategy("PaypalPayment").get();
        paypalPayment.pay(700);


    }


}
