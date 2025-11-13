public class App {
    public static void main(String[] args) {
        ClassThatListens classThatListens = new ClassThatListens(EventBus.getInstance());
        ClassThatTriggers classThatTriggers = new ClassThatTriggers(EventBus.getInstance());

        classThatTriggers.trigger();

        classThatListens.stopListening();

        classThatTriggers.trigger();
    }
}
