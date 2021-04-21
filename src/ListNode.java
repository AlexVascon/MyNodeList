public class ListNode<T> {
    private T data;
    private ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
    }

    // recursion: each node contains a node called next. If that nodes next node has no value, then it is created.
    // Otherwise the node next calls this method on itself and the process is repeated.
    // This is the structure for how a linked list is implemented
    public void insert(T value) {
        if(next == null) {
            // defines attached node
            next = new ListNode<>(value);
        } else {
            // recursion. Adds data to the next node;
            next.insert(value);
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

    // reverses ListNode order
    public ListNode<T> reverse(ListNode<T> head) {
        if(head == null) {
            return head;
        }
        ListNode<T> current = head;
        ListNode<T> previous = null;
        ListNode<T> next = null;
        while(current != null) {
            //
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
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
            // recursion. Checks the next nodes data.
            return next.contains(value);
        }
        return false;
    }

    public ListNode<T> get(T value) {
        if(value == data) {
            return this;
        }
        if(next != null) {
            // recursion. checks the next nodes data.
            return next.get(value);
        }
        return null;
    }

    public String toString() {
        return data + "";
    }
}
