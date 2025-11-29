# 🎯 **Prototype Pattern — Section Start**

## 1️⃣ **Intent**

> **Create new objects by copying an existing object (a prototype) instead of creating from scratch.**
> Useful when object creation is **costly, heavy, or complex**.

---

# 2️⃣ **Real-World Analogy**

### ✔ Copy-Paste

Instead of retyping a big document, you duplicate an existing one and modify it.

### ✔ Cloning a design template

You clone a UI card, email template, invoice layout → then modify only the parts you need.

---

# 3️⃣ **When do we use Prototype Pattern? (important)**

Use it when:

* Object creation is **expensive** (DB fetch, network call, complicated logic)
* You need multiple objects with mostly similar data
* You need **deep cloning** of objects with nested objects
* You want dynamic/customizable instances at runtime
* You want to avoid factories producing objects with many parameters

---

# 4️⃣ UML Diagram (Very Simple)

```
      Prototype
         |
   ----------------
   |              |
ConcretePrototype1 ConcretePrototype2
```

Prototype has:

* `clone()` method
  Concrete prototypes implement the method and return a copy.

---

# 5️⃣ **Core Java Implementation**

You can implement Prototype using:

### Option A — `clone()` method (implements Cloneable)

### Option B — Copy constructor

### Option C — Custom clone() method

### Option D — Deep copy manually

### Option E — Serialization-based copy

We focus on **Option C** — the cleanest approach.

---

# ✔ EXAMPLE: Game Character Prototype (Simple + Deep Copy)

```java
package creational.prototype;

class Weapon {
    String type;

    public Weapon(String type) {
        this.type = type;
    }

    public Weapon(Weapon other) { // Copy constructor
        this.type = other.type;
    }
}

class GameCharacter implements Cloneable {
    String name;
    int health;
    Weapon weapon;

    public GameCharacter(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    // Deep copy
    @Override
    public GameCharacter clone() {
        return new GameCharacter(
            this.name,
            this.health,
            new Weapon(this.weapon) // deep copy nested object
        );
    }

    @Override
    public String toString() {
        return name + " [HP=" + health + ", Weapon=" + weapon.type + "]";
    }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        GameCharacter base = new GameCharacter("Knight", 100, new Weapon("Sword"));

        GameCharacter c1 = base.clone();
        GameCharacter c2 = base.clone();

        c1.weapon.type = "Axe";   // change clone1 weapon
        c2.health = 80;           // change clone2 health

        System.out.println(base); // Knight [HP=100, Weapon=Sword]
        System.out.println(c1);   // Knight [HP=100, Weapon=Axe]
        System.out.println(c2);   // Knight [HP=80,  Weapon=Sword]
    }
}
```

---

# ✔ What You Learned from This Example

* Prototype returns a **new copy** of the base object
* `Weapon` was deep copied → important!
* Changing clone’s nested object does NOT affect the original
* You can modify the clone independently

---

# 6️⃣ **Prototype vs Builder vs Factory Method**

| Pattern            | Purpose                            |
| ------------------ | ---------------------------------- |
| **Builder**        | Build complex objects step-by-step |
| **Factory Method** | Create families of object types    |
| **Prototype**      | Clone existing objects quickly     |

---

# 7️⃣ **Shallow Copy vs Deep Copy**

### Shallow Copy

Copies only references.

```
clone.weapon = base.weapon   ❌ shared object
```

### Deep Copy

Copies nested objects completely.

```
clone.weapon = new Weapon(base.weapon)
```

Deep copy is the correct method for Prototype Pattern most of the time.

---

# 8️⃣ **When Prototype is Very Useful**

### ✔ Object creation requires database/API call

Clone the loaded object instead of reloading.

### ✔ Creating large object graphs

E.g. cloning a document with pages + sections + metadata.

### ✔ Game development

Cloning enemies, characters, bullets, templates.

### ✔ UI frameworks

Cloning component templates.

---

# 9️⃣ ⭐ Interview Questions (Very Important)

### **Q1. What is the Prototype Pattern?**

> Creating new objects by copying an existing object instead of instantiating from scratch.

---

### **Q2. Difference between shallow and deep copy?**

* Shallow copy → copies object, **not** nested objects
* Deep copy → copies everything (nested objects separately)

---

### **Q3. Why does Java’s `clone()` method suck?**

* Complex
* Requires implementing `Cloneable`
* Throws weird exceptions
* Default clone() does shallow copy
* Hard to maintain
  **Better:** use custom clone/copy constructors

---

### **Q4. When should you use Prototype Pattern?**

* When object creation is expensive
* When many similar objects are needed
* When you want to configure objects at runtime
* For complex object graphs

---

### **Q5. How do you implement deep cloning?**

* Override `clone()` and manually clone nested objects
* Or use copy constructors

---

# 🔟 Final Cheat Sheet

```
PROTOTYPE PATTERN
-----------------
Intent: Clone existing objects rather than creating new ones.

Use when:
- Creation is expensive
- You need many similar objects
- You want runtime object configuration

Key idea:
- provide clone()
- return deep copy, not shallow

Pros:
+ Fast
+ Reduces complex creation
+ Runtime changeable prototypes

Cons:
- Requires deep copy logic
- clone() API in Java is bad
```