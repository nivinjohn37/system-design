package patterns.creational.abstractfactory;

// Abstract Products
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

// Concrete Products - Windows
class WindowsButton implements Button {
    public void render() {
        System.out.println("Rendering a Windows Button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering a Windows Checkbox");
    }
}

// Concrete Products - Mac
class MacButton implements Button {
    public void render() {
        System.out.println("Rendering a Mac Button");
    }
}

class MacCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering a Mac Checkbox");
    }
}

// Abstract Factory
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factories
class WindowsUIFactory implements UIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacUIFactory implements UIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// Client
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        UIFactory factory;

        // Choose factory dynamically
        String os = "Mac"; // could come from config/env
        if (os.equals("Windows")) {
            factory = new WindowsUIFactory();
        } else {
            factory = new MacUIFactory();
        }

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.render();
        checkbox.render();
    }
}
