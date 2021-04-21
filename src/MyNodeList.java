public class MyNodeList<T> {
    private ListNode<T> head;
    private ListNode<T> next;
    private int size;

    public void insert(T value) {
        if(head == null) {
            // head is set as start of linked list
            head = new ListNode<>(value);
            size = 1;
        } else {
            // recursion. Elements added to list
            head.insert(value);
            size++;
        }
    }

    public Boolean isEmpty() {
        if(head == null) {
            // if head is empty then there are no values
            return true;
        }
        return false;
    }

    public void clear() {
        head = null;
    }

    public void insert(int index, T value) {
        ListNode<T> dummyHead = head;
        ListNode<T> previous = null;
        int position = 0;
        while(position < index) {
            previous = dummyHead;
            dummyHead = dummyHead.next();
            position++;
        }
        ListNode<T> current = previous;
        ListNode<T> attached = current.next();
        ListNode<T> newNode = new ListNode<>(value);
        current.setNext(newNode);
        newNode.setNext(attached);
        size++;
    }

    public ListNode<T> next() {
        if(next == null) {
            // initialises next reference
            next = head;
        }
        // moves to the next node
        next = next.next();
        return next;
    }

    public boolean contains(T value) {
        if(head != null ) {
            return  head.contains(value);
        }
        return false;
    }

    public ListNode<T> get(T value) {
        if(head!=null) {
            return head.get(value);
        }
        return null;
    }

    public ListNode<T> getNext() {
        // return next without moving to it
        if(next == null) {
            next = head;
        }
        return next;
    }

    public ListNode<T> head() {
        if(head != null) {
            // return the start of the list
            return head;
        }
        return null;
    }

    public ListNode<T> delete(int position) {
        // sets current node
        ListNode<T> previous = head;
        int count = 0;
        // list starts from 0
        while(count < position -1) {
            previous = previous.next();
            count++;
        }
        ListNode<T> current = previous.next();
        previous.setNext(current.next());
        current.setNext(null);
        size--;
        return current;
    }

    public MyIterator<T> myIterator() {
        MyNodeList<T> copy = new MyNodeList<>();
        while(head != null) {
            // head is set to start of copied list
            copy.insert(head.data());
            // traverse the lists values
            head = head.next();
        }

        // create new my iterator
        MyIterator<T> myIterator = new MyIterator<>();
        // add the copied list to my iterator
        myIterator.setMyNodeList(copy);
        return myIterator;
    }

    public void print() {
        // recursion in print() prints all nodes data
        head.print();
    }
}
