package it.miaflotta.assettracker.models.entities;

import it.miaflotta.assettracker.enumerations.MapType;
import it.miaflotta.assettracker.enumerations.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Column
    private String uuid;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column
    @Enumerated(EnumType.STRING)
    private MapType mapType;
}
