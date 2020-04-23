package com.hooklite.endshop.data.models;

import java.util.ArrayList;
import java.util.List;

public class EPage {
    private int number;
    private List<EItem> items = new ArrayList<>();

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<EItem> getItems() {
        return items;
    }

    public void setItems(List<EItem> items) {
        if (items.size() > 45)
            throw new IllegalArgumentException("A page should not contain more than 45 items!");
        this.items = items;
    }
}
