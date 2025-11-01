---

## 🎯 **Learning Objectives**

You’ll learn to:

- Centralize object creation logic in one class.
- Decouple the client from specific product implementations.
- Understand how this pattern improves maintainability.

---

## 💡 **Definition**

> The Simple Factory Pattern provides a single place (a “factory” class) to create objects of different types,
>
>
> **without exposing the creation logic** to the client.
>

---

## 🧠 **Core Idea**

Instead of doing this everywhere:

```java
NotificationService service = new EmailNotification();

```

We delegate it to a **factory**:

```java
NotificationService service = NotificationFactory.create("EMAIL");

```

Now the client doesn’t know or care which concrete class is created.

---

## 💬 **Real-World Analogy**

Think of a **Coffee Shop ☕**

- You don’t make coffee yourself — you ask the **barista**.
- You say “Espresso” or “Cappuccino,” and they decide which ingredients and process to use.
- You get your drink, without worrying *how* it was made.

Here, the **barista = factory**, and **coffee = object**.

---

## 💻 **Java Example — Simple Factory**

Let’s implement a notification system (Email, SMS, Push).

### Step 1️⃣: Define the Product Interface

```java
interface Notification {
    void notifyUser();
}

```

---

### Step 2️⃣: Concrete Implementations

```java
class EmailNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an Email Notification");
    }
}

class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an SMS Notification");
    }
}

class PushNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending a Push Notification");
    }
}

```

---

### Step 3️⃣: The Factory Class

```java
class NotificationFactory {

    public static Notification createNotification(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Notification type required");
        }

        switch (type.toUpperCase()) {
            case "EMAIL":
                return new EmailNotification();
            case "SMS":
                return new SMSNotification();
            case "PUSH":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown notification type " + type);
        }
    }
}

```

---

### Step 4️⃣: The Client

```java
public class NotificationService {
    public static void main(String[] args) {
        Notification email = NotificationFactory.createNotification("EMAIL");
        email.notifyUser();

        Notification sms = NotificationFactory.createNotification("SMS");
        sms.notifyUser();
    }
}

```

---

### ✅ **Output**

```
Sending an Email Notification
Sending an SMS Notification

```

---

## 🧩 **UML Diagram**

```
                Notification (interface)
                       ▲
      ┌────────────────┼────────────────┐
      │                │                │
 EmailNotification  SMSNotification  PushNotification
                       ▲
                       │
              NotificationFactory
              + createNotification(type)
                       ▲
                       │
                    Client

```

---

## ⚙️ **When to Use**

✅ When you have multiple related classes implementing the same interface.

✅ When you want to **encapsulate object creation logic** in one place.

✅ When client code should not depend on concrete class names.

---

## ⚡ **Advantages**

| Benefit | Description |
| --- | --- |
| Centralized creation | One place to manage object creation logic |
| Decoupled client | Client doesn’t need to know which class to instantiate |
| Easy to extend | Add new product types by modifying the factory |

---

## 🚩 **Disadvantages**

❌ Factory class violates **OCP** — adding a new product means modifying the factory.

❌ Harder to scale when there are too many product types.

➡️ (We’ll solve this with the **Factory Method Pattern** next!)

---

## 💬 **Interview Keywords**

| Concept | Keywords |
| --- | --- |
| Type | Object creation pattern |
| Goal | Centralize instantiation |
| Principle | Encapsulation of creation logic |
| Related | Factory Method, Abstract Factory |
| Example | NotificationFactory, ShapeFactory |

---

## ⚡ **Interview One-Liner**

> “A Simple Factory centralizes object creation in one class so that the client code doesn’t depend on concrete implementations.”
>

---

## 🧠 **Common Interview Questions**

1️⃣ What problem does the Simple Factory pattern solve?

2️⃣ How does it violate the Open/Closed Principle?

3️⃣ How would you modify the example to support adding new types easily?

4️⃣ What’s the difference between Simple Factory and Factory Method?

5️⃣ How is it used in Spring Framework?

- **Answer:** The `ApplicationContext` in Spring acts as a factory for beans.

---

## 🧠 **Mini Quiz**

1️⃣ What is the role of `NotificationFactory`?

2️⃣ Which principle does the Simple Factory violate?

3️⃣ If you need to add `WhatsAppNotification`, what must you change?

4️⃣ What design pattern solves this problem more elegantly?

5️⃣ Where have you seen a similar pattern in frameworks like Spring?

---

# 🧠 **Simple Factory Pattern — Interview Q&A Master Notes**

---

### **1️⃣ What problem does the Simple Factory pattern solve?**

✅ **Answer:**

> The Simple Factory pattern centralizes object creation logic in one place instead of scattering new calls throughout the codebase.
>
>
> It **decouples the client** from knowing which specific class to instantiate and **encapsulates instantiation logic** inside a factory class.
>

🔑 **Keywords to mention:**

- *Encapsulation of object creation*
- *Centralized instantiation logic*
- *Decoupled client code*
- *Single point of maintenance*

📦 **Example Answer (spoken style):**

> “The Simple Factory pattern helps remove the direct dependency between the client and the object creation logic. Instead of the client calling new CreditCardPayment(), a factory class decides which object to return. This makes the code easier to manage, test, and extend.”
>

---

### **2️⃣ How does it violate the Open/Closed Principle?**

✅ **Answer:**

> The Simple Factory violates the Open/Closed Principle because whenever we add a new product type,
>
>
> we must **modify the factory class** — changing its `switch` or `if` logic.
>

🔑 **Keywords to mention:**

- *OCP violation*
- *Modification required for extension*
- *Hard-coded class selection logic*

📦 **Example Answer (spoken style):**

> “In a Simple Factory, if I add a new payment method like CryptoPayment, I need to modify the factory’s switch statement.
>
>
> That means the factory is not closed for modification — violating OCP.
>
> The **Factory Method Pattern** solves this by delegating object creation to subclasses.”
>

---

### **3️⃣ How would you modify the example to support adding new types easily?**

✅ **Answer:**

> There are three ways to make the factory more extensible:
>
>
> 1️⃣ **Use Reflection** — load classes dynamically based on configuration (no hardcoded switch).
>
> 2️⃣ **Use a Registration Map** — register new product types at runtime in a `Map<String, Supplier<?>>`.
>
> 3️⃣ **Move to the Factory Method pattern**, where creation is delegated to subclasses.
>

📦 **Example using Reflection:**

```java
class DynamicFactory {
    public static PaymentStrategy create(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return (PaymentStrategy) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unknown type: " + className);
        }
    }
}

```

Now, new types can be added **without modifying** the factory.

🔑 **Keywords to mention:**

- *Reflection*, *Registration Map*, *Factory Method upgrade*
- *Make factory open for extension*
- *Avoid modifying switch cases*

---

### **4️⃣ What’s the difference between Simple Factory and Factory Method?**

✅ **Answer Table:**

| Aspect | Simple Factory | Factory Method |
| --- | --- | --- |
| **Who creates the object** | Factory class (centralized) | Subclasses decide (decentralized) |
| **Open/Closed Principle** | Violated | Respected |
| **Flexibility** | Limited | High |
| **Inheritance use** | None | Uses inheritance/polymorphism |
| **Example** | `NotificationFactory.create("EMAIL")` | Each subclass overrides `createNotification()` |

📦 **Spoken Answer Example:**

> “In a Simple Factory, a single class handles object creation — often using a switch statement.
>
>
> In a Factory Method, the responsibility is moved to subclasses.
>
> This respects OCP because adding a new product doesn’t require modifying the existing factory; you just create a new subclass.”
>

---

### **5️⃣ How is it used in Spring Framework?**

✅ **Answer:**

> In Spring, the ApplicationContext (or BeanFactory) acts as a sophisticated factory that manages object creation and lifecycle.
>
>
> It uses **Dependency Injection** and **Bean Definitions** to create and manage beans instead of hardcoded `new` calls.
>

📦 **Example Explanation:**

```java
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
PaymentService payment = context.getBean("paymentService", PaymentService.class);

```

- The client doesn’t know the concrete class.
- Spring (the factory) decides which implementation to create based on configuration or annotations.

🔑 **Keywords to mention:**

- *Spring IoC container acts as a factory*
- *Decoupled bean instantiation*
- *Dependency Injection replaces manual object creation*
- *Polymorphic bean resolution*

🧠 **Bonus:**

When interviewers ask, “Where have you seen Simple Factory in real life?”,

Answer:

> “In frameworks like Spring — the ApplicationContext is effectively a factory for beans. It abstracts creation and configuration logic away from the client code.”
>

---

## 🧩 **Quick Recap Table**

| Question | Short Answer | Keywords |
| --- | --- | --- |
| What problem does it solve? | Centralizes object creation | Encapsulation, decoupling |
| How does it violate OCP? | Must modify factory for new types | switch-case, modification |
| How to fix it? | Reflection, registration map, Factory Method | extensibility |
| Difference vs Factory Method | Centralized vs subclass delegation | OCP compliance |
| Spring usage | ApplicationContext creates beans | IoC container, DI, factory |