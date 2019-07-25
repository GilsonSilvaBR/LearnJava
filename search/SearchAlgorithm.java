package search;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

interface SearchAlgorithm {

    List<String> searchList = new ArrayList<>();
    TreeMap<String, List<Integer>> mapPeoples = new TreeMap<>();

    void searchDados(String[] dataBase, String filter);

    void mapearChaves(String[] dataBase);
}
