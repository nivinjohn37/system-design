package creational.assessment;

public class PaymentSystem {
    static class SingletonLogger {
        private SingletonLogger() {}

        public static class SingletonLoggerHolder {
            private static final SingletonLogger instance = new SingletonLogger();

        }

        public static SingletonLogger getInstance() {
            return SingletonLoggerHolder.instance;
        }

        public void log(String message) {
            System.out.println(message);
        }
    }
    static class PaymentRequest {
        private final double amount;
        private final String currency;

        private PaymentRequest(Builder builder) {
            this.amount = builder.amount;
            this.currency = builder.currency;
        }

        public double getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }

        public String toString() {
            return "PaymentRequest [amount=" + amount + ", currency=" + currency + "]";
        }

        private static class Builder {
            private double amount;
            private String currency;

            public Builder amount(double amount) {
                this.amount = amount;
                return this;
            }

            public Builder currency(String currency) {
                this.currency = currency;
                return this;
            }

            public PaymentRequest build() {
                return new PaymentRequest(this);
            }
        }
    }

    interface Payment {
        void pay(PaymentRequest paymentRequest);
    }

    static class UPIPayment implements Payment {
        @Override
        public void pay(PaymentRequest paymentRequest) {
            SingletonLogger.getInstance().log("Paid " + paymentRequest.getCurrency() + paymentRequest.getAmount() + " using UPI");
        }
    }

    static class CreditCardPayment implements Payment {
        @Override
        public void pay(PaymentRequest paymentRequest) {
            SingletonLogger.getInstance().log("Paid " + paymentRequest.getCurrency() + paymentRequest.getAmount() + " using CreditCard");
        }
    }

    abstract static class PaymentFactory {
        abstract Payment createPayment();

        public void doPayment(PaymentRequest paymentRequest) {
            Payment payment = createPayment();
            payment.pay(paymentRequest);
        }
    }

    static class UPIPaymentFactory extends PaymentFactory {
        @Override
        Payment createPayment() {
            return new UPIPayment();
        }
    }


    static class CreditCardPaymentFactory extends PaymentFactory {
        @Override
        Payment createPayment() {
            return new CreditCardPayment();
        }
    }

    public static void main(String[] args) {
        PaymentFactory paymentFactory = new UPIPaymentFactory();
        PaymentRequest paymentRequest = new PaymentRequest.Builder().currency("USD").amount(10000).build();
        paymentFactory.doPayment(paymentRequest);

    }
}
