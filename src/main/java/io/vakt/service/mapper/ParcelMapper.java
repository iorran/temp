package io.vakt.service.mapper;


import io.vakt.domain.*;
import io.vakt.service.dto.ParcelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Parcel} and its DTO {@link ParcelDTO}.
 */
@Mapper(componentModel = "spring", uses = {LegNominationMapper.class})
public interface ParcelMapper extends EntityMapper<ParcelDTO, Parcel> {

    @Mapping(source = "legNomination.id", target = "legNominationId")
    ParcelDTO toDto(Parcel parcel);

    @Mapping(source = "legNominationId", target = "legNomination")
    Parcel toEntity(ParcelDTO parcelDTO);

    default Parcel fromId(Long id) {
        if (id == null) {
            return null;
        }
        Parcel parcel = new Parcel();
        parcel.setId(id);
        return parcel;
    }
}
