/*
[결과]
--------------------------------------
$ ./pexec1 who
자식 프로세스로부터 받은 결과
ahnsj    tty7    2020-12-11 12:57 (:0)
--------------------------------------
*/
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

#define MAXLINE 100

int main(int argc, char* argv[])
{
    int n, pid, fd[2];
    char line[MAXLINE];

    pipe(fd); /* 파이프 생성 */

    if((pid=fork()) == 0) { // 자식 프로세스
	close(fd[0]);
	dup2(fd[1], 1); // 표준출력을 fd[1]로 변경
	close(fd[1]);
	execvp(argv[1], &argv[1]); // 인자로 받은 명령어를 실행
    } else { // 부모 프로세스
	close(fd[1]);
	printf("자식 프로세스로부터 받은 결과 \n");
	while((n = read(fd[0], line, MAXLINE)) > 0)
	    write(STDOUT_FILENO, line, n);
    }
    exit(0);
}
