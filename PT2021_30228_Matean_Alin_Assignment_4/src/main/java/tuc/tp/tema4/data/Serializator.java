package tuc.tp.tema4.data;

import tuc.tp.tema4.business.IDeliveryServiceProcessing;

import java.io.*;

public class Serializator {

    public static void serialize(Object object, String fileName) {
        try{
            FileOutputStream outputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Object deserialize(String fileName){
        Object object = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

}
