package com.ra.demo_register.model.entity;

import com.ra.demo_register.model.constants.RoleName;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role()
    {
    }

    public Role(Long id, RoleName roleName)
    {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public RoleName getRoleName()
    {
        return roleName;
    }

    public void setRoleName(RoleName roleName)
    {
        this.roleName = roleName;
    }
}
