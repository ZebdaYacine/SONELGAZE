/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Zed-Yacine
 */
public class Document {

    private int id;
    private String name;

    public Document(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Document(String name) {
        this.name = name;
    }

    public Document(int id) {
        this.id = id;
    }

    public Document() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean isDataEmpty(Document docs) {
        return !docs.getName().isEmpty();
    }

}
