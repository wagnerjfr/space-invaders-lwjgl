# Space-Invaders-LWJGL
------

**UFO Invasion** _(Free Web Game)_ is a better version of the traditional **"Space Invaders"**. UFO Invasion is a two-dimensional 
shooter game in which the player controls an space aircraft by moving it horizontally across the bottom of the screen and 
firing at descending aliens. The aim is to defeat all aliens that move back and forth across the screen as they advance 
towards the bottom of the screen (*the Earth planet!*). The player fights against an alien by shooting it with rockets launched by the space aircraft. 
Defeating the aliens brings another wave that is more difficult, in a loop which can continue indefinitely!

**_Fly through space, defend your planet, destroy aliens.. Have fun!_**

>_NOTE: the source code available is for the Java + LWJGL version._

## Links:

[**Oficial WEB Game - UFO Invasion**](https://sites.google.com/site/ufoinvasiongame/)

[**Available on Chrome Web Store**](https://chrome.google.com/webstore/detail/ufo-invasion/bclabmhmbdonaejglmfohahicllifhgf)

[**History of the game**](https://www.linkedin.com/pulse/taking-advantage-missed-opportunity-publish-my-first-online-franchin/?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_view_base_post_details%3BpgCweIyaQwm3Qe88n3hZsA%3D%3D)

----
## Videos:
### YouTube Video Oficial - Java + PlayN
<a href="http://www.youtube.com/watch?feature=player_embedded&v=QUu4ARi1Sn8
" target="_blank"><img src="http://img.youtube.com/vi/QUu4ARi1Sn8/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>

### YouTube Video - Java + LWJGL
<a href="http://www.youtube.com/watch?feature=player_embedded&v=GUygut8MPDo
" target="_blank"><img src="http://img.youtube.com/vi/GUygut8MPDo/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>

----
## 1. Running with Docker

Coming soon..

----
## 2. Running as Fat Java Jar

### Linux
There is a java jar available to be executed.

Run the command below:
```
$ java -jar docker/Fat_UFO_Invasion.jar
```

### Mac and Windows

For Mac and Windows, a new Fat jar need to be built.

Follow the instructions in [`docker/README.md`]() file.

----
## 3. Installing in Eclipse:

1) Clone the project: 
```
git clone https://github.com/wagnerjfr/space-invaders-lwjgl.git
```
2) Go into Eclipse then click **"File" ⭢ "New" ⭢ "Java Project"**

3) Uncheck "use default location" and browse the project folder just cloned

4) Follow the steps till the project is installed and available in Package Explorer on the left side of your screen

5) Expand the project so it's possible to see src, lib and res folders.

6) Right click on the **"JRE System Library"** of your project, and click **"Build Path" ⭢ "Configure Build Path"**.
![alt text](https://github.com/wagnerjfr/space-invaders-lwjgl/blob/master/res/readme/setup0.png)

7) Include the LWJGL native libraries to your project in the Build Path Configured by clicking the **"Native library location"** which can be seen in the JRE System Library dropdown menu.
![alt text](https://github.com/wagnerjfr/space-invaders-lwjgl/blob/master/res/readme/setup1.png)

8) Click on **"Edit..."**, which will be the only button is enabled in that general area.

9) A file explorer will pop up. Select **"Workspace..."**

10) The location is in the project folder inside the folder **lib/natives**. Select the folder according to your Operational System.
![alt text](https://github.com/wagnerjfr/space-invaders-lwjgl/blob/master/res/readme/setup2.png)
![alt text](https://github.com/wagnerjfr/space-invaders-lwjgl/blob/master/res/readme/setup3.png)

11) Click in **"Apply and Close"** Button

12) Right click in the project folder the **"Run As" ⭢ "Java Application"**

13) A list of class files will appear, choose **"InvadersMain - main"**, and click in **"OK"**
![alt text](https://github.com/wagnerjfr/space-invaders-lwjgl/blob/master/res/readme/setup4.png)

14) Follow the instructions in the screen and **PLAY**
![alt text](https://github.com/wagnerjfr/space-invaders-lwjgl/blob/master/res/readme/setup5.png)
