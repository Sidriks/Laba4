package com.company;

import java.io.*;
import java.util.ArrayList;


public abstract class BackUper<E> {
    public void save(ArrayList<E> list, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(list);
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<E> load(String path) {
        ArrayList<E> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {

            list = ((ArrayList<E>) ois.readObject());
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
        return list;
    }
    public void kryoSave(String path) throws FileNotFoundException {}

    public void kryoLoad(String path) throws FileNotFoundException {}
}
