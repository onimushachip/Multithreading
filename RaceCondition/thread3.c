/* Races in pthreads */
#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>

void *start_routine (void *arg) 
{
  /* Each call to printf is atomic; the C-standard library guarantees
     that for almost every function it contains.  (The man page for a
     function will tell you whether or not a function is thread safe.)
     All system calls are also atomic; the kernel guarantees that.

     However, multiple calls to standard functions and syscalls are
     not guaranteed to be atomic.  Making such an assumption is a
     common error that will introduce race conditions into your
     code. */
  printf ("Thread ");

  int my_id = *(int *) arg;
  free (arg);

  printf (" %2d ", my_id);
  printf ("says");
  printf (" hi.");
  printf ("\n");
	
  return (NULL);
}


int main (int argc, char *argv[])
{
  int nthreads, i;

  printf ("How many threads to you want to create? ");
  scanf ("%d", &nthreads);

  /* Declare an array of pointers to pthread_ts.  C does not support
     dynamic arrays; we have to allocate the space by hand, although
     once we do that, we can treat it like an array.

     This is essentially the declaration 
         pthread_t *tid[nthreads]; 
     if C supported dynamically allocated arrays. */
  pthread_t **tid = (pthread_t **) malloc (sizeof(pthread_t *) * nthreads);

  /* Allocate memory for the pthread_ts. */
  for (i = 0; i < nthreads; i++) {
    tid[i] = (pthread_t *) malloc (sizeof(pthread_t));
  }

  /* Now create the threads. */
  int *t_num;
  for (i = 0; i < nthreads; i++) {
    t_num = malloc (sizeof (int));
    *t_num = i;
    if (pthread_create (tid[i], NULL, start_routine, (void *) t_num)) {
      fprintf (stderr, "Error creating thread %d.\n", i);
      exit(-1);
    }
  }

  printf ("About to join with children...\n");

  for (i = 0; i < nthreads; i++) {
    if (pthread_join(*tid[i], NULL)) {
      fprintf (stderr, "Error joining thread %d.\n", i);
      exit (-1);
    }
  }

  printf ("Children joined ...\n");

  /* Clean up. */
  for (i = 0; i < nthreads; i++) {
    free (tid[i]);
  }
  free (tid);

  exit (0);		
}
