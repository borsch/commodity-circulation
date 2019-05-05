package ua.net.kurpiak.commoditycirculation.services.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ValidationException;
import ua.net.kurpiak.commoditycirculation.persistence.dao.ProductRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;
import ua.net.kurpiak.commoditycirculation.services.BaseValidator;

@Component
public class ProductValidator extends BaseValidator<ProductEntity, Integer> {

    @Autowired
    private ProductRepository productRepository;

    public ProductValidator() {
        super(ProductEntity.class);
    }

    @Override
    protected void validateConstraints(ProductEntity entity) throws ValidationException {
        super.validateConstraints(entity);

        ProductEntity byCode = productRepository.findByCode(entity.getCode());
        if (byCode != null && !entity.equals(byCode)) {
            throw new ValidationException("Product", "Такий код вже існує");
        }
    }
}
