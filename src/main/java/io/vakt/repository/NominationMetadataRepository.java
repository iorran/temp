package io.vakt.repository;

import io.vakt.domain.NominationMetadata;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NominationMetadata entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NominationMetadataRepository extends JpaRepository<NominationMetadata, Long> {
}
