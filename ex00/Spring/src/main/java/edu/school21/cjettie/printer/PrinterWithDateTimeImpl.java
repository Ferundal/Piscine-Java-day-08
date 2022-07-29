package edu.school21.cjettie.printer;

import edu.school21.cjettie.renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    Renderer renderer;

    public PrinterWithDateTimeImpl (Renderer renderer) {
        this.renderer = renderer;
    }
    @Override
    public void print(String stringToPrint) {
        this.renderer.render(LocalDateTime.now().toString() + ": " + stringToPrint);
    }
}
