package it.miaflotta.assettracker.models.entities;

import it.miaflotta.assettracker.enumerations.MapType;
import it.miaflotta.assettracker.enumerations.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<UserContact> contacts;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "guest",
            joinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "child_id", referencedColumnName = "id"))
    private List<User> guests;
}
