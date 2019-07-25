package search;

class SelectionContext {
    private SearchAlgorithm algorithm;

    public void setAlgorithm(SearchAlgorithm algorithm){
        this.algorithm = algorithm;
    }

    public void search(String[] dataBase, String filter){
        this.algorithm.searchDados(dataBase, filter);
    }


}