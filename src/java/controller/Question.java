/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.TestResult;
import entity.Word;
import java.util.List;

/**
 *
 * @author kaizawa
 */
public class Question {
    private Word word;
    private boolean result;
    private SessionBean sessionBean;

    /**
     * Get the value of result
     *
     * @return the value of result
     */
    public boolean isResult() {
        return result;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    /**
     * Set the value of result
     *
     * @param result new value of result
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    Question (Word word, SessionBean sessionBean) {
        this.word = word;
        this.sessionBean = sessionBean;
    }
    
    public boolean isCorrect(){
        return result;
    }
    
    public boolean isIncorrect(){
        return ! result;
    }
    
    public int getAccuracyRate(){
        int rate; // in percent
        rate = sessionBean.getTestResultFacade().getAcucurencyRateByWord(word);
        return rate;
    }
    
}
