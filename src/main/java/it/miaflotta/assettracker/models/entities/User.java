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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user", schema="miaflotta")
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<UserContact> contacts;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "guest",
            joinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "child_id", referencedColumnName = "id"))
    private List<User> guests;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "user_vehicle",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"))
    private List<Vehicle> vehicles;

    @OneToOne
    @JoinColumn(name = "map_category_id")
    private MapCategory map;
}
