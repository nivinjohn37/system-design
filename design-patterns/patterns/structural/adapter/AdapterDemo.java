package patterns.structural.adapter;

interface PaymentProcessor {
    void pay(int amount);
}

// Adaptee: incompatible class
class RazorpayAPI {
    public void makePaymentUsingRazor(double rupees) {
        System.out.println("Razorpay processed: ₹" + rupees);
    }
}

// Adapter: converts our interface to Razorpay API
class RazorpayAdapter implements PaymentProcessor {

    private final RazorpayAPI api = new RazorpayAPI();

    @Override
    public void pay(int amount) {
        api.makePaymentUsingRazor(amount);  // adapting call
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new RazorpayAdapter();
        processor.pay(500);
    }
}
