/* 
fork() retrun value 
[output]
--------------
$ ./fork1
[]
--------------
*/
#include <stdio.h>
#include <unistd.h>

int main()
{
    int pid;
    printf("[%d] process start \n", getpid());
    pid = fork();

    printf("[%d] process : return val %d \n", getpid(), pid);
}

