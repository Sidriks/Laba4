package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
public class Humans extends BackUper{
    ArrayList<Human> list;
    int capacity;

    public Humans() {
        this.list = new ArrayList<>();
    }

    public void add(Human human)
    {
        this.list.add(human);
    }
    @Override
    public void kryoSave(String path) throws FileNotFoundException {
        Kryo kryo = new Kryo();
        kryo.register(Human.class);

        Output output = new Output(new FileOutputStream("file.bin"));
        kryo.writeObject(output, list);
        output.close();
    }

    @Override
    public void kryoLoad(String path) throws FileNotFoundException {
        Kryo kryo = new Kryo();
        kryo.register(Human.class);
        Input input = new Input(new FileInputStream("file.bin"));
        list = kryo.readObjectOrNull(input,ArrayList.class);
        input.close();
        capacity = list.size();
        checkList();
    }
    public void checkList()
        {
            for (Human temp:this.list ) {
                System.out.println(ChronoUnit.DAYS.between(temp.getTermContract(), temp.getDateSignContract()));//negative number????
                if(Math.abs(ChronoUnit.DAYS.between(temp.getTermContract(), LocalDate.now()))<=5)
                {
                    continue;
                }
list.remove(list.indexOf(temp));
            }
        }
}
