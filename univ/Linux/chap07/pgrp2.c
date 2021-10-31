/*
[결과]
---------------
$ ./pgrp2
parent : pid = 1000 gid = 1000
child : pid = 1001 gid = 1001
------------
*/
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>

int main()
{
    int pid, gid;
    printf("parent : pid = %d gid = %d \n", getpid(), getpgrp());
    pid = fork();

    if(pid == 0) {
	setpgid(0, 0);
// 현재 프로세스 ID가 새로운 프로세스 그룹 리더가 됨
	printf("child : pid = %d gid = %d \n", getpid(), getpgrp());
    }
}
