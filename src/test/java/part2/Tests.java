package part2;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class Tests {
    private SplayTree newTree;

    @BeforeEach
    public void setUp() {
        newTree = new SplayTree();
    }

    @Test
    public void emptyFindTest() {
        Assertions.assertThrows(NoSuchElementException.class, () -> newTree.find(1));
    }

    @Test
    public void emptyDeleteTest() {
        Assertions.assertThrows(NoSuchElementException.class, () -> newTree.delete(1));
    }


}
