#include <sys/types.h> /* system.c */
#include <sys/wait.h>
#include <errno.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int system(const char *cmdstring)
{
    pid_t pid;
    int status;

    if(cmdstring == NULL) // if null
	return(1);
    if((pid = fork()) < 0) {
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
