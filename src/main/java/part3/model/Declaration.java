package part3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Declaration {
    private Person owner;
    private String text;
    private DeclarationType type;
}
