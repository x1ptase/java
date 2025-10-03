/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Models.DAO;

import Models.Entities.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author x1pta
 */
public class BookDAO {
    private static List<Book> bookList=null;
    public BookDAO(){
        bookList=new ArrayList<>();
        Book book01=new Book("B001", "C#", 10.5);
        Book book02=new Book("B002", "Java", 11.5);
        Book book03=new Book("B003", "Spring", 12.5);
        Book book04=new Book("B004", "JSP", 13.5);
        Book book05=new Book("B005", "Ruby", 14.5);
        Collections.addAll(bookList, book01, book02, book03, book04, book05);
    }
    
    public List<Book> getBookList(){
        return bookList;
    }
    
    public Book getBookById(String id){
        Book book=bookList.stream().filter(b->b.getId().trim().
                equals(id.trim())).findAny().orElse(null);
        return book;
    }
}
