package com.java.newscycle.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void testRoleEntityConstructor() {
        // Arrange
        int id = 1;
        String name = "ROLE_USER";

        // Act
        Role role = new Role(id, name);

        // Assert
        assertNotNull(role);
        assertEquals(id, role.getId());
        assertEquals(name, role.getName());
    }
}
