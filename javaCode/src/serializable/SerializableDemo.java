package serializable;

import java.io.*;

public class SerializableDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

/*        Person person = new Person();
        person.setName("abc");
        person.setAge(100);

        File file = new File("d:/person.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(person);*/

      /*  ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/person_copy.txt"));
        Person p = (Person)ois.readObject();
        System.out.println(p);
*/
        // System.out.println("------------------顺便试一下文件流操作--------------------");

        InputStream is = new FileInputStream("d:/read.txt");
        OutputStream os = new FileOutputStream("d:/read_copy.txt");
        byte[] b = new byte[2];
        int length = 0;
        while ((length = is.read(b)) != -1) {
            System.out.println(new String(b, 0, length, "gbk"));
            os.write(b, 0, length);
        }
        //osw.flush();
        os.close();
        is.close();

       /* System.out.println("----------------顺便试一下文件拷贝----------------");

        File a= new File("d:/person.txt");
        File b = new File("d:/person_copy.txt");
        OutputStream os = new FileOutputStream(b);
        Files.copy(a.toPath(),os);*/

        /*copyFileUsingFileStreams(a,b);*/

    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }
}
