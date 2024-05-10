package org.agcpag.annotations;

public class DefaultEntityNameValidator implements EntityNameValidator {
    @Override
    public String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("No name provided");
        }
        if (name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("The name cannot contain digits");
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }


}
