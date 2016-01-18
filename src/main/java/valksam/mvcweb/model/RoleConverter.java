package valksam.mvcweb.model;

import javax.persistence.AttributeConverter;

/**
 * Created by Valk on 18.01.16.
 */
public class RoleConverter implements AttributeConverter<Role, Integer>{
    @Override
    public Integer convertToDatabaseColumn(Role attribute) {
        return attribute.getIdx();
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        return Role.getRole(dbData);
    }
}
