package creational.abstractfactory;

interface AlertNotification{
    void alert(String message);
}

interface InfoNotification{
    void alert(String message);
}

class MobileAlertNotification implements AlertNotification{
    @Override
    public void alert(String message) {
        System.out.println("[Mobile] Sending ALERT: " + message);
    }
}

class MobileInfoNotification implements InfoNotification{
    @Override
    public void alert(String message) {
        System.out.println("[Mobile] Sending INFO: " + message);
    }
}

class WebAlertNotification implements AlertNotification{
    @Override
    public void alert(String message) {
        System.out.println("[Web] ALERT pop-up: " + message);
    }
}

class WebInfoNotification implements InfoNotification{
    @Override
    public void alert(String message) {
        System.out.println("[Web] INFO banner: " + message);
    }
}

interface NotificationFactory {
    AlertNotification createAlertNotification();
    InfoNotification createInfoNotification();
}

class MobileNotificationFactory implements NotificationFactory {
    @Override
    public AlertNotification createAlertNotification() {
        return new MobileAlertNotification();
    }

    @Override
    public InfoNotification createInfoNotification() {
        return new MobileInfoNotification();
    }
}

class WebNotificationFactory implements NotificationFactory {
    @Override
    public AlertNotification createAlertNotification() {
        return new WebAlertNotification();
    }

    @Override
    public InfoNotification createInfoNotification() {
        return new WebInfoNotification();
    }
}

enum Platform { MOBILE, WEB }

class NotificationFactoryProvider {
    static NotificationFactory getFactory(Platform platform) {
        return switch (platform) {
            case MOBILE -> new MobileNotificationFactory();
            case WEB -> new WebNotificationFactory();
        };
    }
}

public class CrossPlatformNotificationSystem {
    public static void main(String[] args) {
        NotificationFactory factory = NotificationFactoryProvider.getFactory(Platform.MOBILE);

        AlertNotification notification = factory.createAlertNotification();
        InfoNotification infoNotification = factory.createInfoNotification();

        notification.alert("Server is down!\n");
        infoNotification.alert("Scheduled maintenance at 10 PM.\n");
    }
}
