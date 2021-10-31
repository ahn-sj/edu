/*
[결과]
-----------------
$ ./execute1
부모 프로세스 시작
부모 프로세스 끝
hello
-----------------
*/
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main()
{
    printf("부모 프로세스 시작 \n");

    if(fork() == 0){ // 자식 프로세스
	execl("/bin/echo", "echo", "hello", NULL);
// fprintf()와 exit(1)는 execl이 실행되면서 자식프로세스의 프로그램이 새로운 파일으로 대치가 되므로 아래의 문장은 사라지게 되므로 실행되지 않는다.
	fprintf(stderr, "첫 번째 실패\n");
	exit(1);
    }
    printf("부모 프로세스 끝 \n ");
}
