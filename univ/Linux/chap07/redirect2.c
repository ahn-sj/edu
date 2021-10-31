/*
[결과] argv[1](out.txt)파일에 출력을 재지정하고 test.txt파일을 wc한 결과를 argv[1]파일에 지정해라.
-------------------------------
$ ./redirect2 out.txt wc test.txt
[1000] 자식 프로세스 1001 끝
$ cat out.txt 
 1  3 19 test.txt
-------------------------------
*/
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/wait.h>
/*child process standard output file redirect*/
int main(int argc, char *argv[])
{
    int child, pid, fd, status;
    pid = fork();

    if(pid == 0){
	fd = open(argv[1], O_CREAT|O_TRUNC|O_WRONLY,0600);
	dup2(fd, 1);
	close(fd);
	execvp(argv[2], &argv[2]);
	fprintf(stderr, "%s: 실행 불가 \n", argv[1]); 
    }
    else {
	child = wait(&status);
	printf("[%d] 자식 프로세스 %d end \n", getpid(), child); 
    }
}
