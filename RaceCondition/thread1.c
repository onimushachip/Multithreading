/* A program to demonstrate thread declaration, creation, and
   wait.  */
#include <stdio.h>
#include <stdlib.h>

/* Include the pthread headers so we can compile against the
   library. */
#include <pthread.h>

/* This is the function that will execute as the thread.  Every
   function that runs as a thread returns a void *, and takes a single
   argument of type void */
void *thread_function (void *arg) 
{
  int i;
  for (i = 1; i <= 20; i++) {
    printf ("%2d: Child thread says hi!\n", i);
  }

  /* A thread should always return a single argument: a void *.  If
     you don't want to return a value, return NULL.  If you don't
     return a void * value, a garbage value will be returned. */
  printf ("Child thread is exiting.\n");
  return (NULL);
}		


/* This is where the computation will start when the program is
   executed. */
int main (int argc, char *argv[])
{
  /* Declare a variable to hold the thread and allocate memory for
     it. */
  pthread_t *my_thread = (pthread_t *) malloc (sizeof (pthread_t));

  printf ("Main thread about to create child ... \n");

  /* Create the thread.  Notice that the first argument is pthread_t *,
     and the third argument the name of the function that will execute
     as the thread.  The thread starts to execute as soon as it is
     created. */
  if (pthread_create (my_thread, // pointer to pthread_t
		      NULL,      // pointer to pthread_attrtype
		      thread_function,  // pointer to thread body
		      NULL) ) { // pointer to args to pass to thread
    /* If pthread_create returns a non-zero value, something went
       horribly wrong.  Always check for this condition. */
    fprintf (stderr, "Error creating thread.\n");
    free (my_thread);
    exit(-1);
  }

  /* At this point, the new thread is running. */
  printf ("Main thread has created child thread.\n");

  /* Wait for any threads that this thread created to terminate.  The
     parent of a thread is special; it must always wait for its
     children to terminate.  If a parent exits (or dies), the pthread
     library automatically kills its children. 

     Try commenting this in:
     exit(-1);

     Note that the first parameter of pthread_join() is a pthread_t,
     *not* a pthread_t *.  This parameter is the TID of the thread we
     want to wait on. */
  printf ("Main thread is now going to wait for child.\n");
  if (pthread_join (*my_thread, NULL) ) {
    fprintf (stderr, "Error joining thread.\n");
    free (my_thread);
    exit(-1);
  }

  printf ("Main thread has successfully joined with child.\n");

  /* Clean up and exit. */
  free (my_thread);
  exit (0);

}
