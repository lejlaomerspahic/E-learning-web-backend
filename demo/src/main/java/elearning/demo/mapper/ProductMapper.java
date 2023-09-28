package elearning.demo.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import elearning.demo.dto.product.ProductCreatedReqRes;
import elearning.demo.models.Product;

@Mapper
public interface ProductMapper {

    @InheritConfiguration(name = "dtoToEntity")
    Product dtoToEntity(ProductCreatedReqRes dto);

}
