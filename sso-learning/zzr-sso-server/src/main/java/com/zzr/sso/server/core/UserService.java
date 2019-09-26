package com.zzr.sso.server.core;

import com.zzr.sso.server.core.result.ReturnT;

public interface UserService {

     ReturnT<User> userLogin(String userName, String password);

}