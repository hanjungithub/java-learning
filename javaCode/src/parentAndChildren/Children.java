package parentAndChildren;

public class Children extends Parent {

    private static int i=10;

    static {
        System.out.println("static children");
    }
    private String name = "Children";

    public Children() {
        print();
    }

    public void print() {
        System.out.println("Children print:" + name);
    }

    public static void main(String[] args) {
        System.out.println(Children.i);
        //new Children();
        //System.out.println(c.name);
    }

}
