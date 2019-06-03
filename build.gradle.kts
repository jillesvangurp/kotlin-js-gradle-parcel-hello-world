import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id("kotlin2js") version "1.3.31"
    id("kotlin-dce-js") version "1.3.31"
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
            sourceMap = false
            moduleKind = "umd"
        }
    }

    runDceKotlinJs {
        setDependsOn(listOf(compileKotlin2Js))
        dceOptions {
            keep("main")
        }
    }
    
    val installParcel by creating(YarnTask::class) {
        setArgs(listOf("add","parcel"))
    }

    val parcel by creating(YarnTask::class) {
        dependsOn(runDceKotlinJs)
        setArgs(listOf("parcel","index.html"))
    }

    val parcelBuild by creating(YarnTask::class) {
        dependsOn(installParcel)
        dependsOn(runDceKotlinJs)
        setArgs(listOf("parcel","build","index.html"))
    }

    assemble {
        dependsOn(parcelBuild)
    }
}