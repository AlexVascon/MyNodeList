public class MyIterator<T> {
    private MyNodeList<T> myNodeList;
    private ListNode<T> next;
    private ListNode<T> dummyHead;
    private boolean start = false;
    private ListNode<T> previous;
    private boolean end = false;

    public void setMyNodeList(MyNodeList<T> myNodeList) {
        // add existing node list with values
        this.myNodeList = myNodeList;
    }

    public ListNode<T> next() {
        if(next == null) {
            // initialise next
            next = myNodeList.next();
        } else {
            // move to next node
            next = next.next();
        }
        return next;
    }

    public ListNode<T> getPrevious() {
        // return previous without moving to previous node
        return previous;
    }

    public ListNode<T> previous() {
        // create duplicate list
        // still referenced and effects origional list
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
        // .print() calls .print() which calls .print() on head node
        // each node prints its data then moves to the next node
        myNodeList.print();
    }
}
