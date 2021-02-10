package practice.walmart;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TailCommand implements Runnable {

    boolean runnable = true;
    long currenPos = -1;
    File file;
    int noOfLines;

    TailCommand(String path, int noOfLines) {
        this.file = new File(path);
        this.noOfLines = noOfLines;
    }

    public void stop() {
        runnable = false;
    }

    @Override
    public void run() {

        while (runnable) {
            if (currenPos < file.length()) {

                if (file.length() - noOfLines > currenPos)
                    currenPos = file.length() - noOfLines;
                try {
                    RandomAccessFile rm = new RandomAccessFile(file, "r");
                    rm.seek(currenPos);
                    while (true) {
                        String line = rm.readLine();
                        if (null == line)
                            break;
                        System.out.println(line);
                    }
                    currenPos = rm.getFilePointer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
