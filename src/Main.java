public class Main {

    public static void main(String[] args) {
        MyNodeList<String> myList = new MyNodeList<>();
        myList.insert("Cake");
        myList.insert("Book");
        myList.insert("Sand");
        myList.addFirst("First");

        MyNodeList<String> secondList = new MyNodeList<>();
        myList.insert("Cheese");
        myList.insert("Crackers");
        myList.insert("Bacon");

        myList.addAll(secondList);
        myList.insert("Carrot");

        myList.print();
        System.out.println();
        System.out.println(myList.getLast());
    }
}
