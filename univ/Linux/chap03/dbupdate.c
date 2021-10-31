/*
IMPORTANT POINT --> lseek(fd, (long) -sizeof(record), SEEK_CUR);

[output]
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include "student.h"

int main(int argc, char* argv[])
{
    int fd, id;
    char c;
    struct student record;

    if(argc < 2){
	fprintf(stderr, "manual : %s file \n", argv[0]);
	exit(1);
    }
    if((fd = open(argv[1], O_RDWR)) == -1) {
	perror(argv[1]);
	exit(2);
    }
    do {
	printf("\nEnter the student ID to change > ");
	if(scanf("%d", &id) == 1) {
	    lseek(fd, (long) (id-START_ID) * sizeof(record), SEEK_SET);
	    if((read(fd, (char *) &record, sizeof(record)) > 0) && (record.id !=0)) {
		printf("stud_num : %d\t name : %s\t score : %d\t \n", record.id, record.name, record.score);
		printf("new score : ");
		scanf("%d", &record.score);
		lseek(fd, (long) -sizeof(record), SEEK_CUR);
		write(fd, (char *) &record, sizeof(record));
	    } else
	        printf("%d not existed record \n", id);
	} else 
	    printf("Entered ERROR \n");

        printf("retry? (y/n) > ");	/* in do while */
	scanf(" %c", &c);		/* in do while */
    } while(c == 'y');
    close(fd);
    exit(0);
}
