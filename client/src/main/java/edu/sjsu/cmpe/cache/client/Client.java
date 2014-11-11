package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.hash.Hashing;

public class Client {
	static CacheServiceInterface nodeB;
	static CacheServiceInterface nodeC;
	static CacheServiceInterface nodeA;

	public static void main(String[] args) throws Exception {

		System.out.println("Starting Cache Client...");
		// CacheServiceInterface cache = new DistributedCacheService(
		// "http://localhost:3000");
		ArrayList<String> al = new ArrayList<String>();
		al.add("http://localhost:3000");
		al.add("http://localhost:3002");
		al.add("http://localhost:3001");
		ConsistentHash<String> ch = new ConsistentHash<String>(Hashing.md5(),
				1, al);
		for (int i = 1, j = 97; i <= 10 && j <= 106; i++, j++) {
			String serverURL = ch.get(i);
			CacheServiceInterface cache = new DistributedCacheService(serverURL);
			System.out.println(serverURL);
			cache.put(i, String.valueOf((char) j));
			System.out.println("get(" + i + ") => " + cache.get(i));
		}
		System.out.println("Existing Cache Client...");
		/*
		 * Map<String, String> map;
		 * System.out.println("Starting Cache Client..."); nodeA = new
		 * DistributedCacheService("http://localhost:3000"); nodeB = new
		 * DistributedCacheService("http://localhost:3001"); nodeC = new
		 * DistributedCacheService("http://localhost:3002"); map = setUpCache();
		 * addToNode(map); getValuesFromCache();
		 * System.out.println("Existing Cache Client...");
		 */

	}

	private static void getValuesFromCache() {
		System.out.println("---------Fetching Values---------");
		int keyHashCode;
		int pushToNodeValue;

		for (long i = 1; i < 11; i++) {
			keyHashCode = String.valueOf(i).hashCode();
			pushToNodeValue = keyHashCode % 3;
			System.out.println("mod value " + pushToNodeValue);
			switch (pushToNodeValue) {
			case 0:
				System.out.println("get from node A");
				System.out.println(nodeA.get(i));
				break;
			case 1:
				System.out.println("get from node B");
				System.out.println(nodeB.get(i));
				break;
			case 2:
				System.out.println("get from node C");
				System.out.println(nodeC.get(i));
				break;

			default:
				break;
			}
		}

	}

	private static void addToNode(Map<String, String> map) {
		System.out.println("---------Inserting Values---------");
		int keyHashCode;
		int pushToNodeValue;
		for (long i = 1; i < 11; i++) {
			keyHashCode = String.valueOf(i).hashCode();
			pushToNodeValue = keyHashCode % 3;
			System.out.println("mod value " + pushToNodeValue);
			switch (pushToNodeValue) {
			case 0:
				System.out.println("push " + i + "  to node A");
				nodeA.put(i, map.get(String.valueOf(i)));
				break;
			case 1:
				System.out.println("push " + i + "  to node B");
				nodeB.put(i, map.get(String.valueOf(i)));
				break;
			case 2:
				System.out.println("push " + i + "  to node C");
				nodeC.put(i, map.get(String.valueOf(i)));
				break;

			default:
				break;
			}

		}

	}

	private static Map<String, String> setUpCache() {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map = new HashMap<String, String>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");
		map.put("4", "d");
		map.put("5", "e");
		map.put("6", "f");
		map.put("7", "g");
		map.put("8", "h");
		map.put("9", "i");
		map.put("10", "j");

		return map;

	}

}
