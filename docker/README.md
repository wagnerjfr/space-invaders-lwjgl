
# Creating the FAT jar with all the dependecies

We are going to use **JarSplice** to build the jar.

Inside the "docker" folder, run:
```
java -jar jarsplice-0.40.jar
```

- 1. ADD JAR

Add all jars from the "UFO_Invasion_jar" folder

![alt text](https://github.com/wagnerjfr/space-invaders-lwjgl/blob/master/docker/images/1_ADD_JAR.png)

- 2. ADD NATIVES

Add the libs for the folder "natives/<your OS>"

![alt text](https://github.com/wagnerjfr/space-invaders-lwjgl/blob/master/docker/images/2_ADD_NATIVES.png)

- 3. MAIN CLASS

Add "main.InvadersMain"

![alt text](https://github.com/wagnerjfr/space-invaders-lwjgl/blob/master/docker/images/3_MAIN_CLASS.png)

- 4. CREATE FAT JAR

Click on "Create Fat Jar".



