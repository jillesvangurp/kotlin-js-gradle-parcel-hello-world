import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id("kotlin2js") version "1.3.31"
    id("com.moowork.node") version "1.3.1"
}

dependencies {
    compile(kotlin("stdlib-js"))
    compile("org.jetbrains.kotlinx:kotlinx-html-js:0.6.12")
}

repositories {
    jcenter()
}

tasks {
    compileKotlin2Js {
        kotlinOptions {
            outputFile = "${sourceSets.main.get().output.resourcesDir}/output.js"
            sourceMap = true
            moduleKind = "umd"
        }
    }


    val unpackJars by registering {
        group = "build"
        description = "Unpack the Kotlin JavaScript standard library"
        val outputDir = file("$buildDir/$name")
        inputs.property("compileClasspath", configurations.compileClasspath.get())
        outputs.dir(outputDir)

        doLast {
            val jars = configurations.compileClasspath.get().filter {
                it.name.matches(Regex(".+\\.jar"))
            }
            jars.forEach {jar ->
                copy {
                    includeEmptyDirs = false
                    from(zipTree(jar))
                    into(outputDir)
                    include("**/*.js","**/*.map")
                    exclude("META-INF/**")
                }
            }
        }
    }


    val assembleWeb by registering(Copy::class) {
        group = "build"
        description = "Assemble the web application"
        includeEmptyDirs = false
        from(unpackJars)
        from(sourceSets.main.get().output) {
            exclude("**/*.kjsm")
        }
        into("$buildDir/web")
    }

    val installParcel by creating(YarnTask::class) {
        setArgs(listOf("add","parcel"))
    }

    val parcel by creating(YarnTask::class) {
        dependsOn(installParcel)
        setArgs(listOf("parcel","build","index.html"))
    }


    assemble {
        dependsOn(assembleWeb)
        dependsOn(parcel)
    }
}