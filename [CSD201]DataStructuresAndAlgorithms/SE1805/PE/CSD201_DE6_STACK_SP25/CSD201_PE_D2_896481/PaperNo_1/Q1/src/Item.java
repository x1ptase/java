/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Item {

    private String name;
    private int weight;
    private int length;
    private int fragile;

    public Item(String name, int weight, int length, int fragile) {
        this.name = name;
        this.weight = weight;
        this.length = length;
        this.fragile = fragile;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFragile() {
        return fragile;
    }

    public void setFragile(int fragile) {
        this.fragile = fragile;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + weight + ", " + length + ", " + fragile + ")";
    }
}
