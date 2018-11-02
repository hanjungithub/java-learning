package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CollectionDemo {
    public static void main(String[] args) {
        List c = new ArrayList();
        ((ArrayList) c).add("a");
        ((ArrayList) c).add("c");
        ((ArrayList) c).add("b");

        /*Collection b = new ArrayList();
        ((ArrayList) b).add("a");
        ((ArrayList) b).add("b");
        ((ArrayList) b).add("d");
        Iterator iterator = c.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for(Iterator iterator1 = c.iterator();iterator1.hasNext();){
            System.out.println(iterator1.next());
        }

        boolean flag = c.retainAll(b);
        System.out.println(flag);
        Iterator iterator2 = c.iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }

        System.out.println(c.toArray());

        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("c");
        linkedHashSet.add("b");
        linkedHashSet.add("a");
        Iterator iterator3=linkedHashSet.iterator();
        while (iterator3.hasNext()){
            System.out.println(iterator3.next());
        }
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String a = (String)o1;
                String b = (String)o2;
                return b.compareTo(a);
            }
        };

        TreeSet treeSet = new TreeSet(comparator);
        treeSet.add("c");
        treeSet.add("b");
        treeSet.add("a");
        treeSet.add("z");
        treeSet.add("x");

        Iterator iterator4=treeSet.iterator();
        while (iterator4.hasNext()){
            System.out.println(iterator4.next());
        }
        System.out.println("-----------------------------Map---------------------------");
        Map map = new HashMap();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");

        Set set = map.keySet();
        Iterator mi = set.iterator();
        while (mi.hasNext()){
            String key = (String)mi.next();
            System.out.println("key:"+key+"-->value:"+map.get(key));
        }
        Set set1 = map.entrySet();
        Iterator mientry = set1.iterator();
        while(mientry.hasNext()){
            Map.Entry  me =(Map.Entry)mientry.next();
            System.out.println("entryKey:"+me.getKey()+"-->entryValue:"+me.getValue());
        }*/

        System.out.println("-----------------------------Collections工具类---------------------------");

        Collections.sort(c);
        //System.out.println(Collections.max(c));
        System.out.println(Collections.binarySearch(c, "b"));
        //Collections.reverse(c);
        Iterator iterator5 = c.iterator();
        while (iterator5.hasNext()) {
            System.out.println(iterator5.next());
        }


    }
}
