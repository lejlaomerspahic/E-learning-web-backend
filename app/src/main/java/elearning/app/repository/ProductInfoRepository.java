package elearning.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elearning.app.model.ProductInfo;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {

}
