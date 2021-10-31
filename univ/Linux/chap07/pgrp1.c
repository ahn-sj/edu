#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>

int main()
{
    int pid, gid;
    printf("parent : pid = %d gid = %d \n", getpid(), getpgrp());
    pid = fork();

    if(pid == 0) { /* child process */
	printf("child : pid = %d gid = %d \n", getpid(), getpgrp());
    }
}
