public class Node{
    int info; // Employee, Table, Person, Car, Student,...
    Node next;
    
    public Node(){
    }

    public Node(int info, Node next){
        this.info=info;
        this.next=next;
    }
}