public class Main {

    public static void main(String[] args) {
        MyNodeList<String> myList = new MyNodeList<>();
        myList.insert("Cake");
        myList.insert("Book");
        myList.insert("Sand");
        System.out.println(myList.indexOf("Sand"));
        System.out.println(myList.indexOf("Cake"));
    }
}
