/* covert lowercase to uppercase program */
/* server */

#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>

void toUpper(char* in, char* out);
int readLine(int fd, char* str);

#define DEFAULT_PROTOCOL 0
#define MAXLINE 100

int main()
{
    int listenfd, connfd, clientlen;
    char inmsg[MAXLINE], outmsg[MAXLINE];
    struct sockaddr_un serverUNIXaddr, clientUNIXaddr;

    signal(SIGCHLD, SIG_IGN);
    clientlen = sizeof(clientUNIXaddr);

    listenfd = socket(AF_UNIX, SOCK_STREAM, DEFAULT_PROTOCOL);
    serverUNIXaddr.sun_family = AF_UNIX;

    strcpy(serverUNIXaddr.sun_path, "convert");
    unlink("convert");

    bind(listenfd, (struct sockaddr *)&serverUNIXaddr, sizeof(serverUNIXaddr));
    listen(listenfd, 5);

    while(1) { /* accept socket connection request*/
	connfd = accept(listenfd, (struct sockaddr *)&clientUNIXaddr, &clientlen);
	if(fork() == 0) {
	/* read 1 line from socket and covert uppercase then send client*/
	    readLine(connfd, inmsg);
	    toUpper(inmsg, outmsg);
	    write(connfd, outmsg, strlen(outmsg)+1);
	    close(connfd);
	    exit(0);
	} else
	    close(connfd);
    }
}

/* to uppercase func*/ 
void toUpper(char* in, char* out)
{
    int i;
    for(i = 0; i < strlen(in); i++) {
	if(islower(in[i]))
	    out[i] = toupper(in[i]);
	else
	    out[i] = in[i];
    }
    out[i] = '\0';
}

/* read 1 line */	
int readLine(int fd, char* str)
{
    int n;
    do {
	n = read(fd, str, 1);
    } while(n > 0 && *str++ != '\0');
    return (n>0);
}

