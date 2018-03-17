package com.zeng.dao;

import com.zeng.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {
     User findByName(String name);
     @Override
     User save(User user);

     User update(User user);
     @Override
     void delete(User user);
}
