import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

%%
%class UtilisationsAbcmapAnnee2015
%standalone
%{
	
	// instruction placée en haut de script - ou pas :)
	
	private static HashMap<String, Integer> ipList = new HashMap<>();

	/**
	 * Source: http://www.mkyong.com/, modifiée
	 * 
	 * @param unsortMap
	 * @param croissant
	 * @return
	 */
	public static Map<String, Integer> sortByComparator(
			Map<String, Integer> unsortMap, final boolean croissant) {

		// Convert Map to List
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(
				unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				if (croissant) {
					return (o1.getValue()).compareTo(o2.getValue());
				} else {
					return -(o1.getValue()).compareTo(o2.getValue());
				}
			}
		});

		// Convert sorted map back to a Map
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	static {
		System.out
				.println("Statistiques sur l'utilisation du logiciel Abc-Map pour l'année 2015");
		
	}
%}
%{eof
		
		// instruction executee à la fin du script
		
		System.out.println("Nombre d'adresses IP");
		System.out.println(ipList.size());
		System.out.println();

		int max = 20;
		System.out.println("Les " + max + " plus fréquentes: ");
		System.out.println();


		int i = 0;
		Iterator<String> keys = sortByComparator(ipList, false).keySet().iterator();
		while (keys.hasNext()) {
			String k = keys.next();
			Integer v = ipList.get(k);
			System.out.println("#" + i + ": " + k + " : " + v);
			
			if(i > max){
				break;
			}
			
			i++;
		}
		
%eof}
	ip=[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+
	other=.
%%
 {ip} {

		//System.out.println(yytext());

		Integer ping = ipList.get(yytext());
		if (ping == null) {
			ipList.put(yytext(), 0);
		} else {
			ipList.put(yytext(), ++ping);
		}
	
 	}
 	
 {other} {}
