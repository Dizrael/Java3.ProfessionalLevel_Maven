package lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class MainL3 {
    public static void main(String[] args) throws IOException {
        //task1
//        File f1 = new File("src/main/java/lesson3/File1.txt");
//        System.out.println(f1.length());
//
//        try(FileInputStream fis = new FileInputStream("src/main/java/lesson3/File1.txt")){
//            byte[] arr = new byte[512];
//            int x;
//            while((x=fis.read(arr)) != -1){
//                System.out.print(new String(arr, 0, x));
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        //task2
//        ArrayList<InputStream> ali = new ArrayList<>();
//        ali.add(new FileInputStream("src/main/java/lesson3/File1.txt"));
//        ali.add(new FileInputStream("src/main/java/lesson3/File2.txt"));
//        ali.add(new FileInputStream("src/main/java/lesson3/File3.txt"));
//        ali.add(new FileInputStream("src/main/java/lesson3/File4.txt"));
//        ali.add(new FileInputStream("src/main/java/lesson3/File5.txt"));
//
//        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));
//        int x;
//        while((x = in.read()) != -1){
//            System.out.print((char) x);
//        }
//        in.close();

        //task3
        File bigFile = new File("src/main/java/lesson3/BigFile.txt");
        printPage(bigFile, 354);
    }

    public static void printPage(File file, int pageNumber) {
        try(RandomAccessFile raf = new RandomAccessFile(file, "r")) {

            float pageSize = 1800f;
            double pageCount = Math.ceil(file.length() / pageSize);

            //Проверка на выход страницы за допустимый диапазон
            if (pageNumber > pageCount) {
                System.err.println("В файле нет страницы с номером " + pageNumber);
                System.err.println("Всего страниц в файле: " + (int) pageCount);
                return;
            }

            long t = System.currentTimeMillis();
            ArrayList<Byte> page = new ArrayList<>();
            int startPosition = (pageNumber-1) *  (int)pageSize;

            for (long i = startPosition; i < startPosition + (int)pageSize; i++) {
                raf.seek(i);
                System.out.print((char)raf.read());
            }
            System.out.println("\n\n" + (System.currentTimeMillis() - t));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
