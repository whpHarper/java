package com.task.pool;

import com.github.maltalex.ineter.base.IPv4Address;
import com.github.maltalex.ineter.range.IPv4Range;

import java.util.ArrayList;
import java.util.List;

public class IPSegment {
    private int start;
    private int end;
    private int pos;

    /**
     * ipseg格式：192.168.1.1/23,192.168.1.1-192.168.1.255,192.168.3.1
     * @param ipseg
     * @return
     */
    public static List<IPSegment> parse(String ipseg){
        List<IPSegment> rt=new ArrayList<IPSegment>();
        for(String ips:ipseg.split(",")){
            if((ips.indexOf("-")>=0)||(ips.indexOf("/")>0)){
                IPv4Range ipr=IPv4Range.parse(ips);
                rt.add(new IPSegment(ipr.getFirst().toInt(),ipr.getLast().toInt()));
            }else{
                IPv4Address ip=IPv4Address.of(ips);
                rt.add(new IPSegment(ip.toInt(),ip.toInt()));
            }
        }
        return rt;
    }

    public IPSegment(int start,int end){
        super();
        this.start=start;
        this.end=end;
        this.pos=0;
    }
    public int length(){return end-start+1;}

    public String nextIp(){
        if(start+pos>end){
            return "";
        }
        return IPv4Address.of(start+(pos++)).toString();
    }
}
