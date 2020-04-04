package ca.ubco.cosc341.agconnect;

import java.io.Serializable;

public class UserList implements Serializable {

    private static final long serialVersionUID = 1L;

    //INNER CLASS
    private static class UserNode{
        User user;
        UserNode next;

        UserNode(User user) {
            this.user = user;
            this.next = null;
        }
    }

    //ATTRIBUTES
    private UserNode head, tail;
    private int size;

    //CONSTRUCTOR
    UserList(){
        head = null;
        tail = null;
        size = 0;
    }

    //METHODS
    int size(){
        return size;
    }

    private boolean isEmpty(){
        return size == 0;
    }

    private void addFirst(User user){
        UserNode newNode = new UserNode(user);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            newNode.next = head;
            //head.next;
            head = newNode;
        }
        size++;
    }

    private void addLast(User user){
        UserNode newNode = new UserNode(user);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            //newNode.next = null;
            tail = tail.next;
        }
        size++;
    }

    void add(int index, User user){
        checkIndexToAdd(index);
        if(isEmpty()){
            addFirst(user);
        }else if(index == size){
            addLast(user);
        }else{
            UserNode newNode = new UserNode(user);
            UserNode oneBeforeIdx = head;
            for(int i = 0; i <index-1; i++){
                oneBeforeIdx = oneBeforeIdx.next;
            }
            newNode.next = oneBeforeIdx.next;
            oneBeforeIdx.next = newNode;
            size++;
        }
    }

    private User getFirst(){
        if(isEmpty()){
            return null;
        }else{
            return head.user;
        }
    }

    private User getLast(){
        if(isEmpty()){
            return null;
        }else{
            return tail.user;
        }
    }

    User get(int index){
        checkIndex(index);
        if(index==0){
            return getFirst();
        }else if(index==size-1){
            return getLast();
        }else{
            UserNode current = head;
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            return current.user;
        }
    }

    User removeFirst(){
        if(isEmpty()){
            return null;
        }else{
            UserNode removedNode = head;
            head = head.next;
            if(head == null){
                tail = null;
            }
            size--;
            return removedNode.user;
        }
    }

    User removeLast(){
        if(isEmpty()){
            return null;
        }else if(size == 1){
            return removeFirst();
        }else{
            UserNode removedNode = tail;
            UserNode newTail = head;
            for(int i = 0; i<size-2; i++){
                newTail = newTail.next;
            }
            tail = newTail;
            tail.next = null;
            size--;
            return removedNode.user;
        }
    }

    private User remove(int index){
        checkIndex(index);
        if(index == 0){
            return removeFirst();
        }else if(index == size-1){
            return removeLast();
        }else{
            UserNode oneBeforeIdx = head;
            for (int i = 0; i < index-1; i++){
                oneBeforeIdx = oneBeforeIdx.next;
            }
            UserNode removedNode = oneBeforeIdx.next;
            oneBeforeIdx.next = removedNode.next;
            size--;
            return removedNode.user;
        }
    }

    User remove(String name){ // NOTE!!!!!!!! this is being used to fake it. If someone had the same name, it would not work.
        UserNode searchNode = head;
        int index = -1;
        for(int i = 0; i < size-1; i++){
            if(searchNode.user.getName().equals(name)){
                index = i;
                break;
            }else{
                searchNode = searchNode.next;
            }
        }
        if (index == -1) {
            return null;
        }else{
            return remove(index);
        }
    }

    //HELPER METHODS
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


}
