package com.szf.microservicesimpleprovideruser.dao;

import com.szf.microservicesimpleprovideruser.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
