package com.example.habibi.mynativemodule;


import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MyNativeModule extends ReactContextBaseJavaModule{


  public MyNativeModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "MyNatievModule";
  }

  @ReactMethod
  public void getSubnet(Callback booleanCallback){

    try {
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      while (interfaces.hasMoreElements()) {
        NetworkInterface iface = interfaces.nextElement();
        if (iface.isLoopback() || !iface.isUp())
          continue;

        Enumeration<InetAddress> addresses = iface.getInetAddresses();
        for (InterfaceAddress address : iface.getInterfaceAddresses()) {

          InetAddress addr = addresses.nextElement();
          if (addr instanceof Inet6Address) continue;
          booleanCallback.invoke(intToIP(address.getNetworkPrefixLength()));
          return ;
        }

      }
    } catch (SocketException e) {
      throw new RuntimeException(e);
    }
    booleanCallback.invoke("0.0.0.0");
    return  ;


  }


  @ReactMethod
  public void getIpV4(Callback booleanCallback){

    String ip;
    try {
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      while (interfaces.hasMoreElements()) {
        NetworkInterface iface = interfaces.nextElement();
        if (iface.isLoopback() || !iface.isUp())
          continue;

        Enumeration<InetAddress> addresses = iface.getInetAddresses();
        while(addresses.hasMoreElements()) {
          InetAddress addr = addresses.nextElement();
          if (addr instanceof Inet6Address) continue;

          ip = addr.getHostAddress();
          booleanCallback.invoke(ip);
          return ;
        }
      }
    } catch (SocketException e) {
      throw new RuntimeException(e);
    }
    booleanCallback.invoke("0.0.0.0");

    return ;

  }


  public String intToIP(int ip) {
    String[] finl={"","","",""};
    int k=1;
    for(int i=0;i<4;i++){
      for(int j=0;j<8;j++){
        if(k<=ip){
          finl[i]+="1";
        }else{
          finl[i]+="0";
        }
        k++;

      }
    }
    return Integer.parseInt(finl[0], 2)+"."+Integer.parseInt(finl[1], 2)+"."+Integer.parseInt(finl[2], 2)+"."+Integer.parseInt(finl[3], 2);
  }
}
