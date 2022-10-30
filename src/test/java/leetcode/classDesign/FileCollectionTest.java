package leetcode.classDesign;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileCollectionTest {

    @Test
    void findTopKCollections() {

        String[] input = {"file1.txt, 100",
                "file2.txt, 100, [Collection1]",
                "file3.txt, 100, [Collection1]",
                "file4.txt, 100, [Collection1]",
                "file5.txt, 100, [Collection2]",
                "file6.txt, 100, [Collection2]"
        };
        FileCollection fileCollection = new FileCollection();
        List<String> topKCollections = fileCollection.findTopKCollections(input, 2);

        assertEquals(2, topKCollections.size());
        assertEquals("Collection1", topKCollections.get(0));
        assertEquals("Collection2", topKCollections.get(1));
    }

    @Test
    void findTopKCollections2() {

        String[] input = {"file1.txt, 100",
                "file2.txt, 200, [Collection1]",
                "file3.txt, 200, [Collection1]",
                "file4.txt, 100, [Collection2]",
                "file5.txt, 400, [Collection2; Collection1]",
                "file6.txt, 300, [Collection2; Collection1]",
                "file7.txt, 100, [Collection1]",
                "file8.txt, 500"
        };
        FileCollection fileCollection = new FileCollection();
        List<String> topKCollections = fileCollection.findTopKCollections(input, 2);

        assertEquals(2, topKCollections.size());
        assertEquals("Collection1", topKCollections.get(0));
        assertEquals("Collection2", topKCollections.get(1));
    }

    @Test
    void findTotalSize() {

        String[] input = {"file1.txt, 100",
                "file2.txt, 200, [Collection1]",
                "file3.txt, 200, [Collection1]",
                "file4.txt, 100, [Collection2]",
                "file5.txt, 400, [Collection2; Collection1]",
                "file6.txt, 300, [Collection2; Collection1]",
                "file7.txt, 100, [Collection1]",
                "file8.txt, 500"
        };
        FileCollection fileCollection = new FileCollection();
        int totalFileSize = fileCollection.findTotalFileSize(input);

        assertEquals(1900, totalFileSize);
    }
}