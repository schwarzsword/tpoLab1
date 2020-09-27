package part2;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "parent")
public class TreeElement {
    private int value;
    private TreeElement parent;
    private TreeElement leftChild;
    private TreeElement rightChild;

    public TreeElement(int value) {
        this.value = value;
    }
}
