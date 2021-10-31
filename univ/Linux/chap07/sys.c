#include <sys/types.h> /* system.c */
#include <sys/wait.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int system(const char *cmdstring)
{
    pid_t pid;
    int status;

    if(cmdstring == NULL) // if null
	return(1);
    if((pid == fork()) < 0) {
	status = -1; // process create fail
    }
    else if(pid == 0){ // child process
	execl("/bin/sh", "sh", "-c", cmdstring, (char *)0);
	_exit(127); // execl fail
    }
    else {
	while(waitpid(pid, &status, 0) < 0)
	    if(errno != EINTR) {
		status = -1; // waitpid() EINTR other error
		break;
	    }
    }
    return(status);
}

int main()
{
    int status;
    if((status = system("date")) < 0)
	perror("system() error");
    printf("end code %d \n", WEXITSTATUS(status));

    if((status = system("hello")) < 0)
	perror("system() error");
    printf("end code %d \n", WEXITSTATUS(status));

    if((status = system("who; exit 44")) < 0)
	perror("system() error");
    printf("end code %d \n", WEXITSTATUS(status));
