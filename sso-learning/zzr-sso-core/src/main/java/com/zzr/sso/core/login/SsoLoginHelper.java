package com.zzr.sso.core.login;

import com.zzr.sso.core.store.SsoLoginStore;
import com.zzr.sso.core.store.SsoSessionIdHelper;
import com.zzr.sso.core.user.ZzrSsoUser;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
public class SsoLoginHelper {

    public static ZzrSsoUser loginCheck(String sessionId){
        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            return null;
        }
        ZzrSsoUser zzrSsoUser = SsoLoginStore.get(storeKey);
        if (zzrSsoUser != null) {
            String version = SsoSessionIdHelper.parseVersion(sessionId);
            if(zzrSsoUser.getVersion().equals(version)){

                if((System.currentTimeMillis() - zzrSsoUser.getExpireFreshTime()) > zzrSsoUser.getExpireMinute()/2){
                    zzrSsoUser.setExpireFreshTime(System.currentTimeMillis());
                    SsoLoginStore.put(storeKey,zzrSsoUser);
                }
                return zzrSsoUser;
            }
        }
        return null;
    }

}
