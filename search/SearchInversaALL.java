package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SearchInversaALL implements SearchAlgorithm{

    @Override
    public void searchDados(String[] dataBase, String filter) {

        mapearChaves(dataBase);
        System.out.println(filter);
        searchList.clear();
        for (String info: dataBase){
            boolean all = false;
            for (String item : filter.split(" ")){
                for (String v1 : filter.split(" ")){
                    all = item.toUpperCase() == v1.toUpperCase();
                    if (all) {
                        break;
                    }
                }
                if (!all) break;
            }
            if (all) searchList.add(info);
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
