#include <stdio.h>
#include <stdlib.h>

struct student {
    int id;
    char name[20];
};
/* 
input values student count then
print student infor in reverse order
*/

int main()
{
    struct student *ptr; // pointer point out dynamic assigned block 
    int n, i;
    printf("how many student would you like to enter?");
    scanf("%d", &n);
    if(n <= 0) { // if n <= 0
	fprintf(stderr, "error: student number error!");
	fprintf(stderr, "program exit!!");
	exit(1);
    }
    // if n > 0
    ptr = (struct student *)malloc(n * sizeof(struct student)); 
    if(ptr == NULL) {
	perror("malloc");
	exit(2);
    }

    printf("Enter %d stu_num and stu_name\n", n);
    for(i = 0; i < n; i++){
	scanf("%d %s\n", &ptr[i].id, ptr[i].name);
    }
    
    printf("\n * stu_infor(in reverse order) * \n");
    for(i = n-1; i >= 0; i--){
	printf("%d %s\n", ptr[i].id, ptr[i].name);
    }
    printf("\n");
    exit(0);    
}

