package serializable;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 7592930394427200495L;

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name:" + this.getName() + "###age:" + this.getAge();
    }
}
