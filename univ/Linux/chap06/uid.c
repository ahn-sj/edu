#include <stdio.h>
#include <pwd.h>
#include <grp.h>

/* print user id */
int main()
{
    int pid;
    printf("my real user ID : %d(%s) \n", getuid(), getpwuid(getuid())->pw_name);
    printf("my effe user ID : %d(%s) \n", geteuid(), getpwuid(geteuid())->pw_name);
    printf("my real group ID : %d(%s) \n", getgid(), getgrgid(getgid())->gr_name);
    printf("my effe group ID : %d(%s) \n", getegid(), getgrgid(getegid())->gr_name);
}
