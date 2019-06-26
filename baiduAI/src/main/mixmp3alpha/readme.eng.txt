

                                    MixMP3
                        Version 1.0.8 alpha, 25.01.2002
                             The brief description.


                                   Contents.

1. What is MixMP3?
2. Features.
3. System requirements.
4. Brief description of commandline switches.
5. The Author.
6. Links.


1. What is MixMP3?

   MixMP3  is a coder of the sound information in a popular format mp3
   from  a  sound  card.  If to  be more  exact, it not the high-grade
   coder,  and  only  a superstructure above Lame engine or Fraunhofer
   acm  codec.  The program allows to write down the sound information
   directly  from  a  sound  payment on a hard disk in a mp3-format in
   real  time  if  opportunities of the equipment certainly allow. The
   program is executed as the console application.

2. Features.

   - Recording the sound information in the mp3-format with bitrates
     8, 16, 20, 24, 32, 40, 48, 56, 64, 80, 96, 112, 128, 144, 160,
     192, 224, 256 or 320 kbps; 16 bits; 8, 11, 12, 16, 22, 24, 32,
     44 or 48 kHz, in stereo mode, joint-stereo or mono mode.
     (see keys -b, -d, -m).
   - Automatic cutting pauses on the given level (-s).
   - Automatic  it  is cut  mp3 a stream on files  four various ways,
     including on pauses (-split1..4).
   - Possibility of choice used encoder - lame or fhg layer-III acm
     codec (should be installed in the system) (-fhg).
   - Operation in spy  mode when the  window of the program is
     invisible (-hide).
   - Recording with sampling rate 64 or 96 kHz from a sound card
     (it is interesting, if somebody have one?:). Thus forms mp3 the
     file(s) with sampling rate 48 kHz :) Only for lame.
   - Choice of quality of coding - high, low, normal. Only for lame.
     (-ql, -qh).
   - Termination after a  given period of time.  Time is set in
     minutes (there can be a fractions of minutes) (-t).
   - Installation of the increased priority for encoder. Actually by
     operation in Win9X (-a).
   - The exit on appearance of the given file-flag (-x).
   - Automatic deleting of a constant component of a signal
     (-dc for disable).  
   - Usage of lame mode - ABR (average bitrate) (-abr).
   - Usage of lame mode - VBR (variable bitrate) (-vbr).
   - Possibility of a program control from other applications
     (see interproc.h, mmp3_monitor.cpp).
   - High "competitiveness" for system resources in multitasking
     environment.
   - Playback of mp3-stream (-play).
   - Using lowpass and highpass filters (-fl, -fh).


3. System requirements.

   The operating system: Windows NT 4, a Windows 2000 or
                         a Windows 9X/ME;
   The processor: iP-200 (mono mode or poor quality) or iCeleron 333
                  (stereo with excellence); for an effective
                  utilization fhg encoder need the processor with
                  clock rate not less 600 MHz;
   The RAM: 32 Mb;
   The Sound: a 16-bit sound card.


4. Brief description of commandline switches.

   -b - bitrate, maybe 8, 16, 24, 32, 40, 48, 56, 64, 80, 96, 112, 128,
        144, 160, 192, 224, 256, 320 kbit/s, default - 128 kbps
        in vbr mode specify minimum allowed bitrate, default - 64 kbps
   -d - working samplerate, maybe 8, 11, 12, 16, 22, 24, 32,
        44, 48, 64 or 96 kHz, default 44 kHz;
        if used samplerate exceed 48 kHz, then downsampled it to 48 kHz
   -t - limitation of coding on time, in minutes, by default - no limit
   -f - size of working buffer, in kbytes, starting with 512 kb,
        default - 5%% of physical RAM (for mono - 2.5%%)
   -s - cut of pauses on the indicated level, in dB, default - -50 dB
   -a - to place the increased priority of execution of an encoder,
        by default on Windows 9x, -a- disable this mode
   -x - sets a filename of a flag on which appearance the program
        completes operation, the file - flag thus is deleted
   -dc - prohibits deleting a constant component of a signal
   -ms - to use stereo mode (by default for bitrates >= 192)
   -mj - to use j-stereo mode (by default for bitrates < 192)
   -mm - to use mono mode
   -ql - to encode with low quality
   -qh - to encode with high quality (normal - by default)
   -qv - to encode with maximum quality
   -dr - don't allow samplerate convertion with encoder
   -fl - use lowpass filter
   -fh - use highpass filter
   -abr - enable average bitrate mode (ABR)
   -fhg - force using fhg mp3 acm codec (if installed) - very beta feature
   -vbr n - to use variable bitrate (VBR), when n - vbr quality (0..9)
   -hide - don't show program window, use stopmp3.flg file to exit
   -play - auto decode and play mp3-stream
   -devlist - display the list of record/playback devices
   -pdev - use device n for playback
   -rdev - use device n for recording
   -split1 - to divide into files duration 1 hour, daily cycle
   -split2 - to divide into files duration 1 hour, weekly cycle
   -split3 - to divide into files duration 1 hour, monthly cycle
   -split4 - to divide into files on pauses, see as a option -s


5. The Author.

   The author of MixMP3 program - Dmitry Lesnikov. To contact me use
   addresses:

   2:5025/3.35@fidonet, dlesnikov@mail.ru
   Homepage: http://ldb.tpv.ru

6. Links.

http://mitiok.cjb.net/
http://www.mp3dev.org
