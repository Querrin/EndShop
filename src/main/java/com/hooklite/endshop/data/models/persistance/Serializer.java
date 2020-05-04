package com.hooklite.endshop.data.models.persistance;

import java.io.*;

public class Serializer {
    public static Object deserialize(byte[] primitive) {
        ByteArrayInputStream bis = new ByteArrayInputStream(primitive);
        try {
            ObjectInput input = new ObjectInputStream(bis);
            Object obj = input.readObject();
            input.close();

            return obj;
        }
        catch(IOException | ClassNotFoundException ignore) {
            return null;
        }
    }

    public static byte[] serialize(Object o) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] obj = new byte[] {};

        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(o);
            obj = bos.toByteArray();
            bos.close();
        }
        catch(IOException ignored) {
        }
        return obj;
    }
}
