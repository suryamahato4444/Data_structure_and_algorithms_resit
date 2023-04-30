//QUESTION-9-12
package lang;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileManager {

    private List<File> fileList;
    private ExecutorService executor;

    public FileManager() {
        fileList = new ArrayList<File>();
        executor = Executors.newFixedThreadPool(10);
    }

    public void addDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                } else if (file.isDirectory()) {
                    addDirectory(file);
                }
            }
        }
    }

    public List<File> searchFiles(String keyword) throws InterruptedException {
        List<File> resultFiles = new ArrayList<File>();
        for (final File file : fileList) {
            executor.execute(new Runnable() {
                public void run() {
                    if (file.getName().contains(keyword)) {
                        resultFiles.add(file);
                    }
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        return resultFiles;
    }

    public void sortFilesByName() {
        Collections.sort(fileList, new Comparator<File>() {
            public int compare(File f1, File f2) {
                return f1.getName().compareTo(f2.getName());
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        FileManager fileManager = new FileManager();

        // add directories
        fileManager.addDirectory(new File("/path/to/directory1"));
        fileManager.addDirectory(new File("/path/to/directory2"));

        // search for files
        List<File> resultFiles = fileManager.searchFiles("keyword");

        // sort files by name
        fileManager.sortFilesByName();

        // print out the sorted list of files
        for (File file : resultFiles) {
            System.out.println(file.getName());
        }
    }
}
