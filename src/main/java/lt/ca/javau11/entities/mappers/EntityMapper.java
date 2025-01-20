package lt.ca.javau11.entities.mappers;

import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.Product;
import lt.ca.javau11.entities.ProductType;
import lt.ca.javau11.entities.types.CPU;
import lt.ca.javau11.entities.types.GPU;
import lt.ca.javau11.entities.types.Motherboard;
import lt.ca.javau11.entities.types.RAM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @Mapping(target = "id", ignore = true)
    void updateProduct(CPU source, @MappingTarget CPU target);

    @Mapping(target = "id", ignore = true)
    void updateProduct(GPU source, @MappingTarget GPU target);

    @Mapping(target = "id", ignore = true)
    void updateProduct(Motherboard source, @MappingTarget Motherboard target);

    @Mapping(target = "id", ignore = true)
    void updateProduct(RAM source, @MappingTarget RAM target);

    @Mapping(target = "id", ignore = true)
    void updateManufacturer(Manufacturer source, @MappingTarget Manufacturer target);

    @Mapping(target = "id", ignore = true)
    void updateProductType(ProductType source, @MappingTarget ProductType target);
}
