package pl.piotrbartoszak.model;

import org.springframework.stereotype.Component;

@Component
public class PageParameter {
    private int elementsOnPage = 5;

    public int getElementsOnPage() {
        return elementsOnPage;
    }

    public void setElementsOnPage(int elementsOnPage) {
        this.elementsOnPage = elementsOnPage;
    }
}
