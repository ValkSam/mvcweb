package valksam.mvcweb.model;

import javax.persistence.*;

/**
 * Created by Valk on 12.01.16.
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity {
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    protected Integer id;

    protected BaseEntity() {
    }

    protected BaseEntity(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}