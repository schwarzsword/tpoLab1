package part2;

import lombok.Data;

@Data
public class TreeElement {
    private int value;
    private TreeElement parent;
    private TreeElement leftChild;
    private TreeElement rightChild;

    public TreeElement(int value) {
        this.value = value;
    }
}
