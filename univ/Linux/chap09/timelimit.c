#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>

int pid;
void alarmHandler();
/* if Enter command line arg 
    then give command exec time limit */

int main(int argc, char *argv[])
{
    int child, status, limit;
    signal(SIGALRM, alarmHandler);
    sscanf(argv[1], "%d", &limit);
    alarm(limit);
    pid = fork();

    if(pid == 0) { // child process
	execvp(argv[2], &argv[2]);
	fprintf(stderr, "%s : exec error \n", argv[1]);
    }
    else { // parent process
	child = wait(&status);
	printf("[%d] child process %d end \n", getpid(), pid);
    }
}

void alarmHandler()
{
    printf("[alarm] child process %d time over \n", pid);
    kill(pid, SIGINT);
}
