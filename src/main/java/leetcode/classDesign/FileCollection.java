package leetcode.classDesign;

/*
* Given a list of [FileName, FileSize, [Collection]] - Collection is optional, i.e., a collection can have 1 or more files. Same file can be a part of more than 1 collection.
How would you design a system

To calculate total size of files processed.
To calculate Top K collections based on size.
Example:
file1.txt(size: 100)
file2.txt(size: 200) in collection "collection1"
file3.txt(size: 200) in collection "collection1"
file4.txt(size: 300) in collection "collection2"
file5.txt(size: 100)
Output:

Total size of files processed: 900
Top 2 collections:

collection1 : 400
collection2 : 300
*
* */

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class Collection implements Comparable<Collection> {
    String name;
    List<File> files;

    public Collection(String name) {
        this.name = name;
        files = new ArrayList<>();
    }

    public void addFiles(String name, int size) {
        this.files.add(new File(name, size));
    }

    public int totalSize() {
        return this.files.stream().map(f -> f.size).reduce(0, (acc, size) -> acc + size);
    }

    @Override
    public int compareTo(@NotNull Collection o) {
        return o.totalSize() - this.totalSize();
    }
}

class File {
    String name;
    int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        return this.name.equals(((File) o).name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}

class InputParser {

    public static final String DEFAULT = "DEFAULT";

    public static List<Collection> parse(String[] input) {

        Map<String, Collection> collectionMap = new HashMap<>();
        for (String s : input) {
            String[] split = s.replaceAll(" ", "").split(",");
            String filename = split[0];
            int size = Integer.parseInt(split[1]);
            if (split.length > 2) {
                String[] collections = split[2].replace("[", "").replace("]", "").split(";");
                for (String c : collections) {
                    Collection collection = collectionMap.getOrDefault(c, new Collection(c));
                    collection.addFiles(filename, size);
                    collectionMap.put(c, collection);
                }
            } else {
                Collection collection = collectionMap.getOrDefault(DEFAULT, new Collection(DEFAULT));
                collection.addFiles(filename, size);
                collectionMap.put(DEFAULT, collection);
            }
        }

        return collectionMap.values().stream().collect(Collectors.toList());
    }
}

public class FileCollection {

    public List<String> findTopKCollections(String[] input, int k) {
        PriorityQueue<Collection> sortedCollections = new PriorityQueue<>();
        sortedCollections.addAll(InputParser.parse(input));
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(sortedCollections.poll().name);
        }
        return result;
    }

    public Integer findTotalFileSize(String[] input) {
        List<Collection> collections = InputParser.parse(input);
        Set<File> files = collections.stream().flatMap(c -> c.files.stream()).collect(Collectors.toSet());
        Integer sum = files.stream().map(f -> f.size).reduce(0, (acc, size) -> acc + size);
        return sum;
    }

}
