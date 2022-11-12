package com.fizzbuzz.models;

public class PageOption {
    private int currentPage;
    private int start;
    private int end;

    public PageOption(int currentPage, int start, int end) {
        this.currentPage = currentPage;
        this.start = start;
        this.end = end;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
