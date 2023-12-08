import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class temp {
        public static void main(String[] args) {
            String one = "A";
            String two = "B";
            String three = "C";
            ArrayList<String> list = new ArrayList<>();
            list.add(one);
            list.add(two);
            list.add(three);
            Collections.sort(list,Collections.reverseOrder());
            for(String s : list){
                System.out.println(s);
            }
            System.out.println();
            System.out.println(list.get(0));
        }
    }
