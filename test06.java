package class05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

class Student {

	//멤버변수
	static Scanner sc = new Scanner(System.in);
	private int pk; //학번 : 접근에 주의
	int score; //점수
	String name; //학생이름

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public Student(int pk, String name) {
		this(pk, 0 , name);
	}

	public Student(int pk, int score, String name) {
		this.pk = pk;
		this.score = score;
		this.name = name;
	}

	public void show() { //학생정보출력메서드	
		System.out.println(pk +" | " + score+"점 | "+ name);
		System.out.println();
	}

	@Override
	public String toString() { //출석부
		return pk +" | "+name;
	}

}

public class Test05 {

	private static Scanner sc;

	public static void main(String[] args) {

		sc = new Scanner(System.in);
		Random r = new Random();
		String mainMsg = "1. 출석부 출력 \n2. 학생 등록 \n3. 학생 정보 출력 \n4. 학생 정보 변경 \n5. 학생 삭제 \n6. 1등 학생 조회 \n7. 프로그램 종료";
		ArrayList <Student> data = new ArrayList <Student> ();
		int choice; // main에서의 사용자 선택
		int num; // 입력받는 학번
		int topIndex = 0; // 1등 학생 index
		int pk = 2022000; // 학번의 시작번호
		String errMsg = "해당 학번의 학생은 존재하지 않습니다"; //유효성검사멘트
		String missMsg = "잘못된 범위입니다"; //경계값검사멘트

		//기초데이터
		data.add(new Student(++pk, 73, "고길동"));
		data.add(new Student(++pk, 85, "곽나리"));


		while(true) {

			//main화면 출력
			System.out.println("======화면======");
			System.out.println(mainMsg);
			System.out.println("===============");

			//사용자에게 받을 입력
			System.out.println(">>");
			choice = sc.nextInt();

			// 종료조건
			if(choice==7) { 

				System.out.println("프로그램 종료");
				break;

				//출석부 출력
			} else if(choice==1){ 
        
        // for each와 같은 기능으로 사용
				Iterator <Student> itr = data.iterator();
				while(itr.hasNext()) {
					System.out.println(itr.next() + " ");
				}

				//학생 등록
			} else if (choice==2) { 

				System.out.println("등록할 학생 이름 : ");
				String name = sc.next(); // name = 입력받은 이름
				int score = r.nextInt(101); // score = 랜덤생성
				data.add(new Student(++pk, score, name)); // pk자동증가, score은 랜덤, name은 입력받은 대로

				//학생정보출력
			} else if (choice==3) { 

				while(true) {
					System.out.println("조회할 학생의 학번 : ");
					num = sc.nextInt();

          // 1. 입력 범위 확인
					if(2022000<num && num<2023000) {
						break; //잘 입력됐으면 종료!
					}
					System.out.println(missMsg);
				}

        // 2. pk존재 유무 확인
				boolean flag = false; // 학번존재 : true / 미존재 : false
				for(int i=0; i<data.size(); i++) {
					if(num==data.get(i).getPk()) {
						flag=true;
						data.get(i).show();
						break;
					}					
				}
				if(!flag) {
					System.out.println(errMsg);
				}

				//학생정보변경
			} else if (choice==4) { 

				while(true) {
					System.out.println("변경할 학생의 학번 : ");
					num = sc.nextInt();

					if(2022000<num && num<2023000) {
						break; //잘 입력됐으면 종료!
					}
					System.out.println(missMsg);
				}

				boolean flag = false; // 학번존재 : true / 미존재 : false
				for(int i=0; i<data.size(); i++) {
					if(num==data.get(i).getPk()) {
						flag=true;

						System.out.println("변경할 점수 : ");
						int newScore = sc.nextInt();

						if(newScore>=0 && newScore<=100) { //입력이 0~100사이면
							data.get(i).score=newScore;
							System.out.println("수정 완료");
						} else { // 0~100이외의 값이 입력되면
							System.out.println("잘못된 점수입니다.");
						}
						break;
					}					
				}
				if(!flag) {
					System.out.println(errMsg);
				}

				//학생삭제
			} else if (choice==5) { 

				while(true) {
					System.out.println("삭제할 학생의 학번 : ");
					num = sc.nextInt();

					if(2022000<num && num<2023000) {
						break; //잘 입력됐으면 종료!
					}
					System.out.println(missMsg);
				}

				boolean flag = false; // 학번존재 : true / 미존재 : false
				for(int i=0; i<data.size(); i++) {
					if(num==data.get(i).getPk()) {
						flag=true;
						data.remove(i);
						System.out.println("삭제 완료");
						System.out.println();
						break;
					}					
				}
				if(!flag) {
					System.out.println(errMsg);
				}

				//1등 학생 조회
			} else if(choice==6){ 

				//점수의 최대값 조회
				topIndex = 0;
				for(int i=1;i<data.size();i++) {
					if(topIndex<data.get(i).score) {
						topIndex=data.get(i).score;
					}
				} 

				//최대값을 가진 학생들을 출력
				for(int i=0;i<data.size();i++) {
					if(topIndex==data.get(i).score) {
						data.get(i).show();
					}
				}


			} else { // 유효성 검사
				System.out.println("잘못된 입력입니다");
			}

		}

	}
}
