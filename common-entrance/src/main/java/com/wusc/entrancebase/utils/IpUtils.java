package com.wusc.entrancebase.utils;

public class IpUtils {

	public static byte[] ip2Bytes(String ip) {
		if (ip == null) {
			return null;
		}
		byte[] ret = new byte[] { 0, 0, 0, 0 };
		int index = 0;
		for (int i = 0; i < ip.length(); i++) {
			char c = ip.charAt(i);
			if (c == '.') {
				index++;
				continue;
			}
			ret[index] = (byte) (ret[index] * 10 + (c - 48));
		}
		return ret;
	}

	public static String bytes2Ip(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			if (bytes.length > i) {
				ret.append(0xff & bytes[i]).append(".");
			} else {
				ret.append(0).append(".");
			}
		}
		if (bytes.length > 3) {
			ret.append(0xff & bytes[3]);
		} else {
			ret.append(0);
		}
		return ret.toString();
	}

	public static int ip2Int(String ip) {
		byte[] bytes = ip2Bytes(ip);
		return (0xff000000 & bytes[0] << 24) | (0x00ff0000 & bytes[1] << 16) | (0x0000ff00 & bytes[2] << 8)
				| (0x000000ff & bytes[3]);
	}

	
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(0xff000000));
	}
	
	public static long ip2Long(String ip) {
		byte[] bytes = ip2Bytes(ip);
		return (0xff000000L & bytes[0] << 24) | (0x00ff0000L & bytes[1] << 16)
				| (0x0000ff00L & bytes[2] << 8)
				| (0x000000ffL & bytes[3]);
	}

	public static String int2Ip(int i) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (i >> 24);
		bytes[1] = (byte) (i >> 16);
		bytes[2] = (byte) (i >> 8);
		bytes[3] = (byte) i;
		return bytes2Ip(bytes);
	}

	public static String long2Ip(long i) {
		return int2Ip((int) i);
	}

	public static boolean isPrivateIp(String ip) {
		// 私有IP的范围包括
		// 10.0.0.0~10.255.255.255(167772160-184549375)
		// 127.0.0.0~127.255.255.255(2130706432-2147483647)
		// 172.16.0.0~172.31.255.255(2886729728-2887778303)
		// 169.254.0.0~169.254.255.255(2851995648-2852061183)
		// 192.168.0.0~192.168.255.255(3232235520-3232301055)
		long i = ip2Long(ip);
		return i >= 167772160L && i <= 184549375L || i >= 2130706432L && i <= 2147483647L || i >= 2886729728L
				&& i <= 2887778303L || i >= 2851995648L && i <= 2852061183L || i >= 3232235520L && i <= 3232301055L;
	}
}
