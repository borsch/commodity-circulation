package ua.net.kurpiak.commoditycirculation.controllers.rest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;
import ua.net.kurpiak.commoditycirculation.pojo.response.Response;
import ua.net.kurpiak.commoditycirculation.services.BaseService;

@RequiredArgsConstructor
public abstract class BaseApiController<E extends IHasId<I>, V extends IHasId<I>, I extends Serializable&Comparable<I>> {

    protected final BaseService<E, V, I> service;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public
    @ResponseBody
    Response<Map<String, Object>>
    get(
            @PathVariable("id") I id,
            @RequestParam(value = "fields", required = false, defaultValue = "id") Set<String> fields
    ) throws BaseException {
        return Response.of(service.getById(id, fields));
    }

    @RequestMapping(
            value = { "", "/" },
            method = RequestMethod.GET
    )
    public @ResponseBody
    Response<List<Map<String, Object>>>
    getList(
            @RequestParam(value = "fields", required = false, defaultValue = "id") Set<String> fields,
            @RequestParam(value = "restrict", required = false) String restrict
    ) throws BaseException {
        return Response.of(service.getList(fields, restrict));
    }

    @RequestMapping(
            value = "/count",
            method = RequestMethod.GET
    )
    public @ResponseBody
    Response<Integer>
    count(
            @RequestParam(value = "restrict", required = false) String restrict
    ) throws BaseException {
        return Response.of(service.count(restrict));
    }

    @RequestMapping(
            value = { "", "/" },
            method = RequestMethod.PUT
    )
    public
    @ResponseBody
    Response<I>
    create(
            @RequestBody V view
    ) throws BaseException {
        return Response.of(service.create(view));
    }

    @RequestMapping(
            value = { "", "/" },
            method = RequestMethod.POST
    )
    public
    @ResponseBody
    Response<Boolean>
    update(
            @RequestBody V view
    ) throws BaseException {
        return Response.of(service.update(view));
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public
    @ResponseBody
    Response<Boolean>
    delete(
            @PathVariable("id") I id
    ) throws BaseException {
        return Response.of(service.delete(id));
    }
}
