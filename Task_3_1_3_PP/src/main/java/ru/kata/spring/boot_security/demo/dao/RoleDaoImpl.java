package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAll() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    public Role getByName(String name) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.name=:name", Role.class)
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}
