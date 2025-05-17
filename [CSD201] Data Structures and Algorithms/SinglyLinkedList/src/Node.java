public class Node{
    int info; // Employee, Table, Person, Car, Student,...
    Node Next;
    
    public Node(){
        
    }

    public Node(int info, Node Next){
        this.info=info;
        this.Next=Next;
    }
}