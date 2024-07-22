package bg.softuni.healthheaven.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //test
    //dsasd
    @Override
    public String toString() {
        return "BaseEntity {" +
                "id = " + id +
                "}";
    }
}