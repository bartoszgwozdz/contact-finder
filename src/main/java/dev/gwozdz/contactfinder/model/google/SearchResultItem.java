package dev.gwozdz.contactfinder.model.google;

public class SearchResultItem {
    public String title;
    public String formattedUrl;

    @Override
    public String toString() {
        return String.format("%s \"%s\"\n", title, formattedUrl);
    }
}
