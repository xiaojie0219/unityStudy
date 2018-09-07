package com.sinodata.forJMeter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author zhongyicheng
 *         create date 2017/8/2.
 */
public class PrivateKeyUtil {
    /*证书类型*/
    private final static String KEY_STORE_TYPE = "PKCS12";

    private static KeyStore initKeyStore(String path,String passwd) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore ks = KeyStore.getInstance(PrivateKeyUtil.KEY_STORE_TYPE);
        //绝对路径
        InputStream is = new FileInputStream(new File(path));
        //相对路径
        //InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(CertUtil.CERT_PATH);
        ks.load(is, passwd.toCharArray());
        is.close();
        return ks;
    }

    private static String getAlias(KeyStore ks) throws KeyStoreException {
        Enumeration<String> aliases = ks.aliases();
        String alias = null;
        if (aliases.hasMoreElements()) {
            alias = aliases.nextElement();
        }
        return alias;
    }

    /**
     * 加签
     *
     * @param path   私钥证书路径
     * @param data   原始数据
     * @param passwd 密码
     * @return 加签数据
     */
    public static String sign(String path, String data, String passwd) {
        try {
            KeyStore ks = initKeyStore(path,passwd);
            String alias = getAlias(ks);
            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, passwd.toCharArray());
            X509Certificate x509Certificate = (X509Certificate) ks.getCertificate(alias);
            Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
            signature.initSign(privateKey);
            signature.update(data.getBytes("utf-8"));
            return bytesToStrHex(signature.sign());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 方法: bytesToStrHex
     * 描述: 数组转换成16进制字符串
     *
     * @param bytes 源数组
     * @return String
     */
    public static String bytesToStrHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length);
        String sTemp;
        for (byte aByte : bytes) {
            sTemp = Integer.toHexString(0xFF & aByte);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 方法: hexStrToBytes
     * 描述: 将16进制字符串还原为字节数组
     *
     * @param str 16进制字符串
     * @return byte[]
     */
    private static byte[] hexStrToBytes(String str) {
        byte[] bytes;
        bytes = new byte[str.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(str.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    /**
     * 私钥加密
     * @param path 私钥证书路径
     * @param data 待加密数据
     * @param passwd 私钥
     * @return 加密数据
     */
    public static byte[] encode(String path, String data, String passwd){
        try {
            KeyStore ks = initKeyStore(path,passwd);
            String alias = getAlias(ks);

            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, passwd.toCharArray());
            Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(data.getBytes("utf-8"));
     //       return bytesToStrHex(cipher.doFinal(data.getBytes("utf-8")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥解密
     * @param path 私钥证书路径
     * @param data 加密数据
     * @param passwd 私钥
     * @return 解密数据
     */
    public static String decode(String path, byte[] data, String passwd){
        try {
            KeyStore ks = initKeyStore(path,passwd);
            String alias = getAlias(ks);
      //      byte[] decryptData = hexStrToBytes(data);
            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, passwd.toCharArray());
            Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
          //  return cipher.doFinal(data);
          return new String(cipher.doFinal(data));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
    	String str = PrivateKeyUtil.sign("F:\\1\\test-sile.p12", "200042016-10-24 15:10:101234567", "123456");
    	System.out.println(str);
    }
    	/*
        Map<String, String> requestMap = new LinkedHashMap<String, String>();
        requestMap.put("city", "深圳");
        requestMap.put("company", "思乐");
        requestMap.put("msg", "hello world!");
//        String josnStr = JSONObject.toJSONString(requestMap);
        String josnStr = "{\"PartnerId\":\"30001\",\"TimeStamp\":\"2016-10-24 15:10:10\",\"SerialNum\":\"123456788\",\"Version\":\"1.0.0.0\",\"ReqContent\":{\"Sign\":\"72126C2A580F648CDDB47B3709C0B97D3442C5B7EBE2A98C8885A6225EAE1CC7AFB8F4930CA0EB91933996B2344A2EF09E81D384252BB90BF786A52C63256CA4B67216609C84D0A1B514F3FA0AD619CA1D691AB548D19667E8A40EBDC4384D6B5855C9FD63AB586A7267661CC1C2CB427C20C470501F3CC788CC4544CAA27CEFD08E34931E6EC4CDF80198AF408889F0710EFAB2D430BB38C00891038F7023EF02BA6EA078E33FE3875AF56DE9381F32B85FFE4F0737AFD302D27D0EE1C34F84668DF69E622163AF1364B7D4D494D3D8B565E1129CA22EAD86E8D3EA2F981D28BE2628041F86985E723A0BC234EEF4015EF52FF32277DD9F852966DE53FBAF7C\"}}}";
        System.out.println("数据: " + josnStr);
        String CERT_PATH="F:\\p12\\gdca-test\\test-sile.p12";
        String CERT_PATH2="F:\\p12\\gdca-test\\test-sile.cer";
        
        System.out.println("加签： "+PrivateKeyUtil.sign(CERT_PATH,josnStr,"123456"));
        byte[] encodeData = PrivateKeyUtil.encode(CERT_PATH,josnStr,"123456");
        try {
			System.out.println("私钥加密： " + new String(encodeData, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] res=PublicKeyUtil.decode(CERT_PATH2,encodeData);
        try {
			System.out.println("公钥解密： " + new String(res,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
*/
}
