package patterns.creational.assessment;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Supplier;
import java.util.stream.IntStream;

class ObjectPool<T> {
    private final BlockingQueue<T> availableObjects;
    private final Set<T> inUseObjs= ConcurrentHashMap.newKeySet();
    private final Supplier<T> creator;
    private final int maxSize;

    public ObjectPool(int maxSize, Supplier<T> creator) {
        this.maxSize = maxSize;
        this.creator = creator;
        this.availableObjects = new LinkedBlockingDeque<>(maxSize);

        for (int i = 0; i < maxSize; i++) {
            availableObjects.add(creator.get());
        }
    }

    public T borrowObject() {
        try{
            T obj = availableObjects.take();
            inUseObjs.add(obj);
            System.out.println("Borrowed " + obj);
            return obj;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    public void returnObject(T obj) {
        if(inUseObjs.remove(obj)) {
            availableObjects.offer(obj);
            System.out.println("Returned " + obj);
        }
    }

    public int availableCount() {
        return availableObjects.size();
    }

    public int inUseCount() {
        return inUseObjs.size();
    }
}

class FakeConnection{
    private int id;
    public FakeConnection(int id){
        this.id = id;
    }
    @Override
    public String toString() {
        return "FakeConnection [id=" + id + "]";
    }
}
public class GenericObjectPool {
    public static void main(String[] args) {
        ObjectPool<FakeConnection> pool = new ObjectPool<>(2, new Supplier<FakeConnection>() {
            private int count = 0;
            @Override
            public FakeConnection get() {
                return new FakeConnection(++count);
            }
        });

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        IntStream.range(0, 5).forEach(i -> {
            final int id = i;
            executor.submit(() ->{
               try {
                   FakeConnection conn = pool.borrowObject();
                   Thread.sleep(1000);
                   System.out.println("Thread - " + id + " borrowed " + conn);
                   pool.returnObject(conn);
               }catch(InterruptedException e){
                   Thread.currentThread().interrupt();
               }
            });
        });
        executor.shutdown();

    }

}
