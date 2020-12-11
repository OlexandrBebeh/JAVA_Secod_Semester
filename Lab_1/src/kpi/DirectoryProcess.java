package kpi;

import java.io.*;
import java.util.concurrent.ExecutorService;


public class DirectoryProcess{
    File directory;
    ExecutorService pool;

    DirectoryProcess(File f, ExecutorService p){
        this.directory = f;
        this.pool = p;
    }
    public boolean isTextFile(String name){
        return name.toLowerCase().endsWith(".txt");
    }

    Runnable swapFirstLast(File file){
        return () -> {
        try(BufferedReader br = new BufferedReader (new FileReader(file))){

            StringBuilder buffer = new StringBuilder();

            String str = br.readLine();
            String firstWord, lastWord;
            int i = 1;
            while(str != null){

                if(str.trim().indexOf(' ') > 0) {

                    for (; str.length() > i; i++) {
                        if (Character.isLetterOrDigit(str.charAt(i-1))) {
                            break;
                        }
                    }
                    buffer.append(str,0,i-1);

                    firstWord = str.substring(i-1, str.indexOf(' ', i));

                    str = str.substring(i-1, str.length());
                    str = str.trim();
                    i=1;
                    for (; str.length() > i; i++) {
                        if (Character.isLetterOrDigit(str.charAt(str.length() - i))) {
                            i--;
                            break;
                        }
                    }

                    lastWord = str.substring(str.lastIndexOf(' ') + 1, str.length() - i);

                    buffer.append(lastWord);
                    buffer.append(str, str.indexOf(' '), str.lastIndexOf(' ') + 1);
                    buffer.append(firstWord);
                    buffer.append(str, str.length() - i, str.length());

                    buffer.append("\n");

                    str = br.readLine();

                    i = 1;
                } else {
                    buffer.append(str).append("\n");
                    str = br.readLine();
                }
            }

            br.close();

            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(String.valueOf(buffer));
            System.out.println(file.getAbsoluteFile() + " === Replace complete");
            fileWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        };
    }

    Runnable start() {
        return () ->{
            File[] files = directory.listFiles();

            assert files != null;
            for (File f : files) {

                if (f.isDirectory()) {

                    DirectoryProcess directoryProcess = new DirectoryProcess(f,pool);

                    pool.submit(directoryProcess.start());

                } else if (isTextFile(f.getName())){
                        pool.submit(swapFirstLast(f));
                }
            }
        };
    }
}
