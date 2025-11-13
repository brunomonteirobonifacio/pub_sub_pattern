import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventBus {
    private static final EventBus INSTANCE = new EventBus();

    // WORKAROUND - Typesafe Homogeneous Container
    private final Map<Class<? extends Event<?>>, Object> listenersByEvent = new HashMap<>();

    public static EventBus getInstance() {
        return INSTANCE;
    }

    public <T> EventSubscription subscribe(Class<? extends Event<T>> eventType, EventListener<T> callback) {
        getListeners(eventType).add(callback);
        return new EventSubscription(eventType, callback);
    }

    @SuppressWarnings("unchecked")
    private <T> Set<EventListener<T>> getListeners(Class<? extends Event<T>> eventType) {
        listenersByEvent.putIfAbsent(eventType, new HashSet<>());
        return (Set<EventListener<T>>) listenersByEvent.get(eventType);
    }

    @SuppressWarnings("unchecked")
    public <T> void publish(Event<T> event) {
        getListeners((Class<? extends Event<T>>) event.getClass())
                .forEach(listener -> listener.accept(event));
    }

    @SuppressWarnings("unchecked")
    public <T> void unsubscribe(EventSubscription subscription) {
        getListeners((Class<? extends Event<T>>) subscription.eventType).remove(subscription.listenerToRemove);
    }

    @FunctionalInterface
    public interface EventListener<T> {
        void accept(Event<T> event);
    }

    public static class EventSubscription {
        private final Class<? extends Event<?>> eventType;
        private final EventListener<?> listenerToRemove;

        private <T> EventSubscription(Class<? extends Event<T>> eventType, EventListener<T> listenerToRemove) {
            this.eventType = eventType;
            this.listenerToRemove = listenerToRemove;
        }
    }
}
