package edu.school21.cjettie.renderer;

import edu.school21.cjettie.processor.PreProcessor;

public class RendererErrImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    @Override
    public void render(String stringToRender) {
        System.err.println(preProcessor.proceed(stringToRender));
    }
}
