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
Great!
Let’s first implement your **UniversalMediaPlayer**, then answer your second (very important) question:

> “If we have 10+ formats, will we create an adapter for each?”

The answer: **Yes, but not in the ugly `if/else` way.
We use a registry map.**
I’ll show you the professional approach.

---

# ✅ PART 1 — UniversalMediaPlayer (Simple Version)

The goal:

* Pass a filename: `"song.vlc"`, `"video.mp4"`
* Player detects extension (`vlc`, `mp4`)
* Chooses correct adapter internally
* No need for main() to use VLCAdapter or MP4Adapter directly

---

# **💻 Step 1 — Create the Adapters (same as before)**

```java
interface MediaPlayer {
    void play(String fileName);
}

class VLCPlayer {
    void playVLC(String filename) {
        System.out.println("Playing VLC file: " + filename);
    }
}

class MP4Player {
    void playMP4(String filename) {
        System.out.println("Playing MP4 file: " + filename);
    }
}

class VLCAdapter implements MediaPlayer {
    private final VLCPlayer vlc = new VLCPlayer();

    @Override
    public void play(String fileName) {
        vlc.playVLC(fileName);
    }
}

class MP4Adapter implements MediaPlayer {
    private final MP4Player mp4 = new MP4Player();

    @Override
    public void play(String fileName) {
        mp4.playMP4(fileName);
    }
}
```

---

# **💻 Step 2 — UniversalMediaPlayer (Basic Version)**

```java
class UniversalMediaPlayer implements MediaPlayer {

    @Override
    public void play(String fileName) {
        String extension = getExtension(fileName);

        MediaPlayer player = switch (extension) {
            case "vlc" -> new VLCAdapter(new VLCPlayer());
            case "mp4" -> new MP4Adapter(new MP4Player());
            default -> throw new IllegalArgumentException("Unsupported file: " + fileName);
        };

        player.play(fileName);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }
}
```

---

# **💻 Step 3 — Demo**

```java
public class Demo {
    public static void main(String[] args) {
        MediaPlayer player = new UniversalMediaPlayer();

        player.play("movie.vlc");
        player.play("song.mp4");
    }
}
```

---

# 🎉 Output

```
Playing VLC file: movie.vlc
Playing MP4 file: song.mp4
```

---

# ⚠️ PROBLEM YOU ASKED ABOUT

> **“If we have 10+ formats, will we create adapters for each of them?”**

✔ Yes, each incompatible player should have exactly one adapter.
This is the entire point of the Adapter Pattern.
But…

❌ We should NOT write 10 `if/else` or `switch` statements.

That becomes unmaintainable.

---

# ⭐ PART 2 — PROFESSIONAL SOLUTION

## **Adapter Registry (Factory + Strategy Style)**

This solves scaling to 10, 20, 100 formats.

---

# ✔ Step 1 — Adapter Registry Map

```java
class UniversalMediaPlayer implements MediaPlayer {

    private final Map<String, MediaPlayer> adapterRegistry = new HashMap<>();

    public UniversalMediaPlayer() {
        adapterRegistry.put("vlc", new VLCAdapter(new VLCPlayer()));
        adapterRegistry.put("mp4", new MP4Adapter(new MP4Player()));

        // if future:
        // adapterRegistry.put("avi", new AVIAdapter(new AVIPlayer()));
        // adapterRegistry.put("mkv", new MKVAdapter(new MKVPlayer()));
    }

    @Override
    public void play(String fileName) {
        String ext = getExtension(fileName);

        MediaPlayer adapter = adapterRegistry.get(ext);

        if (adapter == null) {
            throw new IllegalArgumentException("Unsupported file type: " + ext);
        }

        adapter.play(fileName);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }
}
```

---

# ✔ Step 2 — Demo

```java
public class Demo {
    public static void main(String[] args) {
        MediaPlayer player = new UniversalMediaPlayer();
        player.play("file.vlc");
        player.play("video.mp4");
    }
}
```

---

# 🎉 Why This Is Better?

### ✔ OCP Compliant

No change required in `UniversalMediaPlayer` code.

When adding a new format:

```
adapterRegistry.put("mkv", new MKVAdapter(new MKVPlayer()));
```

No need to edit play() method.

---

# 🔥 Deep Answer for Interviews

Here’s the correct interview explanation:

> “Yes, for each incompatible API, you need one adapter.
> But instead of writing multiple if/else cases, we maintain an **adapter registry**, mapping file extensions to adapters.
>
> This keeps UniversalMediaPlayer **Open for extension** but **Closed for modification**, following OCP.
>
> Adding a new file type means providing a new adapter and registering it — without changing existing code.”

Interviewers love this answer.

---

# ⭐ Final Summary

### ✔ You wrote the base Adapter code correctly

### ✔ We built a Universal Player

### ✔ We upgraded it to an Adapter Registry

### ✔ This solves scaling to 10+ adapters

### ✔ OCP is maintained

### ✔ Perfect for interview scenarios

---

