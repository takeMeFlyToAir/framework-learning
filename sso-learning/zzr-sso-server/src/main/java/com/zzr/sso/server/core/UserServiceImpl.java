package com.zzr.sso.server.core;

import com.zzr.sso.server.core.result.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;


    @Override
    public ReturnT<User> userLogin(String account, String password) {
        if (account==null || account.trim().length()==0) {
            return new ReturnT<User>(ReturnT.FAIL_CODE, "请输入用户名");
        }
        if (password==null || password.trim().length()==0) {
            return new ReturnT<User>(ReturnT.FAIL_CODE, "请输入密码");
        }
        User user = userJpaRepository.findByAccount(account);
        if (user != null) {
            if(PasswordProvider.encrypt(password).equals(user.getPassword())){
                return new ReturnT<User>(user);
            }
        }
        return new ReturnT<User>(ReturnT.FAIL_CODE, "用户名或者密码错误");
    }
}