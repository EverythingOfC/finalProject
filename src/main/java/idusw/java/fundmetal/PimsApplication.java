package idusw.java.fundmetal;

import idusw.java.fundmetal.controller.MemberController;
import idusw.java.fundmetal.view.NavView;
import idusw.java.fundmetal.model.Member;
import idusw.java.fundmetal.repository.MemberRepository;
import idusw.java.fundmetal.service.MemberService;

import java.util.*;

/**
 * PIMS(Personal Information Management System)<br>
 * 개인 정보를 관리할 수 있는 프로그램<br>
 *
 */
public class PimsApplication {
    public static MemberController memberController;
    public static MemberService memberService;
    public static MemberRepository memberRepository;
    public static Map<String, Member> session;
    public static List<Member> memberPersistence; // File or
    static void lookup() {
        memberPersistence = new ArrayList<>();
        session = new HashMap<>();
        memberRepository = new MemberRepository();
        memberService = new MemberService();
        memberController = new MemberController();
    }

    public static void main(String[] args) {
        PimsApplication.lookup(); // 의존(dependency) 관계가 있는 객체를 주입합

        NavView navView = new NavView();

        boolean isLogin = false, isRoot = false;

        int menu;
        do {
            if(session.get("login") != null) {
                isLogin = true;
            } else
                isLogin = false;
            menu = navView.showMenu(isLogin, isRoot);
        } while(menu != 0); // 종료 메뉴외에는 종료
    }
}

/*
        1. PimsApplication: 애플리케이션의 진입점 역할, 필요한 개체를 초기화하고 메뉴를 표시하고 사용자 입력을 처리하여 프로그램의 흐름을 제어
        2. NavView: 사용자에게 메뉴를 표시하는 뷰를 나타냄, 사용자와 상호 작용하여 입력을 받고 선택한 메뉴 옵션에 따라 적절한 메서드를 호출
        3. RequestView: 등록 및 로그인과 관련된 사용자 입력 캡처를 담당, 사용자가 제공한 정보로 Member 개체를 생성하는 메서드를 제공
        4. ResponseView: 등록, 로그인 및 로그아웃 작업에 대한 성공 메시지 표시를 처리
        5. InformationView: 프로그램 실행 중에 발생하는 오류 메시지나 예외를 표시하는 역할
        6. Member: 회원 또는 사용자를 대표하는 모델 클래스, 이메일, 비밀번호, 이름, 전화번호, 주소 및 구성원이 루트 액세스 권한이 있는지를 나타내는 플래그와 같은 다양한 속성이 포함
        7. MemberController: 비즈니스 로직을 처리하고 보기와 서비스/저장소 클래스 간의 중재자 역할, 프로필 보기, 프로필 업데이트, 계정 삭제, 이메일 및 이름 목록 인쇄, 등록, 로그인 및 로그아웃과 같은 작업에 대한 사용자 요청을 처리
        8. MemberService: 구성원 작업과 관련된 비즈니스 로직을 처리하는 서비스 계층을 나타냄, 주어진 코드에서 등록 및 로그인을 위한 메서드는 비어 있지만 적절한 작업을 수행하도록 구현할 수 있음
        9. MemberRepository: 이 클래스는 구성원 데이터 저장 및 검색을 담당하는 지속성 계층을 나타냄, 주어진 코드에서 requestRegister() 메서드는 비어 있지만 멤버 데이터를 데이터베이스나 파일에 저장하도록 구현할 수 있음

        이러한 클래스는 함께 개인 정보 관리 시스템(PIMS) 응용 프로그램을 구성하여 사용자가 등록, 로그인, 프로필 보기/업데이트, 계정 삭제 및 기타 관련 작업을 수행할 수 있도록 함
        */

        /*
        1. PimsApplication: 애플리케이션을 실행하는 메인 클래스


        memberController, memberService, memberRepository: 멤버 관리 및 관련 작업 처리에 사용되는 각 클래스의 정적 인스턴스
        세션: 현재 세션 정보를 유지하기 위해 사용하는 맵
        memberPersistence: 이 목록은 모든 구성원의 현재 상태를 저장하는 데 사용
        lookup(): 이 정적 메소드는 MemberController, MemberService, MemberRepository, session 및 memberPersistence의 인스턴스를 초기화
        main(String[] args): 이것은 애플리케이션의 진입점 시스템을 초기화하고 기본 메뉴 표시 및 사용자 상호 작용을 처리

        NavView: 이 클래스는 사용자 상호 작용, 메뉴 표시 및 사용자 입력 처리를 처리


        sc: 사용자 입력을 읽기 위한 스캐너 인스턴스
        showMenu(boolean isLogin, boolean isRoot): 이 메서드는 사용자의 로그인 상태 및 역할에 따라 적절한 메뉴를 보여줌 또한 사용자 메뉴 선택을 처리하고 적절한 작업을 호출

        2. RequestView: 이 클래스는 다양한 요청에 대한 사용자 입력을 읽음


        sc: 사용자 입력을 읽기 위한 스캐너 인스턴스
        register(): 이 메서드는 회원 등록을 위한 사용자 입력을 캡처
        inputToDto(): 이 메서드는 사용자 입력을 캡처하고 Member 개체를 데이터로 채움
        login(): 이 메서드는 로그인을 위한 사용자 입력을 캡쳐

        3. ResponseView: 이 클래스는 성공적인 작업에 대한 사용자 응답 표시를 처리


        registerSuccess(Member m): 등록 성공 메시지를 보여줌
        loginSuccess(Member m): 로그인 성공 메시지를 보여줌
        logoutSuccess(Member m): 성공적인 로그아웃 메시지를 보여줌

        4. InformationView: 이 클래스는 사용자에게 표시되는 오류 메시지를 처리


        exceptionHandling(String message): 매개변수로 전달된 오류 메시지를 보여줌

        5. Member: 이 클래스는 시스템의 구성원을 모델링


        email, pw, name, phone, address, root와 같은 필드: 구성원의 세부 정보를 저장
        Getter 및 Setter 메서드: 이 메서드를 사용하면 Member 클래스의 필드에 액세스하고 수정할 수 있음

        6. MemberController: 이 클래스는 멤버와 관련된 주요 작업을 처리


        requestViewProfile(Member m), requestUpdateProfile(Member m), requestDeleteAccount(Member m), requestPrintEmailNameList(Member m), **requestRegister(Member m)와 같은 메소드 **, requestLogin(Member m), requestLogout(Member m): 이 메서드는 Member와 관련된 작업을 처리

        7. MemberService: 이 클래스에는 회원과 관련된 비즈니스 로직이 있음


        register(), login(): 이 메서드는 현재 구현되지 않음. 회원 등록 및 로그인과 관련된 비즈니스 로직을 포함해야 함

        8. MemberRepository: 이 클래스는 데이터 소스와 상호 작용함


        9. requestRegister(): 이 메서드에는 현재 구현이 없음, 새 구성원의 세부 정보를 데이터 원본에 저장하기 위한 논리를 포함해야 함

         */