package com.hooklite.endshop.data;

import com.hooklite.endshop.gui.EPage;

import java.util.List;

public class EShop {
    private String title;
    private List<String> description;
    private String displayItem;
    private boolean showConditions;
    private boolean showRewards;
    private int slot;
    private List<EItem> items;
    private List<EPage> pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getDisplayItem() {
        return displayItem;
    }

    public void setDisplayItem(String displayItem) {
        this.displayItem = displayItem;
    }

    public boolean isShowConditions() {
        return showConditions;
    }

    public void setShowConditions(boolean showConditions) {
        this.showConditions = showConditions;
    }

    public boolean isShowRewards() {
        return showRewards;
    }

    public void setShowRewards(boolean showRewards) {
        this.showRewards = showRewards;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public List<EItem> getItems() {
        return items;
    }

    public void setItems(List<EItem> items) {
        this.items = items;
    }

    public List<EPage> getPages() {
        return pages;
    }

    public void setPages(List<EPage> pages) {
        this.pages = pages;
    }
}
