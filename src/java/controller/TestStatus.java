/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Test;
import entity.TestResult;
import entity.Word;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author kaizawa
 */
@ManagedBean
public class TestStatus {

    private Test test;
    private SessionBean sessionBean;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    TestStatus(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
        test = new Test();
        test.setTest_date(new Date());
        sessionBean.getTestFacade().create(test);
        setQuestionList();
    }
    private boolean finished = false;
    private boolean resetted = false;

    public boolean isResetted() {
        return resetted;
    }

    public void setResetted(boolean resetted) {
        this.resetted = resetted;
    }

    /**
     * Get the value of finished
     *
     * @return the value of finished
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Set the value of finished
     *
     * @param finished new value of finished
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    private int word_index = 0;
    private List<Question> questionList = new ArrayList<Question>();

    /**
     * Set list of 5 random words within table.
     *
     * @return wordList
     */
    private void setQuestionList() {
        List<Word> allWordList = sessionBean.getWordFacade().findAll();

        for (int i = 0; i < 5; i++) {
            while (true) {
                int rand = new Random().nextInt(allWordList.size());
                Word randomWord = allWordList.get(rand);
                if (isAlreadyAdded(questionList, randomWord)) {
                    continue;
                } else {
                    questionList.add(new Question(randomWord, sessionBean));
                    break;
                }
            }
        }
    }

    private boolean isAlreadyAdded(List<Question> questionList, Word randomWord) {
        for (Question q : questionList) {
            if (q.getWord().getEnglish().equals(randomWord.getEnglish())) {
                // this word is already choosen
                return true;
            }
        }
        return false;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    /**
     * Get the value of question
     *
     * @return the value of question
     */
    public String getQuestion() {
        return questionList.get(word_index).getWord().getJapanese();
    }
    private String input;

    /**
     * Get the value of input
     *
     * @return the value of input
     */
    public String getInput() {
        return input;
    }

    /**
     * Set the value of input
     *
     * @param input new value of input
     */
    public void setInput(String input) {
        this.input = input;
    }

    public String checkAnswer() {
        Question q = questionList.get(word_index);
        TestResult result = new TestResult();
        if (q.getWord().getEnglish().equalsIgnoreCase(input)) {
            // Correct
            q.setResult(true);
        } else {
            // Incorrect
            q.setResult(false);
        }

        result.setTest(test);
        result.setTest_result(q.isResult());
        result.setUser(sessionBean.getUser());
        result.setWord(q.getWord());
        sessionBean.getTestResultFacade().create(result);
        
        input = "";

        word_index++;            
        if (word_index > 4) {
            finished = true;            
            return "result";
        } else {
            return "index";
        }
    }
    
    /**
     * Reset current Test Status
     * @return 
     */
    public String reset(){
        resetted = true;
        word_index = 0;
        return "index";
    }
    public boolean isRunning(){
        return ! finished;
    }
}
