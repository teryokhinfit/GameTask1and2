package JavaCore.Flow.FlowFileWork1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //строка которая собирает лог
        StringBuilder sB = new StringBuilder();

        //чтобы покрасивее было
        String path = "C:/Users/Public/Netology/src/Games";

//      1)В папке Games создайте несколько директорий: src, res, savegames, temp.
        File dirSrc = new File(path, "/src");
        File dirRes = new File(path, "/res");
        File dirSG = new File(path, "/savedgames");
        File dirT = new File(path, "/temp");

        if (dirSrc.mkdir()
                && dirRes.mkdir()
                && dirSG.mkdir()
                && dirT.mkdir()) {
            System.out.println("Каталоги были созданы");
        }
        sB.append("1)Каталоги 'src, res, savegames, temp' были созданы");


//      2)В каталоге src создайте две директории: main, test
        File dirMain = new File(dirSrc.getPath() + "/main");
        File dirTest = new File(dirSrc.getPath() + "/test");
        if (dirMain.mkdir() && dirTest.mkdir()) {
            System.out.println("Каталоги были созданы");
        }
        sB.append("\n2)Каталоги 'main, test' были созданы");

//      3) В подкаталоге main создайте два файла: Main.java, Utils.java.
        File fMain = new File(dirMain.getPath() + "/Main.java");
        File fUtils = new File(dirMain.getPath() + "/Utils.java");
        try {
            if (fMain.createNewFile() && fUtils.createNewFile()) {
                System.out.println("Файлы были созданы");
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        sB.append("\n3)Файлы 'Main.java, Utils.java' были созданы");

//      4) В каталоге res создайте три директории: drawables, vectors, icons.
        File dr = new File(dirRes.getPath() + "/drawables");
        File vec = new File(dirRes.getPath() + "/vectors");
        File ic = new File(dirRes.getPath() + "/icons");
        if (dr.mkdir() && vec.mkdir() && ic.mkdir()) {
            System.out.println("Каталоги были созданы");
        }
        sB.append("\n4)Каталоги 'drawables, vectors, icons' были созданы");

//      5)В директории temp создайте файл temp.txt.
        File fTemp = new File(dirT.getPath() + "/temp.txt");
        try {
            if (fTemp.createNewFile()) {
                System.out.println("Файл был создан");
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        sB.append("\n5)Файл 'temp.txt' был создан");


//      6)Возьмите текст из StringBuilder запишите его в файл temp.txt с помощью класса FileWriter
        try (FileWriter writer = new FileWriter (fTemp, false)) {
            writer.write (String.valueOf (sB));
            writer.flush ();
        } catch (IOException ex) {
            System.out.println (ex.getMessage ());
        }

    }
}
