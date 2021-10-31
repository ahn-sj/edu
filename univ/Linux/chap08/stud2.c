/*
[결과]
----------------------------------------
$ ./stud2
학번과 이름을 입력하세요 > 201605131 안성재
[CTRL+D]
* 학생 정보(역순) *
학번 : 201605131 이름 : 안성재
총 1명 입니다.

----------------------------------------
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct student {
    int id;
    char name[20];
    struct student *next;
};

int main()
{
    int count = 0, id;
    char name[20];
    struct student *p; 
    struct student *head = NULL;
// head는 연결리스트의 시작주소를 가르키는 포인터변수

    printf("학번과 이름을 입력하세요 > ");

// 입력받은 id, name값이 2개인 경우 while문 실행
    while(scanf("%d %s", &id, name) == 2) {
	p = (struct student *)malloc(sizeof(struct student));
	if(p == NULL) { // 메모리 공간 할당 실패 시 에러메세지
	    perror("malloc");
	    exit(1);
        }
/*
-> 연산자(arrow operator) : .이랑 같음
p - >id = id; // 메모리가 할당된 p구조체 id에 입력한 id값 대입
strcpy(p -> name, name); // strcpy함수로 문자열 복사 기능은 위와동
*/
    p->id = id;
    strcpy(p->name, name);
/* 
연결리스트끼리 연결 
1) 처음 실행 시 head = NULL이다. 그러므로 p의 next를 NULL값으로 지정
그리고 할당된 메모리의 시작 주소값을 head에 대입
결과적으로 head는 메모리 할당한 p의 시작주소를 가르키고 있음

2) 두 번째 메모리 할당 시
두 번째 레코드의 값이 새로운 p구조체의 id와 name에 들어간다.
p->next=head를 만나게되면 근데 head는 첫 번째 입력한 값의 시작주소값을 가르키고 있기 때문에 p.next가 첫 번째 입력한 값의 시작주소 값을 가르키게 된다.
그런 다음, head=p문장을 만나게 되면서 두 번째 레코드의 시작주소 값을 가지는 p를 head에 넣어줌으로서 연결리스트가 이루어지게 된다.
*/
    p->next = head;
    head = p;	
    }

    printf("\n * 학생 정보(역순) * \n");
    p = head;
    while(p != NULL) {
	count++;
	printf("학번 : %d 이름 : %s\n", p->id, p->name);
	p = p->next;
    }
    printf("총 %d 명입니다. \n", count);
    exit(0);
}
