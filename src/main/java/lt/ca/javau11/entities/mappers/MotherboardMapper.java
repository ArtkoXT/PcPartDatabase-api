package lt.ca.javau11.entities.mappers;

import lt.ca.javau11.entities.types.Motherboard;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface MotherboardMapper {

    public void updateMotherboard(Motherboard mb1, @MappingTarget Motherboard mb2);
}
