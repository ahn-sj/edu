/*
[결과]
-----------
$ ./inthandler
[CTRL-C]
interrupt signal process 
signal number : 2
*/
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

void intHandler();

int main()
{
    signal(SIGINT, intHandler);
// SIGINT : [CTRL-C]에 대한 시그널
    while(1)
	pause();
    printf("실행되지 않음 \n");
}

void intHandler(int signo)
{
    printf("\ninterrupt signal process \n");
    printf("signal number : %d \n", signo);
    exit(0);
}
