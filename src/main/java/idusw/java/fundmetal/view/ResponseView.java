package idusw.java.fundmetal.view;

import idusw.java.fundmetal.model.Member;

/**
 * 응답을 처리하여 사용자에게 보여줌
 */
public class ResponseView {
    public void registerSuccess(Member m) {
        System.out.println("[Successful Registration] : " + m.getName());
    }

    public void loginSuccess(Member m) {
        System.out.println("Welcome : " + m.getName());
    }

    public void logoutSuccess(Member m) {
        System.out.println("See you again :  " + m.getName());
    }
}
