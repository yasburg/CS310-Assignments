package edu.sabanciuniv.yasinaydinhomework3.model;

import java.util.ArrayList;
import java.util.List;

public class FilterItem {

    private int id;
    private String filterName;

    public FilterItem() {
    }

    public FilterItem(int id, String filterName) {
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

    public static List<FilterItem> getAllFilters(){

        List<FilterItem> filterList = new ArrayList<>();

        FilterItem f1 = new FilterItem(1, "filter1");
        FilterItem f2 = new FilterItem(2, "filter2");
        FilterItem f3 = new FilterItem(3, "filter3");
        FilterItem f4 = new FilterItem(4, "filter4");

        filterList.add(f1);
        filterList.add(f2);
        filterList.add(f3);
        filterList.add(f4);

        return filterList;
    }
}
