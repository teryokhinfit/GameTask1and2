package JavaCore.Flow.FlowFileWork2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        //1)Создать три экземпляра класса GameProgress.
        GameProgress user1 = new GameProgress(94, 10, 2, 254.32);
        GameProgress user2 = new GameProgress(80, 7, 4, 333.43);
        GameProgress user3 = new GameProgress(72, 5, 8, 555.45);

        //путь к папке
        String pathSG = "C:/Users/Public/Netology/src/Games/savedgames";

        ArrayList<GameProgress> userList = new ArrayList<>();//список объектов класса
        ArrayList<String> pathSGList = new ArrayList<>(); //список путей к файлу

        //передаём экземпляры в список
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        //создаём список со строками которые передадим в метод zip
        pathSGList.add(pathSG + "/save1.dat");
        pathSGList.add(pathSG + "/save2.dat");
        pathSGList.add(pathSG + "/save3.dat");

        //2)Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи.
        saveGame(pathSG, userList);

        //3)Созданные файлы сохранений из папки savegames запаковать в архив zip.
        zipFiles(pathSG,pathSGList);

        //4)Удалить файлы сохранений, лежащие вне архива
        deleteFile(pathSGList);
    }

    public static void saveGame(String pathSG, ArrayList<GameProgress> userList) {
        for (int i = 0; i < userList.size(); i++) {
            try (FileOutputStream fos = new FileOutputStream(pathSG + "/save" + (i + 1) + ".dat");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(userList.get(i));
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
        System.out.println("Файлы созданы");

    }

    public static void zipFiles(String pathSG, ArrayList<String> pathSGList) {
        try {
            ZipOutputStream zout = new ZipOutputStream(
                    new FileOutputStream(pathSG + "/zip.zip"));
            for (int i = 0; i < pathSGList.size(); i++) {
                FileInputStream fis = new FileInputStream(pathSGList.get(i));
                zout.putNextEntry(
                        new ZipEntry(pathSGList.get(i).substring(pathSG.length() + 1))); // +1  символ '/'

                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                fis.close();
                zout.flush();
            }
            zout.closeEntry();
            zout.close();
            System.out.println("Файлы были упакованы в архив zip.zip");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteFile(ArrayList<String> pathSGList) {
        for (int i = 0; i < pathSGList.size(); i++) {
            File save = new File(pathSGList.get(i));
            save.delete();
        }
        System.out.println("Файлы save находящиеся вне арзива были удалены");
    }
}
