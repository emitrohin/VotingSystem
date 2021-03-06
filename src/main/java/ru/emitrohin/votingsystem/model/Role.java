package ru.emitrohin.votingsystem.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public enum Role implements GrantedAuthority {

    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
