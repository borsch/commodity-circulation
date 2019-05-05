package ua.net.kurpiak.commoditycirculation.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeOrderView;

@RestController
@RequestMapping("/api/orders/income")
public class IncomeOrderApiController extends BaseApiController<IncomeOrderEntity, IncomeOrderView, Integer> {
}
