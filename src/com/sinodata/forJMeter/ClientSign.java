package com.sinodata.forJMeter;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ClientSign {
	
	private static ActiveXComponent gdca=new ActiveXComponent("Atl_com.Gdca.1");
	private static Dispatch disp=gdca.getObject();
	
	
	public static String sign(String inData){
	        Object[] obj=new Object[1];
	        obj[0]=inData;
	        Variant var=Dispatch.call(disp, "GDCA_Base64Encode", obj);//加密
	        inData=var.getString();
	        //设置设备类型	        
	        var=Dispatch.call(disp, "GDCA_GetDeviceType");//获取设备类型
	        int type=var.getInt();
	        System.out.println("设备类型:"+type);
	        Object[] objsz=new Object[1];
	        obj[0]=inData;
	        objsz[0]=type;
	        var=Dispatch.call(disp, "GDCA_SetDeviceType",objsz);//设置设备类型
	        if(var.getInt()!=0)
	        {
	        	System.out.println("设置设备类型失败");
	        }
	        
	        //初始化
	        var=Dispatch.call(disp, "GDCA_Initialize");
	        if(var.getInt()!=0)
	        {
	        	System.out.println("初始化失败");
	        }
	        //登录
	        Object[] objlogin=new Object[2];
	        objlogin[0]=2;
	        objlogin[1]="123456";
	        var=Dispatch.call(disp, "GDCA_Login",objlogin);
	        if(var.getInt()!=0)
	        {
	        	System.out.println("登录失败");
	        }
	        //读取签名证书
	        Object[] objqm=new Object[2];
	        objqm[0]="LAB_USERCERT_SIG";
	        objqm[1]=7;
	        var=Dispatch.call(disp,"GDCA_ReadLabel",objqm);
	        String signsert=var.getString();
	        //使用签名算法进行签名
	        Object[] objsf=new Object[6];
	        objsf[0]="LAB_USERCERT_SIG";
	        objsf[1]=4;
	        objsf[2]=signsert;
	        objsf[3]=inData;
	        objsf[4]=32771;//md5RSA
	        objsf[5]=0;//不包含原文
	        var=Dispatch.call(disp,"GDCA_OpkiSignData",objsf);
	        return var.getString();
	}
	
	public static void main(String[] args) {
		//将Partnerid+TimeStamp+SerialNum拼接了进行签名
		String result = ClientSign.sign("000082016-04-27 10:39:101234567");
		System.out.println(result);
	}

}
