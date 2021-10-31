#include <stdio.h>
#include <signal.h>
#include <unistd.h>

struct sigaction newact;
struct sigaction oldact;

void sigint_handler(int signo);

int main(void)
{
    newact.sa_handler = sigint_handler; // signal processor designation
    sigfillset(&newact.sa_mask); // all signal block mask

    // SIGINT processor action new designation, oldact old process action restore
    sigaction(SIGINT, &newact, &oldact);

    while(1)
    {
	printf("press ctrl-C\n");
	sleep(1);
    }
}

/* SIGINT processor function */
void sigint_handler(int signo)
{
    printf("cnt : %d signal process\n", signo);
    printf("one more press then EXIT\n");
    sigaction(SIGINT, &oldact, NULL); // change old process action
}
