package io.vakt.service.mapper;


import io.vakt.domain.*;
import io.vakt.service.dto.PlaceholderParcelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlaceholderParcel} and its DTO {@link PlaceholderParcelDTO}.
 */
@Mapper(componentModel = "spring", uses = {LegNominationMapper.class})
public interface PlaceholderParcelMapper extends EntityMapper<PlaceholderParcelDTO, PlaceholderParcel> {

    @Mapping(source = "legNomination.id", target = "legNominationId")
    PlaceholderParcelDTO toDto(PlaceholderParcel placeholderParcel);

    @Mapping(source = "legNominationId", target = "legNomination")
    PlaceholderParcel toEntity(PlaceholderParcelDTO placeholderParcelDTO);

    default PlaceholderParcel fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlaceholderParcel placeholderParcel = new PlaceholderParcel();
        placeholderParcel.setId(id);
        return placeholderParcel;
    }
}
