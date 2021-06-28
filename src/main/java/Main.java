import kz.syllabus.entity.Syllabus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
//        ArrayList<Integer> list1 = new ArrayList<>();
//        Set<Integer> list = new HashSet<>();
//        Set<Integer> list1 = new HashSet<>();
//        list.add(5);
//        list1.add(5);
//        list.add(1);
//        list1.add(1);
//        if(list.equals(list1)) {
//            System.out.println("Equal!");
//        }
//        list.add(2);
//        list1.add(3);
//        list.add(3);
//        list1.add(2);
//        if(list.equals(list1)) {
//            System.out.println("Equal!");
//        }
        Syllabus syllabus = new Syllabus();
        Syllabus syllabus1 = new Syllabus();
        syllabus.setCompetences("Hello");
        syllabus1.setCompetences("Hello");
        if(syllabus.equals(syllabus1)) {
            System.out.println("Equal!");
        }
    }
}
