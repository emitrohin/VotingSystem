package ru.emitrohin.votingsystem.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
public enum Role implements GrantedAuthority {

    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
