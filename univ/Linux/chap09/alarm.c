/* 5초뒤 Alarm clock 메세지 출력 후 프로세스 종료
[결과]
------------
$ ./alarm
무한루프
1초
1초
1초
1초
Alarm clock
$ 
------------
*/
#include <stdio.h>
#include <unistd.h>

int main()
{
    alarm(5);
    printf("무한루프\n");
    while(1) {
	sleep(1);
	printf("1초 \n");
    }
    printf("error : not exec");
// 실행 안됨
}
