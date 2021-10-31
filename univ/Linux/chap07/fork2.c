#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

/* fork() parent / child print different message */
int main()
{
    int pid;
    pid = fork();

    if(pid == 0) { // child process
	printf("[Child] : pid = %d \n", getpid());
    }

    else { // parent process
	printf("[Parent] : pid = %d \n", getpid());
    }
}
