/* 알람 시그널 처리
[결과]
-----------------
$ ./almhandler
무한루프
1초
1초
일어나!
-----------------
*/
#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>

void alarmHandler();

int main()
{
    signal(SIGALRM, alarmHandler);
// alarm()이 종료되면 alarmHandler() 함수 호출
    alarm(3); /* 알람 시간 설정 */

    printf("무한루프 \n");
    while(1) {
	sleep(1);
	printf("1초 \n");	
    }
    printf("실행되지 않음\n");
}

void alarmHandler()
{
    printf("일어나! \n");
    exit(0);
}
