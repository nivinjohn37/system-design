Great — moving on to the next **Structural Design Pattern**:

# 🎭 **Facade Pattern**

This is one of the **simplest and most practical** structural patterns.
Used heavily in:

* Spring Boot starters
* Libraries like Hibernate, JDBC
* API Gateways
* Complex subsystems (authentication, notifications, billing)
* Utility classes (`Files`, `Collections`, etc.)

Let’s make it super clear.

---

# 🎯 1. **Intent**

> **Provide a simple unified interface to a complex subsystem.**
> Hide complexity. Show a clean, friendly API.

In short:

* You have 10 complicated steps
* Facade exposes **1 simple method**

---

# 🎯 2. Real-World Analogies

### ✔ Restaurant 🍽️

You don’t directly talk to:

* chef
* waiter
* cashier
* kitchen system

You just call **waiter.orderFood()** → the waiter handles everything.

**Waiter = Facade**.

---

### ✔ Startup “Services” (Email, SMS, Billing, Logging)

You could call each service manually OR use:

```java
NotificationFacade.sendWelcomeEmail(user);
```

---

# 🎯 3. UML Overview

---

# 🎯 4. Java Example — Home Theater System

Imagine a home theater with many components:

* DVD Player
* Projector
* Lights
* Speakers

Without Facade:

```java
projector.on();
dvd.play();
lights.dim();
speakers.on();
speakers.setVolume(5);
```

This is painful and error-prone.

---

# ✔ Applying Facade Pattern

### Step 1: Subsystems

```java
class DVDPlayer {
    void on() { System.out.println("DVD ON"); }
    void play() { System.out.println("DVD Playing"); }
}

class Projector {
    void on() { System.out.println("Projector ON"); }
}

class Lights {
    void dim() { System.out.println("Lights dimmed"); }
}

class Speakers {
    void on() { System.out.println("Speakers ON"); }
    void setVolume(int level) { System.out.println("Volume set to " + level); }
}
```

---

### Step 2: Create Facade

```java
class HomeTheaterFacade {

    private final DVDPlayer dvd;
    private final Projector projector;
    private final Lights lights;
    private final Speakers speakers;

    public HomeTheaterFacade(DVDPlayer dvd, Projector projector, Lights lights, Speakers speakers) {
        this.dvd = dvd;
        this.projector = projector;
        this.lights = lights;
        this.speakers = speakers;
    }

    public void watchMovie() {
        System.out.println("Starting movie...");
        lights.dim();
        projector.on();
        speakers.on();
        speakers.setVolume(5);
        dvd.on();
        dvd.play();
    }

    public void stopMovie() {
        System.out.println("Stopping movie...");
    }
}
```

---

### Step 3: Client Code (Very Simple)

```java
public class FacadeDemo {
    public static void main(String[] args) {
        HomeTheaterFacade theater = new HomeTheaterFacade(
            new DVDPlayer(), new Projector(), new Lights(), new Speakers()
        );

        theater.watchMovie();
    }
}
```

---

# 🎉 Output

```
Starting movie...
Lights dimmed
Projector ON
Speakers ON
Volume set to 5
DVD ON
DVD Playing
```

Client doesn't know the details — only uses Facade.

---

# 🎯 5. Why Use Facade?

### ✔ Simplifies usage

One clean method instead of many messy subsystem calls.

### ✔ Decouples client from complex code

Client doesn’t depend on subsystem changes.

### ✔ Improves readability

Instead of 10 low-level API calls, you get 1 expressive call.

### ✔ Great for libraries/frameworks

Spring Boot uses Facade style extensively:

```java
SpringApplication.run(App.class);
```

Inside →
hundreds of subsystem initializations.

---

# 🎯 6. Where Facade is Used in Real Code?

| Framework         | Facade Example                  |
| ----------------- | ------------------------------- |
| **Spring Boot**   | `SpringApplication.run()`       |
| **Hibernate**     | `SessionFactory`                |
| **Java Mail API** | `JavaMailSender`                |
| **AWS SDK**       | `S3Client`                      |
| **JDBC**          | `DriverManager.getConnection()` |
| **Security**      | API Gateways                    |

---

# 🎯 7. Interview Questions

### Q1: What is Facade pattern?

A simplified interface over a complex system.

---

### Q2: How does Facade differ from Adapter?

| Facade                         | Adapter                           |
| ------------------------------ | --------------------------------- |
| Simplifies a complex system    | Converts one interface to another |
| Same subsystem, simplified API | Two incompatible interfaces       |

---

### Q3: How does Facade improve maintainability?

Changes inside subsystems don’t affect clients — only the Facade layer changes.

---

### Q4: Can Facade have multiple methods?

Yes — it can group multiple workflows.

---

### Q5: Does Facade hide complexity or remove it?

It **hides** complexity, does not remove it.

---

# 🎯 8. Mini Coding Exercise (Try It!)

Create a **PaymentFacade**:

Subsystems:

* `PaymentGateway`
* `InvoiceGenerator`
* `EmailService`
* `SMSService`
* `FraudCheck`

Facade:

```
paymentFacade.processOrder(orderId);
```

Send me your code — I will review it.

---

# 🚦 Checkpoint

Would you like to:

1️⃣ Move to next pattern → **Bridge Pattern**
2️⃣ Get full Facade notes document
3️⃣ Try the exercise
4️⃣ Compare Facade vs Adapter vs Proxy

Reply: **1 / 2 / 3 / 4**
