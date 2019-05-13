package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * 统计TXT文件中每个单次出现的次数：
 *
 * @author Zhang
 *
 */
@SuppressWarnings("unused")
public class Count_word {
	public static void main(String[] args) throws IOException {
		try {
			InputStream in = new FileInputStream(
					"F:\\Java\\Algorithms\\algs4-data\\shellsST.txt");
			Reader reader = new InputStreamReader(in);
			BufferedReader bufferedReader = new BufferedReader(reader);

			Map<String, Integer> map = new HashMap<>();

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				String[] arr = str.split(" ");
				for (String s : arr) {
					if (map.get(s) == null) {
						map.put(s, 1);
					} else {
						map.put(s, map.get(s) + 1);
					}
				}
			}
			bufferedReader.close();
			reader.close();
			in.close();

			/*System.out
					.println("———————————————————————————1———————————————————————————————————");
			Iterator<Map.Entry<String, Integer>> entries = map.entrySet()
					.iterator();
			while (entries.hasNext()) {
				Map.Entry<String, Integer> entry = entries.next();
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
			 */
			// System.out.println("———————————————————————————2———————————————————————————————————");
			// for(Map.Entry<String, Integer> entry:map.entrySet()){
			// System.out.println( entry.getKey()+":"+entry.getValue());
			// }
			//
			/*
			 * System.out.println(
			 * "———————————————————————————3———————————————————————————————————"
			 * ); Iterator entries2 = map.entrySet().iterator(); while
			 * (entries2.hasNext()) { Map.Entry entry = (Map.Entry)
			 * entries2.next(); System.out.println(
			 * entry.getKey()+":"+entry.getValue()); }
			 */

			// 要想阿排序，就是要是有TreeMap
			/**
			 * 按照key排序
			 *
			 */
			Comparator<String> comparator = new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			};
			Map<String, Integer> map2 = new TreeMap<>(comparator);
			map2.putAll(map);
			System.out
					.println("———————————————————————————3———————————————————————————————————");

			// print(map2);

			/***
			 * 案值排序: 相对计较麻烦 原理：将map中的键值对放入list列表中，Collections.sort(List<T> list,
			 * Comparator<? super T> c),之后装入map中
			 */
			// 将将map中的键值对放入list列表中
			List<Map.Entry<String, Integer>> entriesList = new ArrayList<Map.Entry<String, Integer>>(
					map.entrySet());
			Collections.sort(entriesList,
					new Comparator<Map.Entry<String, Integer>>() {
						@Override
						public int compare(Entry<String, Integer> o1,
								Entry<String, Integer> o2) {
							// TODO Auto-generated method stub
							return o1.getValue().compareTo(o2.getValue());
						}
					});

			Map<String, Integer> map3 = new LinkedHashMap<>();  //LinkedHashMap是有序的

			//这个方法可以
			// for(Map.Entry<String, Integer>ma:entriesList){
			// map3.put(ma.getKey(), ma.getValue());
			// }
			//
			Iterator<Map.Entry<String, Integer>> iterator = entriesList
					.iterator();
			Entry<String, Integer> entry = null;
			while (iterator.hasNext()) {
				entry = iterator.next();
				map3.put(entry.getKey(), entry.getValue());
			}

			// Iterator<Entry<String, Integer>> iter = entriesList.iterator();
			// Entry<String, Integer> tmpEntry = null;
			// while (iter.hasNext()) {
			// tmpEntry = iter.next();
			// map3.put(tmpEntry.getKey(), tmpEntry.getValue());
			// }

			print(map3);
			System.out.println(map3.size() + "\n");
			System.out.println(map2.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("rawtypes")
	private static void print(Map map) {
		// TODO Auto-generated method stub
		Iterator entries2 = map.entrySet().iterator();
		while (entries2.hasNext()) {
			Map.Entry entry = (Map.Entry) entries2.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
				oriMap.entrySet());
		Collections.sort(entryList,
				new Comparator<Map.Entry<String, String>>() {

					@Override
					public int compare(Entry<String, String> o1,
							Entry<String, String> o2) {
						// TODO Auto-generated method stub
						return o1.getValue().compareTo(o2.getValue());
					}
				});

		Iterator<Map.Entry<String, String>> iter = entryList.iterator();
		Map.Entry<String, String> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}
}
