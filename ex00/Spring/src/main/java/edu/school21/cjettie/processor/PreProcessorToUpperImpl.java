package edu.school21.cjettie.processor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String proceed(String stringToProceed) {
        return stringToProceed.toUpperCase();
    }
}
