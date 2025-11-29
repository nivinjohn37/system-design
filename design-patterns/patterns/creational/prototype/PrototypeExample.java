package patterns.creational.prototype;

interface Prototype extends Cloneable {
    Prototype clone();
}

class Document implements Prototype {
    private String title;
    public Document(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Prototype clone() {
        try {
            return (Document) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Document [title=" + title + "]";
    }
}
public class PrototypeExample {
    public static void main(String[] args) {
        Document document = new Document("Document");
        Document clone = (Document) document.clone();
        clone.setTitle("Document Copy");
        System.out.println(document);
        System.out.println(clone);

    }
}
