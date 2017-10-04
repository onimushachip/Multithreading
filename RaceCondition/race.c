/* Demonstrate race conditions in assignment when using pthreads. */
#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>

/* Multiple threads will read and write x. */
int x;

/* The number of additions (or subtractions) each thread will do. */
const int N_ITER = 10000;

/* The number of addition and subtraction threads. */
const int N_ADDERS_SUBTRACTORS = 10;

void *adder (void * args)
{
  int i;
  for (i = 1; i <= N_ITER; i++) {
    /* It's a trap!

       This looks atomic, but is really 
         mov x, %eax
         add $1, %eax 
	 mov %eax, x
    
      The race occurs while X is in the register. */
    x++;
  }
  
  pthread_exit(NULL);
}

void *subtracter (void *args) 
{
  int i;

  for (i = 1; i <= N_ITER; i++) {
    x--;
  }

  pthread_exit(NULL);
}


int main (int argc, char *argv[])
{
  /* Arrays for thread tids. */
  pthread_t adder_thread[N_ADDERS_SUBTRACTORS];
  pthread_t subtractor_thread[N_ADDERS_SUBTRACTORS];

  /* Initialize x.  There are not other threads yet,
     so this is safe. */
  x = 0;
  
  /* Create adding and subtracting threads. */
  int i;
  for (i = 0; i < N_ADDERS_SUBTRACTORS; i++) {

    if (pthread_create (&adder_thread[i],
			NULL,
			adder,
			NULL) ) {
      fprintf (stderr, "Error creating addition thread %d.\n", i);
      exit (-1);
    }

    if (pthread_create (&subtractor_thread[i],
			NULL,
			subtracter,
			NULL) ) {
      fprintf (stderr, "Error creating subtraction thread %d.\n", i);
      exit (-1);
    }
  }
  
  /*  Wait for threads to terminate. */
  for (i = 0; i < N_ADDERS_SUBTRACTORS; i++) {

    if (pthread_join (adder_thread[i], NULL)) {
      fprintf (stderr, "Error joining with addition thread %d.\n", i);
      exit (-1);
    }

    if (pthread_join (subtractor_thread[i], NULL)) {
      fprintf (stderr, "Error joining with subtraction thread %d.\n", i);
      exit (-1);
    }

  }
  
  /* Show the race condition. */
  printf ("X should equal 0, actual value is %d.\n", x);
  
  exit (0);
}


	
