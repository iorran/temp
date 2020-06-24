package io.vakt.repository;

import io.vakt.domain.LegNomination;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LegNomination entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LegNominationRepository extends JpaRepository<LegNomination, Long> {
}
