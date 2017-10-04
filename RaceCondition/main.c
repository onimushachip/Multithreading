/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: Lam
 *
 * Created on September 21, 2016, 4:59 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * 
 */
int main() {
    
//    char* data_file;
//    if (argc != 2) {
//        printf ("Usage.%s <input_filename> \n", argv[0]);
//        exit (-1);
//    }
//    else {
//        data_file = argv[1];
//    }
    
    printf ("hello.\n");
    
    FILE * fPointer;
    fPointer = fopen("data_file", "r");
    char singleLine[1000];
    int arraySize, time, currentArray;
    int firstTime[1000];
    int secondTime[1000];
    int thirdTime[1000];
    
    
//    while (!feof(fPointer)) {
//        fgets(singleLine, 1000, fPointer);
////        puts(singleLine);
//    }
    
    arraySize = 0;
    currentArray = 0;
    while (!feof(fPointer)) {
        if (arraySize == 0) {
            fscanf(fPointer, "%d", &arraySize);
            currentArray++;
        }
        else {
            fscanf(fPointer, "%d", &time);
            if (currentArray == 1) {
                firstTime[arraySize - 1] = time;
            }
            if (currentArray == 2) {
                secondTime[arraySize - 1] = time;
            }
            if (currentArray == 3) {
                thirdTime[arraySize - 1] = time;
            }
            arraySize--;
        }
//        printf ("%d", arraySize);
//        printf ("\n");

    }
    
    fclose(fPointer);
    
    for (int i = 0; i < 5; i++) {
        printf ("%d", firstTime[i]);
        printf (" ");
    }
    printf ("\n");
    
    for (int i = 0; i < 8; i++) {
        printf ("%d", thirdTime[i]);
        printf (" ");
    }
    printf ("\n");    
    

    
    printf ("input complete.\n");
    
//    int firstNum = singleLine[1];
    
    

    return (EXIT_SUCCESS);
}

