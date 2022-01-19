package ru.pigarev.framework.managers;

public class PageManager {

    private static PageManager pageManager = null;

    private PageManager() {
    }

    public PageManager getInstance() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }
}
