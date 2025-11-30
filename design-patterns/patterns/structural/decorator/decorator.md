Great — let’s move to the next Structural Pattern:

# 🧩 **Decorator Pattern**

This is one of the *most important* patterns for interviews, Java frameworks, and Spring Boot.

Decorator is used **everywhere** — Streams API, Filters, HttpServletRequest wrappers, Spring Security filters, etc.

You MUST understand it deeply.

---

# 🎯 **1. Intent**

> **Add new behavior to an object dynamically, without modifying the original class.**

It’s a flexible alternative to inheritance.

---

# 🎯 **2. Real-World Analogy**

### ✔ Coffee Shop Example

Base Coffee = ₹100
Add Milk → +₹20
Add Sugar → +₹10
Add Cream → +₹30

You don’t subclass 50 combinations like:

* CoffeeWithMilkAndSugarAndCream
* CoffeeWithSugar
* CoffeeWithMilk

Instead → you **wrap** the base object.

---

# 🎯 **3. UML Intuition**

**Key components:**

* **Component** → interface (e.g., Coffee)
* **ConcreteComponent** → basic object (e.g., SimpleCoffee)
* **Decorator** → wrapper that implements the same interface
* **ConcreteDecorators** → add extra functionality

---

# 🎯 **4. Java Example — Coffee Cost Calculator**

## Step 1 — Component interface

```java
interface Coffee {
    String getDescription();
    double getCost();
}
```

---

## Step 2 — Concrete Component

```java
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 100;
    }
}
```

---

## Step 3 — Base Decorator (wraps Coffee)

```java
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription();
    }

    public double getCost() {
        return coffee.getCost();
    }
}
```

---

## Step 4 — Concrete Decorators

### Milk

```java
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 20;
    }
}
```

### Sugar

```java
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10;
    }
}
```

### Cream

```java
class CreamDecorator extends CoffeeDecorator {
    public CreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Cream";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 30;
    }
}
```

---

# 🎯 **5. Client Code (showing power of decorator)**

```java
public class DecoratorDemo {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " → ₹" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " → ₹" + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " → ₹" + coffee.getCost());

        coffee = new CreamDecorator(coffee);
        System.out.println(coffee.getDescription() + " → ₹" + coffee.getCost());
    }
}
```

---

# 🎉 Output

```
Simple Coffee → ₹100.0
Simple Coffee, Milk → ₹120.0
Simple Coffee, Milk, Sugar → ₹130.0
Simple Coffee, Milk, Sugar, Cream → ₹160.0
```

---

# 🎯 **6. Why Not Use Inheritance Instead?**

Because:

* Inheritance creates **class explosion**
* Decorators allow **any combination in any order**
* Decorator adds behavior at runtime, not compile-time
* Decorators follow Composition over Inheritance principle

---

# 🎯 **7. Real Examples in Java**

| Framework           | Decorator Usage                              |
| ------------------- | -------------------------------------------- |
| **Java IO**         | `BufferedInputStream(new FileInputStream())` |
| **Servlet API**     | `HttpServletRequestWrapper`                  |
| **Spring Security** | Filter chains wrap requests                  |
| **Jackson**         | Custom serializers/deserializers wrap logic  |

Example:

```java
InputStream input = new BufferedInputStream(new FileInputStream("file.txt"));
```

---

# 🎯 **8. Interview Questions**

### Q1: What problem does the decorator pattern solve?

> Adding behavior without modifying existing code (OCP).

### Q2: How is Decorator different from Adapter?

* Adapter converts interface
* Decorator adds behavior

### Q3: Difference between Decorator vs Proxy?

* Decorator adds responsibilities
* Proxy controls access

### Q4: Is Decorator runtime or compile-time?

> Runtime — the wrapped chain can change dynamically.

---

# 🎯 **9. Hands-on Exercise (Do this next)**

Create a **Notification system**:

```
interface Notifier { void send(String msg); }

EmailNotifier (base)
SMSDecorator
SlackDecorator
WhatsAppDecorator
```

Goal:

```
Notifier notifier = new SlackDecorator(new SMSDecorator(new EmailNotifier()));
notifier.send("Server down!");
```

I will review your code once you write it.

---

# 🚦 Checkpoint

Would you like to:

1️⃣ Solve the **Decorator exercise**
2️⃣ Proceed to **Composite Pattern**
3️⃣ Get a **Decorator Pattern complete document**

Reply: **1 / 2 / 3**
