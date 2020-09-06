FROM ubuntu:18.04

MAINTAINER Wagner Franchin

# Dependencies for glvnd and X11.
RUN apt-get update \
  && apt-get install -y -qq --no-install-recommends \
    libglvnd0 \
    libgl1 \
    libglx0 \
    libegl1 \
    libxext6 \
    libx11-6 \
  && rm -rf /var/lib/apt/lists/*

# Env vars for the nvidia-container-runtime.
ENV NVIDIA_VISIBLE_DEVICES all
ENV NVIDIA_DRIVER_CAPABILITIES graphics,utility,compute

RUN apt-get update && apt-get install -y \
  openjdk-8-jdk \
  x11-xserver-utils \
  xvfb \
  libgl1-mesa-dev \
  liblwjgl-java \
  mesa-utils \
  && rm -rf /var/lib/apt/lists/*

COPY ./docker/res/ ./res

ADD ./docker/Fat_UFO_Invasion.jar /Fat_UFO_Invasion.jar

ENTRYPOINT java -jar /Fat_UFO_Invasion.jar
