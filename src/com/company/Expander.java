package com.company;

import java.util.Collections;

public class Expander implements Runnable {

    Node element;

    @Override
    public void run() {
        element.expand(0);
        if(element.children != null) Collections.addAll(Main.toExpand, element.children);
    }

    Expander(Node element) {
        this.element = element;
    }

}
