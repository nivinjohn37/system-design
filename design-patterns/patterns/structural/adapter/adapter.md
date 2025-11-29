Great — we have now completed **all Creational Patterns**, so we officially enter the next major chapter:

# 🏛️ **SECTION 6 — STRUCTURAL DESIGN PATTERNS**

Structural patterns help you **organize classes and objects** into larger, more efficient, more flexible structures.

To begin, here is the visual overview of the Structural Patterns we will learn:

---

# 📘 **STRUCTURAL DESIGN PATTERNS — Overview & Objectives**

Structural patterns focus on the **composition**, **wrapping**, and **relationships** between objects to make systems more flexible and easier to maintain.

### 🎯 **What you will learn in this section**

You will understand for each pattern:

* Why we need it
* UML intuition
* Runnable Java examples
* Real-world usage
* Interview-ready explanations
* Hands-on exercises
* Common pitfalls

---

# 📚 **Structural Patterns List**

We will cover these **7 patterns** one by one:

1. **Adapter**
2. **Decorator**
3. **Composite**
4. **Proxy**
5. **Facade**
6. **Bridge**
7. **Flyweight**

---

# 🚀 SECTION START: **6.1 Adapter Pattern**

This is the **first** and **easiest** structural pattern — perfect for starting this section.

---

# 🎯 **Adapter Pattern — Intent**

> Convert the interface of one class into another interface clients expect.
>
> Helps incompatible classes work together.

**Simple analogy:**
A **mobile charger adapter** lets a 110V device work in a 230V socket.

---

# 🧠 Adapter Pattern — WHY do we need this?

Because sometimes:

* You have an existing class
* But it doesn’t match the interface your new code expects
* Changing the existing class may not be possible (external library, legacy code)

**Solution:**
Create an *adapter* that converts one interface to another.

---

# 🧩 UML (Simple)

```
Client → Target (interface) ← Adapter → Adaptee (incompatible class)
```

---

# 💻 **Java Example — Payment Gateway Adapter**

Imagine your system expects this:

```java
interface PaymentProcessor {
    void pay(int amount);
}
```

But the third-party library gives you:

```java
class RazorpayAPI {
    public void makePaymentUsingRazor(double rupees) {
        System.out.println("Razorpay processed: ₹" + rupees);
    }
}
```

Not compatible.

### ✔ Use Adapter to connect them.

---

## ✅ Complete Working Example

```java
package structural.adapter;

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
```

---

# 📌 Output:

```
Razorpay processed: ₹500
```

---

# ⭐ Interview-Ready Explanation

> “Adapter pattern is used when you have incompatible interfaces. Instead of modifying existing code, you introduce an adapter that converts one interface into another. It follows composition — the adapter wraps the adaptee and translates method calls.”

---

# 🧠 When To Use Adapter? (Very Important)

Use it when:

* You integrate legacy code with new code
* You use external APIs/SDKs
* You want to avoid modifying classes you do not own
* Your system uses a specific format, but someone provides data in a different format

Real-world examples:

* JDBC drivers
* Spring MVC (HandlerAdapter)
* Payment gateway wrappers
* Jackson JSON object mappers

---

# ⚠ Pitfalls / Red Flags

❌ Too many adapters indicate **bad architecture**
❌ If you keep wrapping classes, maybe interfaces are wrong
❌ Don’t use Adapter when a simple redesign is enough

---

# 🧪 Mini Exercise (Do this for mastery)

**Exercise:**
You have:

```
interface MediaPlayer { void play(String file); }
```

But you need to integrate:

```
class VLCPlayer { void playVLC(String filename); }
class MP4Player { void playMP4(String filename); }
```

👉 Create adapters `VLCAdapter` and `MP4Adapter` so MediaPlayer can play both.

(You can send the code and I will review it.)

---

# 🚦 Checkpoint

Would you like to:

1️⃣ **Go deeper into Adapter** (class adapter vs object adapter, real-world examples)?
2️⃣ **Move to next pattern → Decorator**?
3️⃣ **Do exercises first**?

Just reply: **1 / 2 / 3**
