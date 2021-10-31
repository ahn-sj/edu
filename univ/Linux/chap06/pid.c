#include <stdio.h>

/* print process number*/
int main()
{
    int pid;
    printf("my process num : [%d] \n", getpid());
    printf("my p process num : [%d] \n", getppid());
}
