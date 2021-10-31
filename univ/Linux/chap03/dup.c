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
    int fd1_dup, fd2;

    if((fd1_dup = creat("myfile_dup", 0600)) == -1)
	perror("myfile_dup");

    write(fd1_dup, "Hello! Linux \n", 12);
    fd2 = dup(fd1_dup);
    write(fd2, "Bye! Linux \n", 10);
    exit(0);
}
