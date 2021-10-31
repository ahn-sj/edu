#include <stdio.h>
#include <stdlib.h>
/* print all environment variable */
int main(int argc, char *argv[])
{
    char **ptr;
    extern char **environ;

    for(ptr = environ; *ptr != 0; ptr++) /* print all environment variable */
	printf("%s \n", *ptr);

    exit(0);
}
