public class Main {

    public static void main(String[] args) {
        MyNodeList<String> myList = new MyNodeList<>();
        myList.insert("Cake");
        myList.insert("Book");
        myList.insert("Sand");
        myList.addFirst("First");
        myList.print();
    }
}
