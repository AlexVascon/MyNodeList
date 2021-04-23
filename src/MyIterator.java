import java.util.Scanner;

public class MyIterator<T> {
    private MyNodeList<T> myNodeList;
    private ListNode<T> next;
    private ListNode<T> dummyHead;
    private boolean start = false;
    private ListNode<T> previous;
    private boolean end = false;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MyNodeList<String> list = new MyNodeList<>();
        list.insert("Earth");
        list.insert("Wind");
        list.insert("Fire");
        list.insert("Water");
        MyIterator<String> iterator = list.myIterator();
        iterator.traverse();
    }

    // set up involves a default constructor
    // A linkedList method creates and returns an iterator
    // .setMyNodeList(add list head)  is called within the method
    // iterator now contains an instance of LinkedList
    public void setMyNodeList(MyNodeList<T> myNodeList) {
        this.myNodeList = myNodeList;
        next = myNodeList.head();
    }

    // it works, next, previous, delete
    public void traverse() {
        while(hasNext()) {
            System.out.println("Current element: " + next);
            System.out.println("[1] forward");
            System.out.println("[2] backward");
            System.out.println("[3] delete");
            System.out.println("[4] list values");
            int choice = Integer.parseInt(scanner.nextLine());
            if(choice == 1) {
                next();
            }
            if(choice == 2) {
                previous();
            }
            if(choice == 3) {
                delete();
            }
            if(choice == 4) {
                print();
            }
        }
    }

    // restart next position in list back to start
    public void resetNext() {
        next = myNodeList.head();
    }

    // move to next element in list
    public ListNode<T> next() {
        if(next != null) {
            previous = next;
            next = next.next();
            return next;
        }
        return null;
    }

    // delete current next element
    public ListNode<T> delete() {
        ListNode<T> temp = next;
        ListNode<T> nextNode = next;
        nextNode = nextNode.next();
        if(nextNode == null && next != null) {
            previous();
            previous.setNext(next);
            return next;
        }
        previous.setNext(nextNode);
        next = nextNode;
        return temp;
    }

    // return previous without changing any instance states
    public ListNode<T> getPrevious() {
        if(myNodeList.isEmpty() || previous == null) {
            throw new NullPointerException("List is empty or at the start of the list");
        }
        // return previous without moving to previous node
        return previous;
    }

    // return next without changing any instance states
    public ListNode<T> getNext() {
        if(next != null) {
            System.out.println(next);
            return next;
        }
        return null;
    }

    // sets previous just before next node
    public ListNode<T> previous() {
        if(myNodeList.isEmpty() || next == null) {
            throw new NullPointerException("List is empty or only contains 1 node");
        }
        // create duplicate list
        // still referenced and effects original list
        // used to avoid problems
        dummyHead = myNodeList.head();
        while(dummyHead != null) {
            // check value after previous equals next
            if(dummyHead == next) {
                // moving to previous node
                // next is set to previous, but when .next() is called it -
                // - will move to the next node after previous
                next = previous;
                // move to and retrieve previous value
                return previous;
            }
            // previous is set to current node
            previous = dummyHead;
            // current is set to next node
            dummyHead = dummyHead.next();
        }
        return null;
    }

    public boolean hasNext() {
        if(!start) {
            // used to start list without complications
            // only used once to start iterator
            start = true;
            return myNodeList.head() != null;
        }
        // check if there is another element in the list
        return next != null;
    }

    public void print() {
        // recursion
        // inception
        // .print() calls .print() in MyNodeList.Class which calls .print() in ListNode.Class on head node
        // each node prints its data then calls the method on itself.
        myNodeList.print();
    }
}
