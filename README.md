# Space-Invaders-LWJGL
------

**UFO Invasion** _(Free Web Game)_ is a better version of the traditional **"Space Invaders"**. UFO Invasion is a two-dimensional 
shooter game in which the player controls an space aircraft by moving it horizontally across the bottom of the screen and 
firing at descending aliens. The aim is to defeat all aliens that move back and forth across the screen as they advance 
towards the bottom of the screen (*the Earth planet!*). The player fights against an alien by shooting it with rockets launched by the space aircraft. 
Defeating the aliens brings another wave that is more difficult, in a loop which can continue indefinitely!

**_Fly through space, defend your planet, destroy aliens.. Have fun!_**

<img align="center" alt="GIF" src="https://github.com/wagnerjfr/space-invaders-lwjgl/blob/master/res/ufo.gif?raw=true" width="500" height="320" />

>_NOTE: the source code available is for the Java + LWJGL version._

## Links:

[**Oficial WEB Game - UFO Invasion**](https://sites.google.com/view/ufoinvasiongame)

[**History of the game**](https://medium.com/@wagnerjfr/turning-setbacks-into-success-24b76ca9e357)

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

### 1.1 Pulling the image from DockerHub and launching the container
```
Coming soon
```

### 1.2 Creating a local Docker images
:warning: Not working

If the Fat jar (*Section 2 below*) is running fine in your environment, it's possbile to build a local Docker image by running:

```
$ docker build -t space-invaders-lwjgl -f docker/Dockerfile .
```

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
