package edu.school21.cjettie.processor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String proceed(String stringToProceed) {
        return stringToProceed.toLowerCase();
    }
}
