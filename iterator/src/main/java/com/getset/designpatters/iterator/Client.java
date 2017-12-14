package com.getset.designpatters.iterator;

public class Client {
    public static void main(String[] args) {
        // Java SDK
        String[] strings = new String[]{"Hello,", "Java", "Design", "Patterns."};
        java.util.List<String> stringList = java.util.Arrays.asList(strings);
        java.util.Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        // This example
        ArrayList<String> list = new ArrayList<String>(16);
        list.add("Hello,");
        list.add("Java");
        list.add("Design");
        list.add("Patterns.");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }
}
