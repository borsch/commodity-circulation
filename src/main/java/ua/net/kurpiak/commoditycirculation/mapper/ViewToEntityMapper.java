package ua.net.kurpiak.commoditycirculation.mapper;

public interface ViewToEntityMapper<E, V> {

    E mapToEntity(V view);

    void updateEntity(E entity, V view);

}
