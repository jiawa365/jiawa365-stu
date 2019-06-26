#include <windows.h>
#include <conio.h>
#include <stdio.h>
#include "interproc.h"

void main()
{
   int Stop = 0; // flag for exit
   MixMp3Info* info;
   HANDLE hMap = NULL;
   while(!Stop)
   {
      printf("Waiting for mixmp3...");
      while(1)
      {  // attempt to open file mapping for monitoring mixmp3
         hMap = OpenFileMapping(FILE_MAP_READ, FALSE, MMP3_MFILE);
         if(hMap == NULL) Sleep(500); // not open, sleeping 0.5 sec and repeat
         else break;                  // ok, go...
         // monitor keyboard
         if(kbhit()) if(getch() == 27)
         {
            printf("\nUser break\n");
            Sleep(1000);
            return;
         }
      }
      // map information structure
      info = (MixMp3Info*)MapViewOfFile(hMap, FILE_MAP_READ, 0, 0, sizeof(MixMp3Info));
      if(info == NULL)
      { // not mapped, unexpected error :(
         printf("MapViewOfFile returns NULL :(\n");
         CloseHandle(hMap);
         Sleep(1000);
         return;
      }
      // get event handle
      HANDLE hEvt = OpenEvent(SYNCHRONIZE, FALSE, MMP3_EVENT);
      printf("\n"); 
      for( ; ; ) // <-- begin monitoring loop
      {
         // monitor keyboard
         if(kbhit()) if(getch() == 27) { Stop=1; break; }
         // waiting for data ready
         if(WaitForSingleObject(hEvt, 1000) == WAIT_TIMEOUT) continue;
         // print information
         printf("wt: %d, ut: %.d, buf: %.1f%%, cpu: %.3f%%, sz: %d kb, lv: %.1fdB, dc: (%.1f,%.1f)  \r",
                info->write_time,
                info->up_time,
                info->buf_state,
                info->cpu_usage,
                info->write_kb,
                info->level_dB,
                info->ldc_dB,
                info->rdc_dB);
         // check for mixmp3 termination
         if(info->state == MMP3_TERMINATED) { printf("\nterminated..."); break; }
      } // end moniroting loop -->
      // cleanup
      UnmapViewOfFile(info);
      CloseHandle(hEvt);
      CloseHandle(hMap);
      printf("\n");
   } // while(!Stop) -->
   // ales
   Sleep(1000);
}
