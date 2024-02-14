package com.java.newscycle.service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import java.util.Properties;

import org.springframework.stereotype.Service;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

@Service
public class SentimentAnalyzerService {

    private final StanfordCoreNLP pipeline;

    public SentimentAnalyzerService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    public double analyzeSentiment(String text) {
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        CoreMap sentence = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);
        String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
        return convertSentimentToScore(sentiment);
    }

    private double convertSentimentToScore(String sentiment) {
        switch (sentiment.toLowerCase()) {
            case "very negative":
                return -2.0;
            case "negative":
                return -1.0;
            case "neutral":
                return 0.0;
            case "positive":
                return 1.0;
            case "very positive":
                return 2.0;
            default:
                return 0.0;
        }
    }
}
