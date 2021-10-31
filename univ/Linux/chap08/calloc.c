#include <stdio.h>
#include <stdlib.h>

int main()
{
    int *p, *q;
    p = malloc(10 * sizeof(int)); // 40byte
    if(p == NULL)
	perror("melloc");
    q = calloc(10, sizeof(int));
    if(q == NULL)
	perror("calloc");
}
