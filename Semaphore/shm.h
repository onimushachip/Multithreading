
#include <semaphore.h>

char *segment_name = "/test_shared_memory_segment"; 

#define MAX_MESG_SIZE 1024

typedef struct shared_mem {
  char mesg [MAX_MESG_SIZE];
  unsigned int mesg_size;
  sem_t mySemaphore;
  sem_t mySemaphore2;
} SHARED_MEM;
