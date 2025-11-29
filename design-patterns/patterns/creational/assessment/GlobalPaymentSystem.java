package patterns.creational.assessment;

public class GlobalPaymentSystem {

    // ----------------- 1️⃣ Singleton Logger -----------------
    static class Logger {
        private Logger() {}
        private static class Holder {
            private static final Logger INSTANCE = new Logger();
        }
        public static Logger getInstance() {
            return Holder.INSTANCE;
        }
        public void log(String msg) {
            System.out.println("[LOG] " + msg);
        }
    }

    // ----------------- 2️⃣ Builder for Payment Request -----------------
    static class PaymentRequest {
        private final double amount;
        private final String currency;
        private final String region;

        private PaymentRequest(Builder builder) {
            this.amount = builder.amount;
            this.currency = builder.currency;
            this.region = builder.region;
        }

        public double getAmount() { return amount; }
        public String getCurrency() { return currency; }
        public String getRegion() { return region; }

        @Override
        public String toString() {
            return "PaymentRequest [region=" + region + ", amount=" + amount + ", currency=" + currency + "]";
        }

        public static class Builder {
            private double amount;
            private String currency;
            private String region;

            public Builder amount(double amount) { this.amount = amount; return this; }
            public Builder currency(String currency) { this.currency = currency; return this; }
            public Builder region(String region) { this.region = region; return this; }

            public PaymentRequest build() {
                return new PaymentRequest(this);
            }
        }
    }

    // ----------------- 3️⃣ Payment Abstraction -----------------
    interface Payment {
        void pay(PaymentRequest request);
    }

    static class CreditCardPayment implements Payment {
        public void pay(PaymentRequest request) {
            Logger.getInstance().log("Paid " + request.getCurrency() + request.getAmount() + " using Credit Card");
        }
    }

    static class UPIPayment implements Payment {
        public void pay(PaymentRequest request) {
            Logger.getInstance().log("Paid " + request.getCurrency() + request.getAmount() + " using UPI");
        }
    }

    static class PaypalPayment implements Payment {
        public void pay(PaymentRequest request) {
            Logger.getInstance().log("Paid " + request.getCurrency() + request.getAmount() + " via PayPal");
        }
    }

    // ----------------- 4️⃣ Abstract Factory -----------------
    interface RegionalPaymentFactory {
        Payment createLocalPayment();    // e.g., UPI for India, PayPal for USA
        Payment createCardPayment();     // e.g., RuPay for India, Visa for USA
    }

    // ----------------- 5️⃣ Concrete Factories -----------------
    static class IndiaPaymentFactory implements RegionalPaymentFactory {
        public Payment createLocalPayment() { return new UPIPayment(); }
        public Payment createCardPayment() { return new CreditCardPayment(); }
    }

    static class USAPaymentFactory implements RegionalPaymentFactory {
        public Payment createLocalPayment() { return new PaypalPayment(); }
        public Payment createCardPayment() { return new CreditCardPayment(); }
    }

    // ----------------- 6️⃣ Client (uses Abstract Factory) -----------------
    public static void main(String[] args) {
        PaymentRequest indiaRequest = new PaymentRequest.Builder()
                .region("India").currency("INR").amount(5000).build();

        PaymentRequest usaRequest = new PaymentRequest.Builder()
                .region("USA").currency("USD").amount(200).build();

        RegionalPaymentFactory indiaFactory = new IndiaPaymentFactory();
        RegionalPaymentFactory usaFactory = new USAPaymentFactory();

        // Local Payments
        indiaFactory.createLocalPayment().pay(indiaRequest);
        usaFactory.createLocalPayment().pay(usaRequest);

        // Card Payments
        indiaFactory.createCardPayment().pay(indiaRequest);
        usaFactory.createCardPayment().pay(usaRequest);
    }
}
