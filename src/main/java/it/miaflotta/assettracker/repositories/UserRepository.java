package it.miaflotta.assettracker.repositories;

import it.miaflotta.assettracker.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(String uuid);

    @Query("SELECT e FROM User e join e.contacts c WHERE " +
            "(e.name LIKE CONCAT('%', :searchBy, '%')) " +
            "OR (e.surname LIKE CONCAT('%', :searchBy, '%')) " +
            "OR (c.email LIKE CONCAT('%', :searchBy, '%')) "
    )
    Page<User> findAllByAttributePageable(@Param("searchBy") String searchBy, Pageable pageable);
}
