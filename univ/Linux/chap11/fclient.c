/* file client program*/
/* client */

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
    int clientFd, port, result;
    char *host, inmsg[MAXLINE], outmsg[MAXLINE];

    struct sockaddr_in serverAddr;
    struct hostent *hp;

    if (argc != 3) {
	fprintf(stderr, "사용법 : %s <host> <port>\n", argv[0]);
	exit(0);
    }
    host = argv[1];
    port = atoi(argv[2]);

    clientFd = socket(AF_INET, SOCK_STREAM, DEFAULT_PROTOCOL);

    if ((hp = gethostbyname(host)) == NULL)
	perror("gethostbyname error");

    bzero((char *) &serverAddr, sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    bcopy((char *)hp->h_addr_list[0], (char *)&serverAddr.sin_addr.s_addr, hp->h_length);
    serverAddr.sin_port = htons(port);

    do {
	result = connect(clientFd, &serverAddr,  sizeof(serverAddr));
	if (result == -1) sleep(1);
    } while (result == -1);

    printf("파일 이름 입력:");
    scanf("%s", inmsg);
    write(clientFd,inmsg,strlen(inmsg)+1);

    while (readLine(clientFd,outmsg))
	printf("%s", outmsg);

    close(clientFd);
    exit(0);
}
