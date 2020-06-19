package com.netty.util;


import com.netty.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Classname LoginUtil
 * @Describe TODO
 * @Date 2020/6/18
 * @Auth whp
 * @Version 1.0
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel){
        channel.attr( Attributes.LOGIN ).set( true );
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr=channel.attr( Attributes.LOGIN );
        return loginAttr.get()!=null;
    }
}
