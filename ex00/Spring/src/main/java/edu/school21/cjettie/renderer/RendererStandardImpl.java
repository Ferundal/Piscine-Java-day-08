package edu.school21.cjettie.renderer;

import edu.school21.cjettie.processor.PreProcessor;

public class RendererStandardImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    @Override
    public void render(String stringToRender) {
        System.out.println(stringToRender);
    }
}
