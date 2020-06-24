package io.vakt.repository;

import io.vakt.domain.PlaceholderParcel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PlaceholderParcel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlaceholderParcelRepository extends JpaRepository<PlaceholderParcel, Long> {
}
