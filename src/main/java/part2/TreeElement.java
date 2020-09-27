package part2;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "parent")
@EqualsAndHashCode(of = {"value", "leftChild", "rightChild"})
public class TreeElement {
    private int value;
    private TreeElement parent;
    private TreeElement leftChild;
    private TreeElement rightChild;

    public TreeElement(int value) {
        this.value = value;
    }
}
