package ua.net.kurpiak.commoditycirculation.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.ProductView;

@RestController
@RequestMapping("/api/products")
public class ProductApiController extends BaseApiController<ProductEntity, ProductView, Integer> {
}
