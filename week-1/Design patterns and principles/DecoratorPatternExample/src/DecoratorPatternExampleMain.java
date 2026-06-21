public class DecoratorPatternExampleMain {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        Notifier smsAndEmailNotifier = new SMSNotifierDecorator(emailNotifier);
        Notifier allNotifier = new SlackNotifierDecorator(smsAndEmailNotifier);

        System.out.println("Sending basic email:");
        emailNotifier.send("Hello World!");

        System.out.println("\nSending email + SMS:");
        smsAndEmailNotifier.send("Hello World!");

        System.out.println("\nSending email + SMS + Slack:");
        allNotifier.send("Hello World!");
    }
}