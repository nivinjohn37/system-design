# ✅ **OBJECT POOL PATTERN — Step-by-Step Completion**

We will now complete:

## ✔ Problem 11 — Connection Pool (simple version)

## ✔ Problem 12 — Thread Pool Simulator (advanced version)

You already saw the code earlier, but now we will **fully understand** the concepts.

---

# ⭐ PART 1 — Problem 11: Connection Pool (Simple Object Pool)

Before writing any code, let’s first understand the *intuition*.

---

# ❓ Why do we need an Object Pool?

Some objects are:

* ❗ expensive to create (DB connection, thread, web client)
* ❗ created frequently
* ❗ used briefly, then thrown away

Creating these objects over and over wastes:

* CPU
* memory
* GC time

So instead of creating 100 connections:

➡️ We create 5
➡️ Reuse them
➡️ Return them back to the pool

This is the **Object Pool Pattern**.

---

# 🧠 The Pool Must Do 3 Things

### 1️⃣ Pre-create limited number of objects

Example: only 3 database connections.

### 2️⃣ Give objects to clients on request

`borrowObject()`

### 3️⃣ Accept returned objects

`returnObject(connection)`

---

# 🏗 The Simple Architecture

```
ObjectPool<T>
 |
 |-- availableObjects queue (resources free)
 |-- inUseObjects set     (resources busy)
 |
 |-- borrowObject()
 |-- returnObject()
```

---

# 🧩 Let’s build the Connection Pool (simple version)

### Step 1 — Create a fake DB connection

```java
class FakeConnection {
    private final int id;

    public FakeConnection(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Connection-" + id;
    }
}
```

---

### Step 2 — Create the Object Pool

```java
class ObjectPool<T> {
    private final Queue<T> available = new LinkedList<>();
    private final Set<T> inUse = new HashSet<>();
    private final int maxSize;
    private final Supplier<T> creator;

    public ObjectPool(int maxSize, Supplier<T> creator) {
        this.maxSize = maxSize;
        this.creator = creator;

        for (int i = 0; i < maxSize; i++) {
            available.add(creator.get());
        }
    }

    public synchronized T borrowObject() {
        if (available.isEmpty()) {
            throw new RuntimeException("All objects are in use!");
        }
        T obj = available.poll();
        inUse.add(obj);
        return obj;
    }

    public synchronized void returnObject(T obj) {
        inUse.remove(obj);
        available.add(obj);
    }
}
```

---

### Step 3 — Simulation

```java
public class PoolDemo {
    public static void main(String[] args) {
        ObjectPool<FakeConnection> pool =
            new ObjectPool<>(3, new Supplier<>() {
                private int counter = 0;
                @Override
                public FakeConnection get() {
                    return new FakeConnection(++counter);
                }
            });

        FakeConnection c1 = pool.borrowObject();
        FakeConnection c2 = pool.borrowObject();
        FakeConnection c3 = pool.borrowObject();

        pool.returnObject(c1);
        FakeConnection c4 = pool.borrowObject(); // reuses c1
    }
}
```

---

# 🎉 You have now completed Problem 11

Next:

# ⭐ PART 2 — Problem 12: Thread Pool Simulator (The BEST Object Pool Example)

Let’s fully understand this step-by-step.

---

# 🧠 Intuition Again (important)

A **Thread Pool** is EXACTLY an Object Pool but instead of storing:

```
Connection objects
```

we store:

```
Worker threads
```

and we reuse them.

---

## ✔ Step 1 — Pool must create N worker threads ONCE

This is why inside constructor we have:

```java
for (int i = 0; i < numThreads; i++) {
    Worker worker = new Worker("Worker-" + i);
    workers.add(worker);
    new Thread(worker).start();
}
```

### 🔍 Meaning:

* Create a `Worker` object → a runnable
* Wrap it with a Thread
* Start the Thread
* That Thread now loops forever waiting for tasks

Just like creating 3 cooks in a kitchen:

```
Worker-0 ready
Worker-1 ready
Worker-2 ready
```

They stand idle until orders arrive.

---

## ✔ Step 2 — Where do tasks go?

Tasks go into a **BlockingQueue**:

```java
taskQueue = new LinkedBlockingQueue<>();
```

Task queue = list of pending orders.

---

## ✔ Step 3 — submit() puts tasks into the queue

```java
public void submit(Runnable task) {
    taskQueue.offer(task);
}
```

This is like taking an order and placing it in the kitchen order queue.

---

## ✔ Step 4 — Worker threads wait for tasks

Here’s the magic:

```java
Runnable task = taskQueue.take();
task.run();
```

`take()` means:

* if queue is empty → wait
* if task exists → pick it up

This makes the worker sleep **without using CPU**.

---

## ✔ Step 5 — Worker executes task

Worker thread executes:

```java
task.run();
```

### This is the MOST IMPORTANT LINE.

⭐ **`task.run()` does NOT create a new thread**
⭐ It runs inside the Worker thread
⭐ Tasks are executed IN the pool thread
⭐ No extra threads are ever created

This removes your confusion.

---

## ✔ Step 6 — Why do we need `running` flag and `stopWorker()`?

Workers run forever:

```java
while (running) { ... }
```

But when you call:

```java
shutdown();
```

You want all workers to stop safely.

So shutdown does:

```java
isRunning = false;
worker.stopWorker();
```

That stops the loop → thread ends.

---

# 🎥 Visualization of the Full Flow

### Constructor:

```
Worker-0 created and started
Worker-1 created and started
Worker-2 created and started
```

### submit(task1)

taskQueue = [task1]

Worker-0 wakes up → executes task1

### submit(task2)

taskQueue = [task2]

Worker-1 wakes up → executes task2

### submit(task3)

taskQueue = [task3]

Worker-2 wakes up → executes task3

### submit(task4)

taskQueue = [task4]

Now all workers are busy
task4 waits

As soon as Worker-0 finishes:

* Worker-0 takes task4
* Executes it
* Waits again

---

# 🌟 Summary (What You MUST Remember)

| Concept                      | Meaning                                 |
| ---------------------------- | --------------------------------------- |
| Worker thread                | Long-running thread that loops forever  |
| task.run()                   | Runs task **inside** worker thread      |
| BlockingQueue                | Holds submitted tasks                   |
| take()                       | Makes worker wait efficiently           |
| thread pool = object pool    | Threads are reused objects              |
| start workers in constructor | Thread pool has fixed number of threads |
| shutdown                     | Stops workers gracefully                |

---