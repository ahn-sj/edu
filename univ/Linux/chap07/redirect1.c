/*
[결과] 표준출력을 파일에 재지정하는 프로그램
-----------------------
$ ./redirect1 test.txt
hello stderr!
$ cat test.txt
hello stdout!
-----------------------
*/
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>

int main(int argc, char *argv[])
{
    int fd, status;
    fd = open(argv[1], O_CREAT|O_TRUNC|O_WRONLY, 0600);
    dup2(fd, 1); /* 파일을 표준출력에 복제 */
    close(fd);
    printf("hello stdout! \n");
    fprintf(stderr, "hello stderr! fprtf \n");
}
