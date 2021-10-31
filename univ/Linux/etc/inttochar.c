#include <stdio.h>

int main()
{
    char buff[256];
    int x = 10;
    sprintf(buff, "%d", x);

    printf("%s \n", buff);
}
