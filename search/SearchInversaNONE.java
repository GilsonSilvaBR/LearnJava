package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SearchInversaNONE implements SearchAlgorithm{

    @Override
    public void searchDados(String[] dataBase, String filter) {
        mapearChaves(filter.split(" "));
        System.out.println(filter);
        searchList.clear();

        /*for (String item : filter.split(" ")){
            if (mapPeoples.containsKey(item.toUpperCase())){
                for (int lin: mapPeoples.get(item.toUpperCase())){
                    if (searchList.indexOf(dataBase[lin]) == -1){
                        searchList.add(dataBase[lin]);
                    }
                }
            }
        }
*/
        for (String item : dataBase){
            boolean none = true;
            for (String filtro : item.split(" ")){
                if (mapPeoples.containsKey(filtro.toUpperCase())){
                    none = false;
                }
            }
            if (none) {
                if (searchList.indexOf(item) == -1){
                    searchList.add(item);
                }
            }
        }

        for (String item : searchList){
            System.out.println(item);
        }
    }

    @Override
    public void mapearChaves(String[] dataBase) {
        mapPeoples.clear();
        for (int i = 0; i < dataBase.length; i++){
            String linha = dataBase[i];
            for (int x=0;x < linha.split("[ ]").length; x++){
                String key = linha.split("[ ]")[x];
                if (!mapPeoples.containsKey(key.toUpperCase())){
                    mapPeoples.put(key.toUpperCase(), Collections.singletonList(i));
                }else{
                    List<Integer> value = new ArrayList<>(mapPeoples.get(key.toUpperCase()));
                    value.add(i);
                    mapPeoples.put(key.toUpperCase(), value);
                }
            }
        }
    }
}
