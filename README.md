# Kotlin-js-gradle-parcel

Simple proof of concept to build a kotlin js web application with a minimum of npm/yarn stuff involved.

Currently, you need parcel installed. You first build the javascript using the gradle wrapper and then package it up using parcel.

# How it works

Kotlin-js produces nice javascript in a build directory. We use gradle to do all that stuff. After it does that, all that remains to be done is packaging it up. We use parcel for that.

Parcel is nice in two ways:

- All it needs is an entry point, it figures out the rest from there.
- It manages all the yarn stuff by itself, we can git ignore all those files; it's transient stuff. So, it generates a package.json, goes off and does all the yarn voodoo and does its thing.

So all we have is a kotlin src tree, a gradle file that builds it and an index file that references the javascript file that gradle produces.

We use parcel to package that up. If you want it can also do sass processing and some other stuff. It even knows how to compile .kt files.

# Running

```
./gradlew build
parcel index.html
```

# Production build

```
./gradlew build
parcel build index.html
```