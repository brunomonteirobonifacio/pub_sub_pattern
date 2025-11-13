public class ClassThatTriggers {
    private final EventBus eventBus;

    public ClassThatTriggers(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void trigger() {
        eventBus.publish(new EventTriggeredByClassThatTriggers(new ContentExample()));
    }
}
