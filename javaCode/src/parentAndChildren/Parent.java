package parentAndChildren;

public class Parent {

    private static int x = 11;

    static {
        System.out.println("Parent children");
    }
    public String name = "parent";

    public Parent() {
        this.print();
        System.out.println(44);
    }

    public void print() {
        System.out.println("parent print:" + name);
    }
}
