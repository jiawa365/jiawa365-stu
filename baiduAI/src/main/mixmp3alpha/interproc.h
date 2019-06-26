/*
 * interproc.h - Interface to monitor state of mixmp3
 *
 * 06.03.2001
 *
 */

#ifndef _INTERPROC_H
#define	_INTERPROC_H

// define status of mixmp3
#define MMP3_RUNNING		1 /* normal running */
#define MMP3_TERMINATING	2 /* When the rest of the buffer,
                                     after pressing Esc, appearance
                                     of a file - flag or the expiration
                                     of a limit of time is encoded.
                                     Can occur not once. */
#define MMP3_TERMINATED		3 /* mixmp3 terminated, occurs once. */

// name of file mapping
#define MMP3_MFILE		"MixMp3MFile"
// name of synchronisation event
#define MMP3_EVENT		"MixMp3Event"

// structure, include information about mixmp3 work (statistics, etc.)
struct MixMp3Info
{
   int state;         // one of MMP3_RUNNING, MMP3_TERMINATING or MMP3_TERMINATED
                      // статус программы, может быть MMP3_RUNNING,
                      // MMP3_TERMINATING или MMP3_TERMINATED
   int write_time;    // Duration of the encoded signal, in sec
                      // длительность записанного сигнала, в сек
   int write_kb;      // size of the data packed and written to the disk, in kbytes
                      // размер упакованных и записанных на диск данных, в кбайтах
   double level_dB;   // maximum level of a signal for last 0.5 sec, in dB
                      // максимальный уровень сигнала за последние 0.5 сек, в дБ
   double buf_state;  // percent of free space in the buffer
                      // процент свободного места в буфере
   double cpu_usage;  // % of CPU usage by mixmp3 (only for NT,2000)
                      // % использования процессорного времени программой (толко для NT,2000)
   int up_time;       // operating time of the program in a current session, in sec
                      // время работы программы в текущем сеансе, сек
   double ldc_dB;     // dc level in left channel, in dB
                      // постоянная составляющая левого канала, дБ
   double rdc_dB;     // dc level in right channel, in dB
                      // постоянная составляющая правого канала, дБ
   char filename[260];// current filename where the mp3-stream is written
                      // текущее имя файла, куда пишется mp3-поток
};

#endif // INTERPROC_H
