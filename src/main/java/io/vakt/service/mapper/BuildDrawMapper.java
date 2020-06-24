package io.vakt.service.mapper;


import io.vakt.domain.*;
import io.vakt.service.dto.BuildDrawDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BuildDraw} and its DTO {@link BuildDrawDTO}.
 */
@Mapper(componentModel = "spring", uses = {LegNominationMapper.class})
public interface BuildDrawMapper extends EntityMapper<BuildDrawDTO, BuildDraw> {

    @Mapping(source = "legNomination.id", target = "legNominationId")
    BuildDrawDTO toDto(BuildDraw buildDraw);

    @Mapping(source = "legNominationId", target = "legNomination")
    BuildDraw toEntity(BuildDrawDTO buildDrawDTO);

    default BuildDraw fromId(Long id) {
        if (id == null) {
            return null;
        }
        BuildDraw buildDraw = new BuildDraw();
        buildDraw.setId(id);
        return buildDraw;
    }
}
