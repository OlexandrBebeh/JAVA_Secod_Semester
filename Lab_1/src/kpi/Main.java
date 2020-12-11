package kpi;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please, enter directory ==> \n Example D:\\TEST\\S");
            String str = scanner.next();
            File dir = new File(str);
            if(dir.isDirectory()){
                ExecutorService pool = Executors.newCachedThreadPool();
                DirectoryProcess directoryProcess = new DirectoryProcess(dir,pool);
                pool.submit(directoryProcess.start());
                break;
            }
            System.err.println("This path is incorrect! Pleas enter correct path of real directory!");
        }
    }
}
