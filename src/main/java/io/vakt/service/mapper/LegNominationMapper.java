package io.vakt.service.mapper;


import io.vakt.domain.*;
import io.vakt.service.dto.LegNominationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LegNomination} and its DTO {@link LegNominationDTO}.
 */
@Mapper(componentModel = "spring", uses = {NominationMetadataMapper.class})
public interface LegNominationMapper extends EntityMapper<LegNominationDTO, LegNomination> {

    @Mapping(source = "nominationMetadata.id", target = "nominationMetadataId")
    LegNominationDTO toDto(LegNomination legNomination);

    @Mapping(source = "nominationMetadataId", target = "nominationMetadata")
    @Mapping(target = "parcels", ignore = true)
    @Mapping(target = "removeParcels", ignore = true)
    @Mapping(target = "buildDraws", ignore = true)
    @Mapping(target = "removeBuildDraws", ignore = true)
    @Mapping(target = "placeholderParcels", ignore = true)
    @Mapping(target = "removePlaceholderParcels", ignore = true)
    LegNomination toEntity(LegNominationDTO legNominationDTO);

    default LegNomination fromId(Long id) {
        if (id == null) {
            return null;
        }
        LegNomination legNomination = new LegNomination();
        legNomination.setId(id);
        return legNomination;
    }
}
