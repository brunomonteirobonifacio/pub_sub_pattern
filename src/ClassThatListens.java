public class ClassThatListens {
    private final EventBus eventBus;
    private EventBus.EventSubscription subscription;

    public ClassThatListens(EventBus eventBus) {
        this.eventBus = eventBus;
        registerListener();
    }

    private void registerListener() {
         subscription = eventBus.subscribe(EventTriggeredByClassThatTriggers.class,
                event -> {
                    ContentExample content = event.getContent();
                    if (content != null) {
                        content.someRandomMethod();
                    }
                });
    }

    public void stopListening() {
        eventBus.unsubscribe(subscription);
    }
}
