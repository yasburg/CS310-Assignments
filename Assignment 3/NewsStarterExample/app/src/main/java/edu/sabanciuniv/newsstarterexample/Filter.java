package edu.sabanciuniv.newsstarterexample;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    private int id;
    private String filterName;

    public Filter() {
    }

    public Filter(int id, String filterName) {
        this.id = id;
        this.filterName = filterName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public static List<Filter> getAllFilters(){

        List<Filter> filterList = new ArrayList<>();

        Filter f1 = new Filter(1, "filter1");
        Filter f2 = new Filter(2, "filter2");
        Filter f3 = new Filter(3, "filter3");
        Filter f4 = new Filter(4, "filter4");

        filterList.add(f1);
        filterList.add(f2);
        filterList.add(f3);
        filterList.add(f4);

        return filterList;
    }
}
