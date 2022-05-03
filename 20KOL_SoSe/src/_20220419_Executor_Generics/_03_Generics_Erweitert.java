package _20220419_Executor_Generics;

import java.util.List;

public class _03_Generics_Erweitert {

    public static void main(String[] args) {

        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(2);
        list.add(5);
        list.add(5, 6);
        list.add(2, 6);
        list.add(0, 6);

        System.out.println("\n{ ---------- Full List ---------- }");
        System.out.println(list.toString());
        
        list.deleteAllOccurences(2);
        System.out.println("\n{ - List without deleted Values - }");
        System.out.println(list.toString());
    }
    
}

class MyLinkedList<D> {

    public Element<D> firstElement;

    public void add(D data){
        Element<D> newElement = new Element<>(data);
        if(firstElement == null){
            firstElement = newElement;
        }
        else {
            Element<D> lastElement = lastElement();
            lastElement.nextElement = newElement;
        }
        
    }
    
    public void add(int index, D data){
        Element<D> currentElement = firstElement;
        Element<D> newElement = new Element<>(data);
        
        if(index == 0){
            newElement.nextElement = currentElement;
            firstElement = newElement;
        } else {
            for(int i = 1; i < index; i++){
                currentElement = currentElement.nextElement;
            }
            if(currentElement != null){
                newElement.nextElement = currentElement.nextElement;
                currentElement.nextElement = newElement;
            }
        }
    }

    public void deleteAllOccurences(D data){
        Element<D> currentElement = firstElement;
        if(firstElement.data == data){
            firstElement = currentElement.nextElement;
        }

        while(currentElement.nextElement != null){
            if(currentElement.nextElement.data == data){
                if(currentElement.nextElement.nextElement != null){
                    currentElement.nextElement = currentElement.nextElement.nextElement;
                } else {
                    currentElement.nextElement = null;
                }
            } else {
                currentElement = currentElement.nextElement;
            }
        }
    }


    public Element<D> lastElement(){
        Element<D> currentElement = firstElement;
        while(currentElement.nextElement != null){
            currentElement = currentElement.nextElement;
        }
        return currentElement;
    }

    @Override
    public String toString(){
        String returnString = "[";
        Element<D> currentElement = firstElement;
        while(currentElement != null) {
            returnString += currentElement.data;
            if(currentElement.nextElement != null){
                returnString += ", ";
            }
            currentElement = currentElement.nextElement;
        }
        returnString += "]";
        return returnString;
    }

}

class Element<D> {
    public D data;
    public Element<D> nextElement;

    public Element(D data){
        this.data = data;
    }
}