package com.zzr.sso.core.store;

import com.zzr.sso.core.user.ZzrSsoUser;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
public class SsoSessionIdHelper {

    /**
     * 创建客户端sessionId
     * @param zzrSsoUser
     * @return
     */
    public static String createSessionId(ZzrSsoUser zzrSsoUser){
        String sessionId = zzrSsoUser.getUserId().concat("_").concat(zzrSsoUser.getVersion());
        return sessionId;
    }

    /**
     * 从sessionId解析出用户id
     * @param sessionId
     * @return
     */
    public static String parseStoreKey(String sessionId){
        if (sessionId != null && sessionId.indexOf("_") > -1) {
            String[] sessionIdArray = sessionId.split("_");
            if (sessionIdArray.length==2
                    && sessionIdArray[0]!=null
                    && sessionIdArray[0].trim().length()>0) {
                String userId = sessionIdArray[0].trim();
                return userId;
            }
        }
        return null;
    }

    /**
     * 从sessionId解析出版本号
     * @param sessionId
     * @return
     */
    public static String parseVersion(String sessionId) {
        if (sessionId!=null && sessionId.indexOf("_")>-1) {
            String[] sessionIdArr = sessionId.split("_");
            if (sessionIdArr.length==2
                    && sessionIdArr[1]!=null
                    && sessionIdArr[1].trim().length()>0) {
                String version = sessionIdArr[1].trim();
                return version;
            }
        }
        return null;
    }

}
