/*
output
------------
$ ./dup
$ cat myfile
Hello! Linux
------------
if file name existed myfile then print error messeage
*/
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

int main()
{
    int fd1, fd2_dup2;

    if((fd1 = creat("myfile_dup2", 0600)) == -1)
	perror("myfile_dup2");

    write(fd1, "Hello! Linux \n", 12);
    dup2(fd1, fd2_dup2);
    write(fd2_dup2, "Bye! Linux \n", 10);
    exit(0);
}
