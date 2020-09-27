package part2;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.NoSuchElementException;

@EqualsAndHashCode
@Data
public class SplayTree {
    private TreeElement root;

    public void insert(int value) {
        TreeElement element = root;
        TreeElement parent = null;
        do {
            if (element == null) {
                element = new TreeElement(value);
                element.setParent(parent);
                if (parent != null) {
                    if (value < parent.getValue()) {
                        parent.setLeftChild(element);
                    } else {
                        parent.setRightChild(element);
                    }
                }
            } else {
                parent = element;
                if (value < element.getValue()) {
                    element = element.getLeftChild();
                } else {
                    element = element.getRightChild();
                }
            }
        } while (element == null || element.getValue() != value);
        splay(element);
    }

    public void delete(int value) {
        TreeElement element = find(value);
        merge(element.getLeftChild(), element.getRightChild());
    }

    public TreeElement find(int value) {
        TreeElement element = root;
        do {
            if (element == null) {
                throw new NoSuchElementException();
            }
            if (value < element.getValue()) {
                element = element.getLeftChild();
            } else {
                if (value > element.getValue()) {
                    element = element.getRightChild();
                }
            }
        } while (element == null || element.getValue() != value);
        splay(element);
        return element;
    }

    private void splay(TreeElement element) {
        while (element.getParent() != null)
            if (element == element.getParent().getLeftChild()) {
                if (element.getParent().getParent() == null) {
                    rotateRight(element.getParent());
                } else if (element.getParent() == element.getParent().getParent().getLeftChild()) {
                    rotateRight(element.getParent().getParent());
                    rotateRight(element.getParent());
                } else {
                    rotateRight(element.getParent());
                    rotateLeft(element.getParent());
                }
            } else if (element.getParent().getParent() == null) {
                rotateLeft(element.getParent());
            } else if (element.getParent() == element.getParent().getParent().getRightChild()) {
                rotateLeft(element.getParent().getParent());
                rotateLeft(element.getParent());
            } else {
                rotateLeft(element.getParent());
                rotateRight(element.getParent());
            }
        root = element;
    }

    private void rotateLeft(TreeElement element) {
        TreeElement parent = element.getParent();
        TreeElement rightChild = element.getRightChild();
        if (parent != null) {
            if (parent.getLeftChild() == element) {
                parent.setLeftChild(rightChild);
            } else {
                parent.setRightChild(rightChild);
            }
        }
        TreeElement tmp = rightChild.getLeftChild();
        rightChild.setLeftChild(element);
        element.setRightChild(tmp);
        element.setParent(rightChild);
        rightChild.setParent(parent);
        if (element.getRightChild() != null) {
            element.getRightChild().setParent(element);
        }
    }

    private void rotateRight(TreeElement element) {
        TreeElement parent = element.getParent();
        TreeElement leftChild = element.getLeftChild();
        if (parent != null)
            if (parent.getRightChild() == element)
                parent.setRightChild(leftChild);
            else
                parent.setLeftChild(leftChild);
        TreeElement tmp = leftChild.getRightChild();
        leftChild.setRightChild(element);
        element.setLeftChild(tmp);
        element.setParent(leftChild);
        leftChild.setParent(parent);
        if (element.getLeftChild() != null) {
            element.getLeftChild().setParent(element);
        }
    }

    private void merge(TreeElement leftTree, TreeElement rightTree) {
        TreeElement newRoot = findMax(leftTree);
        if (newRoot == null) {
            root = rightTree;
            return;
        }
        splay(newRoot);
        newRoot.setRightChild(rightTree);
    }

    private TreeElement findMax(TreeElement element) {
        if (element == null) {
            return null;
        }
        while (element.getRightChild() != null) {
            element = element.getRightChild();
        }
        return element;
    }
}
