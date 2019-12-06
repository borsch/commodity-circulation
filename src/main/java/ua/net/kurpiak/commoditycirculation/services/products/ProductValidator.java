package ua.net.kurpiak.commoditycirculation.services.products;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ValidationException;
import ua.net.kurpiak.commoditycirculation.persistence.dao.ProductRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;
import ua.net.kurpiak.commoditycirculation.services.BaseValidator;

@Component
public class ProductValidator extends BaseValidator<ProductEntity, Integer> {

    private final ProductRepository productRepository;

    public ProductValidator(final Validator validator, final ProductRepository productRepository) {
        super(validator, ProductEntity.class);
        this.productRepository = productRepository;
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
