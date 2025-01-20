package lt.ca.javau11.entities.types.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ca.javau11.entities.ProductType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeDTO {

    private Long id;

    private String typeName;
    private String imageUrl;

    public ProductTypeDTO(ProductType productType) {
        this.id = productType.getId();
        this.typeName = productType.getTypeName();
        this.imageUrl = productType.getImageUrl();
    }
}
