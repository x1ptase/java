/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHAM KHAC VINH
 */
public class Song {
    protected String name;
    protected String artist;
    protected int rated;

    public Song(String name, String artist, int rated) {
        this.name = name;
        this.artist = artist;
        this.rated = rated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getRated() {
        return rated;
    }

    public void setRated(int rated) {
        this.rated = rated;
    }

    @Override
    public String toString() {
        return name + "|" + artist +"|" + rated;
    }
    
    
}
