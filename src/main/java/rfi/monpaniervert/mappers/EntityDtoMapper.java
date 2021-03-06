package rfi.monpaniervert.mappers;


import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Entity to Dto Mapper.
 *
 * @param <E> entity to map
 * @param <D> DTO to retrieve
 *
 */
public interface EntityDtoMapper<E, D> {

    /**
     * Converts the given Entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the DTO generated
     */
    D toDto(E entity);

    /**
     * Converts an {@link Optional} Entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the {@link Optional} DTO generated
     */
    default Optional<D> toOptionalDto(Optional<E> entity) {
        return entity.map(this::toDto);
    }

    /**
     * Converts the given DTO to an Entity.
     *
     * @param dto the entity to convert
     * @return the Entity generated
     */
    E toEntity(D dto);

    /**
     * Converts a list of Entity to a list of DTO.
     *
     * @param entityList the List to convert
     * @return the list of DTO generated
     */
    default List<D> toDtoList(List<E> entityList){
        return Optional.ofNullable(entityList).orElse(Collections.emptyList()).stream().filter(Objects::nonNull)
                .map(this::toDto).collect(Collectors.toList());    }

}
