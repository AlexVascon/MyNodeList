import org.junit.Test;

import static org.junit.Assert.*;

public class MyIteratorTest {

    @Test
    public void setMyNodeList() {
        MyNodeList<String> list = new MyNodeList<>();
        list.insert("Earth");
        list.insert("Wind");
        list.insert("Fire");
        list.insert("Water");

        MyIterator<String> iterator = list.myIterator();
        assertEquals(list.head().data(), iterator.getNext().data());
        int count = 0;
        while(count < list.size()) {
            assertEquals(list.getNext().data(), iterator.getNext().data());
            count++;
        }
        System.out.println(count);
    }

    @Test
    public void next() {
        // create and add list to iterator
        MyNodeList<String> list = new MyNodeList<>();
        list.insert("Earth");
        list.insert("Wind");
        list.insert("Fire");
        list.insert("Water");
        MyIterator<String> iterator = list.myIterator();

        // iterator should contain the same values as in list
        // and in the right order when comparing the .next() call against each other
        int count = 0;
        while(count < list.size()-1) {
            assertEquals(list.next().data(), iterator.next().data());
            count++;
        }
        // making sure each element was accounted for in while loop
        assertEquals(count, list.size() - 1);

        // delete current next element and next moves one down the list
        // list: Earth -> Wind -> Fire -> Water
        iterator.resetNext(); // Next = Earth
        iterator.next(); // Next = Wind
        iterator.delete(); // Next value removed. Set to next element if there is one
        // list: Earth -> Fire -> Water
        assertEquals("Fire", iterator.getNext().data());

    }

    @Test
    public void getPrevious() {
    }

    @Test
    public void getNext() {
    }

    @Test
    public void previous() {
    }

    @Test
    public void hasNext() {

    }

    @Test
    public void print() {
    }
}