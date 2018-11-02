package parentAndChildren;

public class Children extends Parent {

    private String name = "Children";

    public Children() {
        print();
    }

    public void print() {
        System.out.println("Children print:" + name);
    }

    public static void main(String[] args) {
        Children c = new Children();
        System.out.println(c.name);
    }

}
