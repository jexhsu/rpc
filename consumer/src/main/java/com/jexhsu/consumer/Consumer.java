package com.jexhsu.consumer;

import com.jexhsu.common.model.User;
import com.jexhsu.common.service.UserService;
import com.jexhsu.rpc.config.RpcConfig;
import com.jexhsu.rpc.proxy.ServiceProxyFactory;
import com.jexhsu.rpc.utils.ConfigUtils;

/**
 * Simple service consumer example.
 */
public class Consumer {

    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);

        // Static proxy instance
        UserService staticProxy = new UserServiceProxy();

        // Dynamic proxy instance
        UserService dynamicProxy = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();
        user.setName("jexhsu");

        printResult("Static Proxy", staticProxy.thumbs_up(user));

        printResult("Dynamic Proxy", dynamicProxy.thumbs_up(user));

        long number = dynamicProxy.getNumber();
        System.out.println("the number is " + number);
    }

    private static void printResult(String method, String res) {
        if (res != null) {
            System.out.println(method + " Result: " + res);
        } else {
            System.out.println(method + " error: Result is null.");
        }
    }
}