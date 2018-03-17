package com.zeng.Service;

import com.zeng.dao.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Administrator
 * @date 2018/3/17
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
