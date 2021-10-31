/*
[결과]
부모 : 1000 / 자식[1] : 1001 / 자식[2] : 1002
--------------------------------
[1000] 부모 프로세스 시작
[1001] 자식 프로세스 [1] 시작
[1002] 자식 프로세스 [2] 시작
[1001] 자식 프로세스 [1] 종료
[1000] 자식 프로세스 #1 1001 종료
    [종료코드] 1
[1002] 자식 프로세스 [2] 종료
--------------------------------
*/
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int pid1, pid2, child, status;

    printf("[%d] 부모 프로세스 시작 \n", getpid());
    pid1 = fork();  // 자식프로세스 [1] 생성

    if(pid1 == 0){
	printf("[%d] 자식프로세스 [1] 시작 \n", getpid());
	sleep(1);
	printf("[%d] 자식프로세스 [1] 종료 \n", getpid());
	exit(1);
    }

    pid2 = fork();   // 자식프로세스 [2] 생성
    if(pid2 == 0){
	printf("[%d] 자식프로세스 [2] 시작 \n", getpid());
	sleep(2);
	printf("[%d] 자식프로세스 [2] 끝 \n", getpid());
	exit(2);
    }

    child = wait(pid1, &status, 0);
    printf("[%d] 자식프로세스 #1 %d 종료 \n", getpid(), child);
    printf("\t 종료코드 %d \n", status >> 8);
}
