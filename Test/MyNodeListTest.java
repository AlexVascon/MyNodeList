import org.junit.Test;

import static org.junit.Assert.*;

public class MyNodeListTest {

    MyNodeList<String> list = new MyNodeList<>();
    @Test
    public void insert_Value() {
        // checks head, its next node, and next next node to see list is working
        list.insert("Cake");
        assertNotNull(list.head());
        assertEquals("Cake", list.head().data());
        list.insert("Carrot");
        assertEquals("Carrot", list.next().data());
        list.insert("Cheese");
        assertEquals("Cheese", list.next().data());

        // delete the head element and see if the next value becomes the head
        assertEquals("Cake", list.delete("Cake"));
        assertEquals("Carrot", list.head().data());

        // rests next position to traverse next from beginning
        list.resetNext();

        // check if new head is still connected to its next value
        assertEquals("Cheese", list.next().data());

        // delete middle node in list and check list still works
        list.insert("Bacon");
        list.insert("Sand");
        assertEquals("Cheese", list.delete("Cheese"));
        list.resetNext();
        assertEquals("Bacon", list.next().data());
        assertEquals("Sand", list.next().data());

        // remove the head element and check list still works
        list.remove();
        assertEquals("Bacon", list.head().data());
    }

    @Test
    public void insert_Node() {
        ListNode<String> node = new ListNode<>("Ham");
        ListNode<String> nodeNext = new ListNode<>("Bacon");
        ListNode<String> nextNextNode = new ListNode<>("Crackers");
        list.insert(node);
        assertEquals("Ham", list.head().data());
        list.insert(nodeNext);
        assertEquals("Bacon", list.next().data());
        list.insert(nextNextNode);
        assertEquals("Crackers", list.next().data());
        list.print();
    }

    @Test
    public void testClone() {
        list.insert("Ham");
        list.insert("Cheese");
        list.insert("Crackers");
        MyNodeList<String> copy = list.clone();
        assertEquals(copy.head(), list.head());
        assertEquals(copy.next(), list.next());
        assertEquals(copy.next(), list.next());
    }

    @Test
    public void get_ByValue() {
        list.insert("Cake");
        list.insert("Vodka");
        list.insert("Veal");
        assertEquals("Cake", list.get("Cake").data());
        assertEquals("Vodka", list.get("Vodka").data());
        assertEquals("Veal", list.get("Veal").data());
    }

    @Test
    public void isEmpty() {
        assertTrue(list.isEmpty());
        list.insert("Bacon");
        assertFalse(list.isEmpty());
        list.remove();
        assertTrue(list.isEmpty());
    }

    @Test
    public void getFirst() {
        list.insert("Cheese");
        assertEquals("Cheese", list.getFirst());
        list.insert("Bacon");
        assertEquals("Cheese", list.getFirst());
        assertNotEquals("Bacon", list.getFirst());

        list.remove();
        assertEquals("Bacon", list.getFirst());
        list.remove();
        assertNull(list.getFirst());

    }

    @Test
    public void getLast() {
        list.insert("Pancake");
        assertEquals("Pancake", list.getLast());

        list.insert("Ham");
        assertEquals("Ham", list.getLast());

        list.delete("Ham");
        assertEquals("Pancake", list.getLast());

        list.insert("Salt");
        list.insert("Beans");
        assertEquals("Beans", list.getLast());
    }

    @Test
    public void clear() {
        list.insert("Sand");
        list.insert("Rock");
        list.insert("Duck");
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void insert_Index() {
        // add next element to am empty list
        list.insert(1,"Bacon");
        assertTrue(list.isEmpty());

        list.insert(2, "Elephant");
        assertTrue(list.isEmpty());

        // add first element to an empty list
        list.insert(0,"Umbrella");
        assertFalse(list.isEmpty());

        // insert new node between two nodes
        list.insert("Candles");
        // list: 0-Umbrella, 1-Candles
        assertEquals(1, list.indexOf("Candles"));
        list.insert(1, "Car");
        // list: 0-Umbrella, 1-Car, 2-Candles
        assertEquals(1, list.indexOf("Car"));
        assertEquals(2, list.indexOf("Candles"));

        // insert at the first empty node
        list.insert(3, "Bike");
        assertEquals(3, list.indexOf("Bike"));

        // delete node between nodes and check order recalibration
        list.delete("Candles");
        // list: 0-Umbrella, 1-Car, 2-Bike
        assertEquals(2, list.indexOf("Bike"));

        list.insert("Candles");
        list.remove();
        // list: 0-Car, 1-Bike, 2-Candles
        assertEquals(0, list.indexOf("Car"));
    }

    @Test
    public void indexOf() {
        // check inserted values are retrievable in order
        list.insert("Cake");
        list.insert("Bacon");
        list.insert("Dreams");
        assertEquals(0, list.indexOf("Cake"));
        assertEquals(1, list.indexOf("Bacon"));
        assertEquals(2, list.indexOf("Dreams"));

        // delete a node and check order accordingly
        list.delete(1);
        // list: 0-Cake, 1-Dreams
        assertEquals(1, list.indexOf("Dreams"));

        // remove first node and check order accordingly
        list.insert("Bacon");
        list.insert("Onion");
        list.remove();
        // list: 0-Dreams, 1-Bacon, 2-Onion
        assertEquals(0, list.indexOf("Dreams"));
        assertEquals(1, list.indexOf("Bacon"));
        assertEquals(2, list.indexOf("Onion"));
    }

    @Test
    public void addFirst() {
        list.insert("Toast");
        assertEquals("Toast", list.head().data());
        list.insert("Candy");
        assertEquals("Toast", list.head().data());
        list.insert("Spoons");
        list.addFirst("Pudding");
        assertEquals("Pudding", list.head().data());
    }

    @Test
    public void addAll() {
        // add an array of elements to the end of the list

        MyNodeList<String> list2 = new MyNodeList<>();
        list2.insert("Bear");
        list2.insert("Cat");
        list2.insert("Mouse");

        list.insert("Apple");
        list.insert("Rice");
        list.insert("Chocolate");

        list.addAll(list2);
        assertEquals("Apple",list.head().data());
        assertEquals("Rice", list.next().data());
        assertEquals("Chocolate", list.next().data());
        assertEquals("Bear", list.next().data());
        assertEquals("Cat", list.next().data());
        assertEquals("Mouse", list.next().data());
    }

    @Test
    public void next() {
        // note: It can be difficult to manage next location if traversal has already started

        // empty list has no value
        assertNull(list.next());

        list.insert("Rhino");
        assertNull(list.next());

        // note: need to reset next even when null because its passed over to null element
        //list.resetNext();

        list.insert("Lion");
        assertEquals("Lion", list.next().data());
        list.insert("Snake");
        assertEquals("Snake", list.next().data());

        list.delete("Snake");

    }

    @Test
    public void contains() {
        list.insert("Bugs");
        list.insert("Daffy");
        list.insert("Porky");
        assertTrue(list.contains("Bugs"));
        assertTrue(list.contains("Daffy"));
        assertTrue(list.contains("Porky"));
        list.delete("Porky");
        assertFalse(list.contains("Porky"));
        list.insert("Porky");
        list.delete("Daffy");
        assertFalse(list.contains("Daffy"));
        list.insert("Daffy");
        list.remove();
        assertFalse(list.contains("Bugs"));
    }

    @Test
    public void get_ByIndex() {
        list.insert("Monkey");
        assertEquals("Monkey", list.get(0));
        list.insert("Tiger");
        assertEquals("Tiger", list.get(1));
        list.insert("Whale");
        assertEquals("Whale", list.get(2));

        list.delete("Tiger");
        assertEquals("Whale", list.get(1));
    }

    @Test
    public void getNext() {
        assertNull(list.getNext());
        list.insert("Computer");
        // note: for iteration start, the next value is always shown
        // the process is less complicated if next == head at start of list
        assertNotNull(list.getNext());

        // list traversal has not started yet so next is still pointed to head
        list.insert("Monitor");
        assertEquals("Computer", list.getNext().data());

        // traverse list calling .next() to move to next element
        // use .getNext() to check current next element without moving in list
        list.insert("Keyboard");
        list.next();
        assertEquals("Monitor", list.getNext().data());
        list.next();
        assertEquals("Keyboard", list.getNext().data());

        // no element left. Should return null;
        list.next();
        assertNull(list.getNext());
    }

    @Test
    public void head() {
        // empty list
        assertNull(list.head());
        list.insert("Fire");
        // first element is head
        assertNotNull(list.head());
        // remove first element (head)
        list.remove();
        // empty list
        assertNull(list.head());

        // head = "Fire"
        list.insert("Fire");
        list.insert("Water");
        list.insert("Earth");
        list.remove();
        // head = "Water"
        assertEquals("Water", list.head().data());
    }

    @Test
    public void pop() {
        //remove last item in que

        assertNull(list.pop());
        list.insert("Dragon");
        assertNotNull(list.head());
        list.pop();
        assertNull(list.head());

        list.insert("Wizard");
        list.insert("Elf");
        list.insert("Orc");
        list.pop();
        assertNull(list.get("Orc"));
        list.pop();
        assertNull(list.get("Elf"));
        list.pop();
        assertNull(list.get("Wizard"));
        assertNull(list.head());
    }

    @Test
    public void delete_Value() {
        // delete head element and check list is empty
        list.insert("Earth");
        assertNotNull(list.head());
        list.delete("Earth");
        assertTrue(list.isEmpty());

        // remove the head element and make the next link the new head
        list.insert("Earth");
        list.insert("Wind");
        list.insert("Fire");
        // list: Earth -> Wind -> Fire
        list.delete("Earth");
        // list: Wind -> Fire
        assertEquals("Wind", list.head().data());

        // remove middle element and check order is maintained accordingly
        list.insert("Earth");
        // list: Wind -> Fire -> Earth
        list.delete("Fire");
        // list: Wind -> Earth
        assertNull(list.get("Fire"));
        assertEquals(1, list.indexOf("Earth"));

    }

    @Test
    public void delete_Position() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void myIterator() {
    }

    @Test
    public void print() {
    }
}