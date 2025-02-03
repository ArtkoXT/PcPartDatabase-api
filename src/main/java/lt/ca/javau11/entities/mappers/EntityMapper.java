package lt.ca.javau11.entities.mappers;

import lt.ca.javau11.entities.Component;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @Mapping(target = "id", ignore = true)
    void updateComponent(Component source, @MappingTarget Component target);

    @Mapping(target = "id", ignore = true)
    void updateManufacturer(Manufacturer source, @MappingTarget Manufacturer target);

    @Mapping(target = "id", ignore = true)
    void updateUser(User source, @MappingTarget User target);
}
