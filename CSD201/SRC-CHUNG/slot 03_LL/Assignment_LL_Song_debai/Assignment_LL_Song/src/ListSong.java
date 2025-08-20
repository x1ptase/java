
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PHAM KHAC VINH
 */
public class ListSong {

    SongNode head, tail;

    public void readFromFile(String fileName) {
        File file = new File(fileName);
        ArrayList<Song> listSongs = new ArrayList<>();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.err.println("IO Exception phan file");
            }
        }
        Song product;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String lineString = "";
            while (true) {
                //doc du lieu cua 1 dong
                lineString = bufferedReader.readLine();
                if (lineString == null) {
                    break;
                }
                String[] txt = lineString.split("[|]+");
                String name = txt[0].trim();
                String artist = txt[1].trim();
                int rated = Integer.parseInt(txt[2].trim());
                Song song = new Song(name, artist, rated);
                addLast(song, "y", 1);
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            try {
                fileReader.close();
                bufferedReader.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.err.println("File Not Found Exception in function " + "readFromFile");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("IO Exception cua ham buffereader trong readFromFile");
        }
    }

    public void writeFile(String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {

            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
            //lap tu gia tri dau tien den gia tri cuoi cung cua mang chuoi
            SongNode currentNode = head;
            while (currentNode != null) {
                bufferedWriter.write(currentNode.info.toString());
                bufferedWriter.newLine();
                currentNode = currentNode.next;

            }

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void addLast(Song x) {
        SongNode qNode = new SongNode(x);
        if (isEmpty()) {
            head = tail = qNode;
            return;
        }
        tail.next = qNode;
        tail = qNode;

    }

    void addLast(Song x, String name, int rated) {

        //neu nhu songname cuoi cung bang chu y or rated <= 1 
        // => return
        SongNode qNode = new SongNode(x);
        if (qNode.info.name.toLowerCase().endsWith(name.toLowerCase()) || qNode.info.rated <= rated) {
            return;
        } else {
            //them Song vao list
            if (isEmpty()) {
                head = tail = qNode;
                return;
            }
            tail.next = qNode;
            tail = qNode;
        }

    }

    boolean isEmpty() {
        return (head == null);
    }

    void traverse() {
        SongNode currentNode = head;

        while (currentNode != null) {
            System.out.println(currentNode.info);
            currentNode = currentNode.next;
        }

    }

    int size() {
        int count = 0;
        SongNode pNode = head;
        while (pNode != null) {
            count++;
            pNode = pNode.next;
        }
        return count;
    }

    void q1() {
        readFromFile("songs.txt");
    }

    void q2() {
        traverse();
    }

    void q3() {
        int total = 0;
        SongNode currentNode = head;

        while (currentNode != null) {
            total += currentNode.info.rated;

            currentNode = currentNode.next;
        }
        System.out.println(total / size());
    }

    void q4() {
        ListSong listSort = new ListSong();
        //add all element in currentList into List Sort 
        SongNode currentNode = head;

        while (currentNode != null) {
            listSort.addLast(currentNode.info);
            currentNode = currentNode.next;
        }
        
        //sort all elements by descending in listsort
        listSort.sortByAge();
        
        //initalize variables in list sort
        SongNode max = listSort.head;
        SongNode temp = new SongNode(new Song("", "", 0));
        int count = 3;
        
        
        //traverse all elements in list sort to find third highest rated
        SongNode iNode = listSort.head;

        while (iNode != null) {
            if (max.info.rated > iNode.info.rated &&
                    temp.info.rated != iNode.info.rated) {
                count--;
                temp = iNode;
            }
            if (count == 2) {
                break;
            }
            
            
            iNode = iNode.next;
        }
        removeAll(temp.info.rated);

    }

    void removeAll(int xAge) {
        SongNode pNode;
        while (true) {
            pNode = searchByAge(xAge);
            if (pNode == null) {
                break;
            }
            remove(pNode);
        }
    }

    SongNode searchByAge(int xAge) {
        SongNode pNode = head;
        while (pNode != null) {
            if (pNode.info.rated == xAge) {
                return pNode;
            }
            pNode = pNode.next;
        }

        return null;
    }

    void remove(SongNode q) {
        if (q == null) {
            return;
        }
        if (q == head) { // xoa not dau tien
            removeFirst();
            return;

        }
        // tim node F truoc node q
        SongNode fNode = head;
        while (fNode != null && fNode.next != q) {
            fNode = fNode.next;
        }
        if (fNode == null) { // q khong co trong list
            return;

        }
        // xoa q khoi list
        fNode.next = q.next;
        if (fNode.next == null) {
            tail = null;
        }

    }

    void removeFirst() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }

    }

    void sortByAge() {
        SongNode piNode, pjNode;
        Song temp;
        piNode = head;
        while (piNode != null) {
            pjNode = piNode.next;
            while (pjNode != null) {
                //dau > => ascending 
                //ddau < => descending
                if (piNode.info.rated < pjNode.info.rated) {
                    temp = piNode.info;
                    piNode.info = pjNode.info;
                    pjNode.info = temp;
                }
                pjNode = pjNode.next;
            }
            piNode = piNode.next;
        }

    }

    void q5() {
        writeFile("output.txt");
    }

    void q6() {
        SongNode currentNode1 = head;
        SongNode currentNode2 = head;
        while (currentNode1 != null) {
            while (currentNode2 != null) {
                if (currentNode1.info.rated > currentNode2.info.rated) {
                    removeAll(currentNode2.info.rated);
                }
                if (currentNode1.info.rated < currentNode2.info.rated) {
                    removeAll(currentNode1.info.rated);
                }
                currentNode2 = currentNode2.next;
            }

            currentNode1 = currentNode1.next;
        }

    }

    void q7() {
        traverse();
    }

}
