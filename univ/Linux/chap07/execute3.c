/*
[결과] 부모프로세스 : 1000 / 자식프로세스 : 1001
----------------------------
$ ./execute3 echo hello
hello
[1000] 자식프로세스 1001 종료
    종료코드 0
----------------------------
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    int child, pid, status;
    pid = fork();

    if(pid == 0) { // child process
	execvp(argv[1], &argv[1]); // 실행할 프로그램 및 인자값전달
	fprintf(stderr, "%s : 실행 불가 \n", argv[1]);
    }
    else { // parent process
	child = wait(&status);
	printf("[%d] 자식 프로세스 %d 종료 \n", getpid(), pid);
	printf("\t 종료코드 %d \n", status >> 8); 
// 정상실행시 반환값이 없으므로 default값인 '0'이 출력됨
    }
}
