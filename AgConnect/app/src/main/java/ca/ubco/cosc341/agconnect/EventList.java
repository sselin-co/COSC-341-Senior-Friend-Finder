package ca.ubco.cosc341.agconnect;

import java.io.Serializable;

class EventList implements Serializable {

    private static final long serialVersionUID = 1L;

    //INNER CLASS
    private static class EventNode{
        Event event;
        EventNode next;

        EventNode(Event event) {
            this.event = event;
            this.next = null;
        }
    }

    //ATTRIBUTES
    private EventNode head, tail;
    private int size;

    //CONSTRUCTOR
    EventList(){
        head = null;
        tail = null;
        size = 0;
    }

    //STANDARD METHODS
    int size(){
        return size;
    }

    private boolean isEmpty(){
        return size == 0;
    }

    private void addFirst(Event event){
        EventNode newNode = new EventNode(event);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            newNode.next = head;
            //head.next;
            head = newNode;
        }
        size++;
    }

    private void addLast(Event event){
        EventNode newNode = new EventNode(event);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            //newNode.next = null;
            tail = tail.next;
        }
        size++;
    }

    void add(int index, Event event){
        checkIndexToAdd(index);
        if(isEmpty()){
            addFirst(event);
        }else if(index == size){
            addLast(event);
        }else{
            EventNode newNode = new EventNode(event);
            EventNode oneBeforeIdx = head;
            for(int i = 0; i <index-1; i++){
                oneBeforeIdx = oneBeforeIdx.next;
            }
            newNode.next = oneBeforeIdx.next;
            oneBeforeIdx.next = newNode;
            size++;
        }
    }

    private Event getFirst(){
        if(isEmpty()){
            return null;
        }else{
            return head.event;
        }
    }

    private Event getLast(){
        if(isEmpty()){
            return null;
        }else{
            return tail.event;
        }
    }

    Event get(int index){
        checkIndex(index);
        if(index==0){
            return getFirst();
        }else if(index==size-1){
            return getLast();
        }else{
            EventNode current = head;
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            return current.event;
        }
    }


    private void checkIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
    }
    private void checkIndexToAdd(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
    }

    EventList sortEventsByLocation(){
        EventList sortedList = new EventList();
        for(int idx = 0; idx < this.size(); idx++){
            sortedList.add(idx, this.get(idx));
        }

        EventNode current = sortedList.head, index;
        Event temp;
        if(current==null){
            return null;
        }else{
            while(current!=null){
                index = current.next;
                while(index!=null){
                    int result = current.event.getEventLocation().compareTo(index.event.getEventLocation());
                    if(result > 0){
                        temp = current.event;
                        current.event = index.event;
                        index.event = temp;

                    }
                    index = index.next;

                }
                current = current.next;

            }
        }
        return sortedList;
    }

    EventList sortEventsByPrice(){
        EventList sortedList = new EventList();
        for(int idx = 0; idx < this.size(); idx++){
            sortedList.add(idx, this.get(idx));
        }

        EventNode current = sortedList.head, index;
        Event temp;
        if(current==null){
            return null;
        }else{
            while(current!=null){
                index = current.next;
                while(index!=null){
                    double result = current.event.getEventPrice()-index.event.getEventPrice();
                    if(result > 0){
                        temp = current.event;
                        current.event = index.event;
                        index.event = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }
        return sortedList;
    }

}
