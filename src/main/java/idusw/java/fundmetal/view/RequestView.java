package idusw.java.fundmetal.view;

import idusw.java.fundmetal.model.Member;

import java.util.Scanner;

public class RequestView {
    private Scanner sc = new Scanner(System.in);

    Member register() {
        System.out.println("[Registration Form]");
        System.out.println(",로 구분지어 입력해주세요.");
        System.out.format("%s, %s, %s, %s, %s\n", "EMAIL", "PW", "NAME", "PHONE", "ADDRESS");
        Member member = inputToDto();
        return member;
    }

    /**
     * 입력한 정보를 Model 객체에 설정하여 반환
     *
     * @return Member 입력한 정보를 담고 있는 객체
     */
    public Member inputToDto() {
        String[] input = sc.nextLine().split("\\s*,\\s*");

        Member member = null;
        if (input.length == 5) {
            member = new Member();
            member.setEmail(input[0]);
            member.setPw(input[1]);
            member.setName(input[2]);
            member.setPhone(input[3]);
            member.setAddress(input[4]);
        }
        return member;
    }

    /**
     * 로그인 정보를 Model 객체에 설정하여 반환
     *
     * @return Member 로그인 정보를 담고 있는 객체
     */
    Member login() {
        System.out.println("[Login Form]");
        System.out.println("Email, Password");
        String[] input = sc.nextLine().split("\\s*,\\s*"); // , 양쪽에 공백이 있는 모든 문자열을 기준으로 나눠서 배열로 만듬
        Member member = null;
        if (input.length == 2) {
            member = new Member();
            member.setEmail(input[0]);
            member.setPw(input[1]);
        }
        return member;
    }
}
