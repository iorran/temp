package io.vakt.repository;

import io.vakt.domain.BuildDraw;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BuildDraw entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuildDrawRepository extends JpaRepository<BuildDraw, Long> {
}
