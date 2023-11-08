package com.qnkj.clouds.modules.VmInstances.services;

import java.util.Map;

/**
 * create by Auto Generator
 * create date 2023-06-27
 * @author Auto Generator
 */
public interface IVmInstancesService  {

    Integer startWebsockifyServer() throws Exception;

    void startVm(String record) throws Exception;
    void restartVm(String record) throws Exception;
    void stopVm(String record) throws Exception;
    void forceStopVm(String record) throws Exception;

    void createDiskImageFromDisk(Map<String, Object> Request) throws Exception;

}
