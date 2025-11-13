public class Event<T> {
    private final T content;

    public Event() {
        this.content = null;
    }

    public Event(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
