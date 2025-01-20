package lt.ca.javau11.services;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.ProductType;
import lt.ca.javau11.entities.mappers.EntityMapper;
import lt.ca.javau11.entities.types.CPU;
import lt.ca.javau11.entities.types.DTOs.ProductTypeDTO;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.ProductTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductTypeService {

    ProductTypeRepository ptRepo;
    EntityMapper entityMapper;

    public List<ProductTypeDTO> getAllTypes(){
        return ptRepo.findAll().stream().map(ProductTypeDTO::new).toList();
    }

    public ProductType getTypeByID(Long id){
        return ptRepo.findById(id).orElseThrow( () -> new NotFoundException("Type with id " + id + " not found!") );
    }

    public ProductType addNewType(ProductType productType){
        return ptRepo.save(productType);
    }

    public Optional<ProductType> updateType(Long id, ProductType source){
        Optional<ProductType> existingType = ptRepo.findById(id);

        if( existingType.isPresent() ) {
            ProductType target = existingType.get();

            entityMapper.updateProductType(source, target);

            return Optional.of(ptRepo.save(target));
        }

        return Optional.empty();
    }

    public boolean deleteType(Long id){
        Optional<ProductType> type = ptRepo.findById(id);
        if ( type.isEmpty() )
            return false;

        ptRepo.delete(type.get());
        return true;
    }
}
