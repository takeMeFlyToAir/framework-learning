package com.zzr.sso.server.core;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhaozhirong on 2019/4/17.
 */
public interface UserJpaRepository extends JpaRepository<User,Integer>{

    User findByAccount(String acccount);

}
