import java.util.NoSuchElementException;

public class MyNodeList<T> {
    private ListNode<T> head;
    private ListNode<T> next;
    private ListNode<T> previous;
    private int size;

    public void insert(T value) { // create new node
        if(head == null) {
            head = new ListNode<>(value); // start
            next = head; // avoid null problems conditioning next
        } else {
            head.insert(value); // recursion. Elements added to list. Check ListNode class for insert()
        }
        size++;
    }

    public void insert(ListNode<T> listNode) { // adds an existing node
        if(head == null) {
            head = listNode;
            next = head;
        } else {
            head.insert(listNode); // recursion. Same principle as above
        }
        size ++;
    }

    public MyNodeList<T> clone() { // return list as copy. Changes over lap because it contains the same reference
        if(head != null) {
            MyNodeList<T> copy = new MyNodeList<>();
            copy.insert(head);
            copy.size = this.size;
            return copy;
        }
        return null;
    }

    public T get(int index) {
        if(index > size || head == null) {
            return null;
        }
        int count = 0;
        ListNode<T> dummyHead = head;
        while(count < index) {
            dummyHead = dummyHead.next();
            count++;
        }
        return dummyHead.data();
    }

    public Boolean isEmpty() {
        if(head == null) { // if head is empty then there are no values
            return true;
        }
        return false;
    }

    public T getFirst() {
        if(head != null) {
            return head.data();
        }
        return null;
    }

    public T getLast() {
        if(head != null) {
            ListNode<T> current = head;
            ListNode<T> nextNode = head.next();
            while(nextNode != null) {
                current = nextNode;
                nextNode = nextNode.next();
            }
            return current.data(); // last element that was not null
        }
        return null;
    }

    public void clear() {
        head = null; // empty list
    }

    public void insert(int index, T value) { // add node at specific position in list
        if(size == 0 && index > size) {
            return;
        }
        if(head == null && index == size) {
            head = new ListNode<>(value);
            size++;
            return;
        }
        if(index > size + 1) {
            return;
            //throw new IllegalArgumentException("index is out of bounds for size of LinkedList");
        }
        ListNode<T> dummyHead = head; // current node
        ListNode<T> previous = null; // node current is attached from
        int position = 0;
        while(position < index) {
            previous = dummyHead;
            dummyHead = dummyHead.next();
            position++;
        }
        ListNode<T> current = previous; // to be linked to new node
        ListNode<T> attached = current.next(); // new node will link to this node
        ListNode<T> newNode = new ListNode<>(value);
        current.setNext(newNode); // squeeze in new node. Current now links to new node
        newNode.setNext(attached); // links to next existing element in list
        size++;
    }

    public int indexOf(T value) {
        if(head == null) {
            throw new NullPointerException("List is empty. No values to return");
        }
        ListNode<T> dummyHead = head;
        int count = 0;
        while(dummyHead.data() != value) {
            count++;
            dummyHead = dummyHead.next();
        }
        return count;
    }

    public void addFirst(T value) {
        if(head == null) {
            head = new ListNode<>(value);
            return;
        }
        ListNode<T> firstNode = new ListNode<>(value);
        ListNode<T> temp = head;
        head = firstNode;
        firstNode.setNext(temp);
    }

    public void addAll(MyNodeList<T> list) {
        if(head != null) {
            head.insert(list.head);
        }
    }

    public ListNode<T> next() {
        if(head == null) {
            return null;
        }
        if(size == 1) {
            return null;
        }
       try {
           next = next.next();
           return next;
       } catch (NullPointerException e) {
           e.printStackTrace();
           return null;
       }
    }

    public int size() { // length of list
        return size;
    }

    public boolean contains(T value) {
        if(head != null ) {
            return  head.contains(value); // Recursion
        }
        return false;
    }

    public ListNode<T> get(T value) {
        if(head!=null) {
            return head.get(value); // Recursion
        }
        return null;
    }

    public ListNode<T> getNext() {
        if(head == null || next == null) {
            return null;
            //throw new NullPointerException("List is empty or only one node present");
        }
        return next;
    }

    public ListNode<T> head() { // return the start of the list
        if(head != null) {
            return head;
        }
        return null;
    }

    public T pop() { // delete last element in stack and return its value
        if(head == null) {
            return null;
            //throw new NullPointerException("List is empty");
        }
        if(head.next() == null) {
            ListNode<T> temp = head;
            head = null;
            return temp.data();
        }
        ListNode<T> current = null;
        ListNode<T> dummyHead = head;
        ListNode<T> previous = null;
        while(dummyHead != null) {
            previous = current; // set last element to null and become last element
            current = dummyHead; // to be set to null (last non null element)
            dummyHead = dummyHead.next(); // first full element
        }
        previous.setNext(null);
        return current.data(); // return removed data
    }

    public ListNode<T> delete(int position) {
        if(position > size) {
            throw new NullPointerException("position is out of bounds");
        }
        if(head == null) {
            throw new NullPointerException("List is empty");
        }
        ListNode<T> previous = head;
        int count = 0;
        while(count < position -1) {
            previous = previous.next();
            count++;
        }
        ListNode<T> current = previous.next();
        previous.setNext(current.next()); // previous points to currents next node, bypassing current
        current.setNext(null); // avoid duplication problems
        size--;
        return current;
    }

    public void resetNext() {
        next = head;
    }

    public T delete(T value) {
       if(head == null) {
           throw new NullPointerException("List is empty. No nodes to remove");
       }
       if(head.data() == value) {
           ListNode<T> temp = head;
           head = head.next();
           return temp.data();
       }
       ListNode<T> current = head;
       ListNode<T> previous = null;
       int count = 0;
       while(count < size) {
           if(current.data() == value) {
               break;
           }
           previous = current;
           current = current.next();
           count++;
       }
       if(count == size) {
           throw new NoSuchElementException("List does not contain value");
       }
       ListNode<T> temp = previous; // holds the previous value of the removed node
       temp.setNext(current.next()); // attaches next node of removed node to temp. Maintaining the chain
       current.setNext(null); // avoid duplicate problems
        return current.data();
    }

    public void remove() { // removes element at bottom of the stack
        if(head == null) {
            throw new NullPointerException("List is empty");
        }
        if(head.next() == null) {
            head = null;
        } else {
            head = head.next();
        }
        size--;
    }

    public MyIterator<T> myIterator() {
        if(head == null) {
            throw new NullPointerException("No elements to traverse in list");
        }
        MyNodeList<T> copy = new MyNodeList<>(); // avoid referencing problem
        ListNode<T> dummyHead = head;
        while(dummyHead != null) {
            copy.insert(dummyHead.data());
            dummyHead = dummyHead.next();
        }
        MyIterator<T> myIterator = new MyIterator<>();
        myIterator.setMyNodeList(copy); // add the copied list to my iterator
        return myIterator;
    }

    public void print() {
        if(head == null) {
            throw new NullPointerException("List is empty");
        }
        head.print();
    }
}
