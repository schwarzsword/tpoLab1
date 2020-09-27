package part2;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    private SplayTree tree;
    private SplayTree expectedTree;

    @BeforeEach
    void setUp() {
        tree = new SplayTree();
        expectedTree = new SplayTree();
    }

    @Test
    void emptyFindTest() {
        assertThrows(NoSuchElementException.class, () -> tree.find(1));
    }

    @Test
    void emptyDeleteTest() {
        assertThrows(NoSuchElementException.class, () -> tree.delete(1));
    }

    @Test
    void fullFindNoneTest() {
        TreeElement four = new TreeElement(4);
        TreeElement three = new TreeElement(3);
        TreeElement five = new TreeElement(5);
        tree.setRoot(four);
        four.setLeftChild(three);
        four.setRightChild(five);
        three.setParent(four);
        five.setParent(four);
        assertThrows(NoSuchElementException.class, () -> tree.find(1));
    }

    @Test
    void fullFindTest() {
        TreeElement three = new TreeElement(3);
        TreeElement four = new TreeElement(4);
        TreeElement five = new TreeElement(5);
        tree.setRoot(four);
        four.setLeftChild(three);
        four.setRightChild(five);
        three.setParent(four);
        five.setParent(four);
        TreeElement element = tree.find(5);
        assertEquals(5, element.getValue());
    }

    @Test
    void splayFindTest() {
        TreeElement one1 = new TreeElement(1);
        TreeElement two1 = new TreeElement(2);
        TreeElement three1 = new TreeElement(3);
        TreeElement four1 = new TreeElement(4);
        TreeElement five1 = new TreeElement(5);
        TreeElement six1 = new TreeElement(6);

        TreeElement one2 = new TreeElement(1);
        TreeElement two2 = new TreeElement(2);
        TreeElement three2 = new TreeElement(3);
        TreeElement four2 = new TreeElement(4);
        TreeElement five2 = new TreeElement(5);
        TreeElement six2 = new TreeElement(6);

        tree.setRoot(two1);
        two1.setLeftChild(one1);
        two1.setRightChild(three1);
        one1.setParent(two1);
        three1.setParent(two1);
        three1.setRightChild(five1);
        five1.setParent(three1);
        five1.setLeftChild(four1);
        five1.setRightChild(six1);
        four1.setParent(five1);
        six1.setParent(five1);

        tree.find(4);

        expectedTree.setRoot(four2);
        four2.setLeftChild(two2);
        four2.setRightChild(five2);
        two2.setParent(four2);
        five2.setParent(four2);
        two2.setLeftChild(one2);
        two2.setRightChild(three2);
        one2.setParent(two2);
        three2.setParent(two2);
        five2.setRightChild(six2);
        six2.setParent(five2);

        assertEquals(expectedTree, tree);
    }

    @Test
    void fullDeleteNoneTest() {
        TreeElement four = new TreeElement(4);
        TreeElement three = new TreeElement(3);
        TreeElement five = new TreeElement(5);
        tree.setRoot(four);
        four.setLeftChild(three);
        four.setRightChild(five);
        three.setParent(four);
        five.setParent(four);
        assertThrows(NoSuchElementException.class, () -> tree.delete(1));
    }

    @Test
    void insertFirstTest() {
        tree.insert(1);
        expectedTree.setRoot(new TreeElement(1));
        assertEquals(expectedTree, tree);
    }

    @Test
    void insertRightTest() {
        tree.setRoot(new TreeElement(2));
        tree.insert(1);
        TreeElement two = new TreeElement(2);
        TreeElement one = new TreeElement(1);
        expectedTree.setRoot(one);
        one.setRightChild(two);
        two.setParent(one);
        assertEquals(expectedTree, tree);
    }

    @Test
    void insertLeftTest() {
        tree.setRoot(new TreeElement(1));
        tree.insert(2);
        TreeElement two = new TreeElement(2);
        TreeElement one = new TreeElement(1);
        expectedTree.setRoot(two);
        two.setLeftChild(one);
        one.setParent(two);
        assertEquals(expectedTree, tree);
    }

    @Test
    void insertSplayTest() {
        TreeElement two1 = new TreeElement(2);
        TreeElement three1 = new TreeElement(3);

        TreeElement one2 = new TreeElement(1);
        TreeElement two2 = new TreeElement(2);
        TreeElement three2 = new TreeElement(3);

        tree.setRoot(three1);
        three1.setLeftChild(two1);
        two1.setParent(three1);
        tree.insert(1);

        expectedTree.setRoot(one2);
        one2.setRightChild(two2);
        two2.setParent(one2);
        two2.setRightChild(three2);
        three2.setParent(two2);

        assertEquals(expectedTree, tree);
    }

    @Test
    void properDeleteSingleTest() {
        tree.setRoot(new TreeElement(1));
        tree.delete(1);
        assertEquals(expectedTree, tree);
    }

    @Test
    void properDeleteWithoutLeftSubtreeTest() {
        TreeElement four = new TreeElement(4);
        TreeElement five = new TreeElement(5);
        tree.setRoot(four);
        four.setRightChild(five);
        five.setParent(four);
        tree.delete(4);
        expectedTree.setRoot(new TreeElement(5));
        assertEquals(expectedTree, tree);
    }

    @Test
    void properDeleteWithMergeTest() {
        TreeElement one1 = new TreeElement(1);
        TreeElement two11 = new TreeElement(2);
        TreeElement two12 = new TreeElement(2);
        TreeElement three11 = new TreeElement(3);
        TreeElement three12 = new TreeElement(3);
        TreeElement four1 = new TreeElement(4);

        TreeElement one2 = new TreeElement(1);
        TreeElement two21 = new TreeElement(2);
        TreeElement two22 = new TreeElement(2);
        TreeElement three21 = new TreeElement(3);
        TreeElement four2 = new TreeElement(4);

        tree.setRoot(three11);
        three11.setLeftChild(two11);
        three11.setRightChild(four1);
        two11.setParent(three11);
        four1.setParent(three11);
        two11.setLeftChild(two12);
        two11.setRightChild(three12);
        two12.setParent(two11);
        three12.setParent(two11);
        two12.setLeftChild(one1);
        one1.setParent(two12);

        tree.delete(3);

        expectedTree.setRoot(three21);
        three21.setLeftChild(two21);
        three21.setRightChild(four2);
        two21.setParent(three21);
        four2.setParent(three21);
        two21.setLeftChild(two22);
        two22.setParent(two21);
        two22.setLeftChild(one2);
        one2.setParent(two22);

        assertEquals(expectedTree, tree);
    }
}
