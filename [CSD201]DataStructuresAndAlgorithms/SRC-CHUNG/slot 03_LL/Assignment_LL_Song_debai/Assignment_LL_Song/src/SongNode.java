/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHAM KHAC VINH
 */
public class SongNode {
    Song info;
    SongNode next;

    public SongNode(Song info, SongNode next) {
        super();
        this.info = info;
        this.next = next;
    }

    public SongNode() {
        super();
    }

    public SongNode(Song info) {
        super();
        this.info = info;
        this.next = null;
    }
}
