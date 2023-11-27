plugins {
    id ("java")
    id ("org.springframework.boot") version ("2.7.4")
    id ("io.spring.dependency-management") version ("1.0.14.RELEASE")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

group = "ru.netology.DubrovinVyacheslav"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-web")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
}

tasks.test{
    useJUnitPlatform()
}