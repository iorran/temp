package io.vakt.service.mapper;


import io.vakt.domain.*;
import io.vakt.service.dto.NominationMetadataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NominationMetadata} and its DTO {@link NominationMetadataDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NominationMetadataMapper extends EntityMapper<NominationMetadataDTO, NominationMetadata> {



    default NominationMetadata fromId(Long id) {
        if (id == null) {
            return null;
        }
        NominationMetadata nominationMetadata = new NominationMetadata();
        nominationMetadata.setId(id);
        return nominationMetadata;
    }
}
