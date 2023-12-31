package it.miaflotta.assettracker.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_contact")
public class UserContact extends BaseEntity {
    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String fax;

    @Column
    private Boolean isPrimary;
}
