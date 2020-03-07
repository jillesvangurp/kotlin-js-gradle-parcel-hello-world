# OBSOLETE

If you landed here hoping to get started with kotlin-js, move on. Everything changed since I created this project last year.

As of kotlin 1.3.70, use their updated (finally) documentation for their new and improved org.jetbrains.kotlin.js plugin. 

Short version, that finally does everything. Webpack gets used underneath but you don't have to deal with it. It gets npmp dependencies and if you use dukat even gets you kotlin bindings from typescript defs (sort of works for simple things). Most importantly it runs dead code elimination and produces a minified, production ready distribution that has reasonable size even if you use some frameworks, co-routines, and all that goodness.

Still some rough edges but planning to have a go at doing some simple UI stuff in Kotlin as this finally seems good enough.

# Kotlin-js-gradle-parcel

Simple proof of concept to build a kotlin js web application with a minimum of npm/yarn stuff involved.

Currently, you need parcel installed. You first build the javascript using the gradle wrapper and then package it up using parcel.

# How it works

Kotlin-js produces nice javascript in a build directory. We use gradle to do all that stuff. After it does that, all that remains to be done is packaging it up. We use parcel for that (via a the `com.moowork.node` gradle plugin).

Parcel is nice in two ways:

- All it needs is an entry point, it figures out the rest from there.
- It manages all the yarn stuff by itself, we can git ignore all those files; it's transient stuff. So, it generates a package.json, goes off and does all the yarn voodoo and does its thing.

So all we have is a kotlin src tree, a gradle file that builds it and an index file that references the javascript file that gradle produces.

We use parcel to package that up. If you want it can also do sass processing and some other stuff. It even knows how to compile .kt files.

# Running

Compiles kotlin and runs parcel against the `index.html`


```
./gradlew parcel

```

If you haven't yet, run `gradlew build` once to get yarn to install parcel.

# Production build

Installs parcel, compiles, and then calls `parcel build`

```
./gradlew build
```
