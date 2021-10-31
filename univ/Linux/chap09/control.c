#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

/* Use signal to control child process*/
int main()
{
    int pid1, pid2;

    pid1 = fork(); // child process #1
    if(pid1 == 0) {
	while(1){
	    sleep(1);
	    printf("Run process [1]\n");
	}
    }

    pid2 = fork(); // child process #2
    if(pid2 == 0){
	while(1) {
	    sleep(1);
	    printf("Run process [2]\n");
	}
    }
    sleep(2);
    kill(pid1, SIGSTOP);
    sleep(2);
    kill(pid1, SIGCONT);

    sleep(2);
    kill(pid2, SIGSTOP);
    sleep(2);
    kill(pid2, SIGCONT);

    sleep(2);
    kill(pid1, SIGKILL);
    kill(pid2, SIGKILL);
}
