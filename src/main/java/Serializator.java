import java.io.*;

public class Serializator<T> {



        public void serializeObject(String filename, T object) throws IOException {
            FileOutputStream outFile = new FileOutputStream(filename);
            ObjectOutputStream outObject = new ObjectOutputStream(outFile);
            outObject.writeObject(object);
            outFile.close();
            outObject.close();
        }

        public T deserializeObject(String filename) throws ClassNotFoundException, IOException {
            FileInputStream inFile = new FileInputStream(filename);
            ObjectInputStream inObject = new ObjectInputStream(inFile);
            T obj = (T) inObject.readObject();
            inFile.close();
            inObject.close();
            return obj;
        }


}
