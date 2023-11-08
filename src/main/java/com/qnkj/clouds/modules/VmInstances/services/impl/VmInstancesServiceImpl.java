package com.qnkj.clouds.modules.VmInstances.services.impl;

//import com.github.restapi.XN_Content;
//import com.github.restapi.XN_Query;

import com.qnkj.clouds.modules.utils.Md5Util;
import com.qnkj.clouds.modules.NoVNC.controller.WebsockifyServer;

//import com.qnkj.clouds.modules.VmInstances.entitys.VmInstances;
import com.qnkj.clouds.modules.VmInstances.services.IVmInstancesService;

//import com.qnkj.clouds.services.IKubeStackService;
//import com.qnkj.common.entitys.CustomDataSearch;
//import com.qnkj.common.entitys.CustomEditEntity;
//import com.qnkj.common.entitys.PickListEntity;
//import com.qnkj.common.entitys.SelectOption;
//import com.qnkj.common.utils.RedisUtils;
import com.qnkj.clouds.modules.utils.Utils;
//import com.qnkj.core.base.BaseEntityUtils;
//import com.qnkj.core.utils.ProfileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.java_websocket.server.WebSocketServer;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.*;

/**
 * create by Auto Generator
 * create date 2023-06-27
 * @author Auto Generator
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class VmInstancesServiceImpl implements IVmInstancesService {
//    private final IKubeStackService kubeStackService;


    private static Map<String,Integer> ports = new HashMap<>();
    private static Integer websockifyServerPort = 6088;
    @Override
    public Integer startWebsockifyServer() throws Exception {
        try {
            //VmInstances vmInstance = (VmInstances)this.load(record);
            if (Utils.isEmpty("5902")) {
                throw new Exception("端口为空");
            }
            //String key = Md5Util.get(vmInstance.zone+vmInstance.name);
            String key = Md5Util.get("zone-1"+"test123");
            if (ports.containsKey(key)) {
                log.info("key: " + ports.get(key));
                return ports.get(key);
            }
            //String vncServcieIP = kubeStackService.getVmVncServiceIp("zone-1","test123");
            String vncServcieIP = "133.133.135.131";
            if (Utils.isEmpty(vncServcieIP)) {
                throw new Exception("VNC服务IP为空");
            }
            ports.put(key,websockifyServerPort);
            websockifyServerPort ++;
            log.info("vms : {}","mytest0916");
            log.info("vncServcieIP : {}",vncServcieIP);
            log.info("port : {}","5903");
            log.info("websockifyServerPort : {}",websockifyServerPort);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String wsHost = "0.0.0.0";
                    Integer port = ports.get(key);
                    WebSocketServer server = new WebsockifyServer(new InetSocketAddress(wsHost, port), vncServcieIP, Integer.parseInt("5902"));
                    server.run();
                }
            }).start();
            return ports.get(key);
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void startVm(String record) throws Exception {

    }

    @Override
    public void restartVm(String record) throws Exception {

    }

    @Override
    public void stopVm(String record) throws Exception {

    }

    @Override
    public void forceStopVm(String record) throws Exception {

    }

    @Override
    public void createDiskImageFromDisk(Map<String, Object> Request) throws Exception {

    }
}
