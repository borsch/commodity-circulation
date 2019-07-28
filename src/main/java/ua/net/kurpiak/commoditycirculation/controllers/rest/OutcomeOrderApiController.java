package ua.net.kurpiak.commoditycirculation.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeOrderView;

@RestController
@RequestMapping("/api/orders/outcome")
public class OutcomeOrderApiController extends BaseApiController<OutcomeOrderEntity, OutcomeOrderView, Integer> {
}
