/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.util.JsfUtil;
import ejb.TestFacade;
import ejb.TestResultFacade;
import ejb.UserListFacade;
import ejb.WordFacade;
import entity.UserList;
import java.security.MessageDigest;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kaizawa
 */
@ManagedBean
@SessionScoped
public class SessionBean {
    @EJB
    private UserListFacade userListFacade;
    @EJB    
    private WordFacade wordFacade;
    @EJB
    private TestFacade testFacade;

    public TestFacade getTestFacade() {
        return testFacade;
    }

    public void setTestFacade(TestFacade testFacade) {
        this.testFacade = testFacade;
    }

    public TestResultFacade getTestResultFacade() {
        return testResultFacade;
    }

    public void setTestResultFacade(TestResultFacade testResultFacade) {
        this.testResultFacade = testResultFacade;
    }

    public UserListFacade getUserListFacade() {
        return userListFacade;
    }

    public void setUserListFacade(UserListFacade userListFacade) {
        this.userListFacade = userListFacade;
    }

    public WordFacade getWordFacade() {
        return wordFacade;
    }

    public void setWordFacade(WordFacade wordFacade) {
        this.wordFacade = wordFacade;
    }
    @EJB
    private TestResultFacade testResultFacade;    
    private String username;
    private UserList user;

    public UserList getUser() {
        return user;
    }

    public void setUser(UserList user) {
        this.user = user;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    private String password;

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    public SessionBean() {
    }
    
    private boolean loggedIn = false;

    /**
     * Get the value of loggedin
     *
     * @return the value of loggedin
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Set the value of loggedin
     *
     * @param loggedin new value of loggedin
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public boolean isLoggedOut() {
        return !isLoggedIn();
    }
    
    public String loginAction(){
        user = userListFacade.findByUsername(this.username);

        /*  Not Using Digest now.
        String digest = null;
        try {      
            digest = getDigest(this.password);
        } catch (Exception e){
            JsfUtil.addErrorMessage("ログイン処理が失敗しました.");
        }  
        */

        if ( user == null || !user.getPassword().equals(password)) {
            JsfUtil.addErrorMessage("ログインに失敗しました. ユーザ名かパスワードが間違っています.");
            return null;
        }
        setLoggedIn(true);

        return "index";
    }
    
    private String getDigest(String data) throws Exception {

        StringBuilder s = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] dat = data.getBytes();
        md.update(dat);

        byte[] digest = md.digest();
        for (int i = 0; i < digest.length; i++) {
            int d = digest[i];

            if (d < 0) { // byte 128-255
                d += 256;
            }
            if (d < 16) { //0-15 16
                s.append("0");
            }
            s.append(Integer.toString(d, 16));
        }
        return s.toString();
    }
    
    private TestStatus testStatus = null;

    /**
     * Get the value of testStatus
     *
     * @return the value of testStatus
     */
    public TestStatus getTestStatus() {
        if(null == testStatus || testStatus.isResetted() ){
            testStatus = new TestStatus(this);
        }
        return testStatus;
    }
    
    public String answerAction() {
        return testStatus.checkAnswer();
    }

}
