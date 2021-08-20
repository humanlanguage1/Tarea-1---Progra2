package demo.api.demo.model;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    private String id;
    private String name;
    private String description;
    private double price;   
}
