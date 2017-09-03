/*
 * File name:  ResponseHeader.java
 * Copyright:  Copyright (c) 2006-2013 hoperun Inc,  All rights reserved
 * Description:  <描述>
 * Author:  ma_zhicheng
 * Last modified date:  11 Sep,2013
 * Version:  <版本编号>
 * Edit history:  <修改内容><修改人>
 */
package com.lzm.appmarket.comment;

import com.google.gson.Gson;

import java.util.List;

/**
 * 解析来自服务器数据的header
 * 
 * @author 
 * @version 
 */
public class ResponseHeader
{
    private String accessToken;
    
    private String appId;
    
    private String appVersion;
    
    private String devId;
    
    private String devType;
    
    private String funcId;
    
    private String osVersion;
    
    private String retMessage;
    
    private int retStatus;
    
    private String userId;
    
    private String userType;
    
    private int flag ;

    private List<String> appid;

    public List<String> getAppid() {
        return appid;
    }

    public void setAppid(List<String> appid) {
        this.appid = appid;
    }
    
    public String getAccessToken()
    {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
    
    public String getAppId()
    {
        return appId;
    }
    
    public void setAppId(String appId)
    {
        this.appId = appId;
    }
    
    public String getAppVersion()
    {
        return appVersion;
    }
    
    public void setAppVersion(String appVersion)
    {
        this.appVersion = appVersion;
    }
    
    public String getDevId()
    {
        return devId;
    }
    
    public void setDevId(String devId)
    {
        this.devId = devId;
    }
    
    public String getDevType()
    {
        return devType;
    }
    
    public void setDevType(String devType)
    {
        this.devType = devType;
    }
    
    public String getFuncId()
    {
        return funcId;
    }
    
    public void setFuncId(String funcId)
    {
        this.funcId = funcId;
    }
    
    public String getOsVersion()
    {
        return osVersion;
    }
    
    public void setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }
    
    public String getRetMessage()
    {
        return retMessage;
    }
    
    public void setRetMessage(String retMessage)
    {
        this.retMessage = retMessage;
    }
    
    public int getFlag()
    {
        return flag;
    }
    
    public void setFlag(int flag)
    {
        this.flag = flag;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public String getUserType()
    {
        return userType;
    }
    
    public void setUserType(String userType)
    {
        this.userType = userType;
    }
    
    public int getRetStatus()
    {
        return retStatus;
    }
    
    public void setRetStatus(int retStatus)
    {
        this.retStatus = retStatus;
    }
    
    public static ResponseHeader parse(String json) throws Exception
    {	
        Gson gson = new Gson();
        try
        {	
            ResponseHeader head = (ResponseHeader)gson.fromJson(json, ResponseHeader.class);
            return head;
        }
        catch (Exception e)
        {
            throw new Exception();
        }
    }
}
