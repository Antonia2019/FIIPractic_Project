package fii.practic.health.entity.repository;

import fii.practic.health.entity.model.User;

public interface UserRepository {

    User findUserByEmailAndPassword(String email, String password);
}
