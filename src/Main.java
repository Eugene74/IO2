/*
2. Напишите программу которая примет на вход 2 текстовых
файла, а вернет один. Содержимым этого файла должны
быть слова которые есть и в первом и во втором файле.
* */

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException {
        String src1 = "text1.txt";
        String src2 = "text2.txt";
        String dest = "text3.txt";

        createNewFile(src1);
        createNewFile(src2);
        createNewFile(dest);
        Vector<FileInputStream> srcFiles = new Vector<FileInputStream>();
        srcFiles.add(new FileInputStream(src1));
        srcFiles.add(new FileInputStream(src2));
        copyFilesToSingleFile(srcFiles, dest);
    }

    private static void createNewFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private static void copyFilesToSingleFile(Vector<FileInputStream> srcFiles, String dest) throws IOException {
        Enumeration<FileInputStream> elements = srcFiles.elements();

        System.out.println(srcFiles.size()); //узнал сколько элементов записалось
        System.out.println(elements.hasMoreElements()); //узнал есть ли элементы

        SequenceInputStream seq = new SequenceInputStream(elements);

        //	FileInputStream in1=new FileInputStream("D:\Java\Catalogs\Catalog1\\1.txt");
        //	FileInputStream in2=new FileInputStream("D:\Java\Catalogs\Catalog1\\2.txt");
        //  FileInputStream in3=new FileInputStream("D:\Java\Catalogs\Catalog1\\3.txt");
        //SequenceInputStream seq=new SequenceInputStream (in1,in2);// можно только 2 потока передать или через Enumeration как и реализовано в данном решении

        try {
            FileOutputStream out = new FileOutputStream(dest);
            try {
                int a;
                while (true) {
                    a = seq.read();
                    if (a == -1) break;
                    out.write(a);
                }
            } finally {
                out.close();
            }
        } finally {
            seq.close();
        }
//	in1.close();
//	in2.close();
//	in3.close();
    }
}

/*
 try (FileInputStream fis = new FileInputStream(scr1);
                 FileOutputStream fos = new FileOutputStream(dest,true)) {
                byte[] buffer = new byte[1024];
                int byteread = 0;
                for (; (byteread = fis.read(buffer)) > 0;) {
                    fos.write(buffer, 0, byteread);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
* */