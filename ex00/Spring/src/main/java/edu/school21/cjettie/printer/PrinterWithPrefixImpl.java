package edu.school21.cjettie.printer;

import edu.school21.cjettie.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String prefix = new String("");

    public PrinterWithPrefixImpl (Renderer renderer) {
        this.renderer = renderer;
    }
    @Override
    public void print(String stringToPrint) {
        if (!prefix.isEmpty()) {
            stringToPrint = String.format("%s%s", prefix, stringToPrint);
        }
        renderer.render(stringToPrint);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
