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
                      // ����� �ணࠬ��, ����� ���� MMP3_RUNNING,
                      // MMP3_TERMINATING ��� MMP3_TERMINATED
   int write_time;    // Duration of the encoded signal, in sec
                      // ���⥫쭮��� ����ᠭ���� ᨣ����, � ᥪ
   int write_kb;      // size of the data packed and written to the disk, in kbytes
                      // ࠧ��� 㯠�������� � ����ᠭ��� �� ��� ������, � ������
   double level_dB;   // maximum level of a signal for last 0.5 sec, in dB
                      // ���ᨬ���� �஢��� ᨣ���� �� ��᫥���� 0.5 ᥪ, � ��
   double buf_state;  // percent of free space in the buffer
                      // ��業� ᢮������� ���� � ����
   double cpu_usage;  // % of CPU usage by mixmp3 (only for NT,2000)
                      // % �ᯮ�짮����� �����୮�� �६��� �ணࠬ��� (⮫�� ��� NT,2000)
   int up_time;       // operating time of the program in a current session, in sec
                      // �६� ࠡ��� �ணࠬ�� � ⥪�饬 ᥠ��, ᥪ
   double ldc_dB;     // dc level in left channel, in dB
                      // ����ﭭ�� ��⠢����� ������ ������, ��
   double rdc_dB;     // dc level in right channel, in dB
                      // ����ﭭ�� ��⠢����� �ࠢ��� ������, ��
   char filename[260];// current filename where the mp3-stream is written
                      // ⥪�饥 ��� 䠩��, �㤠 ������ mp3-��⮪
};

#endif // INTERPROC_H
