/*
[결과]
----------------------------
$ ./pipe
[1000] Hello from PID [1001]
----------------------------
*/
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXLINE 100

int main()
{
    int n, length, fd[2];
    int pid;
    char message[MAXLINE], line[MAXLINE];

    pipe(fd); /* pipe 생성 fd[0] fd[1] */ 

    if((pid = fork()) == 0) { /* 자식 프로세스 */ 
	close(fd[0]);  // 읽기 fd 닫기
	sprintf(message, "Hello from PID %d \n", getpid());
	length = strlen(message) + 1;
	write(fd[1], message, length);  
// 쓰기 fd에 mesaage에 든 내용을 length크기만큼 쓰기
    } else { /* 부모 프로세스 */ 
	close(fd[1]);  // 쓰기 fd 닫기
	n = read(fd[0], line, MAXLINE);
// 읽기 fd에 있는 내용을 MAXLINE만큼 가져와서 line에 모아서 n에 대입
	printf("[%d] %s", getpid(), line);
    }
    exit(0);
}
