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
    struct student *tail = NULL;

    printf("학번과 이름을 입력하세요 > ");

    while(scanf("%d %s", &id, name) == 2) {
	p = (struct student *)malloc(sizeof(struct student));
	if(p == NULL) { // 메모리 공간 할당 실패 시 에러메세지
	    perror("malloc");
	    exit(1);
        }
    p->id = id;
    strcpy(p->name, name);

    p->next = tail;

    if(head == NULL) // first cycle
	head = p;	
    else
	head->next = p;
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
