public class ListNode<T> {
    private T data;
    private ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
    }

    // recursion: each node contains a node called next. If next has no value, then new data is inserted here.
    // Otherwise next calls this method on itself and the process is repeated.
    public void insert(T value) {
        if(next == null) {
            next = new ListNode<>(value);
        } else {
            next.insert(value); // recursion. Adds data to the next node
        }
    }

    public void insert(ListNode<T> listNode) {
        if(next == null) {
            next = listNode;
        } else {
            next.insert(listNode); // recursion
        }
    }

    public T data() {
        return data;
    }

    public ListNode<T> next() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public ListNode<T> reverse(ListNode<T> head) { // reverses ListNode order
        if(head == null) {
            return null;
        }
        ListNode<T> current = head;
        ListNode<T> previous = null;
        ListNode<T> nextNode = null;
        while(current != null) {
            nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        return previous;
    }


    public void print() {
        System.out.print(data + ", ");
        if(next != null) {
            // recursion
            // data on current note printed above first
            // method called again on the next node attached.
            next.print();
        }
    }

    public boolean contains(T value) {
        if(data == value) {
            return true;
        }
        if(next != null) {
            return next.contains(value); // recursion. Checks the next nodes data.
        }
        return false;
    }

    public ListNode<T> get(T value) {
        if(value == data) {
            return this;
        }
        if(next != null) {
            return next.get(value); // recursion. checks the next nodes data.
        }
        return null;
    }

    public String toString() { // print node data
        return data + "";
    }
}
