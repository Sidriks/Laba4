package com.company;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class VinylRecords extends BackUper {
    ArrayList<VinylRecord> list;
    VinylRecords(){
        this.list = new ArrayList<>();
    }
    @Override
    public void kryoSave(String path) throws FileNotFoundException {
        Kryo kryo = new Kryo();
        kryo.register(VinylRecord.class);

        Output output = new Output(new FileOutputStream("file.bin"));
        kryo.writeObject(output, list);
        output.close();
    }

    @Override
    public void kryoLoad(String path) throws FileNotFoundException {
        Kryo kryo = new Kryo();
        kryo.register(VinylRecord.class);
        Input input = new Input(new FileInputStream("file.bin"));
        list = kryo.readObject(input, ArrayList.class);
        input.close();
    }
}
