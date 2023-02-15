package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FTPHelper {
    private final ApplicationManager app;
    private FTPClient ftp;

    public FTPHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));//соединение с хостом
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));//логин на хосте
        ftp.deleteFile(backup);//удаляем резервную копию(если она уже там есть)
        ftp.rename(target, backup);//переименовываем файл на сервере с таргета на бэкап
        ftp.enterLocalPassiveMode();//техническая манипуляция, которой с другим FTP сервером может и не понадобиться
        ftp.storeFile(target, new FileInputStream(file));//передаём локальный файл на сервак(FileInputStream - читает файл по байтам)
        ftp.disconnect();
    }
    //Параметры:
    //target - имя файла на сервере, которое должно быть у конфига
    //backup - имя резервной копии - этот файл мы переименуем в таргет и успокоимся
    public void restore(String backup, String target) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);
        ftp.disconnect();
    }
}
