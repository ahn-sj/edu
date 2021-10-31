/* file server program*/
/* server */

#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

#define DEFAULT_PROTOCOL 0
#define MAXLINE 100

int main(int argc, char* argv[])
{
    int listenfd, connfd, port, clientlen;
    FILE *fp;
    char inmsg[MAXLINE], outmsg[MAXLINE];
    char *haddrp;

    struct sockaddr_in serveraddr, clientaddr;
    struct hostent *hp;

    signal(SIGCHLD, SIG_IGN);

    if(argc != 2) {
	fprintf(stderr, "manual : %s <port> \n", argv[0]);
	exit(0);
    }

    port = atoi(argv[1]);

    listenfd = socket(AF_INET, SOCK_STREAM, DEFAULT_PROTOCOL);

    bzero((char*) &serveraddr, sizeof(serveraddr));
    serveraddr.sin_family = AF_INET;
    serveraddr.sin_addr.s_addr = htonl(INADDR_ANY);
    serveraddr.sin_port = htons((unsigned short)port);

    bind(listenfd, &serveraddr, sizeof(serveraddr));
    listen(listen, 5);

    while (1) { /*connected signal infi_loop*/
	clientlen = sizeof(clientaddr);
	connfd = accept(listenfd, &clientaddr, &clientlen);

    /* client domain name and ip address decision */
	hp = gethostbyaddr((char *)&clientaddr.sin_addr.s_addr, sizeof(clientaddr.sin_addr.s_addr), AF_INET);
	haddrp = inet_ntoa(clientaddr.sin_addr);
	printf("server : %s(%s) %d connected\n", hp->h_name, haddrp, clientaddr.sin_port);

	if(fork() == 0) {
	    readLine(connfd, inmsg); /*read file name in socket*/
	    fp = fopen(inmsg, "r");
	    if(fp == NULL) {
		write(connfd, "not existed file", 10);
	    } else {
		while(fgets(outmsg, MAXLINE, fp) != NULL)
		    write(connfd, outmsg, strlen(outmsg)+1);
	    }
	    close(connfd);
	    exit(0);
	} else
	    close(connfd);
    } // while
} // main
