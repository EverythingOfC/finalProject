package idusw.java.fundmetal.controller;

import idusw.java.fundmetal.*;
import idusw.java.fundmetal.model.Member;
import idusw.java.fundmetal.view.InformationView;
import idusw.java.fundmetal.view.RequestView;
import idusw.java.fundmetal.view.ResponseView;

import java.util.HashMap;

public class MemberController {
    public void requestViewProfile(Member m) {  // 프로필 요청
        System.out.println("Profile Details:");
        System.out.println("Email: " + m.getEmail());
        System.out.println("Name: " + m.getName());
        System.out.println("Phone: " + m.getPhone());
        System.out.println("Address: " + m.getAddress());
    }

    public void requestUpdateProfile(Member m) {
        System.out.println("Update Profile:");
        System.out.println("Enter new information:");

        Member updatedMember = new RequestView().inputToDto();
        if (updatedMember != null) {
            m.setEmail(updatedMember.getEmail());
            m.setPw(updatedMember.getPw());
            m.setName(updatedMember.getName());
            m.setPhone(updatedMember.getPhone());
            m.setAddress(updatedMember.getAddress());
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Invalid input. Profile update failed.");
        }
    }

    public void requestDeleteAccount(Member m) {
        PimsApplication.memberPersistence.remove(m);
        PimsApplication.session = new HashMap<>();
        System.out.println("Account deleted successfully.");
    }

    public void requestPrintEmailNameList(Member m) {
        System.out.println("Email and Name List:");
        for (Member member : PimsApplication.memberPersistence) {
            if (member.getEmail().equals(m.getEmail())) {
                System.out.println("Email: " + m.getEmail() + ", Name: " + m.getName());
            }
        }
    }

    public void requestRegister(Member m) {
        PimsApplication.memberPersistence.add(m);
        // DBMS 또는 File 에 저장하는 것이 필요함
        new ResponseView().registerSuccess(m);
    }

    public void requestLogin(Member m) {
        int rows = 0;
        for (Member member : PimsApplication.memberPersistence) {
            if (m.getEmail().equals(member.getEmail()) && m.getPw().equals(member.getPw())) {
                rows = 1;
                PimsApplication.session.put("login", member);
            }
        }
        // view에게 전달
        if (rows != 0)
            new ResponseView().loginSuccess(PimsApplication.session.get("login"));
        else
            new InformationView().exceptionHandling("Login Failure");
    }

    public void requestLogout(Member m) {
        PimsApplication.session = new HashMap<>();
        new ResponseView().logoutSuccess(m);
    }
}
